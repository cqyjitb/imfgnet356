package com.hand.hap.security;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.RoleException;
import com.hand.hap.account.service.IRole;
import com.hand.hap.account.service.IRoleService;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.fnd.dto.Company;
import com.hand.hap.hr.service.IEmployeeAssignService;
import com.hand.hap.security.service.impl.CustomUserDetailsService;
import com.hand.hap.system.dto.SysPreferences;
import com.hand.hap.system.service.ISysPreferencesService;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecUsernameToken;
import org.apache.wss4j.dom.util.WSSecurityUtil;
import org.dom4j.DocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.Serializable;
import java.util.*;

/**
 * 自定义JwtAccessTokenConverter.
 *
 * @author njq.niu@hand-china.com
 *
 */
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;

    @Autowired
    IUserService userService;

    @Autowired
    ISysPreferencesService preferencesService;

    @Autowired
    IEmployeeAssignService employeeAssignService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = (DefaultOAuth2AccessToken) super.enhance(accessToken,authentication);
        if(authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails cud = (CustomUserDetails) authentication.getUserAuthentication().getPrincipal();
            Map<String,Object> additionalInformation = result.getAdditionalInformation();
            User user = userService.convertToUser(cud);
            setUserInfo(additionalInformation,user);
            setRoleInfo(additionalInformation,user);
            // Encode Token to JWT
            String encoded = super.encode(result, authentication);
            // Set JWT as value of the token
            result.setValue(encoded);
        }
        return result;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication =  super.extractAuthentication(map);

        OAuth2Request originRequest = oAuth2Authentication.getOAuth2Request();
        OAuth2Request request = new OAuth2Request(originRequest.getRequestParameters(), originRequest.getClientId(), originRequest.getAuthorities(), true, originRequest.getScope(), originRequest.getResourceIds(), null, null,
                (Map<String, Serializable>) map);
        return new OAuth2Authentication(request, oAuth2Authentication.getUserAuthentication());
    }

    private void setUserInfo(Map<String,Object> additionalInformation,User user) {
        additionalInformation.put(User.FIELD_USER_ID, user.getUserId());
        additionalInformation.put(User.FIELD_EMPLOYEE_CODE, user.getEmployeeCode());
        if (user.getEmployeeId() != null) {
            Long companyId  = employeeAssignService.getCompanyByEmployeeId(user.getEmployeeId());
            if (companyId != null) {
                additionalInformation.put(Company.FIELD_COMPANY_ID, companyId);
            }

        }
        SysPreferences pref = preferencesService.selectUserPreference(BaseConstants.PREFERENCE_LOCALE, user.getUserId());
        if(pref != null){
            additionalInformation.put(BaseConstants.PREFERENCE_LOCALE, StringUtils.parseLocaleString(pref.getPreferencesValue()));
        }
    }

    private void setRoleInfo(Map<String,Object> additionalInformation,User user) {
        List<IRole> roles  = roleService.selectActiveRolesByUser(RequestHelper.newEmptyRequest(),user);
        if (!roles.isEmpty()) {
            List<Long> roleIds = new ArrayList<Long>();
            roles.forEach(t->{
                roleIds.add(t.getRoleId());
            });
            additionalInformation.put(IRequest.FIELD_ALL_ROLE_ID,roleIds);
        }
    }

}

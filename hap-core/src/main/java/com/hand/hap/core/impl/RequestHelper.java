/*
 * #{copyright}#
 */

package com.hand.hap.core.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.User;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.IRequestListener;
import com.hand.hap.fnd.dto.Company;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 * 维护 IRequest 实例.
 *
 * @author shengyang.zhou@hand-china.com
 */
public final class RequestHelper {
    private static ThreadLocal<IRequest> localRequestContext = new ThreadLocal<>();

    private static IRequestListener requestListener = new DefaultRequestListener();

    public IRequestListener getRequestListener() {
        return requestListener;
    }

    /**
     * requestListener可以更改.
     *
     * @param requestListener requestListener
     */

    public void setRequestListener(IRequestListener requestListener) {
        RequestHelper.requestListener = requestListener;
    }

    public static IRequest newEmptyRequest() {
        return requestListener.newInstance();
    }

    /**
     * 设置 IRequest.
     * <p>
     * 不检查是否已经存在实例.(存在的话将被替换)
     *
     * @param request 新的 IRequest 实例
     */
    public static void setCurrentRequest(IRequest request) {
        localRequestContext.set(request);
    }

    /**
     * 清除当前实例.
     * <p>
     * 理论上优于 setCurrentRequest(null)
     */
    public static void clearCurrentRequest() {
        localRequestContext.remove();
    }

    /**
     * @return 当前session信息.
     */
    public static IRequest getCurrentRequest() {
        return getCurrentRequest(false);
    }

    /**
     * 取得当前线程 IRequest.
     * <p>
     *
     * @param returnEmptyForNull 是否在没有值的时候返回一个空的实例.<br>
     *                           注意,返回的空的实例不会设置为当前实例
     * @return 当前 IRequest 实例,或者一个空的实例
     */
    public static IRequest getCurrentRequest(boolean returnEmptyForNull) {
        IRequest request = localRequestContext.get();
        if (request == null && returnEmptyForNull) {
            return newEmptyRequest();
        }
        return request;
    }

    public static IRequest createServiceRequest(HttpServletRequest httpServletRequest) {
        IRequest requestContext = requestListener.newInstance();
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            if (session.getAttribute(IRequest.FIELD_USER_ID) != null) {
                requestContext.setUserId((Long) session.getAttribute(IRequest.FIELD_USER_ID));
            }
            if (session.getAttribute(Role.FIELD_ROLE_ID) != null) {
                requestContext.setRoleId((Long) session.getAttribute(Role.FIELD_ROLE_ID));
            }
            if (session.getAttribute(User.FIELD_USER_NAME) != null) {
                requestContext.setUserName((String) session.getAttribute(User.FIELD_USER_NAME));
            }
            if (session.getAttribute(Company.FIELD_COMPANY_ID) != null) {
                requestContext.setCompanyId((Long) session.getAttribute(Company.FIELD_COMPANY_ID));
            }
            Object roleIds = session.getAttribute(IRequest.FIELD_ALL_ROLE_ID);
            if (roleIds instanceof Long[]) {
                requestContext.setAllRoleId((Long[]) roleIds);
            }
            requestContext.setEmployeeCode((String) session.getAttribute(IRequest.EMP_CODE));
            //Locale locale = RequestContextUtils.getLocale(httpServletRequest);
            Locale locale = getLocale(httpServletRequest);
            if (locale != null) {
                requestContext.setLocale(locale.toString());
            }
        } else {
            //设置oauth2 token信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof OAuth2Authentication) {
                OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
                if (oauth2Authentication.getUserAuthentication() != null) {
                    requestContext.setUserName(oauth2Authentication.getPrincipal().toString());
                    Map<String, Serializable> extensions = oauth2Authentication.getOAuth2Request().getExtensions();
                    if (extensions.get(User.FIELD_USER_ID) != null) {
                        requestContext.setUserId(Long.valueOf(extensions.get(User.FIELD_USER_ID).toString()));
                    }
                    if (extensions.get(IRequest.FIELD_ALL_ROLE_ID) != null) {
                        List ids = (List) extensions.get(IRequest.FIELD_ALL_ROLE_ID);
                        Long[] idsArry = new Long[ids.size()];
                        for (int i = 0; i < ids.size(); i++) {
                            idsArry[i] = Long.valueOf(ids.get(i).toString());
                        }
                        requestContext.setAllRoleId(idsArry);
                    }
                    if (extensions.get(Company.FIELD_COMPANY_ID) != null) {
                        requestContext.setCompanyId(Long.valueOf(extensions.get(Company.FIELD_COMPANY_ID).toString()));
                    }
                    if (extensions.get(IRequest.EMP_CODE) != null) {
                        requestContext.setEmployeeCode(extensions.get(IRequest.EMP_CODE).toString());
                    }
                    if (extensions.get(BaseConstants.PREFERENCE_LOCALE) != null) {
                        requestContext.setLocale(extensions.get(BaseConstants.PREFERENCE_LOCALE).toString());
                    } else {
                        Locale locale = getLocale(httpServletRequest);
                        if (locale != null) {
                            requestContext.setLocale(locale.toString());
                        }
                    }
                }
            }
        }
        Map<String, String> mdcMap = MDC.getCopyOfContextMap();
        if (mdcMap != null) {
            mdcMap.forEach((k, v) -> requestContext.setAttribute(IRequest.MDC_PREFIX.concat(k), v));
        }
        requestListener.afterInitialize(httpServletRequest, requestContext);
        return requestContext;
    }

    /**
     * 获取请求Locale信息.
     *
     * @param request HttpServletRequest
     * @return Locale
     */
    private static Locale getLocale(HttpServletRequest request) {
        Locale locale;
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            locale = localeResolver.resolveLocale(request);
        } else {
            locale = (Locale) WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
            if (locale == null) {
                locale = request.getLocale();
            }
        }
        return locale;
    }
}

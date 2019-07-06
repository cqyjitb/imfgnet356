package yj.core.webservice.service;

import com.hand.hap.message.profile.SystemConfigListener;
import com.hand.hap.mybatis.util.StringUtil;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TFR on 2017/6/19.
 */
@Service("myUrlLogoutSuccessHandler")
public class MyUrlLogoutSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements SystemConfigListener {

    public MyUrlLogoutSuccessHandler(){
        this.setDefaultTargetUrl("/login");
    }

    @Override
    public List<String> getAcceptedProfiles() {
        return Arrays.asList(new String[]{"DEFAULT_TARGET_URL"});
    }

    @Override
    public void updateProfile(String s, String s1) {
        if(StringUtil.isNotEmpty(s1)) {
            this.setDefaultTargetUrl(s1);
        }
    }
}

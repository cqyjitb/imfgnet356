package com.hand.hap.core.components;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.hand.hap.core.BaseConstants;
import com.hand.hap.message.profile.SystemConfigListener;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置.
 *
 * @author xuhailin@hand-china.com
 * @author qixiangyu@hand-china.com
 * @author njq.niu@hand-china.com
 */
@Component
public class SysConfigManager implements SystemConfigListener, BaseConstants {

    public static final String SYS_LOGO_VERSION = "sysLogoVersion";
    public static final String SYS_FAVICON_VERSION = "sysFaviconVersion";
    public static final String KEY_SYS_LOGO_VERSION = "SYS_LOGO_VERSION";
    public static final String KEY_SYS_FAVICON_VERSION = "SYS_FAVICON_VERSION";

    private static final String KEY_SYS_TITLE = "SYS_TITLE";
    private static final String KEY_PROHIBIT_REPEAT_LOGIN = "PROHIBIT_REPEAT_LOGIN";
    private static final String KEY_USER_ROLE_MERGE = "USER_ROLE_MERGE";
    private static final String KEY_FIRST_LOGIN_RESET_PASSWORD = "FIRST_LOGIN_RESET_PASSWORD";

    private static final String KEY_OAUTH2_AUTHENTICATION_NUM = "OAUTH2_AUTHENTICATION_NUM";
    private static final String KEY_OAUTH2_AUTHENTICATION_LOCK_TIME = "OAUTH2_AUTHENTICATION_LOCK_TIME";

    private static final String DEFAULT_SYS_TITLE = "Hand Application Platform";

    /**
     * 系统标题
     */
    private String sysTitle = DEFAULT_SYS_TITLE;

    /**
     * 是否禁止用户重复登录
     */
    private boolean prohibitRepeatLogin = true;

    /**
     * 是否开启角色合并.
     */
    private boolean roleMergeFlag = true;

    /**
     * 第一次登录是否强制修改密码.
     */
    private boolean resetPwFlag = false;

    private String systemLogoVersion = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    private String systemFaviconVersion = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

    /**
     * OAUTH2 认证限制.
     */
    private int oauth2AuthenticationNum = 3;

    private long oauth2AuthenticationLockTime = 10 * 60;

    /**
     * 是否在登录的时候设置敏感Cookie的Secure属性.
     */
    public static boolean useHttps;

    @Value("${sys.login.useHttps:false}")
    public void setUseHttps(boolean useHttps) {
        SysConfigManager.useHttps = useHttps;
    }

    public String getSysTitle() {
        return sysTitle;
    }

    public boolean isProhibitRepeatLogin() {
        return prohibitRepeatLogin;
    }

    public boolean getRoleMergeFlag() {
        return roleMergeFlag;
    }

    public boolean getResetPwFlag() {
        return resetPwFlag;
    }

    public String getSystemLogoVersion() {
        return systemLogoVersion;
    }

    public String getSystemFaviconVersion() {
        return systemFaviconVersion;
    }

    public int getOauth2AuthenticationNum() {
        return oauth2AuthenticationNum;
    }

    public long getOauth2AuthenticationLockTime() {
        return oauth2AuthenticationLockTime;
    }


    @Override
    public List<String> getAcceptedProfiles() {
        return Arrays.asList(KEY_SYS_TITLE, KEY_PROHIBIT_REPEAT_LOGIN, KEY_USER_ROLE_MERGE,
                KEY_FIRST_LOGIN_RESET_PASSWORD, KEY_SYS_LOGO_VERSION, KEY_SYS_FAVICON_VERSION,
                KEY_OAUTH2_AUTHENTICATION_NUM, KEY_OAUTH2_AUTHENTICATION_LOCK_TIME);
    }

    @Override
    public void updateProfile(String profileName, String profileValue) {
        if (KEY_SYS_TITLE.equalsIgnoreCase(profileName)) {
            this.sysTitle = profileValue;
        } else if (KEY_USER_ROLE_MERGE.equalsIgnoreCase(profileName)) {
            this.roleMergeFlag = YES.equalsIgnoreCase(profileValue);
        } else if (KEY_FIRST_LOGIN_RESET_PASSWORD.equalsIgnoreCase(profileName)) {
            this.resetPwFlag = YES.equalsIgnoreCase(profileValue);
        } else if (KEY_PROHIBIT_REPEAT_LOGIN.equalsIgnoreCase(profileName)) {
            this.prohibitRepeatLogin = YES.equalsIgnoreCase(profileValue);
        } else if (KEY_SYS_LOGO_VERSION.equalsIgnoreCase(profileName)) {
            this.systemLogoVersion = profileValue;
        } else if (KEY_SYS_FAVICON_VERSION.equalsIgnoreCase(profileName)) {
            this.systemFaviconVersion = profileValue;
        } else if (KEY_OAUTH2_AUTHENTICATION_NUM.equalsIgnoreCase(profileName)) {
            this.oauth2AuthenticationNum = Integer.parseInt(profileValue);
        } else if (KEY_OAUTH2_AUTHENTICATION_LOCK_TIME.equalsIgnoreCase(profileName)) {
            this.oauth2AuthenticationLockTime = Long.parseLong(profileValue);
        }
    }

}

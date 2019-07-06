package com.hand.hap.system.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * create by:jialong.zuo@hand-china.com on 2016/10/11.
 */

@Table(name = "sys_user_login")
@ExtensionAttribute(disable = true)
public class UserLogin extends BaseDTO{
    @Id
    @GeneratedValue
    private Long loginId;

    private Long userId;

    private Date loginTime;

    private String ip;

    private String referer;

    private String userAgent;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}

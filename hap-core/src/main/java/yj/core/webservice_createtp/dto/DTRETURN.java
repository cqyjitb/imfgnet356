package yj.core.webservice_createtp.dto;

/**
 * Created by 917110140 on 2018/9/20.
 */
public class DTRETURN {
    private String MTYPE;//返回消息类型 S：成功 E：失败
    private String ZMESSAGE;//返回消息文本
    private String ZTPBAR;//返回托盘号

    public String getMTYPE() {
        return MTYPE;
    }

    public void setMTYPE(String MTYPE) {
        this.MTYPE = MTYPE;
    }

    public String getZMESSAGE() {
        return ZMESSAGE;
    }

    public void setZMESSAGE(String ZMESSAGE) {
        this.ZMESSAGE = ZMESSAGE;
    }

    public String getZTPBAR() {
        return ZTPBAR;
    }

    public void setZTPBAR(String ZTPBAR) {
        this.ZTPBAR = ZTPBAR;
    }
}

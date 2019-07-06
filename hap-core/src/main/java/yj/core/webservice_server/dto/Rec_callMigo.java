package yj.core.webservice_server.dto;

/**
 * Created by 917110140 on 2018/9/8.
 */
public class Rec_callMigo {
    private String MBLNR;//物料凭证
    private String MJAHR;//会计年度
    private String MTYPE;//消息类型
    private String MTMSG;//消息

    public String getMBLNR() {
        return MBLNR;
    }

    public void setMBLNR(String MBLNR) {
        this.MBLNR = MBLNR;
    }

    public String getMJAHR() {
        return MJAHR;
    }

    public void setMJAHR(String MJAHR) {
        this.MJAHR = MJAHR;
    }

    public String getMTYPE() {
        return MTYPE;
    }

    public void setMTYPE(String MTYPE) {
        this.MTYPE = MTYPE;
    }

    public String getMTMSG() {
        return MTMSG;
    }

    public void setMTMSG(String MTMSG) {
        this.MTMSG = MTMSG;
    }
}

package yj.core.webserver_outsrgreceipt.dto;

public class DTOUTSRGRECEIPTReturn {
    private String MSGTY;

    /**
     *消息号
     */
    private String MSGNO;

    /**
     *消息ID
     */
    private String MSGID;

    private String MSGV1;

    private String MSGV2;

    private String MSGV3;

    private String MSGV4;

    /**
     *消息描述
     */
    private String MESSAGE;

    public String getMSGTY() {
        return MSGTY;
    }

    public void setMSGTY(String MSGTY) {
        this.MSGTY = MSGTY;
    }

    public String getMSGNO() {
        return MSGNO;
    }

    public void setMSGNO(String MSGNO) {
        this.MSGNO = MSGNO;
    }

    public String getMSGID() {
        return MSGID;
    }

    public void setMSGID(String MSGID) {
        this.MSGID = MSGID;
    }

    public String getMSGV1() {
        return MSGV1;
    }

    public void setMSGV1(String MSGV1) {
        this.MSGV1 = MSGV1;
    }

    public String getMSGV2() {
        return MSGV2;
    }

    public void setMSGV2(String MSGV2) {
        this.MSGV2 = MSGV2;
    }

    public String getMSGV3() {
        return MSGV3;
    }

    public void setMSGV3(String MSGV3) {
        this.MSGV3 = MSGV3;
    }

    public String getMSGV4() {
        return MSGV4;
    }

    public void setMSGV4(String MSGV4) {
        this.MSGV4 = MSGV4;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}

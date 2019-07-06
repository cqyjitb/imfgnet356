package yj.core.webservice_server.dto;

public class ReturnQueryjjbg  {
    private String ZTPBAR;

    /**
     * 订单号
     */
    private String AUFNR;

    /**
     * 物料号
     */
    private String MATNR;

    /**
     * 物料描述
     */
    private String MAKTX;

    /**
     *消息状态  S(成功)/E(失败)
     */
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

    private Double YEILD;

    private Double WORKSCRP;

    private Double ROWSCRAP;

    public Double getYEILD() {
        return YEILD;
    }

    public void setYEILD(Double YEILD) {
        this.YEILD = YEILD;
    }

    public Double getWORKSCRP() {
        return WORKSCRP;
    }

    public void setWORKSCRP(Double WORKSCRP) {
        this.WORKSCRP = WORKSCRP;
    }

    public Double getROWSCRAP() {
        return ROWSCRAP;
    }

    public void setROWSCRAP(Double ROWSCRAP) {
        this.ROWSCRAP = ROWSCRAP;
    }

    /**
     *消息描述
     */
    private String MESSAGE;

    /**
     * 确认号
     */
    private String RSNUM;

    /**
     * 计数器
     */
    private String RSPOS;

    /**
     * 工序管理员
     */
    private String FEVOR;

    /**
     * 工序管理员描述
     */
    private String TXT;

    /**
     * 工序描述
     */
    private String LTXA1;

    private String CHARG;

    private String MBLNR;

    private String MJAHR;

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

    public String getZTPBAR() {
        return ZTPBAR;
    }

    public void setZTPBAR(String ZTPBAR) {
        this.ZTPBAR = ZTPBAR;
    }

    public String getCHARG() {
        return CHARG;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getMAKTX() {
        return MAKTX;
    }

    public void setMAKTX(String MAKTX) {
        this.MAKTX = MAKTX;
    }

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

    public String getRSNUM() {
        return RSNUM;
    }

    public void setRSNUM(String RSNUM) {
        this.RSNUM = RSNUM;
    }

    public String getRSPOS() {
        return RSPOS;
    }

    public void setRSPOS(String RSPOS) {
        this.RSPOS = RSPOS;
    }

    public String getFEVOR() {
        return FEVOR;
    }

    public void setFEVOR(String FEVOR) {
        this.FEVOR = FEVOR;
    }

    public String getTXT() {
        return TXT;
    }

    public void setTXT(String TXT) {
        this.TXT = TXT;
    }

    public String getLTXA1() {
        return LTXA1;
    }

    public void setLTXA1(String LTXA1) {
        this.LTXA1 = LTXA1;
    }
}

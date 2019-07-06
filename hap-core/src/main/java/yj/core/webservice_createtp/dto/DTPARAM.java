package yj.core.webservice_createtp.dto;

/**
 * Created by 917110140 on 2018/9/20.
 */
public class DTPARAM {
    private String WERKS;//工厂
    private String MATNR;//物料编码
    private String DATUM;//托盘日期
    private String MENGE;
    private String ZTXT2;
    private String ZTPBAR;
    private String ZBQBD;
    private String ZMNUM;

    public String getWERKS() {
        return WERKS;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getDATUM() {
        return DATUM;
    }

    public void setDATUM(String DATUM) {
        this.DATUM = DATUM;
    }

    public String getMENGE() {
        return MENGE;
    }

    public void setMENGE(String MENGE) {
        this.MENGE = MENGE;
    }

    public String getZTXT2() {
        return ZTXT2;
    }

    public void setZTXT2(String ZTXT2) {
        this.ZTXT2 = ZTXT2;
    }

    public String getZTPBAR() {
        return ZTPBAR;
    }

    public void setZTPBAR(String ZTPBAR) {
        this.ZTPBAR = ZTPBAR;
    }

    public String getZBQBD() {
        return ZBQBD;
    }

    public void setZBQBD(String ZBQBD) {
        this.ZBQBD = ZBQBD;
    }

    public String getZMNUM() {
        return ZMNUM;
    }

    public void setZMNUM(String ZMNUM) {
        this.ZMNUM = ZMNUM;
    }
}

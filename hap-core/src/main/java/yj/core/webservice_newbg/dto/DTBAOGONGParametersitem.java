package yj.core.webservice_newbg.dto;

/**
 * Created by 917110140 on 2018/8/15.
 */
public class DTBAOGONGParametersitem {
    private String SUBRSNUM;//
    private String SUBRSPOS;//
    private String MATNR;//物料编码
    private String BDMNG;//数量
    private String MEINS;//单位
    private String CHARG;//批次
    private String WERKS;//工厂
    private String LGORT;//库存地点

    public String getSUBRSNUM() {
        return SUBRSNUM;
    }

    public void setSUBRSNUM(String SUBRSNUM) {
        this.SUBRSNUM = SUBRSNUM;
    }

    public String getSUBRSPOS() {
        return SUBRSPOS;
    }

    public void setSUBRSPOS(String SUBRSPOS) {
        this.SUBRSPOS = SUBRSPOS;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getBDMNG() {
        return BDMNG;
    }

    public void setBDMNG(String BDMNG) {
        this.BDMNG = BDMNG;
    }

    public String getMEINS() {
        return MEINS;
    }

    public void setMEINS(String MEINS) {
        this.MEINS = MEINS;
    }

    public String getCHARG() {
        return CHARG;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getWERKS() {
        return WERKS;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public String getLGORT() {
        return LGORT;
    }

    public void setLGORT(String LGORT) {
        this.LGORT = LGORT;
    }
}

package yj.core.webservice_server.dto;

public class Rec_Outsrgrfe {

    private String werks; //工厂

    private String aufnr; //订单号

    private String matnr; //物料号

    private String vornr; //外协工序号

    private String lifnr; //供应商编码

    private String ktsch; //标准文本码

    private String sortl; //供应商简称

    private String vsnda; //生产版本

    private String ebeln; //采购订单

    private String ebelp; //采购订单行

    private Double menge; //采购订单行数目

    private String loekz;

    private String tcode; //sap 实务代码

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getVornr() {
        return vornr;
    }

    public void setVornr(String vornr) {
        this.vornr = vornr;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getKtsch() {
        return ktsch;
    }

    public void setKtsch(String ktsch) {
        this.ktsch = ktsch;
    }

    public String getVsnda() {
        return vsnda;
    }

    public void setVsnda(String vsnda) {
        this.vsnda = vsnda;
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public Double getMenge() {
        return menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public String getLoekz() {
        return loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getSortl() {
        return sortl;
    }

    public void setSortl(String sortl) {
        this.sortl = sortl;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }
}

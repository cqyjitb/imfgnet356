package yj.core.webservice_outsrgissue.dto;

public class DTOUTSRGISSUEhead {
    private String issuenm; //外协发料单号

    private String werks; //工厂

    private String txz01; //工序说明

    private String lifnr; //供应商编号

    private String matnr; //物料编号

    private String status; //单据状态

    private String prtflag; //打印标识

    private String zipuser;//单据打印账号

    private String zipdat; //单据打印日期

    private String ziptim; //单据打印时间

    public String getIssuenm() {
        return issuenm;
    }

    public void setIssuenm(String issuenm) {
        this.issuenm = issuenm;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getTxz01() {
        return txz01;
    }

    public void setTxz01(String txz01) {
        this.txz01 = txz01;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrtflag() {
        return prtflag;
    }

    public void setPrtflag(String prtflag) {
        this.prtflag = prtflag;
    }

    public String getZipuser() {
        return zipuser;
    }

    public void setZipuser(String zipuser) {
        this.zipuser = zipuser;
    }

    public String getZipdat() {
        return zipdat;
    }

    public void setZipdat(String zipdat) {
        this.zipdat = zipdat;
    }

    public String getZiptim() {
        return ziptim;
    }

    public void setZiptim(String ziptim) {
        this.ziptim = ziptim;
    }
}

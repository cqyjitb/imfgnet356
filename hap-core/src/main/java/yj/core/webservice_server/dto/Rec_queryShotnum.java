package yj.core.webservice_server.dto;

public class Rec_queryShotnum {
    private String werks;
    private String fevor;
    private String prdDateBefore;//结束日期
    private String prdDateAfter;//开始日期

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getFevor() {
        return fevor;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getPrdDateBefore() {
        return prdDateBefore;
    }

    public void setPrdDateBefore(String prdDateBefore) {
        this.prdDateBefore = prdDateBefore;
    }

    public String getPrdDateAfter() {
        return prdDateAfter;
    }

    public void setPrdDateAfter(String prdDateAfter) {
        this.prdDateAfter = prdDateAfter;
    }
}

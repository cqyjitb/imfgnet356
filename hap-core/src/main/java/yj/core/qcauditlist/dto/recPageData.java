package yj.core.qcauditlist.dto;

public class recPageData {

    private String werks;
    private String recordid;
    private String item;
    private Double confirmnum;//接收判定报废数量

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getConfirmnum() {
        return confirmnum;
    }

    public void setConfirmnum(Double confirmnum) {
        this.confirmnum = confirmnum;
    }
}

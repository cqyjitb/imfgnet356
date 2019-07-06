package yj.kanb.vbline.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "vb_line")
public class Vbline extends BaseDTO {
    @Id
    @GeneratedValue
    private String bukrs;
    private String werks;
    private Integer lineId;
    private String groupId;
    private String product;
    private String workshopId;
    private String lineTxt;
    private String lineCode;
    private String zpgdbar;
    private String zxhbar;
    private String shift;
    private String erdat;


    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getLineTxt() {
        return lineTxt;
    }

    public void setLineTxt(String lineTxt) {
        this.lineTxt = lineTxt;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getZpgdbar() {
        return zpgdbar;
    }

    public void setZpgdbar(String zpgdbar) {
        this.zpgdbar = zpgdbar;
    }

    public String getZxhbar() {
        return zxhbar;
    }

    public void setZxhbar(String zxhbar) {
        this.zxhbar = zxhbar;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getErdat() {
        return erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }
}

package yj.kanb.vblinegroupheader.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ExtensionAttribute(disable=true)
@Table(name = "vb_line_group_h")
public class Vblinegroupheader  extends BaseDTO {
    @Id
    @GeneratedValue
    private String groupId;//产线组ID
    private String bukrs;//公司
    private String werks;//工厂
    private String workshopId;//车间ID
    private String product;//产品物料编码
    private String kunnr;//客户编码

    private String groupName;//产线组名称
    private String groupNameEn;
    private String groupType;//资源类别：LINEDATA
    private String templeteUrl;//模板URL
    @Transient
    private String vbgroupId;//显示组ID

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getVbgroupId() {
        return vbgroupId;
    }

    public void setVbgroupId(String vbgroupId) {
        this.vbgroupId = vbgroupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getTempleteUrl() {
        return templeteUrl;
    }

    public void setTempleteUrl(String templeteUrl) {
        this.templeteUrl = templeteUrl;
    }

    public String getGroupNameEn() {
        return groupNameEn;
    }

    public void setGroupNameEn(String groupNameEn) {
        this.groupNameEn = groupNameEn;
    }
}

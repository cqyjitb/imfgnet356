package yj.core.wipbarcodesetting.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_barcode_setting")
public class BarcodeSetting {
    @NotEmpty
    private String werks;//工厂
    @Id
    @GeneratedValue
    private Integer headerId;//表头ID
    @NotEmpty
    private Long lineId;//生产线ID
    @NotEmpty
    private Integer matnr;//物料编码
    @NotEmpty
    private Integer status;//状态  默认为0,0为有效，1为失效
    @NotEmpty
    private Integer barcodeCnt;//条码位数

    private Integer pkgQty;//箱容

    private Integer tuoQty;//托盘数量（托容）

    private String adminPassword;//管理员放行密码

    private Integer isCheck;//检测验证

    private Integer isStep;//跳序验证

    private Integer isPrint;//是否打印箱码

    private Integer isRelCustomer;//绑定客户条码

    private Double onlinePer;//机加上线容差值

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;
    @NotNull
    private Date creationDate; //创建时间
    @NotEmpty
    private String createdBy; //创建人

    private Date lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人

    @Transient
    private String deptId;
    private Integer matnr2;
    @Transient
    private String maktx;//物料描述

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public Integer getMatnr2() {
        return matnr2;
    }

    public void setMatnr2(Integer matnr2) {
        this.matnr2 = matnr2;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Integer getMatnr() {
        return matnr;
    }

    public void setMatnr(Integer matnr) {
        this.matnr = matnr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBarcodeCnt() {
        return barcodeCnt;
    }

    public void setBarcodeCnt(Integer barcodeCnt) {
        this.barcodeCnt = barcodeCnt;
    }

    public Integer getPkgQty() {
        return pkgQty;
    }

    public void setPkgQty(Integer pkgQty) {
        this.pkgQty = pkgQty;
    }

    public Integer getTuoQty() {
        return tuoQty;
    }

    public void setTuoQty(Integer tuoQty) {
        this.tuoQty = tuoQty;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getIsStep() {
        return isStep;
    }

    public void setIsStep(Integer isStep) {
        this.isStep = isStep;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public Integer getIsRelCustomer() {
        return isRelCustomer;
    }

    public void setIsRelCustomer(Integer isRelCustomer) {
        this.isRelCustomer = isRelCustomer;
    }

    public Double getOnlinePer() {
        return onlinePer;
    }

    public void setOnlinePer(Double onlinePer) {
        this.onlinePer = onlinePer;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}

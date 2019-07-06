package yj.core.wipbarcodespecialcode.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_barcode_specialcode")
public class BarcodeSpecialcode {
    @NotEmpty
    private String werks;//工厂
    @Id
    @GeneratedValue
    private Integer specRowId;//行表ID
    @NotEmpty
    private Integer headerId;//表头ID

    private String remark;//特殊字符描述
    @NotEmpty
    private Integer startPos;//起始位置
    @NotEmpty
    private Integer endPos;//结束位置
    @NotEmpty
    private Integer codeType;//数据类型  0：特殊字符 1：检测值

    private String speciallCode;//特殊字符

    private Double checkUpData;//检测值上限

    private Double checkDownData;//检测值下限

    private Integer status;//状态  默认为0,0为有效，1为失效

    private Integer pkgQty;//箱容

    private Integer tuoQty;//托盘数量（托容）

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

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Integer getSpecRowId() {
        return specRowId;
    }

    public void setSpecRowId(Integer specRowId) {
        this.specRowId = specRowId;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStartPos() {
        return startPos;
    }

    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getSpeciallCode() {
        return speciallCode;
    }

    public void setSpeciallCode(String speciallCode) {
        this.speciallCode = speciallCode;
    }

    public Double getCheckUpData() {
        return checkUpData;
    }

    public void setCheckUpData(Double checkUpData) {
        this.checkUpData = checkUpData;
    }

    public Double getCheckDownData() {
        return checkDownData;
    }

    public void setCheckDownData(Double checkDownData) {
        this.checkDownData = checkDownData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

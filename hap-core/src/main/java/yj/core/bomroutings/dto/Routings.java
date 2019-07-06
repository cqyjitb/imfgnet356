package yj.core.bomroutings.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ExtensionAttribute(disable=true)
@Table(name = "bom_routings")
public class Routings{
    @NotEmpty
    private String werks;//工厂
    @Id
    @GeneratedValue
    private Integer routingId;//工艺路线ID
    @NotEmpty
    private String routingCode;//工艺路线编码
    @NotEmpty
    private String descriptions;//工艺路线描述
    @NotEmpty
    private String matnr;//物料编码

    private String enableFlag;//是否生效
    @NotEmpty
    private String startDate; //生效日期

    private String endDate; //失效日期
    @NotEmpty
    private String creationDate2; //创建时间
    @NotEmpty
    private String createdBy2; //创建人

    private String lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人

    @Transient
    private String maktx; //物料描述
    private String matnr2;
    private String startDateAfter;
    private String startDateBefore;
    private String endDateAfter;
    private String endDateBefore;
    private String startDate2;
    private String endDate2;

    public String getCreatedBy2() {
        return createdBy2;
    }

    public void setCreatedBy2(String createdBy2) {
        this.createdBy2 = createdBy2;
    }

    public String getCreationDate2() {
        return creationDate2;
    }

    public void setCreationDate2(String creationDate2) {
        this.creationDate2 = creationDate2;
    }

    public String getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(String startDate2) {
        this.startDate2 = startDate2;
    }

    public String getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(String endDate2) {
        this.endDate2 = endDate2;
    }

    public String getMatnr2() {
        return matnr2;
    }

    public void setMatnr2(String matnr2) {
        this.matnr2 = matnr2;
    }

    public String getStartDateAfter() {
        return startDateAfter;
    }

    public void setStartDateAfter(String startDateAfter) {
        this.startDateAfter = startDateAfter;
    }

    public String getStartDateBefore() {
        return startDateBefore;
    }

    public void setStartDateBefore(String startDateBefore) {
        this.startDateBefore = startDateBefore;
    }

    public String getEndDateAfter() {
        return endDateAfter;
    }

    public void setEndDateAfter(String endDateAfter) {
        this.endDateAfter = endDateAfter;
    }

    public String getEndDateBefore() {
        return endDateBefore;
    }

    public void setEndDateBefore(String endDateBefore) {
        this.endDateBefore = endDateBefore;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Integer getRoutingId() {
        return routingId;
    }

    public void setRoutingId(Integer routingId) {
        this.routingId = routingId;
    }

    public String getRoutingCode() {
        return routingCode;
    }

    public void setRoutingCode(String routingCode) {
        this.routingCode = routingCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}

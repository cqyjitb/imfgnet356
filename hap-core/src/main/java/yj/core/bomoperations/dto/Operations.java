package yj.core.bomoperations.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@ExtensionAttribute(disable=true)
@Table(name = "bom_operations")
public class Operations {
    @NotEmpty
    private String werks;//工厂
    @NotEmpty
    private Long lineId;//产线ID
    @NotEmpty
    private Integer pointId;//工序ID
    @NotEmpty
    private Integer routingId;//工艺路线ID
    @Id
    @GeneratedValue
    private Integer operationId;//工艺节点ID
    @NotEmpty
    private Integer operationNum;//工艺节点序号
    @NotEmpty
    private String operationCode;//工艺节点编码
    @NotEmpty
    private String descriptions;//工艺节点描述

    private String techDesc;//工艺节点技术要求

    private String stdCode;//工序标准值码  来源于SAP标准工艺路线

    private String enableFlag;//是否生效
    @NotEmpty
    private String startDate; //生效日期

    private String endDate; //失效日期
    @NotNull
    private String creationDate; //创建时间
    @NotEmpty
    private String createdBy; //创建人

    private String lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人
    /*添加字段 918100064*/
    private String description;//产线描述

    private String pointCode;//工序编码

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getRoutingId() {
        return routingId;
    }

    public void setRoutingId(Integer routingId) {
        this.routingId = routingId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getOperationNum() {
        return operationNum;
    }

    public void setOperationNum(Integer operationNum) {
        this.operationNum = operationNum;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getTechDesc() {
        return techDesc;
    }

    public void setTechDesc(String techDesc) {
        this.techDesc = techDesc;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

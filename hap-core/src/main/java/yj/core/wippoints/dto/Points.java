package yj.core.wippoints.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_points")
public class Points{
    @NotEmpty
    private String werks;//工厂
    @NotEmpty
    private Long lineId;//生产线ID
    @Id
    @GeneratedValue
    private Integer pointId;//工序ID

    private Integer pointNum;//工序序号
    @NotEmpty
    private String pointCode;//工序编码
    @NotEmpty
    private String descriptions;//工序描述

    private String prntbarcodeFlag;//打印二维码工序标识

    private String assemblyFlag;//装配工序标识

    private String gp12Flag;//GP12工序标识

    private String firstFlag;//首工序标识

    private String endFlag;//结束工序标识

    private String keyFlag;//关键工序标识

    private String checkFlag;//检验工序标识

    private String monitorFlag;//监控工序标识

    private String planFlag;//计划工序标识

    private String enableFlag;//是否生效
    @NotEmpty
    private Date startDate; //生效日期
    @NotEmpty
    private Date endDate; //失效日期

    private Integer taktTime; //工序节拍

    private String custEngNm;//客户工程图号版本

    private Integer parPointId;//父工序

    private String arbpl;//工作中心
    @NotEmpty
    private Date creationDate; //创建时间
    @NotEmpty
    private String createdBy; //创建人

    private String lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
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

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getPrntbarcodeFlag() {
        return prntbarcodeFlag;
    }

    public void setPrntbarcodeFlag(String prntbarcodeFlag) {
        this.prntbarcodeFlag = prntbarcodeFlag;
    }

    public String getAssemblyFlag() {
        return assemblyFlag;
    }

    public void setAssemblyFlag(String assemblyFlag) {
        this.assemblyFlag = assemblyFlag;
    }

    public String getGp12Flag() {
        return gp12Flag;
    }

    public void setGp12Flag(String gp12Flag) {
        this.gp12Flag = gp12Flag;
    }

    public String getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(String firstFlag) {
        this.firstFlag = firstFlag;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public String getKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(String keyFlag) {
        this.keyFlag = keyFlag;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getMonitorFlag() {
        return monitorFlag;
    }

    public void setMonitorFlag(String monitorFlag) {
        this.monitorFlag = monitorFlag;
    }

    public String getPlanFlag() {
        return planFlag;
    }

    public void setPlanFlag(String planFlag) {
        this.planFlag = planFlag;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTaktTime() {
        return taktTime;
    }

    public void setTaktTime(Integer taktTime) {
        this.taktTime = taktTime;
    }

    public String getCustEngNm() {
        return custEngNm;
    }

    public void setCustEngNm(String custEngNm) {
        this.custEngNm = custEngNm;
    }

    public Integer getParPointId() {
        return parPointId;
    }

    public void setParPointId(Integer parPointId) {
        this.parPointId = parPointId;
    }

    public String getArbpl() {
        return arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
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

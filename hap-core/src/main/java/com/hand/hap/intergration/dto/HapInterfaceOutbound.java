package com.hand.hap.intergration.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.mybatis.annotation.Condition;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
@Table(name = "sys_if_invoke_outbound")
@ExtensionAttribute(disable=true)
public class HapInterfaceOutbound extends BaseDTO {

    @Id
    @GeneratedValue
    private Long outboundId;

    @NotEmpty
    private String interfaceName;

    @NotEmpty
    private String interfaceUrl;

    private Date requestTime;

    private String requestParameter;

    private String responseContent;

    private String responseCode;

    @Column(name = "STACKTRACE")
    private String stackTrace;

    private Long responseTime;

    private String requestStatus;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer pagesize = 10;

    @Transient
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    private Date startDate;

    @Transient
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    private Date endDate;



    public void setOutboundId(Long outboundId) {
        this.outboundId = outboundId;
    }

    public Long getOutboundId() {
        return outboundId;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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
}

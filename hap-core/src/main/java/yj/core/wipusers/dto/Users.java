package yj.core.wipusers.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_users")
public class Users {
    @Id
    @GeneratedValue
    private Integer seqId;//表ID
    @NotEmpty
    private String userId;//用户ID
    @NotEmpty
    private Long lineId;//生产线ID
    @NotEmpty
    private Integer dotId;//采集点ID

    private String enableFlag;//是否生效
    @NotEmpty
    private String startDate; //生效日期

    private String endDate; //失效日期
    @NotNull
    private Date creationDate; //创建时间
    @NotEmpty
    private String createdBy; //创建人

    private Date lastUpdatedDate; //更新时间

    private String lastUpdatedBy; //更新人

    @Transient
    private String deptId;
    private String startDateAfter;
    private String startDateBefore;
    private String endDateAfter;
    private String endDateBefore;
    @Transient
    private String dotCode;
    @Transient
    private String descriptions;
    @Transient
    private String userName;//用户名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDotCode() {
        return dotCode;
    }

    public void setDotCode(String dotCode) {
        this.dotCode = dotCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Integer getDotId() {
        return dotId;
    }

    public void setDotId(Integer dotId) {
        this.dotId = dotId;
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

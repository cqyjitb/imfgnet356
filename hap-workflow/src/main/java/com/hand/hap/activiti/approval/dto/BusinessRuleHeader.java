package com.hand.hap.activiti.approval.dto;

/**
 * @author xiangyu.qi@hand-china.com
 */

import com.hand.hap.core.annotation.Children;
import com.hand.hap.mybatis.annotation.Condition;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@ExtensionAttribute(disable = true)
@Table(name = "wfl_business_rule_header")
public class BusinessRuleHeader extends BaseDTO {
    @Id
    @GeneratedValue
    private Long businessRuleId;

    @NotEmpty
    @Length(max = 50)
    //@Condition(operator = LIKE)
    private String code;

    @NotEmpty
    @Length(max = 255)
    @Condition(operator = LIKE)
    private String description;

    private String wflType;

    private String enableFlag;

    @Condition(operator = "&gt;=")
    private Date startActiveDate;

    @Condition(operator = "&lt;=")
    private Date endActiveDate;

    @Children
    @Transient
    private List<BusinessRuleLine> lines;

    public void setBusinessRuleId(Long businessRuleId) {
        this.businessRuleId = businessRuleId;
    }

    public Long getBusinessRuleId() {
        return businessRuleId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setWflType(String wflType) {
        this.wflType = wflType;
    }

    public String getWflType() {
        return wflType;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setStartActiveDate(Date startActiveDate) {
        this.startActiveDate = startActiveDate;
    }

    public Date getStartActiveDate() {
        return startActiveDate;
    }

    public void setEndActiveDate(Date endActiveDate) {
        this.endActiveDate = endActiveDate;
    }

    public Date getEndActiveDate() {
        return endActiveDate;
    }

    public List<BusinessRuleLine> getLines() {
        return lines;
    }

    public void setLines(List<BusinessRuleLine> lines) {
        this.lines = lines;
    }
}

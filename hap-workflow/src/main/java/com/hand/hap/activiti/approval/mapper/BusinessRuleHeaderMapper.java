package com.hand.hap.activiti.approval.mapper;

import com.hand.hap.activiti.approval.dto.BusinessRuleHeader;
import com.hand.hap.mybatis.common.Mapper;

public interface BusinessRuleHeaderMapper extends Mapper<BusinessRuleHeader> {

    BusinessRuleHeader selectByCode(String code);
}
package com.hand.hap.activiti.approval.service;

import com.hand.hap.activiti.approval.dto.BusinessRuleHeader;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

public interface IBusinessRuleHeaderService
        extends IBaseService<BusinessRuleHeader>, ProxySelf<IBusinessRuleHeaderService> {

    BusinessRuleHeader createRule(BusinessRuleHeader header);

    BusinessRuleHeader updateRule(BusinessRuleHeader header);

    boolean batchDelete(IRequest request, List<BusinessRuleHeader> headers);

    List<BusinessRuleHeader> selectAll(IRequest request, BusinessRuleHeader header);
}
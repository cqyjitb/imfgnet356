package com.hand.hap.activiti.approval.service.impl;

import com.hand.hap.activiti.approval.dto.BusinessRuleLine;
import com.hand.hap.activiti.approval.service.IBusinessRuleLineService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessRuleLineServiceImpl extends BaseServiceImpl<BusinessRuleLine> implements IBusinessRuleLineService {

}
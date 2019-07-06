package com.hand.hap.activiti.approval.service;

import com.hand.hap.activiti.approval.dto.ApproveCandidateRule;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

public interface IApproveCandidateRuleService
        extends IBaseService<ApproveCandidateRule>, ProxySelf<IApproveCandidateRuleService> {

    List<ApproveCandidateRule> selectAll(IRequest request, ApproveCandidateRule rule);
}
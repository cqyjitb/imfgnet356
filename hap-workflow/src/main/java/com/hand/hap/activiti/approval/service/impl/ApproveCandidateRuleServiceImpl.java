package com.hand.hap.activiti.approval.service.impl;

import com.hand.hap.activiti.approval.dto.ApproveCandidateRule;
import com.hand.hap.activiti.approval.mapper.ApproveCandidateRuleMapper;
import com.hand.hap.activiti.approval.service.IApproveCandidateRuleService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApproveCandidateRuleServiceImpl extends BaseServiceImpl<ApproveCandidateRule> implements IApproveCandidateRuleService {

    @Autowired
    private ApproveCandidateRuleMapper mapper;

    @Override
    public List<ApproveCandidateRule> selectAll(IRequest request, ApproveCandidateRule rule) {
        return mapper.select(rule);
    }
}
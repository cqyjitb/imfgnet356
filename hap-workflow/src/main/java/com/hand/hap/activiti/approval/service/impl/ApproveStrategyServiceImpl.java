package com.hand.hap.activiti.approval.service.impl;

import com.hand.hap.activiti.approval.dto.ApproveStrategy;
import com.hand.hap.activiti.approval.mapper.ApproveStrategyMapper;
import com.hand.hap.activiti.approval.service.IApproveStrategyService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApproveStrategyServiceImpl extends BaseServiceImpl<ApproveStrategy> implements IApproveStrategyService {

    @Autowired
    private ApproveStrategyMapper mapper;

    @Override
    public List<ApproveStrategy> selectAll(IRequest request, ApproveStrategy strategy) {
        return mapper.select(strategy);
    }
}
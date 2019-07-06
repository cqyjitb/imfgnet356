package com.hand.hap.activiti.approval.service;

import com.hand.hap.activiti.approval.dto.ApproveStrategy;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

public interface IApproveStrategyService
        extends IBaseService<ApproveStrategy>, ProxySelf<IApproveStrategyService> {

    List<ApproveStrategy> selectAll(IRequest request, ApproveStrategy strategy);
}
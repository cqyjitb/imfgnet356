package com.hand.hap.intergration.components;

import com.hand.hap.core.IRequest;
import com.hand.hap.intergration.InvokeLogStrategy;
import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.intergration.service.IHapInterfaceInboundService;
import com.hand.hap.intergration.service.IHapInterfaceOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiangyu.qi@hand-china.com on 2017/9/23.
 */
@Component
public class DefaultInvokeLogStrategy implements InvokeLogStrategy {

    @Autowired
    private IHapInterfaceInboundService inboundService;

    @Autowired
    private IHapInterfaceOutboundService outboundService;

    @Override
    public void logInbound(HapInterfaceInbound inbound) {
        //HapInvokeLogUtils.processExceptionInfo(inbound,throwable);
        inboundService.inboundInvoke(inbound);
    }

    @Override
    public void logOutbound(HapInterfaceOutbound outbound) {
        //HapInvokeLogUtils.processExceptionInfo(outbound,throwable);
        outboundService.outboundInvoke(outbound);
    }

    @Override
    public List<HapInterfaceInbound> queryInbound(IRequest request, HapInterfaceInbound condition, int pageNum, int pageSize) {
        return inboundService.select(request, condition, pageNum, pageSize);
    }

    @Override
    public List<HapInterfaceOutbound> queryOutbound(IRequest request, HapInterfaceOutbound condition, int pageNum, int pageSize) {
        return outboundService.select(request,condition,pageNum,pageSize);
    }
}

package com.hand.hap.intergration.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.system.service.IBaseService;

public interface IHapInterfaceOutboundService
        extends IBaseService<HapInterfaceOutbound>, ProxySelf<IHapInterfaceOutboundService> {

    int outboundInvoke(HapInterfaceOutbound outbound);
}
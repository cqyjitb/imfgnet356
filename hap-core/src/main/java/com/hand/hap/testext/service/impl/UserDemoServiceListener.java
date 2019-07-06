/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.testext.service.impl;

import org.springframework.stereotype.Component;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.ServiceListener;
import com.hand.hap.extensible.base.ServiceListenerAdaptor;
import com.hand.hap.extensible.base.ServiceListenerChain;
import com.hand.hap.testext.dto.UserDemo;
import com.hand.hap.testext.dto.UserDemoExt;
import com.hand.hap.testext.dto.UserDemoExt2;

/**
 * @author shengyang.zhou@hand-china.com
 */
@Component
@ServiceListener(target = UserDemoServiceImpl.class)
public class UserDemoServiceListener extends ServiceListenerAdaptor<UserDemo> {

    @Override
    public void beforeUpdate(IRequest request, UserDemo record, ServiceListenerChain<UserDemo> chain) {
        System.out.println(getClass().getSimpleName() + "::beforeUpdate");
        System.out.println("extension attribute: userPhone:" + ((UserDemoExt) record).getUserPhone());
        System.out.println("extension attribute: endActiveTime:" + ((UserDemoExt2) record).getEndActiveTime());
        chain.beforeUpdate(request, record);
    }

    @Override
    public void afterUpdate(IRequest request, UserDemo record, ServiceListenerChain<UserDemo> chain) {
        System.out.println(getClass().getSimpleName() + "::afterUpdate");
        chain.afterUpdate(request, record);
    }
}

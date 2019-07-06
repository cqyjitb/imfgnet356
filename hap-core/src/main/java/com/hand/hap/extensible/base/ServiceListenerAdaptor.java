/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.extensible.base;

import com.hand.hap.core.IRequest;

/**
 * @author shengyang.zhou@hand-china.com
 */
public class ServiceListenerAdaptor<T> implements IServiceListener<T> {
    @Override
    public void beforeInsert(IRequest request, T record, ServiceListenerChain<T> chain) {

    }

    @Override
    public void afterInsert(IRequest request, T record, ServiceListenerChain<T> chain) {

    }

    @Override
    public void beforeUpdate(IRequest request, T record, ServiceListenerChain<T> chain) {

    }

    @Override
    public void afterUpdate(IRequest request, T record, ServiceListenerChain<T> chain) {

    }

    @Override
    public void beforeDelete(IRequest request, T record, ServiceListenerChain<T> chain) {

    }

    @Override
    public void afterDelete(IRequest request, T record, ServiceListenerChain<T> chain) {

    }
}

/*
 * #{copyright}#
 */

package com.hand.hap.core.impl;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.IRequestListener;

/**
 * @author shengyang.zhou@hand-china.com
 */
public class DefaultRequestListener implements IRequestListener {
    
    @Override
    public IRequest newInstance() {
        return new ServiceRequest();
    }

    @Override
    public void afterInitialize(HttpServletRequest httpServletRequest, IRequest request) {

    }
}

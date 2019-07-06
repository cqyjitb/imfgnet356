package com.hand.hap.api.gateway.service.impl;

import com.hand.hap.api.gateway.service.IApiInvokeService;
import com.hand.hap.core.AppContextInitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取IApiInvokeService bean.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/9/28.
 **/

@Service
public class ApiInvokeServiceManager implements AppContextInitListener {

    private List<IApiInvokeService> invokeServices;

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        invokeServices = new ArrayList<>(applicationContext.getBeansOfType(IApiInvokeService.class).values());
    }

    public List<IApiInvokeService> getInvokeServices() {
        return invokeServices;
    }

}

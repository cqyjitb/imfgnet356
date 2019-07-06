package com.hand.hap.core.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.hand.hap.core.annotation.FreeMarkerBean;

/**
 * @author shengyang.zhou@hand-china.com
 */
public class FreeMarkerBeanProvider {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, Object> beanMap;

    public Map<String, Object> getAvailableBean() {
        if (beanMap == null) {
            beanMap = applicationContext.getBeansWithAnnotation(FreeMarkerBean.class);
        }
        return beanMap;
    }

}

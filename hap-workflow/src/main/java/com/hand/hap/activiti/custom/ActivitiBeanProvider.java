package com.hand.hap.activiti.custom;

import com.hand.hap.core.AppContextInitListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动扫描实现了 接口 IActivitiBean 的 bean
 *
 * @author shengyang.zhou@hand-china.com
 */
public class ActivitiBeanProvider extends HashMap<Object, Object> implements AppContextInitListener {

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        Map<String, IActivitiBean> beans = applicationContext.getBeansOfType(IActivitiBean.class);
        beans.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(v.getBeanName())) {
                ActivitiBeanProvider.this.put(v.getBeanName(), v);
            } else {
                ActivitiBeanProvider.this.put(k, v);
            }
        });
    }
}

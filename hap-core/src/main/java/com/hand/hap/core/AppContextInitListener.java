/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.core;

import org.springframework.context.ApplicationContext;

/**
 * @author shengyang.zhou@hand-china.com
 */
public interface AppContextInitListener{
    void contextInitialized(ApplicationContext applicationContext);
}

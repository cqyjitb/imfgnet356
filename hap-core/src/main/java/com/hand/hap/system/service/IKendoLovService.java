/*
 * #{copyright}#
 */
package com.hand.hap.system.service;

import java.util.Locale;

import com.hand.hap.core.ProxySelf;

/**
 * 基于KendoUI的通用lov的service.
 * 
 * @author njq.niu@hand-china.com
 */
public interface IKendoLovService extends ProxySelf<IKendoLovService> {

    /**
     * 根据lovCode获取lov的配置.
     * 
     * @param contextPath
     *            contextPath
     * @param locale
     *            locale
     * @param lovCode
     *            lovCode
     * @return lov配置
     */
    String getLov(String contextPath, Locale locale, String lovCode);
    
}

/*
 * #{copyright}#
 */

package com.hand.hap.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 修订,历史记录启用标记.
 *
 * @author shengyang.zhou@hand-china.com
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditEnabled {

    /**
     * 审计表名称.
     * <p>
     * 默认为空, 按照全局的规则计算
     * 
     * @see com.hand.hap.audit.service.IAuditTableNameProvider
     * @return
     */
    String auditTable() default "";
}
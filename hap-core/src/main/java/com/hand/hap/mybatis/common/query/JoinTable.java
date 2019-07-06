/**
 * Copyright 2016 www.extdo.com 
 */
package com.hand.hap.mybatis.common.query;


import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.criteria.JoinType;
import java.lang.annotation.*;

/**
 * @author njq.niu@hand-china.com
 */
@Repeatable(JoinTables.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinTable {
    
    String name();
    
    Class<?> target();

    
    JoinType type() default JoinType.INNER;
    
    JoinOn[] on() default {};

    boolean joinMultiLanguageTable() default false;
}

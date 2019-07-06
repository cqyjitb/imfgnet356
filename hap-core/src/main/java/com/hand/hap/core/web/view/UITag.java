package com.hand.hap.core.web.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hand.hap.core.web.view.ui.ViewTag;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UITag {
    
    String name() default "";

    String nameSpace() default ViewTag.DEFAULT_NAME_SPACE;
}

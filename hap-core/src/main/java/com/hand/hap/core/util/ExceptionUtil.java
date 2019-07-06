package com.hand.hap.core.util;

import com.google.common.base.Throwables;


/**
 * @author peng.jiang@hand-china.com
 * @date 2018/3/6.
 */
public class ExceptionUtil {

    /**
     *  截取root cause
     *  @return
     */
    public static String getRootCauseStackTrace(Throwable throwable){
        // 获取异常堆栈
        Throwable t = Throwables.getRootCause(throwable);
        String stackTrace = Throwables.getStackTraceAsString(t);
        return stackTrace;
    }
}

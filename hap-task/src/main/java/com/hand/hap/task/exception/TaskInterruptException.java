package com.hand.hap.task.exception;

import com.hand.hap.core.exception.BaseException;

/**
 * @author peng.jiang@hand-china.com
 * @date 2017/12/1
 **/
public class TaskInterruptException extends BaseException {

    public static final String CODE_INTERRUPT_ERROR = "TASK_INTERRUPT";

    public static final String MSG_INTERRUPT = "The task is interrupted.";

    public TaskInterruptException(String code, String descriptionKey, Object[] parameters) {
        super(code, descriptionKey, parameters);
    }

    public TaskInterruptException(String code, String descriptionKey) {
        super(code, descriptionKey, null);
    }
}

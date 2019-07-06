package com.hand.hap.task;

import com.hand.hap.task.dto.TaskExecution;

/**
 * @author peng.jiang@hand-china.com
 * @date 2018/1/15
 **/
public interface ExecuteLogConvertStrategy {


    /**
     * 日志输出
     * @param taskExecution
     * @return
     */
    String convertLog(TaskExecution taskExecution);
}

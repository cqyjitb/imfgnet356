package com.hand.hap.task.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.task.dto.TaskExecutionDetail;

/**
 * 任务执行记录-接口.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/6.
 **/

public interface ITaskExecutionDetailService extends IBaseService<TaskExecutionDetail>,
        ProxySelf<ITaskExecutionDetailService> {

    /**
     * 写入执行异常信息
     *
     * @param taskExecutionDetail 任务/任务组执行 执行记录详情
     */
    void updateStacktrace(@StdWho TaskExecutionDetail taskExecutionDetail);

    /**
     * 写入执行日志
     *
     * @param taskExecutionDetail 任务/任务组执行 执行记录详情
     */
    void updateExecuteLog(@StdWho TaskExecutionDetail taskExecutionDetail);

    /**
     * 获取执行日志
     *
     * @param taskExecutionDetail 任务/任务组执行 执行记录详情
     * @return 任务/任务组执行 执行记录详情
     */
    TaskExecutionDetail getExecutionLog(TaskExecutionDetail taskExecutionDetail);
}
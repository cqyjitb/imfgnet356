package com.hand.hap.message.components;

import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.TopicMonitor;
import com.hand.hap.task.dto.TaskExecutionDetail;
import com.hand.hap.task.service.ITaskExecutionDetailService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author peng.jiang@hand-china.com on 2017/9/23.
 */
@Component
@TopicMonitor(channel = {TaskExecuteLogManager.TASK_EXECUTE_LOG })
public class TaskExecuteLogManager implements IMessageConsumer<TaskExecutionDetail> , InitializingBean {

    public static final String TASK_EXECUTE_LOG = "task_execute_log";

    @Autowired
    private ITaskExecutionDetailService taskExecutionDetailService;

    @Override
    public void onMessage(TaskExecutionDetail message, String pattern) {
        taskExecutionDetailService.updateExecuteLog(message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

package com.hand.hap.activiti.custom.task;

import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.task.TaskQuery;

/**
 * @author njq.niu@hand-china.com
 */
public class CustomTaskService extends TaskServiceImpl {
    public TaskQuery createTaskQuery() {
        return new CustomTaskQuery(commandExecutor, processEngineConfiguration.getDatabaseType());
    }

    public CustomTaskService(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }
}

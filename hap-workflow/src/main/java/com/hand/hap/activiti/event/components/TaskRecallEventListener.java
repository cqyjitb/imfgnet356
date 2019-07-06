package com.hand.hap.activiti.event.components;

import com.hand.hap.activiti.event.TaskRecallEvent;
import com.hand.hap.activiti.event.TaskRecallListener;
import com.hand.hap.activiti.event.dto.TaskRecallInfo;
import com.hand.hap.core.AppContextInitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu.qi@hand-china.com on 2017/11/7.
 */
@Component
public class TaskRecallEventListener implements AppContextInitListener {

    private List<TaskRecallListener> listeners;

    @EventListener
    public void handleTaskRecall(TaskRecallEvent event) {
        TaskRecallInfo taskRecallInfo = (TaskRecallInfo) event.getSource();
        listeners.forEach(t -> {
            if ("*".equals(t.processDefinitionKey()) || t.processDefinitionKey().contains(taskRecallInfo.getProcessDefinitionKey())) {
                t.doRecall(event.getiRequest(), taskRecallInfo);
            }
        });
    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        listeners = new ArrayList<>(applicationContext.getBeansOfType(TaskRecallListener.class).values());
    }
}

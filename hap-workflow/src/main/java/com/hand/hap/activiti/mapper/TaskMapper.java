package com.hand.hap.activiti.mapper;

import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author xiangyu.qi@hand-china.com on 2017/9/9.
 */
public interface TaskMapper {

    List<Task> selectTaskDueDateNotNull();
}

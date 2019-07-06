package com.hand.task.test;

import com.hand.hap.task.dto.TaskDetail;
import com.hand.hap.task.service.impl.TaskDetailServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TaskDetailTest {

    @Autowired
    TaskDetailServiceImpl service;
    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        service.queryChildrenByPrimaryKey(list);
    }
}

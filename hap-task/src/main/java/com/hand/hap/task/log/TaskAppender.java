package com.hand.hap.task.log;

import ch.qos.logback.core.OutputStreamAppender;

/**
 * @author peng.jiang@hand-china.com
 * @date 2017/11/28
 **/
public class TaskAppender<E> extends OutputStreamAppender<E> {

    private TaskOutputStream outputStream;

    @Override
    public void start() {
        outputStream = new TaskOutputStream();
        setOutputStream(outputStream);
        super.start();
    }


}

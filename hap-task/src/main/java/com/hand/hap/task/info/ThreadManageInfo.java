package com.hand.hap.task.info;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程维护.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/11/22
 **/

public class ThreadManageInfo {
    public static ConcurrentHashMap<String, Thread> threadHashMap = new ConcurrentHashMap<>();
}

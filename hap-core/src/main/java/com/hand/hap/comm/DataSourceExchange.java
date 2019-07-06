package com.hand.hap.comm;

import org.aspectj.lang.JoinPoint;

public class DataSourceExchange {

    public void before(JoinPoint point) {

        //获取目标对象的类类型
        Class<?> aClass = point.getTarget().getClass();
        String c = aClass.getName();
        String[] ss = c.split("\\.");
        //获取包名用于区分不同数据源
        String packageName = ss[1];

        if ("kanb".equals(packageName)) {
            System.out.println(DataSourceEnum.mySqlDataSource.getKey());
            DataSourceHolder.setDataSources(DataSourceEnum.mySqlDataSource.getKey());
           // System.out.println("数据源："+DataSourceEnum.mySqlDataSource.getKey());
        } else {
            DataSourceHolder.setDataSources(DataSourceEnum.mainDataSource.getKey());
           // System.out.println("数据源："+DataSourceEnum.mainDataSource.getKey());
        }
    }

    /**
     * 执行后将数据源置为空
     */
    public void after() {
        DataSourceHolder.setDataSources(null);
    }

}

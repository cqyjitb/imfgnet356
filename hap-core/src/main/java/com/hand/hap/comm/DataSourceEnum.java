package com.hand.hap.comm;

public enum  DataSourceEnum {

    mainDataSource("mainDataSource"), mySqlDataSource("mySqlDataSource");

    private String key;

    DataSourceEnum(String key) { this.key = key; }

    public String getKey() { return key; }


    public void setKey(String key) {  this.key = key; }
}



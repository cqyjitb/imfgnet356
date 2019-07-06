/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.testext.dto;

import org.apache.ibatis.type.JdbcType;

import com.hand.hap.mybatis.annotation.ColumnType;

/**
 * @author shengyang.zhou@hand-china.com
 */
public interface UserDemoExt {

    @ColumnType(jdbcType = JdbcType.VARCHAR)
    void setUserPhone(String userPhone);

    String getUserPhone();

}

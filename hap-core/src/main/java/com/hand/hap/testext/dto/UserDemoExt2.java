/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.testext.dto;

import java.util.Date;

import org.apache.ibatis.type.JdbcType;

import com.hand.hap.mybatis.annotation.ColumnType;

/**
 * @author shengyang.zhou@hand-china.com
 */
public interface UserDemoExt2 extends UserDemoExt {

    @ColumnType(jdbcType = JdbcType.TIMESTAMP)
    void setEndActiveTime(Date date);

    Date getEndActiveTime();

}

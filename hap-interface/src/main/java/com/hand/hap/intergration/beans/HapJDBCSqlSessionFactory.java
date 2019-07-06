/**
 * Copyright (c) 2016. Hand Enterprise Solution Company. All right reserved.
 * Project Name:hstaffParent
 * Package Name:hstaff.core.beans 
 * Date:2016/7/8 0008
 * Create By:zongyun.zhou@hand-china.com
 *
 */
package com.hand.hap.intergration.beans;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class HapJDBCSqlSessionFactory {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplateObject() {
        return jdbcTemplateObject;
    }
}

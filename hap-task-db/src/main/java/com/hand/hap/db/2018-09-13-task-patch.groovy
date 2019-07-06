package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper

def mhi = MigrationHelper.getInstance()
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"patch.groovy"){

    changeSet(author: "vista yih", id: "201809-13-sys_task_detail_b") {
        renameTable(oldTableName: 'SYS_TASK_DETAIL', newTableName: 'SYS_TASK_DETAIL_B')
    }
    changeSet(author: "vista yih", id: "2018-09-13-sys_task_detail_tl") {
        createTable(tableName: "SYS_TASK_DETAIL_TL", remarks: "任务详情多语言表") {
            column(name:"TASK_ID",type:"bigint",remarks: "任务ID"){
                constraints(nullable: "false", primaryKey: "true")
            }
            column(name:"LANG",type:"varchar(10)",remarks: "语言"){
                constraints(nullable: "false", primaryKey: "true")
            }

            column(name:"NAME",type:"VARCHAR(200)",remarks: "名称")
            column(name:"DESCRIPTION",type:"VARCHAR(255)",remarks: "描述")

            column(name: "OBJECT_VERSION_NUMBER", type: "BIGINT", defaultValue : "1")
            column(name: "REQUEST_ID", type: "BIGINT", defaultValue : "-1")
            column(name: "PROGRAM_ID", type: "BIGINT", defaultValue : "-1")
            column(name: "CREATED_BY", type: "BIGINT", defaultValue : "-1")
            column(name: "CREATION_DATE", type: "DATETIME", defaultValueComputed : "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATED_BY", type: "BIGINT", defaultValue : "-1")
            column(name: "LAST_UPDATE_DATE", type: "DATETIME", defaultValueComputed : "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATE_LOGIN", type: "BIGINT", defaultValue : "-1")
        }
    }

    changeSet(author: "qiang.zeng", id: "20190111-qiangzeng-1") {
        if(mhi.getDbType().isSupportSequence()){
            dropSequence(sequenceName: 'SYS_TASK_DETAIL_S')
            createSequence(sequenceName: 'SYS_TASK_DETAIL_B_S', startValue: "10001")
        }
    }

}
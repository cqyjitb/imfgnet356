package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper

def mhi = MigrationHelper.getInstance()
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"patch.groovy"){
    changeSet(author: "qiangzeng", id: "20180119-sys-report-file-1") {
        addColumn(tableName: "SYS_REPORT_FILE") {
            column(name: "SOURCE_TYPE", type: "VARCHAR(20)", remarks: "数据源类型",afterColumn:"PARAMS",defaultValue: "buildin")
        }
    }
}
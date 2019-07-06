package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper

def mhi = MigrationHelper.getInstance()

databaseChangeLog(logicalFilePath: "2017-03-16-init-table-migration.groovy") {

    changeSet(author: "qiangzeng", id: "20170922-sys-report-1") {
        if (mhi.getDbType().isSupportSequence()) {
            createSequence(sequenceName: 'SYS_REPORT_S', startValue: "10001")
        }
        createTable(tableName: "SYS_REPORT", remarks: "报表定义") {
            if (mhi.getDbType().isSupportAutoIncrement()) {
                column(name: "REPORT_ID", type: "BIGINT", autoIncrement: "true", startWith: "10001", remarks: "报表ID") {
                    constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_REPORT_PK")
                }
            } else {
                column(name: "REPORT_ID", type: "BIGINT", remarks: "报表ID") {
                    constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_REPORT_PK")
                }
            }

            column(name: "REPORT_CODE", type: "VARCHAR(50)", remarks: "报表编码") {
                constraints(nullable: "false")
            }
            column(name: "FILE_ID", type: "BIGINT", remarks: "报表文件ID") {
                constraints(nullable: "false")
            }
            column(name: "NAME", type: "VARCHAR(50)", remarks: "报表名称") {
                constraints(nullable: "false")
            }
            column(name: "DESCRIPTION", type: "VARCHAR(255)", remarks: "报表描述")
            column(name: "DEFAULT_QUERY", type: "VARCHAR(1)", remarks: "默认查询")
            column(name: "OBJECT_VERSION_NUMBER", type: "BIGINT", defaultValue: "1")
            column(name: "REQUEST_ID", type: "BIGINT", defaultValue: "-1")
            column(name: "PROGRAM_ID", type: "BIGINT", defaultValue: "-1")
            column(name: "CREATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "CREATION_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "LAST_UPDATE_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATE_LOGIN", type: "BIGINT", defaultValue: "-1")
        }
        if(!mhi.isDbType('postgresql')){
            addUniqueConstraint(columnNames: "REPORT_CODE", tableName: "SYS_REPORT", constraintName: "SYS_REPORT_U1")
        }else {
            addUniqueConstraint(columnNames: "REPORT_ID,REPORT_CODE", tableName: "SYS_REPORT", constraintName: "SYS_REPORT_U1")
        }

    }

    changeSet(author: "qiangzeng", id: "20170922-sys-report-file-1") {
        if (mhi.getDbType().isSupportSequence()) {
            createSequence(sequenceName: 'SYS_REPORT_FILE_S', startValue: "10001")
        }
        createTable(tableName: "SYS_REPORT_FILE", remarks: "报表文件") {
            if (mhi.getDbType().isSupportAutoIncrement()) {
                column(name: "FILE_ID", type: "BIGINT", autoIncrement: "true", startWith: "10001", remarks: "文件ID") {
                    constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_REPORT_FILE_PK")
                }
            } else {
                column(name: "FILE_ID", type: "BIGINT", remarks: "文件ID") {
                    constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_REPORT_FILE_PK")
                }
            }

            column(name: "NAME", type: "VARCHAR(50)", remarks: "文件名") {
                constraints(nullable: "false")
            }
            column(name: "CONTENT", type: "CLOB", remarks: "文件内容")
            column(name: "PARAMS", type: "VARCHAR(255)", remarks: "报表参数")

            column(name: "OBJECT_VERSION_NUMBER", type: "BIGINT", defaultValue: "1")
            column(name: "REQUEST_ID", type: "BIGINT", defaultValue: "-1")
            column(name: "PROGRAM_ID", type: "BIGINT", defaultValue: "-1")
            column(name: "CREATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "CREATION_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATED_BY", type: "BIGINT", defaultValue: "-1")
            column(name: "LAST_UPDATE_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATE_LOGIN", type: "BIGINT", defaultValue: "-1")
        }
        if(!mhi.isDbType('postgresql')){
            addUniqueConstraint(columnNames: "NAME", tableName: "SYS_REPORT_FILE", constraintName: "SYS_REPORT_FILE_U1")
        }else {
            addUniqueConstraint(columnNames: "FILE_ID,NAME", tableName: "SYS_REPORT_FILE", constraintName: "SYS_REPORT_FILE_U1")
        }

    }
}
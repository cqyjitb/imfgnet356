package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader

dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2017-10-12-task-init-data-migration.groovy"){
    changeSet(author: "vista yih", id: "20180917-task-detail-b") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_task_detail_tl.sql"), encoding: "UTF-8")
    }
}
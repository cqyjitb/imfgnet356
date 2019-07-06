package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader

dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2017-10-12-task-init-data-migration.groovy"){

    changeSet(author: "jiangpeng", id: "task-init-data-xlsx", runAlways:"true"){
        customChange(class:ExcelDataLoader.class.name){
            param(name:"filePath",value:MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/task-init-data.xlsx"))
        }
    }

}

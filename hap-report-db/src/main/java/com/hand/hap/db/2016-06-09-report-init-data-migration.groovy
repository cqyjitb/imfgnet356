package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2016-06-09-init-data-migration.groovy"){

    changeSet(author: "jessen", id: "report-init-data-xlsx", runAlways:"true"){
        customChange(class:ExcelDataLoader.class.name){
            param(name:"filePath",value:MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/report-init-data.xlsx"))
        }
    }
}

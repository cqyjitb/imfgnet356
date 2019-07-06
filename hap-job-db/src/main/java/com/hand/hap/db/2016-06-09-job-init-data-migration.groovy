package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2016-06-09-init-data-migration.groovy"){

    //Milestone , excel data, runAlways=true
    changeSet(author: "jessen", id: "job-init-data-xlsx", runAlways:"true"){
        customChange(class:ExcelDataLoader.class.name){
            param(name:"filePath",value:MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/job-init-data.xlsx"))
        }
    }

}

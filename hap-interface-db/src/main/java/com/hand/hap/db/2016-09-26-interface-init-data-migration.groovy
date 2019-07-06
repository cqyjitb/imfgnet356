package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader

dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2016-09-26-interface-init-data-migration.groovy"){

    changeSet(author: "jiangpeng", id: "interface-init-data-xlsx", runAlways:"true"){
        customChange(class:ExcelDataLoader.class.name){
            param(name:"filePath",value:MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/interface-init-data.xlsx"))
        }
    }

}

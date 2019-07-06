package com.hand.hap.db

//import com.hand.hap.liquibase.MigrationHelper
import com.hand.hap.db.excel.ExcelDataLoader
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"2016-06-09-init-data-migration.groovy"){

    changeSet(author: "jessen", id: "20160613-data-sys-attach-category-1") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_attach_category.sql"), encoding: "UTF-8")
    }

    changeSet(author: "qixiangyu", id: "20171116-data-sys-user-update-name") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_user_username_update.sql"), encoding: "UTF-8")
    }

    //Milestone , excel data, runAlways=true
    changeSet(author: "jessen", id: "20160930-init-data-xlsx", runAlways:"true"){
        customChange(class:ExcelDataLoader.class.name){
            param(name:"filePath",value:MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/core-init-data.xlsx"))
        }
    }

    changeSet(author: "qiangzeng", id: "20170401-data-sys-user-1") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_user_update.sql"), encoding: "UTF-8")
    }

    changeSet(author: "qiangzeng", id: "20170904-data-sys-resource-item-1") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_resource_item_update.sql"), encoding: "UTF-8")
    }

    changeSet(author: "qiangzeng", id: "20180820-data-sys-code-value-1") {
        sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/"+dbType+"/init/sys_code_value_update.sql"), encoding: "UTF-8")
    }

}

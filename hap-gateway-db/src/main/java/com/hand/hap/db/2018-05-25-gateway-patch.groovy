package com.hand.hap.db

import com.hand.hap.liquibase.MigrationHelper

def mhi = MigrationHelper.getInstance()
dbType = MigrationHelper.getInstance().dbType()
databaseChangeLog(logicalFilePath:"patch.groovy"){

    if (!mhi.isDbType('hana')) {
        changeSet(author: "jiangpeng", id: "api_config_server-alter-namespace-length-1") {
            modifyDataType(tableName: "API_CONFIG_SERVER", columnName: "NAMESPACE", newDataType: "VARCHAR(500)")
        }
    }else {
        changeSet(author: "jiangpeng", id: "api_config_server-alter-namespace-length-1-hana") {
            sqlFile(path: MigrationHelper.getInstance().dataPath("com/hand/hap/db/data/hana/patch/api_config_server-alter-namespace-length.sql"), encoding: "UTF-8")
        }
    }

}
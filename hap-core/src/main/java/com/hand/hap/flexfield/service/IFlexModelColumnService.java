package com.hand.hap.flexfield.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.flexfield.dto.FlexModelColumn;

import java.util.List;

public interface IFlexModelColumnService extends IBaseService<FlexModelColumn>, ProxySelf<IFlexModelColumnService> {

    /** 通过表名获取表中列
     * @param tableName 表名
     * @return 表所包含的列
     */
    List<String> getTableColumn(String tableName);

}
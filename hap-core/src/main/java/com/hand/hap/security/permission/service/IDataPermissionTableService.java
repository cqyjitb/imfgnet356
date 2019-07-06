package com.hand.hap.security.permission.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.security.permission.dto.DataPermissionTable;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com on 2017/12/8
 */
public interface IDataPermissionTableService extends IBaseService<DataPermissionTable>, ProxySelf<IDataPermissionTableService> {

    /**通过规则删除表
     * @param dataMaskTables 将要删除的表规则
     */
    void removeTableWithRule(List<DataPermissionTable> dataMaskTables);
}
package com.hand.hap.security.permission.service.impl;

import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.security.permission.dto.DataPermissionTableRule;
import com.hand.hap.security.permission.mapper.DataPermissionTableRuleMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.security.permission.dto.DataPermissionTable;
import com.hand.hap.security.permission.service.IDataPermissionTableService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com
 */
@Service
public class DataPermissionTableServiceImpl extends BaseServiceImpl<DataPermissionTable> implements IDataPermissionTableService {

    @Autowired
    DataPermissionTableRuleMapper dataPermissionTableRuleMapper;

    @Autowired
    IMessagePublisher iMessagePublisher;

    @Override
    public void removeTableWithRule(List<DataPermissionTable> dataMaskTables) {
        batchDelete(dataMaskTables);
        dataMaskTables.forEach(v -> {
            DataPermissionTableRule rule = new DataPermissionTableRule();
            rule.setTableId(v.getTableId());
            dataPermissionTableRuleMapper.delete(rule);
            iMessagePublisher.publish("dataPermission.tableRemove", v);
        });
    }
}
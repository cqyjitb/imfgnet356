package com.hand.hap.security.permission.service.impl;

import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.TopicMonitor;
import com.hand.hap.security.permission.dto.DataPermissionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jialong.zuo@hand-china.com
 * @date 2017/9/13.
 */
@Service
@TopicMonitor(channel = {"dataPermission.tableRemove"})
public class DataPermissionTableListener implements IMessageConsumer<DataPermissionTable> {

    @Autowired
    DataPermissionCacheContainer container;

    @Override
    public void onMessage(DataPermissionTable message, String pattern) {

        container.removeMaskTableRuleMap(message.getTableName());
    }
}

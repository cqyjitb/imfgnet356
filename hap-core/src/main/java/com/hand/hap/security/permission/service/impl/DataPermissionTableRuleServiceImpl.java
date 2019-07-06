package com.hand.hap.security.permission.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.security.permission.dto.DataPermissionTableRule;
import com.hand.hap.security.permission.service.IDataPermissionTableRuleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com
 */
@Service
public class DataPermissionTableRuleServiceImpl extends BaseServiceImpl<DataPermissionTableRule> implements IDataPermissionTableRuleService {

    @Autowired
    IMessagePublisher iMessagePublisher;

    @Override
    public void removeRule(List<DataPermissionTableRule> list) {
        batchDelete(list);
        updateCache(list, "dataPermission.tableRuleRemove");
    }

    @Override
    public List<DataPermissionTableRule> updateRule(IRequest request, List<DataPermissionTableRule> list) {
        List<DataPermissionTableRule> dto = batchUpdate(request, list);
        updateCache(list, "dataPermission.tableRuleUpdate");
        return dto;
    }

    private void updateCache(List<DataPermissionTableRule> list, String channel) {
        list.forEach(v -> {
            iMessagePublisher.publish(channel, v);
        });
    }
}
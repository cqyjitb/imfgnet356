package com.hand.hap.security.permission.service.impl;

import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.TopicMonitor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jialong.zuo@hand-china.com on 2017/9/13.
 */

@Component
@TopicMonitor(channel = {"dataPermission.ruleRefresh", "dataPermission.ruleRemove"})
public class DataPermissionRuleRefreshListener implements IMessageConsumer<Long> {

    @Autowired
    DataPermissionCacheContainer container;

    @Override
    public void onMessage(Long message, String pattern) {
        if (StringUtils.equals(pattern, "dataPermission.ruleRefresh")) {
            container.updateMaskRuleMap(message);
        } else if (StringUtils.equals(pattern, "dataPermission.ruleRemove")) {
            container.removeMaskRuleMap(message);
        }
    }
}

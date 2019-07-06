package com.hand.hap.security.permission.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.security.permission.dto.DataPermissionTableRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author jialong.zuo@hand-china.com
 * @date 2017/8/30.
 */
@Component
public class UserMaskRangeFilter extends DataPermissionRangeFilter {
    @Autowired
    DataPermissionCacheContainer container;

    private final String maskRange = "user_lov";

    @Override
    public void doFilter(IRequest iRequest, String tableName, Map data) throws ExecutionException {
        if ("-1".equals(iRequest.getUserId().toString())) {
            return;
        }

        List<DataPermissionTableRule> ruleCodeList = container.getTableRule(tableName);
        if (ruleCodeList.size() == 0) {
            return;
        }

        setRuleDetail(ruleCodeList, maskRange, iRequest.getUserId().toString(), container, data);

    }
}

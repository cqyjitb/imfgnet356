package com.hand.hap.security.permission.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.security.permission.dto.DataPermissionTableRule;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com on 2017/12/8
 */
public interface IDataPermissionTableRuleService extends IBaseService<DataPermissionTableRule>, ProxySelf<IDataPermissionTableRuleService> {

    /**删除表规则
     * @param list 将要删除的规则
     */
    void removeRule(List<DataPermissionTableRule> list);

    /**更新分配表规则
     * @param request IRequest环境
     * @param list 将要更新的规则
     * @return 更新过后的规则
     */
    List<DataPermissionTableRule> updateRule(IRequest request, List<DataPermissionTableRule> list);
}
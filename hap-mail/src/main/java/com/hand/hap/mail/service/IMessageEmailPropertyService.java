package com.hand.hap.mail.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.mail.dto.MessageEmailProperty;
import com.hand.hap.system.service.IBaseService;

/**
 * 邮件服务器属性服务接口.
 *
 * @author qiang.zeng@hand-china.com
 */
public interface IMessageEmailPropertyService extends IBaseService<MessageEmailProperty>, ProxySelf<IMessageEmailPropertyService> {
    /**
     * 根据邮件配置Id删除邮件服务器属性.
     *
     * @param obj 邮件服务器属性
     * @return int
     */
    int deleteByConfigId(MessageEmailProperty obj);
}
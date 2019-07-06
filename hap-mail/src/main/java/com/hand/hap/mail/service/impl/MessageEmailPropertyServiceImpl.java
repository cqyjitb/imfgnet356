package com.hand.hap.mail.service.impl;

import com.hand.hap.mail.dto.MessageEmailProperty;
import com.hand.hap.mail.mapper.MessageEmailPropertyMapper;
import com.hand.hap.mail.service.IMessageEmailPropertyService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邮件服务器属性服务接口实现.
 *
 * @author qiang.zeng@hand-china.com
 */
@Service
public class MessageEmailPropertyServiceImpl extends BaseServiceImpl<MessageEmailProperty> implements IMessageEmailPropertyService {

    @Autowired
    private MessageEmailPropertyMapper messageEmailPropertyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByConfigId(MessageEmailProperty obj) {
        if (obj.getPropertyId() == null) {
            return 0;
        }
        int result = messageEmailPropertyMapper.deleteByPrimaryKey(obj);
        checkOvn(result, obj);
        return result;
    }


}
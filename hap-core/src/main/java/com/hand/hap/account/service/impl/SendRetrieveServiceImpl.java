package com.hand.hap.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hand.hap.account.constants.UserConstants;
import com.hand.hap.account.dto.SendRetrieve;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.mapper.SendRetrieveMapper;
import com.hand.hap.account.service.ISendRetrieveService;
import com.hand.hap.core.IRequest;


/**
 * 发送次数限制服务接口实现.
 *
 * @author shengyang.zhou@hand-china.com
 */
@Service
public class SendRetrieveServiceImpl implements ISendRetrieveService {

    @Autowired
    private SendRetrieveMapper sendRetrieveMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(IRequest request, SendRetrieve sendRetrieve) throws UserException {
        // TODO Auto-generated method stub
        sendRetrieveMapper.insertRecord(sendRetrieve);
        Integer result = sendRetrieveMapper.query(sendRetrieve);
        if (result > UserConstants.NUMBER_2) {
            throw new UserException(UserConstants.SENT_LIMIT_ERROR, new Object[]{});
        }
        return result;
    }
}

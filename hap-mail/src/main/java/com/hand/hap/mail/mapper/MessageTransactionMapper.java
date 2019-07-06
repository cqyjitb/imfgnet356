package com.hand.hap.mail.mapper;

import com.hand.hap.mail.dto.MessageTransaction;
import com.hand.hap.mybatis.common.Mapper;

/**
 * 消息事务Mapper.
 *
 * @author chuangsheng.zhang
 */
public interface MessageTransactionMapper extends Mapper<MessageTransaction> {
    /**
     * 根据消息Id删除消息事务.
     *
     * @param messageId 消息Id
     * @return int
     */
    int deleteByMessageId(Long messageId);

    /**
     * 根据消息Id查询消息发送成功数量.
     *
     * @param messageId 消息Id
     * @return 消息发送成功数量
     */
    long selectSuccessCountByMessageId(Long messageId);
}
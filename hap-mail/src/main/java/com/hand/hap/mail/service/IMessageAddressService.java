package com.hand.hap.mail.service;

import com.hand.hap.mail.MessageTypeEnum;
import com.hand.hap.mail.dto.MessageAddress;

import java.util.List;

/**
 * 消息地址服务接口.
 *
 * @author shiliyan
 */
public interface IMessageAddressService {
    /**
     * 返回消息地址列表.
     *
     * @param msgType 消息类型 MessageTypeEnum
     * @param address 地址
     * @return 地址列表
     */
    List<String> queryMessageAddress(MessageTypeEnum msgType, MessageAddress address);
}

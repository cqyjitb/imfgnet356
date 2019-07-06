package com.hand.hap.mail.service.impl;

import com.hand.hap.mail.dto.Message;
import com.hand.hap.mail.service.IEmailService;
import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.QueueMonitor;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 普通邮件队列接收者.
 *
 * @author jialong.zuo@hand-china.com on 2016/12/8.
 */
@Service
@QueueMonitor(queue = "hap:queue:email:normal")
public class NormalMessageReceiver implements IMessageConsumer<Message> {
    @Autowired
    IEmailService emailService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message, String pattern) {
        Map params = new HashedMap();
        params.put("batch", 0);
        params.put("isVipQueue", false);
        try {
            emailService.sendMessageByReceiver(message, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}

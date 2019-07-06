package com.hand.hap.system.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.message.websocket.BadgeManager;
import com.hand.hap.message.websocket.CommandMessage;
import com.hand.hap.message.websocket.WebSocketSessionManager;
import com.hand.hap.system.service.IBadgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhailin
 * @date 2017/7/20.
 */
@Service
public class BadgeServiceImpl implements IBadgeService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    IMessagePublisher iMessagePublisher;

    @Autowired
    WebSocketSessionManager webSocketSessionManager;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public static final String HAP_CACHE_BADGE = BaseConstants.HAP_CACHE + "badge:";

    private Map<String, Object> updateBadgeCount(String userName, String badgeCode, Long num) {
        String codes = (String) redisTemplate.opsForHash().get(HAP_CACHE_BADGE, userName);
        Map<String, Object> map = new HashMap<>();
        try {
            if (codes != null) {
                map = objectMapper.readValue(codes, Map.class);
            }
            map.put(badgeCode, num);
            redisTemplate.opsForHash().put(HAP_CACHE_BADGE, userName, objectMapper.writeValueAsString(map));
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(HAP_CACHE_BADGE + userName + ":{} not an object", codes);
            }
        }
        return map;
    }

    @Override
    public void sendBadgeMessageToUser(String userName, String badgeCode, Long num) {
        CommandMessage commandMessage = new CommandMessage();
        commandMessage.setUserName(userName);
        commandMessage.setAction("SYS_BADGE");
        Map<String, Object> map = updateBadgeCount(userName, badgeCode, num);
        commandMessage.setParameter(map);
        iMessagePublisher.publish(BadgeManager.CHANNEL_BADGE, commandMessage);
    }

    @Override
    public void initBadgeMessage(WebSocketSession session) {
        String userName = webSocketSessionManager.getPrincipalName(session);
        if (!StringUtils.isEmpty(userName)) {
            String codes = (String) redisTemplate.opsForHash().get(HAP_CACHE_BADGE, userName);
            Map<String, Object> map = new HashMap<>();
            try {
                if (codes != null) {
                    map = objectMapper.readValue(codes, Map.class);
                }
                if (map != null) {
                    CommandMessage commandMessage = new CommandMessage();
                    commandMessage.setUserName(userName);
                    commandMessage.setAction("SYS_BADGE");
                    commandMessage.setParameter(map);
                    webSocketSessionManager.sendCommandMessage(session, commandMessage);
                }
            } catch (IOException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(HAP_CACHE_BADGE + userName + ":{} not an object", codes);
                }
            }
        }
    }

}

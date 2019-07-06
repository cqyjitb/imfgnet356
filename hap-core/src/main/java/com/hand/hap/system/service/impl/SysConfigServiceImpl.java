package com.hand.hap.system.service.impl;

import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.message.components.GlobalProfileSubscriber;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hand.hap.cache.CacheSet;
import com.hand.hap.core.IRequest;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.system.dto.GlobalProfile;
import com.hand.hap.system.dto.SysConfig;
import com.hand.hap.system.mapper.SysConfigMapper;
import com.hand.hap.system.service.ISysConfigService;

import java.util.Date;

/**
 * @author hailin.xu@hand-china.com
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements ISysConfigService { 

    @Autowired
    private SysConfigMapper configMapper;
    
    @Autowired
    private IMessagePublisher messagePublisher;
    
	@Override
    @CacheSet(cache = "config")
    public SysConfig insertSelective(IRequest request, @StdWho SysConfig config) {
        super.insertSelective(request, config);
        //配置更改时 通知监听者
        messagePublisher.publish(GlobalProfileSubscriber.CONFIG,
                new GlobalProfile(config.getConfigCode(), config.getConfigValue()));
        return config;
    }

    /*@Override
    @CacheDelete(cache = "config")
    public int deleteByPrimaryKey(Config config) {
        return super.deleteByPrimaryKey(config);
    }*/

    @Override
    @CacheSet(cache = "config")
    public SysConfig updateByPrimaryKeySelective(IRequest request, @StdWho SysConfig config) {
        super.updateByPrimaryKeySelective(request, config);
        //配置更改时 通知监听者
        messagePublisher.publish(GlobalProfileSubscriber.CONFIG,
                new GlobalProfile(config.getConfigCode(), config.getConfigValue()));
        return config;
    }

    @Override
    public String getConfigValue(String configCode) { 
        SysConfig config = configMapper.selectByCode(configCode);
        if(config !=null){
            return config.getConfigValue();
        }else{
            return null;
        }
    }

    @Override
    public String updateSystemImageVersion(String type) {
        String tag = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        messagePublisher.publish(GlobalProfileSubscriber.CONFIG, new GlobalProfile(type, tag));
        return tag;
    }
}

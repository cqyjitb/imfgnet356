package com.hand.hap.system.service;


import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.dto.SysConfig;

/**
 * @author hailin.xu@hand-china.com
 */
public interface ISysConfigService extends IBaseService<SysConfig>, ProxySelf<ISysConfigService> {
    /**
     * 根据ConfigCode获取配置值.
     * 
     * @param configCode
     *            配置代码
     * @return 配置值
     */
            
    String getConfigValue(String configCode);

    /**
     * 更新系统图片时间戳.
     */
    String updateSystemImageVersion(String type);


    
}

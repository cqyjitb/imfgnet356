package com.hand.hap.system.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.SysConfig;

/**
 * @author hailin.xu@hand-china.com
 */
public interface SysConfigMapper extends Mapper<SysConfig> {

    SysConfig selectByCode(String configCode);
}

package com.hand.hap.system.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.Profile;

/**
 * 配置维护Mapper
 *
 * @author Frank.li
 * @date 2016/6/9.
 */
public interface ProfileMapper extends Mapper<Profile> {

    /**
     * 查询配置
     *
     * @param profileName 配置名称
     * @return 配置
     */
    Profile selectByName(String profileName);
}
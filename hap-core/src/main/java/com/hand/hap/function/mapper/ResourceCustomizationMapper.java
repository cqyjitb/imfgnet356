package com.hand.hap.function.mapper;

import com.hand.hap.function.dto.ResourceCustomization;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

/**
 * 资源合并配置项Mapper.
 *
 * @author zhizheng.yang@hand-china.com
 */
public interface ResourceCustomizationMapper extends Mapper<ResourceCustomization> {
    /**
     * 根据资源Id查询资源合并配置项.
     *
     * @param resourceId 资源Id
     * @return 资源合并配置项集合
     */
    List<ResourceCustomization> selectResourceCustomizationsByResourceId(Long resourceId);

}

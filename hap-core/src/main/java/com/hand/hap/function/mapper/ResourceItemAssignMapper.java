package com.hand.hap.function.mapper;

import com.hand.hap.function.dto.ResourceItemAssign;
import com.hand.hap.mybatis.common.Mapper;

/**
 * 权限组件分配Mapper.
 *
 * @author qiang.zeng@hand-china.com
 */
public interface ResourceItemAssignMapper extends Mapper<ResourceItemAssign> {
    /**
     * 根据角色Id和功能Id删除权限组件分配.
     *
     * @param roleId     角色Id
     * @param functionId 功能Id
     * @return int
     */
    int deleteByRoleIdAndFunctionId(Long roleId, Long functionId);

    /**
     * 根据用户Id和功能Id删除权限组件分配.
     *
     * @param userId     用户Id
     * @param functionId 功能Id
     * @return int
     */
    int deleteByUserIdAndFunctionId(Long userId, Long functionId);

    /**
     * 根据权限组件元素Id删除权限组件分配.
     *
     * @param elementId 权限组件元素Id
     * @return int
     */
    int deleteByElementId(Long elementId);

    /**
     * 根据权限组件Id删除权限组件分配.
     *
     * @param resourceItemId 权限组件Id
     * @return int
     */
    int deleteByResourceItemId(Long resourceItemId);
}
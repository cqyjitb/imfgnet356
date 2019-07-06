package com.hand.hap.function.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.function.dto.RoleFunction;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 角色功能服务接口.
 *
 * @author liuxiawang
 * @author njq.niu@hand-china.com
 */
public interface IRoleFunctionService extends IBaseService<RoleFunction>, ProxySelf<IRoleFunctionService> {

    /**
     * 从缓存中查询角色的所有功能ID的集合.
     *
     * @param roleId 角色Id
     * @return 角色功能Id集合
     */
    Long[] getRoleFunctionById(Long roleId);


    /**
     * 清空角色功能.
     *
     * @param roleId 角色id
     */
    void removeRoleFunctionByRoleId(Long roleId);

    /**
     * 清空数据库角色功能.
     *
     * @param roleId 角色ID
     */
    void removeByRoleId(Long roleId);

    /**
     * 重新加载角色资源缓存.
     *
     * @param roleId 角色Id
     */
    void reloadRoleResourceCache(Long roleId);
}

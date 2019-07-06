package com.hand.hap.account.service;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.RoleExt;
import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.RoleException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 角色服务接口.
 *
 * @author shengyang.zhou@hand-china.com
 * @author njq.niu@hand-china.com
 */
public interface IRoleService extends IBaseService<Role>, ProxySelf<IRoleService> {

    /**
     * 条件查询角色.
     *
     * @param requestContext 请求上下文
     * @param role           角色
     * @param pageNum        页码
     * @param pageSize       每页显示数量
     * @return 角色集合
     */
    List<Role> selectRoles(IRequest requestContext, Role role, int pageNum, int pageSize);

    /**
     * 查询不属于当前用户的角色.
     *
     * @param requestContext 请求上下文
     * @param roleExt        条件,至少包含 userId
     * @param page           页码
     * @param pageSize       每页显示数量
     * @return 角色集合
     */
    List<IRole> selectRoleNotUserRoles(IRequest requestContext, RoleExt roleExt, int page, int pageSize);

    /**
     * 查询用户所有启用状态的角色.
     *
     * @param requestContext 请求上下文
     * @param user           包含 userId
     * @return 角色集合
     */
    List<IRole> selectRolesByUser(IRequest requestContext, User user);

    /**
     * 根据用户ID查询用户的角色.
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    List<IRole> selectUserRolesByUserId(Long userId);


    /**
     * 判断用户角色分配是否存在.
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @throws RoleException 角色无效异常
     */
    void checkUserRoleExists(Long userId, Long roleId) throws RoleException;

    /**
     * 根据角色代码查询角色.
     *
     * @param roleCode 角色代码
     * @return 角色
     */
    IRole selectRoleByCode(String roleCode);

    /**
     * 查询用户的所有有效角色.
     *
     * @param requestContext 请求上下文
     * @param user           包含 userId
     * @return 角色集合
     */
    List<IRole> selectActiveRolesByUser(IRequest requestContext, User user);

}

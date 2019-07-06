package com.hand.hap.account.service;

import com.hand.hap.account.dto.UserRole;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

/**
 * 用户角色分配服务接口.
 *
 * @author xiawang.liu@hand-china.com
 */
public interface IUserRoleService extends IBaseService<UserRole>, ProxySelf<IUserRoleService> {

}
package com.hand.hap.function.service.impl;

import java.util.List;

import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.message.components.DefaultRoleResourceListener;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hand.hap.cache.impl.RoleFunctionCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.function.dto.RoleFunction;
import com.hand.hap.function.mapper.RoleFunctionMapper;
import com.hand.hap.function.service.IRoleFunctionService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;

/**
 * 角色功能服务接口实现.
 *
 * @author liuxiawang
 * @author njq.niu@hand-china.com
 */
@Service
public class RoleFunctionServiceImpl extends BaseServiceImpl<RoleFunction> implements IRoleFunctionService {

    @Autowired
    private RoleFunctionMapper rolefunctionMapper;

    @Autowired
    private RoleFunctionCache roleFunctionCache;

    @Autowired
    private IMessagePublisher messagePublisher;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long[] getRoleFunctionById(Long roleId) {
        Long[] roleFuction = roleFunctionCache.getValue(roleId.toString());
        if (roleFuction == null) {
            roleFunctionCache.reload();
            return roleFunctionCache.getValue(roleId.toString());
        }
        return roleFuction;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RoleFunction> batchUpdate(IRequest requestContext, List<RoleFunction> roleFunctions) {
        if (CollectionUtils.isNotEmpty(roleFunctions)) {
            RoleFunction rf = roleFunctions.get(0);
            Long[] ids = new Long[roleFunctions.size()];
            Long roleId = rf.getRoleId();
            int i = 0;
            rolefunctionMapper.deleteByRoleId(roleId);
            for (RoleFunction rolefunction : roleFunctions) {
                if (rolefunction.getFunctionId() != null) {
                    rolefunctionMapper.insertSelective(rolefunction);
                    ids[i++] = rolefunction.getFunctionId();
                } else {
                    ids = null;
                }
            }
            removeCache(roleId);
            if (ArrayUtils.isNotEmpty(ids)) {
                roleFunctionCache.setValue(roleId.toString(), ids);
                self().reloadRoleResourceCache(roleId);
            }
        }
        return roleFunctions;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void reloadRoleResourceCache(Long roleId) {
        notifyCache(roleId);
    }

    @Override
    public void removeRoleFunctionByRoleId(Long roleId) {
        self().removeByRoleId(roleId);
        removeCache(roleId);
    }

    /**
     * 通过角色ID删除数据库角色.
     *
     * @param roleId 角色ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByRoleId(Long roleId) {
        rolefunctionMapper.deleteByRoleId(roleId);
    }

    /**
     * 清除角色功能缓存和角色资源二级缓存.
     *
     * @param roleId 角色Id
     */
    private void removeCache(Long roleId) {
        roleFunctionCache.remove(roleId.toString());
        notifyCache(roleId);
    }

    /**
     * 发消息操作角色资源二级缓存.
     *
     * @param roleId 角色ID
     */
    private void notifyCache(Long roleId) {
        messagePublisher.publish(DefaultRoleResourceListener.CACHE_ROLE_RESOURCE, roleId);
    }

}

package com.hand.hap.function.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.function.dto.ResourceItemElement;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 权限组件元素服务接口.
 *
 * @author qiang.zeng
 */
public interface IResourceItemElementService extends IBaseService<ResourceItemElement>, ProxySelf<IResourceItemElementService> {
    /**
     * 批量删除权限组件元素.
     *
     * @param requestContext IRequest
     * @param elementList    权限组件元素集合
     * @return int
     */
    int batchDelete(IRequest requestContext, List<ResourceItemElement> elementList);

    /**
     * 根据权限组件Id查询权限组件元素.
     *
     * @param requestContext IRequest
     * @param element        权限组件元素DTO
     * @return 权限组件元素集合
     */
    List<ResourceItemElement> selectByResourceItemId(IRequest requestContext, ResourceItemElement element);
}
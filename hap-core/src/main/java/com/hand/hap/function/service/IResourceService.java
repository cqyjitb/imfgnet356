package com.hand.hap.function.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.function.dto.Resource;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 资源服务接口.
 *
 * @author wuyichu
 */
public interface IResourceService extends IBaseService<Resource>, ProxySelf<IResourceService> {

    /**
     * 根据资源的url查询资源数据.
     *
     * @param url 资源的路径
     * @return 资源
     */
    Resource selectResourceByUrl(String url);

    /**
     * 根据资源的Id查询资源数据.
     *
     * @param id 资源ID
     * @return 资源
     */
    Resource selectResourceById(Long id);

    /**
     * 批量删除资源记录.
     *
     * @param requestContext IRequest
     * @param resources      资源集合
     * @return int
     */
    int batchDelete(IRequest requestContext, List<Resource> resources);

}

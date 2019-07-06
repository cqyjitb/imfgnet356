package com.hand.hap.api.gateway.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.api.gateway.dto.ApiInterface;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * server-interface 接口.
 *
 * @author xiangyu.qi@hand-china.com
 * @date 2017/9/20.
 */

public interface IApiInterfaceService extends IBaseService<ApiInterface>, ProxySelf<IApiInterfaceService>{

    /**
     * 接口查询.
     *
     * @param request
     * @param srInterface
     * @return
     */
    List<ApiInterface> selectByServerId(IRequest request, ApiInterface srInterface);

    /**
     * 查询服务接口（包括接口限制信息）.
     *
     * @param request
     * @param clientId
     * @param serverId
     * @return
     */
    List<ApiInterface> selectByServerIdWithLimit(IRequest request, String clientId, Long serverId);

    /**
     * 根据服务代码获取接口.
     *
     * @param requestContext
     * @param clientId
     * @param serverCode
     * @return
     */
    List<ApiInterface> selectInterfacesByServerCode(IRequest requestContext, String clientId, String serverCode);
}
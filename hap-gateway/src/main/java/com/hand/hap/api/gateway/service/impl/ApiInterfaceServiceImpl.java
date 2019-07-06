package com.hand.hap.api.gateway.service.impl;

import com.hand.hap.api.gateway.dto.ApiServer;
import com.hand.hap.api.gateway.mapper.ApiServerMapper;
import com.hand.hap.cache.impl.ApiServerCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.api.ApiConstants;
import com.hand.hap.api.application.dto.ApiAccessLimit;
import com.hand.hap.api.gateway.mapper.ApiInterfaceMapper;
import com.hand.hap.api.gateway.service.IApiInterfaceService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.api.gateway.dto.ApiInterface;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * 接口Service - 实现类.
 *
 * @author lijian.yin@hand-china.com
 **/

@Service
public class ApiInterfaceServiceImpl extends BaseServiceImpl<ApiInterface> implements IApiInterfaceService {

    @Autowired
    private ApiInterfaceMapper mapper;

    @Autowired
    private ApiServerMapper serverMapper;

    @Autowired
    private ApiServerCache serverCache;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ApiInterface> selectByServerId(IRequest request, ApiInterface srInterface) {
        return mapper.selectByServerId(srInterface);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ApiInterface> selectByServerIdWithLimit(IRequest request, String clientId, Long serverId) {
        List<ApiInterface> apiInterfaces = mapper.selectByServerIdWithLimit(clientId, serverId);

        for(ApiInterface apiInterface : apiInterfaces) {
            if(null == apiInterface.getApiAccessLimit()) {
                apiInterface.setApiAccessLimit(new ApiAccessLimit());
            }
            if(null == apiInterface.getApiAccessLimit().getAccessFlag()) {
                apiInterface.getApiAccessLimit().setAccessFlag(ApiConstants.ENABLE_FLAG_Y);
            }
        }
        return apiInterfaces;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ApiInterface> selectInterfacesByServerCode(IRequest requestContext, String clientId, String serverCode) {
        List<ApiInterface> apiInterfaces = mapper.selectInterfacesByServerCode(serverCode);
        apiInterfaces.stream().forEach(apiInterface -> {
            ApiAccessLimit apiAccessLimit = new ApiAccessLimit();
            apiAccessLimit.setAccessFlag("Y");
            apiAccessLimit.setClientId(clientId);
            apiAccessLimit.setServerCode(serverCode);
            apiAccessLimit.setInterfaceCode(apiInterface.getCode());
            apiInterface.setApiAccessLimit(apiAccessLimit);
        });
        return apiInterfaces;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int batchDelete(List<ApiInterface> list) {
        int count = super.batchDelete(list);
        if (count > 0){
            ApiServer server = serverMapper.selectByPrimaryKey(list.get(0).getServerId());
            serverCache.removeInterface(server, list);
        }
        return count;
    }
}
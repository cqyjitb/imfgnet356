package com.hand.hap.api.gateway.controllers;

import com.hand.hap.api.gateway.dto.ApiInterface;
import com.hand.hap.api.gateway.service.IApiInterfaceService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口控制器.
 *
 * @author lijian.yin@hand-china.com
 **/

@RestController
@RequestMapping(value = {"/sys/gateway/interface","/api/sys/gateway/interface"})
public class ApiInterfaceController extends BaseController {

    @Autowired
    private IApiInterfaceService service;

    @PostMapping(value = "/remove")
    public ResponseData remove(HttpServletRequest request, @RequestBody List<ApiInterface> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @PostMapping(value = "/getInterfacesByServerId")
    public ResponseData queryInterfacesByServerId(ApiInterface dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectByServerId(requestContext, dto));
    }

    @PostMapping(value = "/getInterfacesByServerCode/{clientId}/{serverId}")
    public ResponseData getInterfacesByServerCode(@PathVariable String clientId,
                                                @PathVariable Long serverId,
                                                HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectByServerIdWithLimit(requestContext, clientId, serverId));
    }

}
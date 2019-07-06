package com.hand.hap.api.logs.controllers;

import com.hand.hap.message.components.InvokeApiManager;
import com.hand.hap.api.logs.dto.ApiInvokeRecord;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * api调用记录.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/9/23.
 */

@RestController
@RequestMapping(value = {"/sys/logs/invokeRecord","/api/sys/logs/invokeRecord"})
public class ApiInvokeRecordController extends BaseController{

    @Autowired
    private InvokeApiManager invokeApiManager;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData query(@RequestBody ApiInvokeRecord dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(invokeApiManager.getInvokeApiStrategy().queryInvokeRecord(requestContext,dto,dto.getPage(),dto.getPagesize()));
    }

    @PostMapping(value = "/getById")
    public ResponseData queryById(HttpServletRequest request , @RequestBody ApiInvokeRecord dto) {
        return new ResponseData(invokeApiManager.getInvokeApiStrategy().selectById(dto.getRecordId()));
}


}
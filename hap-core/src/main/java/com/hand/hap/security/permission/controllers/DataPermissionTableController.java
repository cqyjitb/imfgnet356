package com.hand.hap.security.permission.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.security.permission.dto.DataPermissionTable;
import com.hand.hap.security.permission.service.IDataPermissionTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com on 2017/12/8
 */
@RestController
@RequestMapping(value = {"/sys/data/permission/table","/api/sys/data/permission/table"})
public class DataPermissionTableController extends BaseController {

    @Autowired
    private IDataPermissionTableService service;


    @RequestMapping(value = "/query")
    public ResponseData query(DataPermissionTable dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @PostMapping(value = "/submit")
    public ResponseData update(@RequestBody List<DataPermissionTable> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @PostMapping(value = "/remove")
    public ResponseData delete(HttpServletRequest request, @RequestBody List<DataPermissionTable> dto) {
        service.removeTableWithRule(dto);
        return new ResponseData();
    }
}
package com.hand.hap.security.permission.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.security.permission.dto.DataPermissionRuleAssign;
import com.hand.hap.security.permission.service.IDataPermissionRuleAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author jialong.zuo@hand-china.com on 2017/12/8
 */
@RestController
@RequestMapping(value = {"/sys/data/permission/rule/assign","/api/sys/data/permission/rule/assign"})
public class DataPermissionRuleAssignController extends BaseController {

    @Autowired
    private IDataPermissionRuleAssignService service;


    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData query(DataPermissionRuleAssign dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) throws IllegalAccessException {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectRuleAssign(dto, page, pageSize, requestContext));
    }

    @PostMapping(value = "/submit")
    public ResponseData update(@RequestBody List<DataPermissionRuleAssign> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.updateDataMaskRuleAssign(requestCtx, dto));
    }

    @PostMapping(value = "/remove")
    public ResponseData delete(HttpServletRequest request, @RequestBody List<DataPermissionRuleAssign> dto) {
        service.removeDataMaskRuleAssign(dto);
        return new ResponseData();
    }
}
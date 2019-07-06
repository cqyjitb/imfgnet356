package com.hand.hap.flexfield.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.flexfield.dto.FlexRuleDetail;
import com.hand.hap.flexfield.service.IFlexRuleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/fnd/flex/rule/detail", "/api/fnd/flex/rule/detail"})
public class FlexRuleDetailController extends BaseController {

    @Autowired
    private IFlexRuleDetailService service;


    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData query(FlexRuleDetail dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @PostMapping(value = "/submit")
    public ResponseData update(@RequestBody List<FlexRuleDetail> dto, HttpServletRequest request, BindingResult result) {
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
    public ResponseData delete(HttpServletRequest request, @RequestBody List<FlexRuleDetail> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}
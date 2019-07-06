package com.hand.hap.code.rule.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.query.Comparison;
import com.hand.hap.mybatis.common.query.WhereField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hand.hap.code.rule.dto.CodeRulesHeader;
import com.hand.hap.code.rule.service.ICodeRulesHeaderService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

@Controller
@RequestMapping(value = {"/sys/code/rules/header","/api/sys/code/rules/header"})
public class CodeRulesHeaderController extends BaseController {

    @Autowired
    private ICodeRulesHeaderService service;

    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseData query(CodeRulesHeader dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Criteria criteria = new Criteria(dto);
        criteria.where(new WhereField(CodeRulesHeader.FIELD_HEADER_ID),new WhereField( CodeRulesHeader.FIELD_RULE_CODE,Comparison.LIKE),new WhereField(CodeRulesHeader.FIELD_RULE_NAME,Comparison.LIKE));
        return new ResponseData(service.selectOptions(requestContext, dto,criteria, page, pageSize));
    }

    @RequestMapping(value = "/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<CodeRulesHeader> dto, BindingResult result,
            HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<CodeRulesHeader> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}
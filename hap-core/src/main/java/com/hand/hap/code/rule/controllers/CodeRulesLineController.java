package com.hand.hap.code.rule.controllers;

import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.query.SQLField;
import com.hand.hap.mybatis.common.query.SortField;
import com.hand.hap.mybatis.common.query.SortType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.code.rule.dto.CodeRulesLine;
import com.hand.hap.code.rule.service.ICodeRulesLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value= {"/sys/code/rules/line","/api/sys/code/rules/line"})
public class CodeRulesLineController extends BaseController {

    @Autowired
    private ICodeRulesLineService service;

    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseData query(CodeRulesLine dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Criteria criteria = new Criteria(dto);
        criteria.setSortFields(Arrays.asList(new SortField(CodeRulesLine.FIELD_FIELD_SEQUENCE,SortType.ASC)));
        return new ResponseData(service.selectOptions(requestContext,dto,criteria));
    }

    @RequestMapping(value = "/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<CodeRulesLine> dto, BindingResult result, HttpServletRequest request) {
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
    public ResponseData delete(HttpServletRequest request, @RequestBody List<CodeRulesLine> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}
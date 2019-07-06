package com.hand.hap.fnd.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.fnd.dto.Company;
import com.hand.hap.fnd.service.ICompanyService;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.query.Comparison;
import com.hand.hap.mybatis.common.query.WhereField;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 对公司的操作.
 *
 * @author jialong.zuo@hand-china.com
 * @date 2016/10/9.
 */
@RestController
@RequestMapping(value = {"/fnd/company", "/api/fnd/company"})
public class CompanyController extends BaseController {
    @Autowired
    ICompanyService companyService;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData select(Company company, HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                               @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestCtx = createRequestContext(request);
        Criteria criteria = new Criteria(company);
        criteria.where(new WhereField(Company.FIELD_COMPANY_CODE, Comparison.LIKE), Company.FIELD_COMPANY_ID, Company.FIELD_COMPANY_TYPE, Company.FIELD_PARENT_COMPANY_ID, Company.FIELD_COMPANY_FULL_NAME, Company.FIELD_COMPANY_SHORT_NAME);
        return new ResponseData(companyService.selectOptions(requestCtx, company, criteria, page, pagesize));
    }

    @PostMapping(value = {"/submit", "/update"})
    public ResponseData update(@RequestBody List<Company> companies, BindingResult result, HttpServletRequest request) {
        IRequest requestCtx = createRequestContext(request);
        getValidator().validate(companies, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        return new ResponseData(companyService.batchUpdate(requestCtx, companies));
    }

    @PostMapping(value = "/remove")
    public ResponseData delete(@RequestBody List<Company> companies, HttpServletRequest request) {
        companyService.batchDelete(companies);
        return new ResponseData();
    }
}

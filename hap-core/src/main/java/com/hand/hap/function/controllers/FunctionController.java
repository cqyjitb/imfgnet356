package com.hand.hap.function.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.function.dto.Function;
import com.hand.hap.function.dto.Resource;
import com.hand.hap.function.service.IFunctionService;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.query.Comparison;
import com.hand.hap.mybatis.common.query.WhereField;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对功能的操作.
 *
 * @author wuyichu
 * @author njq.niu@hand-china.com
 * @author xiawang.liu@hand-china.com
 */
@RestController
@RequestMapping(value = {"/sys/function", "/api/sys/function"})
public class FunctionController extends BaseController {

    @Autowired
    private IFunctionService functionService;

    @RequestMapping(value = "/fetchResource", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData fetchResource(final HttpServletRequest request, final Long functionId, final Integer isExit,
                                      final Resource resource, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize) {
        IRequest requestContext = createRequestContext(request);
        Function function = new Function();
        function.setFunctionId(functionId);
        return new ResponseData(functionService.selectExitResourcesByFunction(requestContext, function, resource, page, pagesize));
    }

    @RequestMapping(value = "/fetchNotResource", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData fetchNotResource(final HttpServletRequest request, final Long functionId, final Integer isExit,
                                         final Resource resource, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize) {
        IRequest requestContext = createRequestContext(request);
        Function function = new Function();
        if (StringUtils.isEmpty(resource.getUrl())) {
            resource.setUrl(null);
        }
        if (StringUtils.isEmpty(resource.getName())) {
            resource.setName(null);
        }
        if (StringUtils.isEmpty(resource.getType())) {
            resource.setType(null);
        }
        function.setFunctionId(functionId);
        return new ResponseData(functionService.selectNotExitResourcesByFunction(requestContext, function, resource, page, pagesize));
    }

    @RequestMapping(value = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    public Object queryMenuTree(HttpServletRequest request) {
        return functionService.selectRoleFunctions(createRequestContext(request));
    }

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData query(final Function function, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Criteria criteria = new Criteria(function);
        criteria.where(new WhereField(Function.FIELD_FUNCTION_CODE, Comparison.LIKE), Function.FIELD_FUNCTION_NAME, Function.FIELD_PARENT_FUNCTION_NAME, Function.FIELD_PARENT_FUNCTION_ID, new WhereField(Function.FIELD_MODULE_CODE, Comparison.LIKE), Function.FIELD_RESOURCE_ID);
        return new ResponseData(functionService.selectOptions(requestContext, function, criteria, page, pagesize));
    }

    @PostMapping(value = "/remove")
    public ResponseData remove(@RequestBody final List<Function> functions, final BindingResult result,
                               final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        functionService.batchDelete(requestContext, functions);
        return new ResponseData();
    }

    @PostMapping(value = "/submit")
    public ResponseData submit(@RequestBody final List<Function> functions, final BindingResult result,
                               final HttpServletRequest request) {
        getValidator().validate(functions, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(functionService.batchUpdate(requestContext, functions));
    }

    @PostMapping(value = "/updateFunctionResource")
    public ResponseData updateFunctionResource(final HttpServletRequest request, @RequestBody final Function function) {
        IRequest requestContext = createRequestContext(request);
        ResponseData data = new ResponseData();
        functionService.updateFunctionResources(requestContext, function, function.getResources());
        data.setSuccess(true);
        return data;
    }
}

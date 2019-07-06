package com.hand.hap.function.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.function.dto.Resource;
import com.hand.hap.function.service.IResourceService;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对资源的操作.
 *
 * @author xiawang.liu@hand-china.com
 * @author zhizheng.yang@hand-china.com
 * @author njq.niu@hand-china.com
 */

@RestController
@RequestMapping(value = {"/sys/resource", "/api/sys/resource"})
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData queryResource(HttpServletRequest request, Resource resource,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = createRequestContext(request);
        Criteria criteria = new Criteria(resource);
        criteria.select(Resource.FIELD_RESOURCE_ID, Resource.FIELD_URL, Resource.FIELD_TYPE, Resource.FIELD_NAME, Resource.FIELD_LOGIN_REQUIRE, Resource.FIELD_ACCESS_CHECK, Resource.FIELD_DESCRIPTION);
        criteria.selectExtensionAttribute();
        return new ResponseData(resourceService.selectOptions(requestContext, resource, criteria, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Resource> resources,
                                       BindingResult result) throws BaseException {
        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(resourceService.batchUpdate(requestContext, resources));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Resource> resources)
            throws BaseException {
        IRequest requestContext = createRequestContext(request);
        resourceService.batchDelete(requestContext, resources);
        return new ResponseData();
    }

}

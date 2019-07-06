package com.hand.hap.account.controllers;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.RoleExt;
import com.hand.hap.account.service.IRoleService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色控制器.
 *
 * @author shengyang.zhou@hand-china.com
 * @date 2016/6/9
 */
@RestController
@RequestMapping(value = {"/sys/role", "/api/sys/role"})
public class RoleController extends BaseController {

    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;

    @RequestMapping(value = "/queryRoleNotUserRole", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData queryRoleNotUserRoles(HttpServletRequest request, RoleExt roleExt,
                                              @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.selectRoleNotUserRoles(requestContext, roleExt, page, pagesize));
    }

    @RequestMapping(value = "/query", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData queryRoles(Role role, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.selectRoles(requestContext, role, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitRole(@RequestBody List<Role> roles, BindingResult result,
                                   HttpServletRequest request) throws BaseException {
        getValidator().validate(roles, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.batchUpdate(requestContext, roles));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeRole(HttpServletRequest request, @RequestBody List<Role> roles) throws BaseException {
        roleService.batchDelete(roles);
        return new ResponseData();
    }
}

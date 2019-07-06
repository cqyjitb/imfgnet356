package com.hand.hap.account.controllers;

import com.hand.hap.account.dto.UserRole;
import com.hand.hap.account.service.IUserRoleService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户角色分配控制器.
 *
 * @author xiawang.liu@hand-china.com
 */

@RestController
public class UserRoleController extends BaseController {

    @Autowired
    private IUserRoleService userRoleService;

    @PostMapping(value = "/sys/userrole/submit")
    public ResponseData submitUserRole(HttpServletRequest request, @RequestBody List<UserRole> userRoles,
                                       BindingResult result) throws BaseException {
        getValidator().validate(userRoles, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(userRoleService.batchUpdate(requestContext, userRoles));
    }

}

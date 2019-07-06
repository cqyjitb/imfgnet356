package com.hand.hap.hr.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.EmployeeAssign;
import com.hand.hap.hr.service.IEmployeeAssignService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对员工岗位分配的操作.
 *
 * @author yuliao.chen@hand-china.com
 */
@RestController
@RequestMapping(value = {"/hr/employee/assign", "/api/hr/employee/assign"})
public class EmployeeAssignController extends BaseController {
    @Autowired
    private IEmployeeAssignService employeeAssignService;


    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData queryByEmployeeId(final Long employeeId, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(employeeAssignService.selectByEmployeeId(requestContext, employeeId, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitAssign(HttpServletRequest request, @RequestBody List<EmployeeAssign> assignList) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(employeeAssignService.batchUpdate(requestContext, assignList));
    }


}

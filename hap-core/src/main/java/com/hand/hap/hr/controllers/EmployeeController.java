package com.hand.hap.hr.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.UserAndRoles;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.query.Comparison;
import com.hand.hap.mybatis.common.query.WhereField;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 对员工的操作.
 *
 * @author yuliao.chen@hand-china.com
 */
@RestController
@RequestMapping(value = {"/hr/employee", "/api/hr/employee"})
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData query(final Employee employee, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Criteria criteria = new Criteria(employee);
        criteria.where(new WhereField(Employee.FIELD_EMPLOYEE_CODE, Comparison.LIKE), Employee.FIELD_EMPLOYEE_ID, Employee.FIELD_NAME);
        return new ResponseData(employeeService.selectOptions(requestContext, employee, criteria, page, pagesize));
    }

    @RequestMapping(value = "/queryAll", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData queryAll(final Employee employee, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(employeeService.queryAll(requestContext, employee, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submit(@RequestBody final List<Employee> employees, final BindingResult result,
                               final HttpServletRequest request) {
        getValidator().validate(employees, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(employeeService.submit(requestContext, employees));
    }

    @PostMapping(value = "/create_user")
    public ResponseData createUserByEmployee(@RequestBody UserAndRoles userAndRoles, HttpServletRequest request) {
        IRequest request1 = createRequestContext(request);
        employeeService.createUserByEmployee(request1, userAndRoles);
        return new ResponseData();
    }

    @PostMapping(value = "/update_user")
    public ResponseData updateUser(@RequestBody UserAndRoles userAndRoles, HttpServletRequest request, @RequestParam(required = false) String userName) {
        IRequest irequest = createRequestContext(request);
        employeeService.updateUser(irequest, userAndRoles, userName);
        return new ResponseData();
    }

    @PostMapping(value = "/queryExistingUser")
    public ResponseData queryUsers(HttpServletRequest request, @ModelAttribute User user) {
        IRequest iRequest = createRequestContext(request);
        return new ResponseData(employeeService.selectExistingUser(iRequest, user));
    }

    @RequestMapping(value = "/hr/employee/queryByCode",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData queryByCode(HttpServletRequest request){
        String code = request.getParameter("code");
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = this.employeeService.queryByCode(code);
        list.add(employee);
        return new ResponseData(list);
    }

}

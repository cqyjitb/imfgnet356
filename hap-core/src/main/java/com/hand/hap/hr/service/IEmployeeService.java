package com.hand.hap.hr.service;

import com.hand.hap.account.dto.User;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.UserAndRoles;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 员工服务接口.
 *
 * @author yuliao.chen@hand-china.com
 */
public interface IEmployeeService extends IBaseService<Employee>, ProxySelf<IEmployeeService> {
    /**
     * 条件查询员工.
     *
     * @param requestContext IRequest
     * @param employee       员工
     * @param page           页码
     * @param pageSize       页数
     * @return 员工列表
     */
    List<Employee> queryAll(IRequest requestContext, Employee employee, int page, int pageSize);

    /**
     * 通过员工创建用户.
     *
     * @param request IRequest
     * @param roles   用户角色
     */
    void createUserByEmployee(IRequest request, UserAndRoles roles);

    /**
     * 通过员工编码查找上级主管.
     *
     * @param employeeCode 员工编码
     * @return 员工列表
     */
    List<Employee> getDirector(String employeeCode);

    /**
     * 通过员工编码查找部门领导.
     *
     * @param employeeCode 员工编码
     * @return 员工列表
     */
    List<Employee> getDeptDirector(String employeeCode);

    /**
     * 添加为用户功能更新用户角色.
     *
     * @param request      IRequest
     * @param userAndRoles 用户角色
     * @param userName     用户名
     */
    void updateUser(IRequest request, UserAndRoles userAndRoles, String userName);

    /**
     * 员工批量更新.
     *
     * @param request IRequest
     * @param list    员工列表
     * @return 员工列表
     */
    List<Employee> submit(IRequest request, List<Employee> list);

    /**
     * 通过员工编码查询员工信息.
     *
     * @param employeeCode 员工编码
     * @return 员工
     */
    Employee queryInfoByCode(String employeeCode);

    /**
     * 通过岗位编码查询员工.
     *
     * @param positionCode 岗位编码
     * @return 员工列表
     */
    List<Employee> selectByPostionCode(String positionCode);

    /**
     * 添加为用户功能 查询已经创建过的用户信息.
     *
     * @param request IRequest
     * @param user    用户
     * @return 用户信息
     */
    List<User> selectExistingUser(IRequest request, User user);

    Employee queryByCode(String code);
}

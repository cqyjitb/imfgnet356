package com.hand.hap.hr.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.EmployeeAssign;
import com.hand.hap.hr.mapper.EmployeeAssignMapper;
import com.hand.hap.hr.service.IEmployeeAssignService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工岗位分配服务接口实现.
 *
 * @author yuliao.chen@hand-china.com
 */
@Service
public class EmployeeAssignServiceImpl extends BaseServiceImpl<EmployeeAssign> implements IEmployeeAssignService {

    @Autowired
    private EmployeeAssignMapper employeeAssignMapper;

    @Override
    public List<EmployeeAssign> selectByEmployeeId(IRequest requestContext, Long employeeId, int page, int pagesize) {
        return employeeAssignMapper.selectByEmployeeId(employeeId);
    }

    @Override
    public Long getCompanyByEmployeeId(Long employeeId) {
        return employeeAssignMapper.getCompanyByEmployeeId(employeeId);
    }

}

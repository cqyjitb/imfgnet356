package com.hand.hap.hr.mapper;

import com.hand.hap.hr.dto.HrOrgUnit;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

/**
 * 部门组织Mapper.
 *
 * @author jialong.zuo@hand-china.com
 */
public interface OrgUnitMapper extends Mapper<HrOrgUnit> {
    /**
     * 条件查询部门组织.
     *
     * @param hrOrgUnit 部门组织
     * @return 部门组织列表
     */
    List<HrOrgUnit> selectUnit(HrOrgUnit hrOrgUnit);
}

package com.hand.hap.hr.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.HrOrgUnit;
import com.hand.hap.hr.service.IOrgUnitService;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门组织服务接口实现.
 *
 * @author jialong.zuo@hand-china.com
 * @date 2016/9/16.
 */
@Service
public class OrgUnitServiceImpl extends BaseServiceImpl<HrOrgUnit> implements IOrgUnitService {

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HrOrgUnit> batchUpdate(IRequest request, List<HrOrgUnit> list) {
        Criteria criteria = new Criteria();
        criteria.update(HrOrgUnit.FIELD_NAME, HrOrgUnit.FIELD_UNIT_CATEGORY,
                HrOrgUnit.FIELD_UNIT_TYPE, HrOrgUnit.FIELD_PARENT_ID, HrOrgUnit.FIELD_DESCRIPTION,
                HrOrgUnit.FIELD_MANAGER_POSITION, HrOrgUnit.FIELD_COMPANY_ID, HrOrgUnit.FIELD_ENABLED_FLAG);
        criteria.updateExtensionAttribute();
        for (HrOrgUnit unit : list) {
            if (unit.get__status().equalsIgnoreCase(DTOStatus.UPDATE)) {
                self().updateByPrimaryKeyOptions(request, unit, criteria);
            } else {
                self().insertSelective(request, unit);
            }
        }
        return list;
    }
}

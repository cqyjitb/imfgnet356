package com.hand.hap.hr.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Position;
import com.hand.hap.hr.mapper.EmployeeAssignMapper;
import com.hand.hap.hr.service.IPositionService;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位服务接口实现.
 *
 * @author hailin.xu@hand-china.com
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<Position> implements IPositionService {
    @Autowired
    private IMessagePublisher messagePublisher;

    @Autowired
    private EmployeeAssignMapper employeeAssignMapper;

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Position> batchUpdate(IRequest request, List<Position> list) {
        Criteria criteria = new Criteria();
        criteria.update(Position.FIELD_NAME, Position.FIELD_DESCRIPTION, Position.FIELD_UNIT_ID, Position.FIELD_PARENT_POSITION_ID);
        criteria.updateExtensionAttribute();
        for (Position position : list) {
            if (position.get__status().equalsIgnoreCase(DTOStatus.UPDATE)) {
                self().updateByPrimaryKeyOptions(request, position, criteria);
            } else {
                self().insertSelective(request, position);
            }
            messagePublisher.publish("position.change", position);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Position position) {
        int ret = super.deleteByPrimaryKey(position);
        employeeAssignMapper.deleteByPositionId(position.getPositionId());
        return ret;
    }
}

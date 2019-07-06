package yj.kanb.wipdateclass.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.wipdateclass.dto.DateClass;
import yj.kanb.wipdateclass.mapper.DateClassMapper;
import yj.kanb.wipdateclass.service.IDateClassService;

import java.util.List;

@Service
@Transactional
public class DateClassServiceImpl extends BaseServiceImpl<DateClass> implements IDateClassService{
    @Autowired
    private DateClassMapper dateClassMapper;

    @Override
    public List<DateClass> selectFromPage(String className) {
        return dateClassMapper.queryDateClass(className);
    }

    @Override
    public void insertDateClass(IRequest requestCtx, DateClass dateClass) {
        dateClassMapper.insertDateClass(dateClass);
    }

    @Override
    public void updateDateClass(IRequest requestCtx, DateClass dateClass) {
        dateClassMapper.updateDateClass(dateClass);
    }
}

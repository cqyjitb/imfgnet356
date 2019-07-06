package yj.core.shiftstime.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.shiftstime.dto.Shiftstime;
import yj.core.shiftstime.mapper.ShiftstimeMapper;
import yj.core.shiftstime.service.IShiftstimeService;

@Service
@Transactional
public class ShiftstimeServiceImpl extends BaseServiceImpl<Shiftstime> implements IShiftstimeService {
    @Autowired
    private ShiftstimeMapper shiftstimeMapper;

}
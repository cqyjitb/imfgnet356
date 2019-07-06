package yj.core.zrwkhead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zrwkhead.dto.Zrwkhead;
import yj.core.zrwkhead.mapper.ZrwkheadMapper;
import yj.core.zrwkhead.service.IZrwkheadService;

@Service
@Transactional
public class ZrwkheadServiceImpl extends BaseServiceImpl<Zrwkhead> implements IZrwkheadService{
    @Autowired
    private ZrwkheadMapper zrwkheadMapper;
    @Override
    public String selectMaxNo(String curdat) {
        return zrwkheadMapper.selectMaxNo(curdat);
    }

    @Override
    public int insertHead(Zrwkhead zrwkhead) {
        return zrwkheadMapper.insertHead(zrwkhead);
    }
}
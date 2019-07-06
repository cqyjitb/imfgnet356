package yj.core.zudhead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zudhead.dto.Zudhead;
import yj.core.zudhead.mapper.ZudheadMapper;
import yj.core.zudhead.service.IZudheadService;

@Service
@Transactional
public class ZudheadServiceImpl extends BaseServiceImpl<Zudhead> implements IZudheadService{
    @Autowired
    private ZudheadMapper zudheadMapper;
    @Override
    public String selectMaxNo(String curdat) {
        return zudheadMapper.selectMaxNo(curdat);
    }

    @Override
    public int insertHead(Zudhead zudhead) {
        return zudheadMapper.insertHead(zudhead);
    }

    @Override
    public Zudhead selectByZudnum(String zudnum) {
        return zudheadMapper.selectByZudnum(zudnum);
    }

    @Override
    public int updateHead(Zudhead head) {
        return zudheadMapper.updateHead(head);
    }
}
package yj.core.resb.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.resb.dto.Resb;
import yj.core.resb.mapper.ResbMapper;
import yj.core.resb.service.IResbService;

import java.util.List;

@Service
@Transactional
public class ResbServiceImpl extends BaseServiceImpl<Resb> implements IResbService{
    @Autowired
    private ResbMapper resbMapper;
    @Override
    public int isExit(String rsnum, String rspos) {
        return resbMapper.isExit(rsnum,rspos);
    }

    @Override
    public int updateByRsnum(Resb resb) {
        return resbMapper.updateByRsnum(resb);
    }

    @Override
    public int insertByRsnum(Resb resb) {
        return resbMapper.insertByRsnum(resb);
    }

    @Override
    public List<Resb> selectByRsnum(String rsnum) {
        return resbMapper.selectByRsnum(rsnum);
    }

    @Override
    public int deleteByAufpl(String aufpl) {
        return resbMapper.deleteByAufpl(aufpl);
    }

    @Override
    public List<Resb> selectByRsnumForzpjsx(String rsnum) {
        return resbMapper.selectByRsnumForzpjsx(rsnum);
    }
}
package yj.core.afvc.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.mapper.AfvcMapper;
import yj.core.afvc.service.IAfvcService;

import java.util.List;

@Service
@Transactional
public class AfvcServiceImpl
        extends BaseServiceImpl<Afvc>
        implements IAfvcService
{
    @Autowired
    AfvcMapper afvcMapper;

    public int updateSync(Afvc afvc)
    {
        return afvcMapper.updateSync(afvc);
    }

    public int insertSync(Afvc afvc)
    {
        return afvcMapper.insertSync(afvc);
    }

    public int selectReturnNum(Afvc afvc)
    {
        return afvcMapper.selectReturnNum(afvc);
    }

    public List<Afvc> selectByAufpl(String aufpl)
    {
        return afvcMapper.selectByAufpl(aufpl);
    }

    @Override
    public List<Afvc> selectByAufnr(String aufnr) {
        return afvcMapper.selectByAufnr(aufnr);
    }

    @Override
    public int deleteByAufpl(String aufpl) {
        return afvcMapper.deleteByAufpl(aufpl);
    }

    @Override
    public Afvc selectByZpgdbar(String zpgdbar) {
        return afvcMapper.selectByZpgdbar(zpgdbar);
    }

    @Override
    public List<Afvc> selectByArbpl(String arbpl) {
        return afvcMapper.selectByArbpl(arbpl);
    }
}

package yj.core.afko.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.afko.dto.Afko;
import yj.core.afko.mapper.AfkoMapper;
import yj.core.afko.service.IAfkoService;

import java.util.List;

@Service
@Transactional
public class AfkoServiceImpl
        extends BaseServiceImpl<Afko>
        implements IAfkoService
{
    @Autowired
    AfkoMapper afkoMapper;

    @Override
    public int updateSync(Afko afko)
    {
        return afkoMapper.updateSync(afko);
    }

    @Override
    public int insertSync(Afko afko)
    {
        return afkoMapper.insertSync(afko);
    }

    @Override
    public int selectReturnNum(Afko afko)
    {
        return afkoMapper.selectReturnNum(afko);
    }

    @Override
    public int selectExist(Afko afko)
    {
        return afkoMapper.selectExist(afko);
    }

    @Override
    public List<Afko> selectZuhe(IRequest iRequest, Afko paramAfko, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return afkoMapper.selectZuhe(paramAfko);
    }

    @Override
    public Afko selectByAufnr(String aufnr) {
        return afkoMapper.selectByAufnr(aufnr);
    }

    @Override
    public List<Afko> selectYaZu(IRequest iRequest, Afko paramAfko, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return afkoMapper.selectYaZu(paramAfko);
    }

    @Override
    public List<Afko> selectJiJa(IRequest iRequest, Afko paramAfko, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return afkoMapper.selectJija(paramAfko);
    }
}

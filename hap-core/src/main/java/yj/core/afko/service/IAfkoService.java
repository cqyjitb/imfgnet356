package yj.core.afko.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.afko.dto.Afko;

import java.util.List;

public abstract interface IAfkoService
        extends IBaseService<Afko>, ProxySelf<IAfkoService>
{
    public abstract int updateSync(Afko paramAfko);

    public abstract int insertSync(Afko paramAfko);

    public abstract int selectReturnNum(Afko paramAfko);

    public abstract int selectExist(Afko paramAfko);

    public List<Afko> selectZuhe(IRequest iRequest, Afko paramAfko, int page, int pagesize);

    public List<Afko> selectYaZu(IRequest iRequest, Afko paramAfko, int page, int pagesize);

    public List<Afko> selectJiJa(IRequest iRequest, Afko paramAfko, int page, int pagesize);
    Afko selectByAufnr(String aufnr);
}

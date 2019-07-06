package yj.core.afvc.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.afvc.dto.Afvc;

import java.util.List;

public abstract interface IAfvcService
        extends IBaseService<Afvc>, ProxySelf<IAfvcService>
{
     int updateSync(Afvc paramAfvc);

     int insertSync(Afvc paramAfvc);

     int selectReturnNum(Afvc paramAfvc);

     List<Afvc> selectByAufpl(String paramString);

     List<Afvc> selectByAufnr(String aufnr);

     /**
      *  根据 aufpl 删除数据 917110140
      * @param aufpl
      * @return
      */
     int deleteByAufpl(String aufpl);

     /**
      *  根据派工单 查询工作中心 917110140
      * @param zpgdbar
      * @return
      */
     Afvc selectByZpgdbar(String zpgdbar);

     /**
      *  根据工作中心编号 获取描述 917110140
      * @param arbpl
      * @return
      */
     List<Afvc> selectByArbpl(String arbpl);
}

package yj.core.cardh.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.cardh.dto.Cardh;

import java.util.List;

public  interface ICardhService
        extends IBaseService<Cardh>, ProxySelf<ICardhService>
{
     List<Cardh> selectByAufnr(String paramString);

     int insertCardh(List<Cardh> paramList);

     List<Cardh> queryAfterSort(IRequest paramIRequest, Cardh paramCardh, int paramInt1, int paramInt2);

     List<Cardh> queryZuheAfterSort(IRequest paramIRequest, Cardh paramCardh, int paramInt1, int paramInt2);
     int deleteCardh(List<Cardh> paramList);

     int updateCardhStatus(List<Cardh> paramList);

     String selectByAufnrMxno(String aufnr);

     Cardh selectByBarcode(String zpgdbar);

     Cardh selectByZxhbar(String aufnr, String zxhnum);

     int updateDatforLsvor(String zpgdbar);

     int updateDatforFsvor(String zpgdbar);

     int updateForLgort(Cardh cardh);

     /**
      *  c#打印客户端查询流转卡专用 917110140
      * @param dto
      * @return
      */
     List<Cardh> queryAfterSortForClientPrint(Cardh dto);

}

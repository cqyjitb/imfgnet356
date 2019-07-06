package yj.core.cardt.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;

import java.util.List;

public  interface ICardtService
        extends IBaseService<Cardt>, ProxySelf<ICardtService>
{
     int insertCardt(List<Cardt> paramList);

     int deleteCardt(List<Cardt> paramList);

     List<Cardt> selectByZpgdbar(String paramString);

     List<Cardt> queryAfterSort(IRequest paramIRequest, Cardt paramCardt, int paramInt1, int paramInt2);

     List<Cardt> selectBybarcode(String zpgdbar);

     VCardt selectViewVCardt(VCardt vCardt);

     Cardt selectByBarcodeAndKtsch(Cardt dto);

     int updateCardtConfirmed(Cardt dto);

     List<Cardt> selectByZpgdbarAsc(String zpgdbar);

     List<Cardt> selectByZpgdbarDesc(String zpgdbar);

     /**
      *  根据工序流转卡号 工序号 查询工序记录 917110140
      * @param zpgdbar
      * @param vornr
      * @return
      */
     Cardt selectByZpgdbarAndVornr(String zpgdbar, String vornr);

     /**
      *  根据标准文本码，生产订单 取工序号 917110140
      * @param aufnr
      * @param ktsch
      * @return
      */
     Cardt selectByAufnrAndKtsch(String aufnr, String ktsch);
}

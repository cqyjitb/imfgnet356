package yj.core.xhcard.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_xhcard.dto.XhcardReturnResult;
import yj.core.xhcard.dto.Xhcard;

import java.util.List;

public  interface IXhcardService
        extends IBaseService<Xhcard>, ProxySelf<IXhcardService>
{
     int insertXhcard(List<Xhcard> paramList);

     int deleteXhcard(List<Xhcard> paramList);

     List<Xhcard> queryAfterSort(IRequest paramIRequest, Xhcard paramXhcard, int paramInt1, int paramInt2);

     XhcardReturnResult returnSyncXhcard(Xhcard paramXhcard);

     Xhcard selectByXhAndAufnr(String zxhbar);

     Xhcard selectByBacode(String zxhbar);

     QueryXhcardReturnResult selectByBacodeFromSap(String zxhbar, String matnr, String lgort, String qtype);

     int updateXhcard(List<Xhcard> list);

     List<Xhcard> selectByMatnrAndLgortSort(String matnr, String lgort);

     String selectMaxCharg(String matnr, String charg);

     int updateXhcardFromSap(List<Xhcard> list);

     int updateZsxwc(Xhcard xhcard);

     List<Xhcard> selectXbkc(Xhcard dto);

     /**
      *  根据生产订单 工厂 箱号 查询XHCARD记录 917110140
      * @param werks
      * @param aufnr
      * @param zxhnum
      * @return
      */
     Xhcard selectForZxhbar(String werks, String aufnr, String zxhnum);

     /**
      *  根据物料编码 库存地点 查询箱号状态为7的数据记录 917110140
      * @param matnr
      * @param lgort
      * @return
      */
     List<Xhcard> selectByMatnrAndLgortSortS7(String matnr, String lgort);

     int updateXhcardFromSapSinger(Xhcard xhcard);

     /**
      * 线边库毛坯库存状态查询 918100064
      * @param iRequest
      * @param dto
      * @return
      */
     List<Xhcard> queryXhcard(IRequest iRequest, Xhcard dto);
}

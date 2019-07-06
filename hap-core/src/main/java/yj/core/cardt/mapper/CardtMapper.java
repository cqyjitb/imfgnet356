package yj.core.cardt.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;

import java.util.List;

public  interface CardtMapper
        extends Mapper<Cardt>
{
     int insertCardt(Cardt paramCardt);

     int deleteCardt(Cardt paramCardt);

     List<Cardt> selectByZpgdbar(String paramString);

     List<Cardt> queryAfterSort(Cardt paramCardt);

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
     Cardt selectByZpgdbarAndVornr(@Param("zpgdbar") String zpgdbar, @Param("vornr") String vornr);

     /**
      *  根据标准文本码，生产订单 取工序号 917110140
      * @param aufnr
      * @param ktsch
      * @return
      */
     Cardt selectByAufnrAndKtsch(@Param("aufnr") String aufnr, @Param("ktsch") String ktsch);
}

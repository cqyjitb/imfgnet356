package yj.core.xhcard.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.xhcard.dto.Xhcard;

import java.util.List;

public  interface XhcardMapper
        extends Mapper<Xhcard>
{
     int insertXhcard(Xhcard paramXhcard);

     int deleteXhcard(Xhcard paramXhcard);

     List<Xhcard> queryAfterSort(Xhcard paramXhcard);

     Xhcard selectByXhAndAufnr(String zxhbar);

     Xhcard selectByBacode(String zxhbar);

     int updateXhcard(Xhcard paramXhcard);

     List<Xhcard> selectByMatnrAndLgortSort(@Param("matnr") String matnr, @Param("lgort") String lgort);

     String selectMaxCharg(@Param("matnr") String matnr, @Param("charg") String charg);

     int updateZsxwc(Xhcard xhcard);

     List<Xhcard> selectXbkc(Xhcard xhcard);

     /**
      *  根据生产订单 工厂 箱号 查询XHCARD记录 917110140
      * @param werks
      * @param aufnr
      * @param zxhnum
      * @return
      */
     Xhcard selectForZxhbar(@Param("werks") String werks, @Param("aufnr") String aufnr, @Param("zxhnum") String zxhnum);

     /**
      *  根据物料编码 库存地点 查询箱号状态为7的数据记录 917110140
      * @param matnr
      * @param lgort
      * @return
      */
     List<Xhcard> selectByMatnrAndLgortSortS7(@Param("matnr") String matnr, @Param("lgort") String lgort);

     /**
      *线边库毛坯库存状态查询   918100064
      * @param dto
      * @return
      */
     List<Xhcard> queryXhcard(Xhcard dto);
}

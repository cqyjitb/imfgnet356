package yj.core.zudlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface ZudlistMapper extends Mapper<Zudlist> {
    int insertItem(Zudlist zudlist);

    /**
     * 不合格品审理单1查询 918100064
     * @param dto
     * @return
     */
    List<Zudlist> selectZudlist(Zudlist dto);

    List<Zudlist> selectZudlistTypeBlpcl(Zudlist dto);

    /**
     * 根据物料编码、不良原因查询数据的条数 918100064
     * @param matnr2
     * @param zissuetxt
     * @return
     */
    int selectDftdtl(@Param("matnr2") String matnr2, @Param("zissuetxt") String zissuetxt);

    /**
     *  更新不合格品审理单1 行信息 917110140
     * @param zudlist
     * @return
     */
    int updateItem(Zudlist zudlist);

    /**
     *  根据表单号查询 status = 0 的记录 917110140
     * @param zudnum
     * @return
     */
    List<Zudlist> selectByZudnumForUnprocess(String zudnum);

    /**
     *  根据单号 行号查询行记录 917110140
     * @param zudnum
     * @param item
     * @return
     */
    Zudlist selectByIdAndItem(@Param("zudnum") String zudnum, @Param("item") String item);

    /**
     * 根据生产线查询所有单号记录 918100064
     * @param lineId
     * @return
     */
    List<Zudlist> selectByLineId(@Param("lineId") String lineId, @Param("zudnum") String zudnum);

    List<Zudlist> selectForPassrateReportgf(@Param("lineId") String lineId, @Param("matnr") String matnr, @Param("datestart") String datestart, @Param("dateend") String dateend);

    List<Zudlist> selectForPassrateReportlf(@Param("lineId") String lineId, @Param("matnr") String matnr, @Param("datestart") String datestart, @Param("dateend") String dateend);

    List<Zudlist> selectForBaltuChart(@Param("lineId") String lineId, @Param("matnr") String matnr, @Param("datestart") String datestart, @Param("dateend") String dateend);
}
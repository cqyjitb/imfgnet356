package yj.core.crhd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.crhd.dto.Crhd;

import java.util.List;

public interface CrhdMapper extends Mapper<Crhd> {
    /**
     * 根据工厂、车间条件加载工作中心LOV_ARBPL 918100064
     * @param werks
     * @param veran
     * @param arbpl
     * @return
     */
    List<Crhd> selectByVeran(@Param("werks") String werks, @Param("veran") String veran, @Param("arbpl") String arbpl);

    /**
     *  根据OBJID 查询记录数量 917110140
     * @param objid
     * @return
     */
    int selectnumByObjid(@Param("objid") String objid);

    /**
     *  更新行 917110140
     * @param crhd
     * @return
     */
    int updateRow(Crhd crhd);

    /**
     *  插入新行 917110140
     * @param crhd
     * @return
     */
    int insertRow(Crhd crhd);

    /**
     *  根据工作中心编号，工厂查询记录
     * @param werks
     * @param arbpl
     * @return
     */
    List<Crhd> selecByWerksAndArbpl(@Param("werks") String werks, @Param("arbpl") String arbpl);

    /**
     * 根据工厂、车间以4开头的条件加载工作中心 918100064
     * @param werks
     * @return
     */
    List<Crhd> selectByVeran2(@Param("werks") String werks);
}
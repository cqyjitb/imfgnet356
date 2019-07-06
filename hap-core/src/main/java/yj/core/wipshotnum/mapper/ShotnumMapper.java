package yj.core.wipshotnum.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipshotnum.dto.Shotnum;

import java.util.List;

public interface ShotnumMapper extends Mapper<Shotnum> {
    /**
     * 根据工厂 工作中心 生产日期 班次查询记录 918100064
     * @param werks
     * @param fevor
     * @param shifts
     * @param prdDateAfter
     * @param prdDateBefore
     * @return
     */
    List<Shotnum> selectShotnum(@Param("werks") String werks, @Param("fevor") String fevor, @Param("shifts") String shifts, @Param("arbpl") String arbpl,
                                @Param("prdDateAfter") String prdDateAfter, @Param("prdDateBefore") String prdDateBefore);

    /**
     *  插入新记录 917110140
     * @param shot
     * @return
     */
    int insertRow(Shotnum shot);

    /**
     *  根据 工厂 工作中心 生产日期 班次 查询记录 917110140
     * @param werks
     * @param arbpl
     * @param prd_date
     * @param shifts
     * @return
     */
    List<Shotnum> isExit(@Param("werks") String werks, @Param("arbpl") String arbpl, @Param("prd_date") String prd_date, @Param("shifts") String shifts);

    /**
     *压射号查询 918100064
     * @param dto
     * @return
     */
    List<Shotnum> queryShotnum(Shotnum dto);

    /**
     *压射号修改 918100064
     * @param dto
     */
    void updateShotnum(Shotnum dto);

    /**
     *压射号删除 918100064
     * @param dto
     */
    void deleteShotnum(Shotnum dto);

    /**
     * 根据工厂、日期查询压射号 918100064
     * @param werks
     * @param fevor
     * @param arbpl
     * @param prdDateAfter
     * @param prdDateBefore
     * @return
     */
    List<Shotnum> selectByPrdDate(@Param("werks") String werks, @Param("fevor") String fevor, @Param("arbpl") String arbpl,
                                  @Param("prdDateAfter") String prdDateAfter, @Param("prdDateBefore") String prdDateBefore);
}
package yj.core.fevor.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.dto.Zwipqhz;

import java.util.List;

public interface FevorMapper extends Mapper<Fevor> {
    /**
     *查询4开头的记录 918100064
     * @param fevor
     * @return
     */
    List<Fevor> selectFevor(@Param("fevor") String fevor);

    /**
     *  查询 1 2 3 开头的记录 917110140
     * @param fevor
     * @return
     */
    List<Fevor> selectFevor2(@Param("fevor") String fevor);

    Fevor selectByfevorSinger(@Param("fevor") String fevor);

    List<Zwipqhz> selectZwipqhz(@Param("unitCode") String unitCode, @Param("lineId") String lineId);
}

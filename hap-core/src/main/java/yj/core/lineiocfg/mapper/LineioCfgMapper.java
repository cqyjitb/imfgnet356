package yj.core.lineiocfg.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.lineiocfg.dto.LineioCfg;

import java.util.List;

public interface LineioCfgMapper extends Mapper<LineioCfg> {
    List<LineioCfg> selectinoutcfg(@Param("line_id") String line_id, @Param("werks") String werks);
    List<LineioCfg> selectinoutcfgforzbjsx(@Param("line_id") String line_id, @Param("werks") String werks);
    LineioCfg selectBYLineVornr(@Param("line_id") String line_id, @Param("werks") String werks, @Param("vornr") String vornr);
}
package yj.core.shiftstime.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.shiftstime.dto.Shiftstime;

public interface ShiftstimeMapper extends Mapper<Shiftstime> {
    /**
     * 根据班次查询班次时间表 918100064
     * @param shifts
     * @return
     */
    Shiftstime selectByShift(@Param("shifts") String shifts);
}
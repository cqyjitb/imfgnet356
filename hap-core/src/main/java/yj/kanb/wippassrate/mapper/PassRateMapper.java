package yj.kanb.wippassrate.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.wippassrate.dto.PassRate;

import java.util.List;

public interface PassRateMapper extends Mapper<PassRate> {
    /**
     * 合格率报表的添加 918100064
     * @param dto
     */
    void insertPassRate(PassRate dto);

    /**
     * 合格率报表的修改 918100064
     * @param dto
     */
    void updatePassRate(PassRate dto);

    /**
     * 合格率报表数据查询 918100064
     * @param dto
     * @return
     */
    List<PassRate> queryPassRate(PassRate dto);

    /**
     * 合格率报表按月汇总查询 918100064
     * @param dto
     * @return
     */
    List<PassRate> queryByMouth(PassRate dto);
}

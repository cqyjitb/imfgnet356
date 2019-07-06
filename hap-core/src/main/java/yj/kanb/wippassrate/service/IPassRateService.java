package yj.kanb.wippassrate.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.wippassrate.dto.PassRate;

import java.util.List;

public interface IPassRateService extends IBaseService<PassRate>,ProxySelf<IPassRateService> {
    /**
     * 合格率报表数据任务处理 918100064
     * @param dto
     */
    void insertPassRate(PassRate dto);

    /**
     * 合格率报表数据任务处理修改 918100064
     * @param dto
     */
    void updatePassRate(PassRate dto);

    /**
     * 合格率报表查询请求 918100064
     * @param dto
     * @return
     */
    List<PassRate> queryPassRate(PassRate dto);

    /**
     * 合格率按月汇总查询 918100064
     * @param dto
     * @return
     */
    List<PassRate> queryByMouth(PassRate dto);
}

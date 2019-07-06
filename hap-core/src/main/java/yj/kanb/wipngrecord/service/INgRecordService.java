package yj.kanb.wipngrecord.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.wipngrecord.dto.NgRecord;

import java.util.List;

public interface INgRecordService extends IBaseService<NgRecord>,ProxySelf<INgRecordService> {
    /**
     * 不合格品记录数据任务处理 918100064
     * @param dto
     */
    void insertNgRecord(NgRecord dto);

    /**
     * 不合格品记录表修改 918100064
     * @param dto
     */
    void updateNgRecord(NgRecord dto);
    /**
     * 不合格品记录表查询 918100064
     * @param dto
     */
    List<NgRecord> selectNgRecord(NgRecord dto);

    /**
     * 终检&过程&GP12&走样不良柏拉图数据查询
     * @param dto
     * @return
     */
    List<NgRecord> queryNgRecord(NgRecord dto);

    /**
     * 根据缺陷代码汇总 918100064
     * @param dto
     * @return
     */
    Integer queryByQty(NgRecord dto);
    /**
     * 终检&过程&GP12&走样不良柏拉图的汇总 918100064
     * @param dto
     * @return
     */
    Integer queryNgRecordByQty(NgRecord dto);
}

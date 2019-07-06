package yj.kanb.wipngrecord.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.wipngrecord.dto.NgRecord;

import java.util.List;

public interface NgRecordMapper extends Mapper<NgRecord> {
    /**
     * 不合格品记录表添加 918100064
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

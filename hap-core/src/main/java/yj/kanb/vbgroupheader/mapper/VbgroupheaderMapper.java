package yj.kanb.vbgroupheader.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;

import java.util.List;

public interface VbgroupheaderMapper extends Mapper<Vbgroupheader> {
    /**
     * 表vb_group_h数据添加 918100064
     * @param dto
     */
    void insertVbGroupH(Vbgroupheader dto);

    /**
     * 看板显示组查询 918100064
     * @param dto
     * @return
     */
    List<Vbgroupheader> queryGroupH(Vbgroupheader dto);

    /**
     * 根据设备ID删除看板显示组 918100064
     * @param eqId
     */
    void deleteGroupH(@Param("eqId") String eqId);
    /**
     * 表vb_group_h数据修改 918100064
     * @param dto
     */
    void updateVbGroupH(Vbgroupheader dto);
}

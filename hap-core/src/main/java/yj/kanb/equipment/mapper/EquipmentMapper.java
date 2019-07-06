package yj.kanb.equipment.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.equipment.dto.Equipment;

import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {
    List<Equipment> selectAllData();
    /**
     * 看板设备表查询 918100064
     * @param dto
     * @return
     */
    List<Equipment> selectEquipment(Equipment dto);

    /**
     * 看板设备表删除 918100064
     * @param dto
     */
    void deleteEquipment(Equipment dto);

    /**
     * 根据MAC地址查询 918100064
     * @param mac
     * @return
     */
    Equipment selectByMac(@Param("mac") String mac);

    /**
     * 看板设备表添加 918100064
     * @param dto
     */
    void insertEquipment(Equipment dto);

    /**
     * 看板设备表修改 918100064
     * @param dto
     */
    void updateEquipment(Equipment dto);
}

package yj.kanb.equipment.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.equipment.dto.Equipment;

import java.util.List;

public interface IEquipmentService  extends IBaseService<Equipment>,ProxySelf<IEquipmentService> {

    List<Equipment> selectAllData();
    /**
     * 看板设备维护查询 918100064
     * @param dto
     * @return
     */
    List<Equipment> queryEquipment(Equipment dto);

    /**
     * 看板设备维护删除 918100064
     * @param dto
     */
    void deleteEquipment(List<Equipment> dto);

    /**
     * 看板设备维护添加 918100064
     * @param dto
     * @param userId
     * @return
     */
    String insertEquipment(List<Equipment> dto, String userId);

    /**
     * 看板设备维护修改 918100064
     * @param dto
     * @param userId
     */
    void updateEquipment(List<Equipment> dto, String userId);
}

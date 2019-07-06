package yj.kanb.equipment.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.mapper.EquipmentMapper;
import yj.kanb.equipment.service.IEquipmentService;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;
import yj.kanb.vbgroupheader.mapper.VbgroupheaderMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements IEquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private VbgroupheaderMapper vbgroupheaderMapper;

    @Override
    public List<Equipment> selectAllData() {
        return equipmentMapper.selectAllData();
    }
    @Override
    public List<Equipment> queryEquipment(Equipment dto) {
        return equipmentMapper.selectEquipment(dto);
    }

    @Override
    public void deleteEquipment(List<Equipment> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                equipmentMapper.deleteEquipment(dto.get(i));
                vbgroupheaderMapper.deleteGroupH(dto.get(i).getEqId());
            }
        }
    }

    @Override
    public String insertEquipment(List<Equipment> dto, String userId) {
        if(dto.size() > 0){
            Equipment result = equipmentMapper.selectByMac(dto.get(0).getMac());
            if(result == null){
                Equipment equipment = dto.get(0);
                String eqId = UUID.randomUUID().toString();
                equipment.setEqId(eqId);
                equipment.setCreatedBy(Long.valueOf(userId));
                equipment.setCreationDate(new Date());
                equipmentMapper.insertEquipment(equipment);
                Vbgroupheader vbgrouph = new Vbgroupheader();
                String vbgroupId = UUID.randomUUID().toString();
                vbgrouph.setVbgroupId(vbgroupId);
                vbgrouph.setBukrs(equipment.getBukrs());
                vbgrouph.setWerks(equipment.getWerks());
                vbgrouph.setEqId(eqId);
                vbgrouph.setMac(equipment.getMac());
                vbgrouph.setVbgroupName(equipment.getVbgroupName());
                vbgrouph.setCreatedBy(Long.valueOf(userId));
                vbgrouph.setCreationDate(new Date());
                vbgrouph.setVbgroupNameEn(equipment.getVbgroupNameEn());
                vbgroupheaderMapper.insertVbGroupH(vbgrouph);
            }else{
                return "该MAC地址已存在，请重新输入！";
            }
        }
        return null;
    }

    @Override
    public void updateEquipment(List<Equipment> dto, String userId) {
        if(dto.size() > 0){
            Equipment equipment = dto.get(0);
            equipment.setLastUpdatedBy(Long.valueOf(userId));
            equipment.setLastUpdateDate(new Date());
            equipmentMapper.updateEquipment(equipment);
            Vbgroupheader vbgrouph = new Vbgroupheader();
            vbgrouph.setEqId(equipment.getEqId());
            vbgrouph.setVbgroupName(equipment.getVbgroupName());
            vbgrouph.setVbgroupNameEn(equipment.getVbgroupNameEn());
            vbgrouph.setLastUpdatedBy(Long.valueOf(userId));
            vbgrouph.setLastUpdateDate(new Date());
            vbgroupheaderMapper.updateVbGroupH(vbgrouph);
        }
    }
}

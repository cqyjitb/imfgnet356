package yj.core.wiparea.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiparea.dto.Area;
import yj.core.wiparea.mapper.AreaMapper;
import yj.core.wiparea.service.IAreaService;

import java.util.List;

@Service
@Transactional
public class AreaServiceImpl extends BaseServiceImpl<Area> implements IAreaService{
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public int updateAreaBatch(List<Area> list) {
        int sum = 0;
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                sum = sum + areaMapper.updateAreaBatch(list.get(i));
            }
        }
        return sum;
    }

    @Override
    public List<Area> selectByUserName(String userName) {
        return areaMapper.selectByUserName(userName);
    }

    @Override
    public int updateArea(Area area) {
        return areaMapper.updateAreaBatch(area);
    }

    @Override
    public int insertArea(Area area) {
        return areaMapper.insertArea(area);
    }
}
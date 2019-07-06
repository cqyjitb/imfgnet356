package yj.core.wiparea.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiparea.dto.Area;

import java.util.List;

public interface IAreaService extends IBaseService<Area>, ProxySelf<IAreaService> {
    int updateAreaBatch(List<Area> list);
    int updateArea(Area area);
    int insertArea(Area area);
    List<Area> selectByUserName(String userName);
}
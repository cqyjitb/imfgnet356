package yj.core.wiparea.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wiparea.dto.Area;

import java.util.List;

public interface AreaMapper extends Mapper<Area> {

   int updateAreaBatch(Area area);
   int insertArea(Area area);
   List<Area> selectByUserName(String userName);
}
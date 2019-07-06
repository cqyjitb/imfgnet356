package yj.core.qppdrcd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qppdrcd.dto.Qppdrcd;

import java.util.List;

public interface QppdrcdMapper extends Mapper<Qppdrcd> {

    int insertPdRow(Qppdrcd qppdrcd);

    List<Qppdrcd> queryAll(@Param("werks") String werks, @Param("pddatbefore") String pddatbefore, @Param("pddatafter") String pddatafter, @Param("fevor") String fevor);
}
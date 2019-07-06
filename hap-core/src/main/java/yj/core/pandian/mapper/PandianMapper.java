package yj.core.pandian.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.pandian.dto.Pandian;

import java.util.List;

public interface PandianMapper extends Mapper<Pandian> {
    int insertpdlog(Pandian pd);
    List<Pandian> queryAlllog(Pandian pd);
}
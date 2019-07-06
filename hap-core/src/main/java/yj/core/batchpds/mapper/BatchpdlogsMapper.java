package yj.core.batchpds.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.batchpds.dto.Batchpdlogs;

import java.util.List;

public interface BatchpdlogsMapper extends Mapper<Batchpdlogs> {
    List<Batchpdlogs> queryAll();
}
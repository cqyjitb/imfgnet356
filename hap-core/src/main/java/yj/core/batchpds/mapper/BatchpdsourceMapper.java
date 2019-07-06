package yj.core.batchpds.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.batchpds.dto.Batchpdsource;

import java.util.List;

public interface BatchpdsourceMapper extends Mapper<Batchpdsource> {
    int updateflag(Batchpdsource bt);

    List<Batchpdsource> querybyflag();
}
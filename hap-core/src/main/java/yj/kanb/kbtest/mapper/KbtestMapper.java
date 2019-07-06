package yj.kanb.kbtest.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.kbtest.dto.Kbtest;

public interface KbtestMapper extends Mapper<Kbtest> {
    int insertNewData(String id);
}

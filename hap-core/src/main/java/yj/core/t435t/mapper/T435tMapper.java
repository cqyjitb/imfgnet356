package yj.core.t435t.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.t435t.dto.T435t;

public interface T435tMapper extends Mapper<T435t> {
    int isExit(String vlsch);

    int updateByVlsch(T435t dto);

    int insertByVlsch(T435t dto);
}
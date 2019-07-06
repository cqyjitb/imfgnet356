package yj.core.wiptrasfer.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wiptrasfer.dto.Trasfer;

public interface TrasferMapper extends Mapper<Trasfer> {
    int insertTrasfer(Trasfer trasfer);
}
package yj.core.qpcd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qpcd.dto.Qpcd;

import java.util.List;

public interface QpcdMapper extends Mapper<Qpcd> {
    Qpcd selectForLov(@Param("code") String code);

    public List<Qpcd> selectAllForBlcl();

    public List<Qpcd> selectAllForJjqj();
}
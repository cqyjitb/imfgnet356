package yj.core.ztbc0002.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.ztbc0002.dto.Ztbc0002;

public interface Ztbc0002Mapper extends Mapper<Ztbc0002> {

    Ztbc0002 selectByTpcode(@Param("ztpbar") String ztpbar, @Param("werks") String werks);
}
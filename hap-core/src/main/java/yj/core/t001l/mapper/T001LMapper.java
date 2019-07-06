package yj.core.t001l.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.t001l.dto.T001L;

import java.util.List;

public interface T001LMapper extends Mapper<T001L> {
    /**
     * 以9开头的仓库LOV_LGORT  918100064
     * @param werks
     * @param lgort
     * @return
     */
    List<T001L> selectLgort(@Param("werks") String werks, @Param("lgort") String lgort);
}
package yj.core.pmimptt.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.pmimptt.dto.Imptt;

import java.util.List;


public interface ImpttMapper extends Mapper<Imptt> {
    /**
     * 根据字段checkcycle和checkreq查询数据 918100064
     * @param checkcycle
     * @param checkreq
     * @return
     */
    List<Imptt> selectCheckcycleAndCheckreq(@Param("checkcycle") String checkcycle, @Param("checkreq") String checkreq);
}
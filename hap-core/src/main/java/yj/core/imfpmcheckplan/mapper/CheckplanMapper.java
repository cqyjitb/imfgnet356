package yj.core.imfpmcheckplan.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.imfpmcheckplan.dto.Checkplan;

import java.util.List;

public interface CheckplanMapper extends Mapper<Checkplan> {
    /**
     * 根据字段checkcycle和checkreq查询数据 918100064
     * @param checkcycle
     * @param checkrequest
     * @return
     */
    List<Checkplan> selectCheckcycleAndCheckreq(@Param("checkcycle") String checkcycle, @Param("checkrequest") String checkrequest);
}
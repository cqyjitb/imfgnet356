package yj.core.imfpmcheckdoc.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.imfpmcheckdoc.dto.Checkdoc;

import java.util.List;

public interface CheckdocMapper extends Mapper<Checkdoc> {
    /**
     * 根据字段checkcycle和checkreq查询数据 918100064
     * @param checkcycle
     * @param checkreq
     * @return
     */
    List<Checkdoc> selectCheckcycleAndCheckreq(@Param("checkcycle") String checkcycle, @Param("checkreq") String checkreq);

}
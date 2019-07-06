package yj.core.assembonline.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.assembonline.dto.AssembliesOnline;

public interface AssembliesOnlineMapper extends Mapper<AssembliesOnline> {

    int insertNewData(AssembliesOnline assembliesOnline);

    AssembliesOnline selectByZxhbar(@Param("zxhbar") String zxhbar);

    int deleteData(@Param("assid") String assid);
}
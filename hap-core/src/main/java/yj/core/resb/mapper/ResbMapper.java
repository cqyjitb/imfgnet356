package yj.core.resb.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.resb.dto.Resb;

import java.util.List;

public interface ResbMapper extends Mapper<Resb> {
    int isExit(@Param("rsnum") String rsnum, @Param("rspos") String rspos);
    int updateByRsnum(Resb resb);
    int insertByRsnum(Resb resb);
    List<Resb> selectByRsnum(String rsnum);
    List<Resb> selectByRsnumForzpjsx(String rsnum);
    /**
     *  根据 aufpl 删除预留记录 917110140
     * @param aufpl
     * @return
     */
    int deleteByAufpl(String aufpl);
}
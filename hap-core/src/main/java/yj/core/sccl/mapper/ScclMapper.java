package yj.core.sccl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.sccl.dto.Sccl;

public interface ScclMapper extends Mapper<Sccl> {
    Sccl selectByMatnr(@Param("matnr") String matnr, @Param("werks") String werks);
    int isExit(@Param("matnr") String matnr, @Param("werks") String werks);
    int updateSccl(Sccl dto);
    int insertSccl(Sccl dto);
}
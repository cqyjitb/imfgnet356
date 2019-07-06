package yj.core.logdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.logdtl.dto.Logdtl;

public interface LogdtlMapper extends Mapper<Logdtl> {

    /**
     *  插入新的日志明细 917110140
     * @param logdtl
     * @return
     */
    int insertNewDtl(Logdtl logdtl);
}
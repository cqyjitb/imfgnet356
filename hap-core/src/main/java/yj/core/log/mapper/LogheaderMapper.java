package yj.core.log.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.log.dto.Logheader;

public interface LogheaderMapper extends Mapper<Logheader> {
    /**
     *  插入新的日志头记录 917110140
     * @param logheader
     * @return
     */
    int insertNewHeader(Logheader logheader);

}
package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.Log;

public interface LogMapper extends Mapper<Log> {

    int queryLogById(Long id);//根据ID查询confirmation_log

    int insertLog(Log log);//插入一条数据到confirmation_log

    Log queryLogByRefidbg(Long refId);

    Log queryLogByRefidcx(Long refId);

    int updateMsgty(Log log);
}
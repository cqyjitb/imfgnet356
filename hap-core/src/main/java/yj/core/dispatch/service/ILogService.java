package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.Log;

public interface ILogService extends IBaseService<Log>, ProxySelf<ILogService> {

    int queryLogById(Long id);//根据ID查询confirmation_log

    int insertLog(Log log);//插入一条数据到confirmation_log
}
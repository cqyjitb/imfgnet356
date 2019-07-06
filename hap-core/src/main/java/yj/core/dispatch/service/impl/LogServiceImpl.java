package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.service.ILogService;

@Service
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService {

    @Autowired
    LogMapper logMapper;

    @Override

    //根据ID查询confirmation_log
    public int queryLogById(Long id){
        return logMapper.queryLogById(id);
    };

    //插入一条数据到confirmation_log
    public int insertLog(Log log){
        return logMapper.insertLog(log);
    };
}
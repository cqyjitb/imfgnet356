package yj.core.log.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.log.dto.Logheader;
import yj.core.log.mapper.LogheaderMapper;
import yj.core.log.service.ILogheaderService;

@Service
@Transactional
public class LogheaderServiceImpl extends BaseServiceImpl<Logheader> implements ILogheaderService{

    @Autowired
    private LogheaderMapper logheaderMapper;
    @Override
    public int insertNewHeader(Logheader logheader) {
        return logheaderMapper.insertNewHeader(logheader);
    }
}
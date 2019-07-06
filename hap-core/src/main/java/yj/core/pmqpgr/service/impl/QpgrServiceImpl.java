package yj.core.pmqpgr.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pmqpgr.dto.Qpgr;
import yj.core.pmqpgr.mapper.QpgrMapper;
import yj.core.pmqpgr.service.IQpgrService;

@Service
@Transactional
public class QpgrServiceImpl extends BaseServiceImpl<Qpgr> implements IQpgrService {

    @Autowired
    private QpgrMapper qpgrMapper;

}
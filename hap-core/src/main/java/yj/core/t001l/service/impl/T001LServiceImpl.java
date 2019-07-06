package yj.core.t001l.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.t001l.dto.T001L;
import yj.core.t001l.mapper.T001LMapper;
import yj.core.t001l.service.IT001LService;

@Service
@Transactional
public class T001LServiceImpl extends BaseServiceImpl<T001L> implements IT001LService {
    @Autowired
    private T001LMapper t001LMapper;


}
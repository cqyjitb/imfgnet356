package yj.core.imfpmcheckplan.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmcheckplan.dto.Checkplan;
import yj.core.imfpmcheckplan.mapper.CheckplanMapper;
import yj.core.imfpmcheckplan.service.ICheckplanService;

@Service
@Transactional
public class CheckplanServiceImpl extends BaseServiceImpl<Checkplan> implements ICheckplanService {

    @Autowired
    private CheckplanMapper checkplanMapper;

}
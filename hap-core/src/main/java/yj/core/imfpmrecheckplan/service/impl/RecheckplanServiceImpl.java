package yj.core.imfpmrecheckplan.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmrecheckplan.dto.Recheckplan;
import yj.core.imfpmrecheckplan.mapper.RecheckplanMapper;
import yj.core.imfpmrecheckplan.service.IRecheckplanService;

@Service
@Transactional
public class RecheckplanServiceImpl extends BaseServiceImpl<Recheckplan> implements IRecheckplanService {

    @Autowired
    private RecheckplanMapper recheckplanMapper;



}
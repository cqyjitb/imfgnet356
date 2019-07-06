package yj.core.pmimptt.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pmimptt.dto.Imptt;
import yj.core.pmimptt.mapper.ImpttMapper;
import yj.core.pmimptt.service.IImpttService;

@Service
@Transactional
public class ImpttServiceImpl extends BaseServiceImpl<Imptt> implements IImpttService {

    @Autowired
    private ImpttMapper impttMapper;

}
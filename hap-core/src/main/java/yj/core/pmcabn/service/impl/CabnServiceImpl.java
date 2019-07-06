package yj.core.pmcabn.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pmcabn.dto.Cabn;
import yj.core.pmcabn.mapper.CabnMapper;
import yj.core.pmcabn.service.ICabnService;

@Service
@Transactional
public class CabnServiceImpl extends BaseServiceImpl<Cabn> implements ICabnService {

    @Autowired
    private CabnMapper cabnMapper;

}
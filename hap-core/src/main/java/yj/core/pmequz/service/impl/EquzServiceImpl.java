package yj.core.pmequz.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pmequz.dto.Equz;
import yj.core.pmequz.mapper.EquzMapper;
import yj.core.pmequz.service.IEquzService;

@Service
@Transactional
public class EquzServiceImpl extends BaseServiceImpl<Equz> implements IEquzService {

    @Autowired
    private EquzMapper equzMapper;

}
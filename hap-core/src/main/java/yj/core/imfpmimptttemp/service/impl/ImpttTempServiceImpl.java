package yj.core.imfpmimptttemp.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmimptttemp.dto.ImpttTemp;
import yj.core.imfpmimptttemp.mapper.ImpttTempMapper;
import yj.core.imfpmimptttemp.service.IImpttTempService;

@Service
@Transactional
public class ImpttTempServiceImpl extends BaseServiceImpl<ImpttTemp> implements IImpttTempService {

    @Autowired
    private ImpttTempMapper impttTempMapper;



}
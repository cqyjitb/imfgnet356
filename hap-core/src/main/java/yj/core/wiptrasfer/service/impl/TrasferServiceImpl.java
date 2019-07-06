package yj.core.wiptrasfer.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiptrasfer.dto.Trasfer;
import yj.core.wiptrasfer.mapper.TrasferMapper;
import yj.core.wiptrasfer.service.ITrasferService;

@Service
@Transactional
public class TrasferServiceImpl extends BaseServiceImpl<Trasfer> implements ITrasferService {
    @Autowired
    private TrasferMapper trasferMapper;
    @Override
    public int insertTrasfer(Trasfer trasfer) {
        return trasferMapper.insertTrasfer(trasfer);
    }
}
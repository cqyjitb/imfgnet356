package yj.core.cust.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cust.dto.Cust;
import yj.core.cust.mapper.CustMapper;
import yj.core.cust.service.ICustService;

import java.util.List;

@Service
@Transactional
public class CustServiceImpl extends BaseServiceImpl<Cust> implements ICustService{
    @Autowired
    private CustMapper custMapper;
    @Override
    public List<Cust> selectForLov(String kunnr) {
        return custMapper.selectForLov(kunnr);
    }

    @Override
    public Cust selectByKunnr(String kunnr) {
        return custMapper.selectByKunnr(kunnr);
    }
}
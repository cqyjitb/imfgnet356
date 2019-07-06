package yj.core.fevor.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.dto.Zwipqhz;
import yj.core.fevor.mapper.FevorMapper;
import yj.core.fevor.service.IFevorService;

import java.util.List;

@Service
@Transactional
public class FevorServiceImpl extends BaseServiceImpl<Fevor> implements IFevorService {
    @Autowired
    FevorMapper fevorMapper;

    @Override
    public List<Fevor> selectFevor2(String fevor) {
        return fevorMapper.selectFevor2(fevor);
    }

    @Override
    public Fevor selectByfevorSinger(String fevor) {
        return fevorMapper.selectByfevorSinger(fevor);
    }

    @Override
    public List<Zwipqhz> queryZwipqhz(IRequest requestContext, String unitCode, String lineId) {
        return fevorMapper.selectZwipqhz(unitCode,lineId);
    }
}

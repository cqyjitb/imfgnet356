package yj.core.t435t.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.t435t.dto.T435t;
import yj.core.t435t.mapper.T435tMapper;
import yj.core.t435t.service.IT435tService;

@Service
@Transactional
public class T435tServiceImpl extends BaseServiceImpl<T435t> implements IT435tService{

    @Autowired
    private T435tMapper t435tMapper;
    @Override
    public int isExit(String vlsch) {
        return t435tMapper.isExit(vlsch);
    }

    @Override
    public int insertByVlsch(T435t dto) {
        return t435tMapper.insertByVlsch(dto);
    }

    @Override
    public int updateByVlsch(T435t dto) {
        return t435tMapper.updateByVlsch(dto);
    }
}
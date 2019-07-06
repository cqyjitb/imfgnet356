package yj.core.qjcode.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.mapper.QjcodeMapper;
import yj.core.qjcode.service.IQjcodeService;

import java.util.List;

@Service
@Transactional
public class QjcodeServiceImpl extends BaseServiceImpl<Qjcode> implements IQjcodeService{
    @Autowired
    private  QjcodeMapper qjcodeMapper;
    @Override
    public List<Qjcode> selectcode() {
        return qjcodeMapper.selectcode();
    }

    @Override
    public Qjcode selectById(int id) {
        return qjcodeMapper.selectById(id);
    }
}
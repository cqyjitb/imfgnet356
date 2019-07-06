package yj.core.pandiantcode.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pandiantcode.dto.Pandiantcode;
import yj.core.pandiantcode.mapper.PandiantcodeMapper;
import yj.core.pandiantcode.service.IPandiantcodeService;

@Service
@Transactional
public class PandiantcodeServiceImpl extends BaseServiceImpl<Pandiantcode> implements IPandiantcodeService{

    @Autowired
    private PandiantcodeMapper pandiantcodeMapper;
    @Override
    public Pandiantcode checkTjcode(String username) {
        return pandiantcodeMapper.checkTjcode(username);
    }

    @Override
    public Pandiantcode checkGzcode(String username) {
        return pandiantcodeMapper.checkGzcode(username);
    }
}
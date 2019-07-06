package yj.core.pandian.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pandian.dto.Pandiantmp;
import yj.core.pandian.mapper.PandiantmpMapper;
import yj.core.pandian.service.IPandiantmpService;

@Service
@Transactional
public class PandiantmpServiceImpl extends BaseServiceImpl<Pandiantmp> implements IPandiantmpService {
    @Autowired
    private PandiantmpMapper pandiantmpMapper;
    @Override
    public int insertNewRow(Pandiantmp pdtmp) {
        return pandiantmpMapper.insertNewRow(pdtmp);
    }
}
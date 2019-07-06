package yj.core.assembonline.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.assembonline.dto.AssembliesOnline;
import yj.core.assembonline.mapper.AssembliesOnlineMapper;
import yj.core.assembonline.service.IAssembliesOnlineService;

@Service
@Transactional
public class AssembliesOnlineServiceImpl extends BaseServiceImpl<AssembliesOnline> implements IAssembliesOnlineService {

    @Autowired
    private AssembliesOnlineMapper assembliesOnlineMapper;
    @Override
    public int insertNewData(AssembliesOnline assembliesOnline) {
        return assembliesOnlineMapper.insertNewData(assembliesOnline);
    }

    @Override
    public AssembliesOnline selectByZxhbar(String zxhbar) {
        return assembliesOnlineMapper.selectByZxhbar(zxhbar);
    }

    @Override
    public int deleteData(String assid) {
        return assembliesOnlineMapper.deleteData(assid);
    }
}
package yj.core.batchpds.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.batchpds.dto.Batchpdsource;
import yj.core.batchpds.mapper.BatchpdsourceMapper;
import yj.core.batchpds.service.IBatchpdsourceService;

import java.util.List;

@Service
@Transactional
public class BatchpdsourceServiceImpl extends BaseServiceImpl<Batchpdsource> implements IBatchpdsourceService {
    @Autowired
    private BatchpdsourceMapper batchpdsourceMapper;
    @Override
    public int updateflag(Batchpdsource bt) {
        return batchpdsourceMapper.updateflag(bt);
    }

    @Override
    public List<Batchpdsource> querybyflag(IRequest requestContext, Batchpdsource dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return batchpdsourceMapper.querybyflag();
    }
}
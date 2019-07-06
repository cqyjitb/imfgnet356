package yj.core.batchpds.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.batchpds.dto.Batchpdlogs;
import yj.core.batchpds.mapper.BatchpdlogsMapper;
import yj.core.batchpds.service.IBatchpdlogsService;

import java.util.List;

@Service
@Transactional
public class BatchpdlogsServiceImpl extends BaseServiceImpl<Batchpdlogs> implements IBatchpdlogsService{
    @Autowired
    BatchpdlogsMapper batchpdlogsMapper;
    @Override
    public List<Batchpdlogs> queryAll(IRequest request, Batchpdlogs dto, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return batchpdlogsMapper.queryAll();
    }
}
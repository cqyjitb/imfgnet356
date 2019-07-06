package yj.core.zudlog.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.mapper.ZudlogMapper;
import yj.core.zudlog.service.IZudlogService;

import java.util.List;

@Service
@Transactional
public class ZudlogServiceImpl extends BaseServiceImpl<Zudlog> implements IZudlogService {
    @Autowired
    private ZudlogMapper zudlogMapper;
    @Override
    public int insertLog(Zudlog zudlog) {
        return zudlogMapper.insertLog(zudlog);
    }

    @Override
    public List<Zudlog> selectFromPage(IRequest requestContext, Zudlog dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return zudlogMapper.selectFromPage(dto);
    }
}
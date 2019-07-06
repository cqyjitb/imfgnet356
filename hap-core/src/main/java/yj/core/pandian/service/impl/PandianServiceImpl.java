package yj.core.pandian.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.pandian.dto.Pandian;
import yj.core.pandian.mapper.PandianMapper;
import yj.core.pandian.service.IPandianService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PandianServiceImpl extends BaseServiceImpl<Pandian> implements IPandianService {
    @Autowired
    PandianMapper pandianMapper;
    @Override
    public int insertpdlog(Pandian pd) {
        return pandianMapper.insertpdlog(pd);
    }

    @Override
    public List<Pandian> queryAlllog(IRequest iRequest, Pandian pd, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Pandian> returnlist = new ArrayList<Pandian>();
        returnlist = pandianMapper.queryAlllog(pd);
        if (returnlist.size() > 0){
            for (int i=0;i<returnlist.size();i++){
                returnlist.get(i).setCymng(returnlist.get(i).getQrmng() - returnlist.get(i).getGamng());//计算差异盘点数量
                returnlist.get(i).setZpgdbarstr(returnlist.get(i).getZpgdbar().substring(0,18));
            }
        }
        return returnlist;
    }
}
package yj.core.qppdrcd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.qppdrcd.dto.Qppdrcd;
import yj.core.qppdrcd.mapper.QppdrcdMapper;
import yj.core.qppdrcd.service.IQppdrcdService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QppdrcdServiceImpl extends BaseServiceImpl<Qppdrcd> implements IQppdrcdService{
    @Autowired
    private QppdrcdMapper qppdrcdMapper;
    @Override
    public int insertPdRow(Qppdrcd qppdrcd) {
        return qppdrcdMapper.insertPdRow(qppdrcd);
    }

    @Override
    public List<Qppdrcd> queryAll(String werks, String pddatbefore, String pddatafter, String fevor,int page,int pageSize,IRequest iRequest) {
        PageHelper.startPage(page, pageSize);
        List<Qppdrcd> list = new ArrayList<>();
         list = qppdrcdMapper.queryAll(werks,pddatbefore,pddatafter,fevor);
        return list;
    }
}
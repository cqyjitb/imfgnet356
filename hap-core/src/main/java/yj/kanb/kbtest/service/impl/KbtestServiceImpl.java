package yj.kanb.kbtest.service.impl;


import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.kbtest.dto.Kbtest;
import yj.kanb.kbtest.mapper.KbtestMapper;
import yj.kanb.kbtest.service.IKbtestService;

@Service
@Transactional
public class KbtestServiceImpl extends BaseServiceImpl<Kbtest> implements IKbtestService {
    @Autowired
    private KbtestMapper kbtestMapper;
    @Override
    public int insertNewData(String id) {
        //DataSourceHolder.setDataSources(DataSourceEnum.mySqlDataSource.getKey());
        return kbtestMapper.insertNewData(id);
    }
}

package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.dispatch.service.IResultService;

@Service
@Transactional
public class ResultServiceImpl extends BaseServiceImpl<Result> implements IResultService{

    @Autowired
    ResultMapper resultMapper;

    @Override

    //根据ID查询数据confirmation_result
    public int queryResultById(Long id){
        return resultMapper.queryResultById(id);
    };

    //插入一条数据到confirmation_result
    public int insertResult(Result result){
        return resultMapper.insertResult(result);
    };

}
package com.hand.hap.api.logs.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.api.logs.service.IApiInvokeRecordService;
import com.hand.hap.core.IRequest;
import com.hand.hap.api.logs.mapper.ApiInvokeRecordDetailsMapper;
import com.hand.hap.api.logs.mapper.ApiInvokeRecordMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.api.logs.dto.ApiInvokeRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 访问记录Service - 实现类.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/9/25.
 */
@Service
public class ApiInvokeRecordServiceImpl extends BaseServiceImpl<ApiInvokeRecord> implements IApiInvokeRecordService {

    @Autowired
    private ApiInvokeRecordMapper apiInvokeRecordMapper;

    @Autowired
    private ApiInvokeRecordDetailsMapper apiInvokeRecordDetailsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertApiInvokeRecord(ApiInvokeRecord apiInvokeRecord) {
        apiInvokeRecordMapper.insertSelective(apiInvokeRecord);
        apiInvokeRecord.getApiInvokeRecordDetails().setRecordId(apiInvokeRecord.getRecordId());
        apiInvokeRecordDetailsMapper.insertSelective(apiInvokeRecord.getApiInvokeRecordDetails());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<ApiInvokeRecord> selectById(Long recordId) {
        return apiInvokeRecordMapper.selectById(recordId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<ApiInvokeRecord> selectList(IRequest request, ApiInvokeRecord condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return apiInvokeRecordMapper.selectList(condition);
    }
}
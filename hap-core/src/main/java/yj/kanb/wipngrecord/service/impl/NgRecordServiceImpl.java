package yj.kanb.wipngrecord.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.wipngrecord.dto.NgRecord;
import yj.kanb.wipngrecord.mapper.NgRecordMapper;
import yj.kanb.wipngrecord.service.INgRecordService;

import java.util.List;

@Service
@Transactional
public class NgRecordServiceImpl extends BaseServiceImpl<NgRecord> implements INgRecordService {
    @Autowired
    private NgRecordMapper ngRecordMapper;

    @Override
    public void insertNgRecord(NgRecord dto) {
        ngRecordMapper.insertNgRecord(dto);
    }

    @Override
    public void updateNgRecord(NgRecord dto) {
        ngRecordMapper.updateNgRecord(dto);
    }

    @Override
    public List<NgRecord> selectNgRecord(NgRecord dto) {
        return ngRecordMapper.selectNgRecord(dto);
    }

    @Override
    public List<NgRecord> queryNgRecord(NgRecord dto) {
        return ngRecordMapper.queryNgRecord(dto);
    }

    @Override
    public Integer queryByQty(NgRecord dto) {
        return ngRecordMapper.queryByQty(dto);
    }

    @Override
    public Integer queryNgRecordByQty(NgRecord dto) {
        return ngRecordMapper.queryNgRecordByQty(dto);
    }
}

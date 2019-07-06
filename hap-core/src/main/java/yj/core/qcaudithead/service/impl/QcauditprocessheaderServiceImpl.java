package yj.core.qcaudithead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.qcaudithead.dto.Qcauditprocessheader;
import yj.core.qcaudithead.mapper.QcauditprocessheaderMapper;
import yj.core.qcaudithead.service.IQcauditprocessheaderService;

@Service
@Transactional
public class QcauditprocessheaderServiceImpl extends BaseServiceImpl<Qcauditprocessheader> implements IQcauditprocessheaderService {
    @Autowired
    private QcauditprocessheaderMapper qcauditprocessheaderMapper;
    @Override
    public int updateData(Qcauditprocessheader qcauditprocessheader) {
        Qcauditprocessheader qcauditprocessheadertmp = new Qcauditprocessheader();
        qcauditprocessheadertmp = qcauditprocessheaderMapper.selectById(qcauditprocessheader.getWerks(),qcauditprocessheader.getRecordid());
        int m = 0;
        if (qcauditprocessheadertmp != null){
            m = qcauditprocessheaderMapper.updateData(qcauditprocessheader);
        }else{
            m = qcauditprocessheaderMapper.insertData(qcauditprocessheader);
        }
        return m;
    }

    @Override
    public Qcauditprocessheader selectById(String werks, String recordid) {
        return qcauditprocessheaderMapper.selectById(werks,recordid);
    }

    @Override
    public int insertData(Qcauditprocessheader qcauditprocessheader) {
        return qcauditprocessheaderMapper.insertData(qcauditprocessheader);
    }
}
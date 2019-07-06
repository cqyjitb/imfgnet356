package yj.core.outsrgreceipthead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;
import yj.core.outsrgreceipthead.mapper.OutsrgreceiptheadMapper;
import yj.core.outsrgreceipthead.service.IOutsrgreceiptheadService;

import java.util.List;

@Service
@Transactional
public class OutsrgreceiptheadServiceImpl extends BaseServiceImpl<Outsrgreceipthead> implements IOutsrgreceiptheadService {
    @Autowired
    private OutsrgreceiptheadMapper outsrgreceiptheadMapper;
    @Override
    public List<Outsrgreceipthead> selectByMatnrAndLifnrDesc(String matnr, String lifnr,String status) {
        return outsrgreceiptheadMapper.selectByMatnrAndLifnrDesc(matnr,lifnr,status);
    }

    @Override
    public int insertNewRow(Outsrgreceipthead outsrgreceipthead) {
        return outsrgreceiptheadMapper.insertNewRow(outsrgreceipthead);
    }

    @Override
    public int updateOutsrgreceipthead(Outsrgreceipthead outsrgreceipthead) {
        return outsrgreceiptheadMapper.updateOutsrgreceipthead(outsrgreceipthead);
    }

    @Override
    public List<Outsrgreceipthead> selectAllDesc() {
        return outsrgreceiptheadMapper.selectAllDesc();
    }

    @Override
    public String getMaxNo() {
        return outsrgreceiptheadMapper.getMaxNo();
    }
}
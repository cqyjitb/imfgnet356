package yj.core.outsrgissuehead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgissuehead.mapper.OutsrgissueheadMapper;
import yj.core.outsrgissuehead.service.IOutsrgissueheadService;

import java.util.List;

@Service
@Transactional
public class OutsrgissueheadServiceImpl extends BaseServiceImpl<Outsrgissuehead> implements IOutsrgissueheadService {
    @Autowired
    private OutsrgissueheadMapper outsrgissueheadMapper;
    @Override
    public List<Outsrgissuehead> selectByMatnrAndLifnrDesc(String matnr, String lifnr) {
        return outsrgissueheadMapper.selectByMatnrAndLifnrDesc(matnr,lifnr);
    }

    @Override
    public int insertNewRow(Outsrgissuehead outsrgissuehead) {
        return outsrgissueheadMapper.insertNewRow(outsrgissuehead);
    }

    @Override
    public int updateOutsrgissueHead(Outsrgissuehead outsrgissuehead) {
        return outsrgissueheadMapper.updateOutsrgissueHead(outsrgissuehead);
    }

    @Override
    public Outsrgissuehead selectByIssuenm(String issuenm) {
        return outsrgissueheadMapper.selectByIssuenm(issuenm);
    }

    @Override
    public String selectMaxIssuenm(String issuenm) {
        return outsrgissueheadMapper.selectMaxIssuenm(issuenm);
    }
}
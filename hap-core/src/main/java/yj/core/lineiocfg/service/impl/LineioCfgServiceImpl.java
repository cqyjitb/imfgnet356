package yj.core.lineiocfg.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.lineiocfg.dto.LineioCfg;
import yj.core.lineiocfg.mapper.LineioCfgMapper;
import yj.core.lineiocfg.service.ILineioCfgService;

import java.util.List;

@Service
@Transactional
public class LineioCfgServiceImpl extends BaseServiceImpl<LineioCfg> implements ILineioCfgService {
    @Autowired
    private LineioCfgMapper lineioCfgMapper;
    @Override
    public List<LineioCfg> selectinoutcfg(String line_id,String werks) {
        return lineioCfgMapper.selectinoutcfg(line_id,werks);
    }

    @Override
    public LineioCfg selectBYLineVornr(String line_id, String werks, String vornr) {
        return lineioCfgMapper.selectBYLineVornr(line_id,werks,vornr);
    }

    @Override
    public List<LineioCfg> selectinoutcfgforzbjsx(String line_id, String werks) {
        return lineioCfgMapper.selectinoutcfgforzbjsx(line_id,werks);
    }
}
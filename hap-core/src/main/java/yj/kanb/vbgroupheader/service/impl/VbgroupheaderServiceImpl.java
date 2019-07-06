package yj.kanb.vbgroupheader.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;
import yj.kanb.vbgroupheader.mapper.VbgroupheaderMapper;
import yj.kanb.vbgroupheader.service.IVbgroupheaderService;

import java.util.List;

@Service
@Transactional
public class VbgroupheaderServiceImpl extends BaseServiceImpl<Vbgroupheader> implements IVbgroupheaderService{
    @Autowired
    private VbgroupheaderMapper vbgroupheaderMapper;

    @Override
    public List<Vbgroupheader> queryGroupH(Vbgroupheader dto) {
        return vbgroupheaderMapper.queryGroupH(dto);
    }
}

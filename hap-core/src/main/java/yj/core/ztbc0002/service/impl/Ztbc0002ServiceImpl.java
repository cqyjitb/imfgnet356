package yj.core.ztbc0002.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.mapper.Ztbc0002Mapper;
import yj.core.ztbc0002.service.IZtbc0002Service;

@Service
@Transactional
public class Ztbc0002ServiceImpl extends BaseServiceImpl<Ztbc0002> implements IZtbc0002Service {
    @Autowired
    private Ztbc0002Mapper ztbc0002Mapper;
    @Override
    public Ztbc0002 selectByTpcode(String ztpbar, String werks) {
        return ztbc0002Mapper.selectByTpcode(ztpbar,werks);
    }
}
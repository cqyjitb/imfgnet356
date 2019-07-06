package yj.core.ztpp0023.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.ztpp0023.dto.Ztpp0023;
import yj.core.ztpp0023.mapper.Ztpp0023Mapper;
import yj.core.ztpp0023.service.IZtpp0023Service;

@Service
@Transactional
public class Ztpp0023ServiceImpl extends BaseServiceImpl<Ztpp0023> implements IZtpp0023Service{
    @Autowired
    Ztpp0023Mapper ztpp0023Mapper;
    @Override
    public int insertZtpp0023(Ztpp0023 dto) {
        return ztpp0023Mapper.insert(dto);
    }
}
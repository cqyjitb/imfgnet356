package yj.core.ztbc0018.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.mapper.Ztbc0018Mapper;
import yj.core.ztbc0018.service.IZtbc0018Service;

import java.util.List;

@Service
@Transactional
public class Ztbc0018ServiceImpl extends BaseServiceImpl<Ztbc0018> implements IZtbc0018Service{
    @Autowired
    private Ztbc0018Mapper ztbc0018Mapper;
//    @Override
//    public int insertZtbc0018(Ztbc0018 ztbc0018) {
//        return ztbc0018Mapper.insertZtbc0018(ztbc0018);
//    }


    @Override
    public List<Ztbc0018> selectByZxhbar(String zxhbar) {
        return ztbc0018Mapper.selectByZxhbar(zxhbar);
    }
}
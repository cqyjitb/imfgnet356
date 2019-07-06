package yj.core.arearole.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.arearole.dto.Arearole;
import yj.core.arearole.mapper.ArearoleMapper;
import yj.core.arearole.service.IArearoleService;

import java.util.List;

@Service
@Transactional
public class ArearoleServiceImpl extends BaseServiceImpl<Arearole> implements IArearoleService{
    @Autowired
    private ArearoleMapper arearoleMapper;
    @Override
    public int insertArearole(List<Arearole> list) {
        int sum = 0;
        for (int i=0;i<list.size();i++){
            sum = sum + arearoleMapper.insertArearole(list.get(i));
        }
        return sum;
    }

    @Override
    public int deleteArearole(List<Arearole> list) {
        int sum = 0;
        for (int i=0;i<list.size();i++){
            sum = sum + arearoleMapper.insertArearole(list.get(i));
        }
        return sum;
    }
}
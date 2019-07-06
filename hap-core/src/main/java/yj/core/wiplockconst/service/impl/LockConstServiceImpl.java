package yj.core.wiplockconst.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiplockconst.dto.LockConst;
import yj.core.wiplockconst.mapper.LockConstMapper;
import yj.core.wiplockconst.service.ILockConstService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LockConstServiceImpl extends BaseServiceImpl<LockConst> implements ILockConstService {
    @Autowired
    private LockConstMapper lockConstMapper;

    @Override
    public List<LockConst> selectFrompage(IRequest requestContext, LockConst dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return lockConstMapper.selectLockConst(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<LockConst> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                LockConst lockConst = dto.get(i);
                if(lockConst.getId() == null || lockConst.getId() == ""){
                    String uuid = UUID.randomUUID().toString();
                    lockConst.setId(uuid);
                    lockConst.setCreatedBy(userName);
                    lockConst.setCreationDate(new Date());
                    lockConstMapper.insertLockConst(lockConst);
                }else{
                    lockConst.setLastUpdatedBy(userName);
                    lockConst.setLastUpdatedDate(new Date());
                    lockConstMapper.updateLockConst(lockConst);
                }
            }
        }
        return null;
    }
}
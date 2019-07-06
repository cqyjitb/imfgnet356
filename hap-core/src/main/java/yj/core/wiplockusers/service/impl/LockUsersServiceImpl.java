package yj.core.wiplockusers.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiplockusers.dto.LockUsers;
import yj.core.wiplockusers.mapper.LockUsersMapper;
import yj.core.wiplockusers.service.ILockUsersService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LockUsersServiceImpl extends BaseServiceImpl<LockUsers> implements ILockUsersService {
    @Autowired
    private LockUsersMapper lockUsersMapper;

    @Override
    public List<LockUsers> selectFromPage(IRequest iRequest, LockUsers dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return lockUsersMapper.selectLockUsers(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<LockUsers> dto, String userName) {
        if(dto.size() > 0){
            dto.get(0).setCreatedBy(userName);
            dto.get(0).setCreationDate(new Date());
            lockUsersMapper.insertLockUsers(dto.get(0));
            /*Integer num = lockUsersMapper.isExit(dto.get(0).getWerks(),dto.get(0).getLineId(),dto.get(0).getUserid());
            if(num == 0){
                dto.get(0).setCreatedBy(userName);
                dto.get(0).setCreationDate(new Date());
                lockUsersMapper.insertLockUsers(dto.get(0));
            }else{
                dto.get(0).setLastUpdatedBy(userName);
                dto.get(0).setLastUpdatedDate(new Date());
                lockUsersMapper.updateLockUsers(dto.get(0));
            }*/
        }
        return null;
    }

    @Override
    public void deleteLockUsers(List<LockUsers> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                lockUsersMapper.deleteLockUsers(dto.get(i));
            }
        }
    }
}
package yj.core.wipusers.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipusers.dto.Users;
import yj.core.wipusers.mapper.UsersMapper;
import yj.core.wipusers.service.IUsersService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl extends BaseServiceImpl<Users> implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> selectFromPage(IRequest requestContext, Users dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return usersMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Users> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Users users = dto.get(i);
                if("Y".equals(users.getEnableFlag())){
                    users.setEnableFlag("1");
                }else{
                    users.setEnableFlag("0");
                }
                if (users.getSeqId() != null){
                    users.setLastUpdatedDate(new Date());
                    users.setLastUpdatedBy(userName);
                    usersMapper.updateUsers(users);
                }else{
                    users.setCreationDate(new Date());
                    users.setCreatedBy(userName);
                    usersMapper.insertUsers(users);
                }
            }
        }
        return null;
    }
}

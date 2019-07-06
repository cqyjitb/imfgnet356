package yj.core.wipusers.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipusers.dto.Users;

import java.util.List;

public interface IUsersService extends IBaseService<Users>, ProxySelf<IUsersService> {

    List<Users> selectFromPage(IRequest requestContext, Users dto, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<Users> dto, String userName);
}
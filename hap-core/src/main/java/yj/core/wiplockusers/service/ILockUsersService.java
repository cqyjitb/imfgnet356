package yj.core.wiplockusers.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplockusers.dto.LockUsers;

import java.util.List;

public interface ILockUsersService extends IBaseService<LockUsers>, ProxySelf<ILockUsersService> {
    /**
     * 装箱报错锁程序解锁用户维护查询 918100064
     * @param iRequest
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<LockUsers> selectFromPage(IRequest iRequest, LockUsers dto, int page, int pageSize);

    /**
     * 装箱报错锁程序解锁用户维护添加和修改 918100064
     * @param requestCtx
     * @param dto
     * @param userName
     * @return
     */
    String updateOrInsert(IRequest requestCtx, List<LockUsers> dto, String userName);

    /**
     * 装箱报错锁程序解锁用户维护删除 918100064
     * @param dto
     */
    void deleteLockUsers(List<LockUsers> dto);
}
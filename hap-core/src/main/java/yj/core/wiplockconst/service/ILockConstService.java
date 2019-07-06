package yj.core.wiplockconst.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplockconst.dto.LockConst;

import java.util.List;

public interface ILockConstService extends IBaseService<LockConst>, ProxySelf<ILockConstService> {
    /**
     * 装箱报错锁程序常量维护查询 918100064
     * @param requestContext
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<LockConst> selectFrompage(IRequest requestContext, LockConst dto, int page, int pageSize);

    /**
     * 装箱报错锁程序常量维护添加和修改 918100064
     * @param requestCtx
     * @param dto
     * @param userName
     * @return
     */
    String updateOrInsert(IRequest requestCtx, List<LockConst> dto, String userName);
}
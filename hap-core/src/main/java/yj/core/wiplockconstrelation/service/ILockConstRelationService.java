package yj.core.wiplockconstrelation.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplockconstrelation.dto.LockConstRelation;

import java.util.List;

public interface ILockConstRelationService extends IBaseService<LockConstRelation>, ProxySelf<ILockConstRelationService> {
    /**
     * 装箱报错锁程序常量关系维护查询 918100064
     * @param requestContext
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<LockConstRelation> selectFromPage(IRequest requestContext, LockConstRelation dto, int page, int pageSize);

    /**
     * 装箱报错锁程序常量关系维护添加和修改 918100064
     * @param requestCtx
     * @param dto
     * @param userName
     * @return
     */
    String updateOrInsert(IRequest requestCtx, List<LockConstRelation> dto, String userName);
}
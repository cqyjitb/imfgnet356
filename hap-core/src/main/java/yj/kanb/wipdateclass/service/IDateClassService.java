package yj.kanb.wipdateclass.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.wipdateclass.dto.DateClass;

import java.util.List;

public interface IDateClassService extends IBaseService<DateClass>,ProxySelf<IDateClassService> {
    /**
     * 日期范围及类名维护查询 918100064
     * @param className
     * @return
     */
    List<DateClass> selectFromPage(String className);

    /**
     * 日期范围及类名维护添加 918100064
     * @param requestCtx
     * @param dateClass
     */
    void insertDateClass(IRequest requestCtx, DateClass dateClass);

    /**
     * 日期范围及类名维护修改 918100064
     * @param requestCtx
     * @param dateClass
     */
    void updateDateClass(IRequest requestCtx, DateClass dateClass);
}

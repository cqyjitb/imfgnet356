package yj.core.zudlog.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zudlog.dto.Zudlog;

import java.util.List;

public interface IZudlogService extends IBaseService<Zudlog>, ProxySelf<IZudlogService> {
    /**
     *  插入日志 917110140
     * @param zudlog
     * @return
     */
    int insertLog(Zudlog zudlog);

    /**
     * 查询日志 918100064
     * @param requestContext
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<Zudlog> selectFromPage(IRequest requestContext, Zudlog dto, int page, int pageSize);
}
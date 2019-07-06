package yj.core.imfpmcheckcycle.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.imfpmcheckcycle.dto.Checkcycle;

import java.util.List;

public interface ICheckcycleService extends IBaseService<Checkcycle>, ProxySelf<ICheckcycleService> {

    /**
     * 点检周期的删除 918100064
     * @param dto
     */
    String deleteCheckcycle(List<Checkcycle> dto, String userName);

    /**
     * 点检周期的查询 918100064
     * @param requestContext
     * @param page
     * @param pageSize
     * @return
     */
    List<Checkcycle> selectCheckcycle(IRequest requestContext, Integer id, String checkcycle, int page, int pageSize);

    /**
     * 查询点检周期id的最大值 918100064
     * @return
     */
    int selectMaxCheckcycle();

    /**
     * 点检周期的添加和修改 918100064
     * @param dto
     * @return
     */
    String updateCheckcycle(List<Checkcycle> dto, String userName);
}
package yj.core.imfpmcheckrequest.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.imfpmcheckrequest.dto.Checkrequest;

import java.util.List;

public interface ICheckrequestService extends IBaseService<Checkrequest>, ProxySelf<ICheckrequestService> {

    /**
     * 点检要求的删除 918100064
     * @param dto
     */
    String deleteCheckrequest(List<Checkrequest> dto, String userName);

    /**
     * 点检要求的查询 918100064
     * @param requestContext
     * @param page
     * @param pageSize
     * @return
     */
    List<Checkrequest> selectCheckrequest(IRequest requestContext, Integer id, String checkrequest, int page, int pageSize);

    /**
     * 查询点检要求id的最大值 918100064
     * @return
     */
    int selectMaxCheckrequest();

    /**
     * 点检要求的添加和修改 918100064
     * @param dto
     * @return
     */
    String updateCheckrequest(List<Checkrequest> dto, String userName);
}
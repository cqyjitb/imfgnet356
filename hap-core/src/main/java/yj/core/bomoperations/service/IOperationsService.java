package yj.core.bomoperations.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.bomoperations.dto.Operations;

import java.util.List;

public interface IOperationsService extends IBaseService<Operations>, ProxySelf<IOperationsService> {

    List<Operations> selectFromPage(Integer routingId, IRequest requestContext, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<Operations> dto, String userId);
    String setMessageOperations(List<Operations> dto);
    void deleteOperations(List<Operations> dto);
}
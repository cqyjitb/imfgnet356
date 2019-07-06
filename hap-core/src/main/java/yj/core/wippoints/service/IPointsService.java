package yj.core.wippoints.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wippoints.dto.Points;

import java.util.List;

public interface IPointsService extends IBaseService<Points>, ProxySelf<IPointsService> {

    List<Points> selectFromPage(Points dto, IRequest requestContext, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<Points> dto, String userId);
    String setMessageLines(List<Points> dto);
    String deleteDftdtl(List<Points> dto);
}
package yj.core.bomroutings.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.bomroutings.dto.Routings;

import java.util.List;

public interface IRoutingsService extends IBaseService<Routings>, ProxySelf<IRoutingsService> {
    List<Routings> selectFromPage(Routings dto, IRequest requestContext, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<Routings> dto, String userId);
    void deleteRoutings(List<Routings> dto);
}
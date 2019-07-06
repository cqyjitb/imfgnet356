package yj.core.wipdot.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipdot.dto.Dot;

import java.util.List;

public interface IDotService extends IBaseService<Dot>, ProxySelf<IDotService> {

    List<Dot> selectFromPage(IRequest requestContext, Dot dto, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<Dot> dto, String userName);
    String deleteDot(List<Dot> dto);
}
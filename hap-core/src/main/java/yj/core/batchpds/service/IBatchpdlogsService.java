package yj.core.batchpds.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.batchpds.dto.Batchpdlogs;

import java.util.List;

public interface IBatchpdlogsService extends IBaseService<Batchpdlogs>, ProxySelf<IBatchpdlogsService> {
        List<Batchpdlogs> queryAll(IRequest requestContext, Batchpdlogs dto, int page, int pageSize);
}
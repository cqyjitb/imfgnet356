package yj.core.batchpds.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.batchpds.dto.Batchpdsource;

import java.util.List;

public interface IBatchpdsourceService extends IBaseService<Batchpdsource>, ProxySelf<IBatchpdsourceService> {
    int updateflag(Batchpdsource bt);

    List<Batchpdsource> querybyflag(IRequest requestContext, Batchpdsource dto, int page, int pageSize);
}
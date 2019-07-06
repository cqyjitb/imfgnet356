package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.Result;

public interface IResultService extends IBaseService<Result>, ProxySelf<IResultService> {

    int queryResultById(Long id);//根据ID查询数据confirmation_result

    int insertResult(Result result);//插入一条数据到confirmation_result

}
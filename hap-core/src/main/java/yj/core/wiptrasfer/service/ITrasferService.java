package yj.core.wiptrasfer.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiptrasfer.dto.Trasfer;

public interface ITrasferService extends IBaseService<Trasfer>, ProxySelf<ITrasferService> {
    int insertTrasfer(Trasfer trasfer);
}
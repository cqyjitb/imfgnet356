package yj.core.logdtl.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.logdtl.dto.Logdtl;

public interface ILogdtlService extends IBaseService<Logdtl>, ProxySelf<ILogdtlService> {

    /**
     *  插入新的日志明细 917110140
     * @param logdtl
     * @return
     */
    int insertNewDtl(Logdtl logdtl);
}
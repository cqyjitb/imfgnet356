package yj.core.log.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.log.dto.Logheader;

public interface ILogheaderService extends IBaseService<Logheader>, ProxySelf<ILogheaderService> {

    int insertNewHeader(Logheader logheader);
}
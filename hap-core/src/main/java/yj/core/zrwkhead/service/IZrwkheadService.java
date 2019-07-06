package yj.core.zrwkhead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zrwkhead.dto.Zrwkhead;

public interface IZrwkheadService extends IBaseService<Zrwkhead>, ProxySelf<IZrwkheadService> {
    String selectMaxNo(String curdat);
    int insertHead(Zrwkhead zrwkhead);
}
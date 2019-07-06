package yj.core.cust.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.cust.dto.Cust;

import java.util.List;

public interface ICustService extends IBaseService<Cust>, ProxySelf<ICustService> {
    List<Cust> selectForLov(String kunnr);

    Cust selectByKunnr(String kunnr);
}
package yj.kanb.kbtest.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.kbtest.dto.Kbtest;

public interface IKbtestService extends IBaseService<Kbtest>, ProxySelf<IKbtestService> {
    int insertNewData(String id);
}

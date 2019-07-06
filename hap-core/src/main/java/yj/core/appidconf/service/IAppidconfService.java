package yj.core.appidconf.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.appidconf.dto.Appidconf;

import java.util.List;

public interface IAppidconfService extends IBaseService<Appidconf>, ProxySelf<IAppidconfService> {
    String updateOrInsert(List<Appidconf> list);
    Appidconf selectByAppid(String appid);

}
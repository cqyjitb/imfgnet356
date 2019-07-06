package yj.core.assembonline.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.assembonline.dto.AssembliesOnline;

public interface IAssembliesOnlineService extends IBaseService<AssembliesOnline>, ProxySelf<IAssembliesOnlineService> {

    int insertNewData(AssembliesOnline assembliesOnline);
    AssembliesOnline selectByZxhbar(String zxhbar);
    int deleteData(String assid);
}
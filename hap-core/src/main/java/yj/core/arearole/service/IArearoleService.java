package yj.core.arearole.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.arearole.dto.Arearole;

import java.util.List;

public interface IArearoleService extends IBaseService<Arearole>, ProxySelf<IArearoleService> {

    int insertArearole(List<Arearole> list);

    int deleteArearole(List<Arearole> list);
}
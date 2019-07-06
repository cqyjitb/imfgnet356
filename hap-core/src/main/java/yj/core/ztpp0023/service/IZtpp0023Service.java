package yj.core.ztpp0023.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.ztpp0023.dto.Ztpp0023;

public interface IZtpp0023Service extends IBaseService<Ztpp0023>, ProxySelf<IZtpp0023Service> {
    int insertZtpp0023(Ztpp0023 dto);

}
package yj.core.ztbc0002.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.ztbc0002.dto.Ztbc0002;

public interface IZtbc0002Service extends IBaseService<Ztbc0002>, ProxySelf<IZtbc0002Service> {
    Ztbc0002 selectByTpcode(String ztpbar, String werks);
}
package yj.kanb.vbline.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.vbline.dto.Vbline;

import java.util.List;

public interface IVblineService extends IBaseService<Vbline>,ProxySelf<IVblineService> {

    List<Vbline> selectLineFromHap(String erdat);
}

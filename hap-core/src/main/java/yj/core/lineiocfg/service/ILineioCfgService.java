package yj.core.lineiocfg.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.lineiocfg.dto.LineioCfg;

import java.util.List;

public interface ILineioCfgService extends IBaseService<LineioCfg>, ProxySelf<ILineioCfgService> {
    List<LineioCfg> selectinoutcfg(String line_id, String werks);
    List<LineioCfg> selectinoutcfgforzbjsx(String line_id, String werks);
    LineioCfg selectBYLineVornr(String line_id, String werks, String vornr);
}
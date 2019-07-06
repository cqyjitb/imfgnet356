package yj.kanb.vbgroupheader.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;

import java.util.List;

public interface IVbgroupheaderService extends IBaseService<Vbgroupheader>,ProxySelf<IVbgroupheaderService> {
    /**
     * 看板显示组查询 918100064
     * @param dto
     * @return
     */
    List<Vbgroupheader> queryGroupH(Vbgroupheader dto);
}

package yj.kanb.vbgroupdtl.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;

import java.util.List;

public interface IVbgroupdtlService extends IBaseService<Vbgroupdtl>,ProxySelf<IVbgroupdtlService> {
    /**
     * 看板设备产线维护的添加 918100064
     * @param dto
     * @param userId
     * @return
     */
    String insertGroupDtl(List<Vbgroupdtl> dto, String userId);
    /**
     * 看板设备产线维护的删除 918100064
     * @param dto
     * @return
     */
    String deleteGroupDtl(List<Vbgroupdtl> dto);
}

package yj.kanb.vblinegroupheader.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;

import java.util.List;

public interface IVblinegroupheaderService extends IBaseService<Vblinegroupheader>,ProxySelf<IVblinegroupheaderService> {
    /**
     *  查询车间产线分组
     * @return
     */
    List<Vblinegroupheader> selectAllGroup();
    /**
     * 看板车间产线组头维护查询 918100064
     * @param requestCtx
     * @param dto
     * @return
     */
    List<Vblinegroupheader> queryLineGroupH(IRequest requestCtx, Vblinegroupheader dto);

    /**
     * 看板车间产线组头维护删除 918100064
     * @param requestCtx
     * @param dto
     */
    void deleteLineGroupH(IRequest requestCtx, List<Vblinegroupheader> dto);

    /**
     * 看板车间产线组头维护添加和修改 918100064
     * @param requestCtx
     * @param dto
     * @param userId
     * @return
     */
    String insertOrUpdate(IRequest requestCtx, List<Vblinegroupheader> dto, String userId);

    /**
     * 看板车间产线组头维护判断字段是否为空 918100064
     * @param dto
     * @return
     */
    String setMessage(List<Vblinegroupheader> dto);

    /**
     * 根据显示组ID查询  918100064
     * @param requestCtx
     * @param vbgroupId
     * @return
     */
    List<Vblinegroupheader> selectLineGroupH(IRequest requestCtx, String vbgroupId);
}

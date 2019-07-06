package yj.core.mouldcavity.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.mouldcavity.dto.Mouldcavity;

import java.util.List;

public interface IMouldcavityService extends IBaseService<Mouldcavity>, ProxySelf<IMouldcavityService> {
    /**
     * 产品模具型腔表查询 918100064
     * @param requestCtx
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<Mouldcavity> selectMouldcavity(IRequest requestCtx, Mouldcavity dto, int page, int pageSize);

    /**
     * 产品模具型腔表添加和修改 918100064
     * @param requestCtx
     * @param dto
     * @param userId
     * @return
     */
    String updateMouldcavity(IRequest requestCtx, List<Mouldcavity> dto, String userId);

    /**
     * 产品模具型腔表删除 918100064
     * @param dto
     */
    void deleteMouldcavity(List<Mouldcavity> dto);

    /**
     *  根据物料号 工厂 获取模号和出模数 917110140
     * @param matnr
     * @param werks
     * @return
     */
    List<Mouldcavity> selectByWerksAndMatnr(String matnr, String werks);
}
package yj.kanb.marcres.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.marcres.dto.MarcRes;

import java.util.List;

public interface IMarcResService extends IBaseService<MarcRes>, ProxySelf<IMarcResService> {
    /**
     * 图片的上传及修改
     * @param requestCtx
     * @param dto
     * @param userId
     * @return
     */
    String submitMarcRes(IRequest requestCtx, List<MarcRes> dto, String userId);

    /**
     * 根据公司、工厂及物料编码查询 918100064
     * @param bukrs
     * @param werks
     * @param matnr
     * @return
     */
    MarcRes selectByMatnr(String bukrs, String werks, String matnr);
}
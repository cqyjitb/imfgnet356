package yj.core.wipcurlzk.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipcurlzk.dto.Curlzk;

import java.util.List;

public interface ICurlzkService extends IBaseService<Curlzk>, ProxySelf<ICurlzkService> {
    Curlzk selectById(Long line_id, String classgrp);
    int updateZxhbar(Curlzk dto);
    Curlzk selectById2(Long line_id);
    List<Curlzk> selectAllLinesforZxhbar(Long line_id);
    List<Curlzk> selectByZpgdbar(String zpgdbar);

    /**
     *  根据生产日期 查询当前生产线当前流转卡记录 （车间看板）
     * @param erdat
     * @return
     */
    List<Curlzk> selectAllLinesForKanbByErdat(String erdat);
}
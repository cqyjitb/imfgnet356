package com.hand.hap.api.logs.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.api.logs.dto.ApiInvokeRecord;

import java.util.List;

/**
 * api调用记录service - 接口.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/10/14.
 */

public interface IApiInvokeRecordService extends IBaseService<ApiInvokeRecord>, ProxySelf<IApiInvokeRecordService>{

    /**
     * 保存调用记录.
     *
     * @param invokeRecord
     */
    void insertApiInvokeRecord(ApiInvokeRecord invokeRecord);

    /**
     * 根据ID获取记录.
     *
     * @param recordId
     * @return
     */
    List<ApiInvokeRecord> selectById(Long recordId);

    /**
     * 查询调用记录.
     *
     * @param request
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ApiInvokeRecord> selectList(IRequest request, ApiInvokeRecord condition, int pageNum, int pageSize);

}
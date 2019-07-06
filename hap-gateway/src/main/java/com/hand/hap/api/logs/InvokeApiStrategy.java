package com.hand.hap.api.logs;

import com.hand.hap.core.IRequest;
import com.hand.hap.api.logs.dto.ApiInvokeRecord;

import java.util.List;

/**
 * api映射策略.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/10/14.
 */
public interface InvokeApiStrategy {

    /**
     *  记录api调用记录.
     *
     * @param invokeRecord 入站请求相关信息
     * @return
     */
    void saveApiInvokeRecord(ApiInvokeRecord invokeRecord);


    /**
     *  查询出站请求记录.
     *
     * @param request
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ApiInvokeRecord> queryInvokeRecord(IRequest request, ApiInvokeRecord condition, int pageNum, int pageSize);


    /**
     * 查询调用记录.
     *
     * @param recordId
     * @return
     */
    List<ApiInvokeRecord> selectById(Long recordId);


}

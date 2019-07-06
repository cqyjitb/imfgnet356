package com.hand.hap.api.application.service;

import com.hand.hap.audit.service.IAuditRecordService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.api.application.dto.ApiAccessLimit;
import com.hand.hap.api.application.dto.ApiApplication;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;

/**
 * 服务访问限制.
 *
 * @author lijian.yin@hand-china.com
 **/
public interface IApiAccessLimitService extends IBaseService<ApiAccessLimit>, ProxySelf<IApiAccessLimitService>{

    /**
     * 应用绑定 解绑服务 时 同步访问限制记录.
     *
     * @param before
     * @param apiApplication
     */
    void updateByApplication(String before, @StdWho ApiApplication apiApplication);

    /**
     * 根据客户端ID删除记录.
     *
     * @param id
     * @return
     */
    int removeByClientId(Long id);

    /**
     * 修改访问限制.
     *
     * @param iRequest
     * @param apiApplication
     * @return
     */
    int updateAccessLimit(IRequest iRequest, ApiApplication apiApplication);
}
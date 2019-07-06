package com.hand.hap.api.application.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.api.gateway.dto.ApiServer;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.api.application.dto.ApiApplication;

import java.util.List;

/**
 * 访问限制service - 实现类.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/15.
 **/

public interface IApiApplicationService extends IBaseService<ApiApplication>, ProxySelf<IApiApplicationService>{

    /**
     * 根据应用ID获取应用.
     *
     * @param request
     * @param applicationId
     * @return
     */
    ApiApplication getById(IRequest request, Long applicationId);

    /**
     * 添加应用.
     *
     * @param request
     * @param application
     * @return
     */
    ApiApplication insertApplication(IRequest request, ApiApplication application);

    /**
     * 修改应用.
     *
     * @param request
     * @param application
     * @return
     */
    ApiApplication updateApplication(IRequest request, @StdWho ApiApplication application);

    /**
     * 查询应用未关联的服务.
     *
     * @param request
     * @param exitsCodes
     * @param srServer
     * @param page
     * @param pageSize
     * @return
     */
    List<ApiServer> selectNotExistsServerByApp(IRequest request, String exitsCodes, ApiServer srServer , int page, int pageSize);

    /**
     * 条件查询应用.
     *
     * @param request
     * @param apiApplication
     * @param page
     * @param pageSize
     * @return
     */
    List<ApiApplication> selectApplications(IRequest request, ApiApplication apiApplication, int page, int pageSize);
}
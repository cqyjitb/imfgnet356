package com.hand.hap.api.gateway.service;

import com.hand.hap.api.gateway.dto.ApiServer;

/**
 * 导入服务.
 *
 * @author xiangyu.qi@hand-china.com
 * @date 2017/9/20.
 */

public interface IApiImportService {

    /**
     * 导入url，解析
     * @param srServer
     * @return
     */
    ApiServer importServer(ApiServer srServer);

}

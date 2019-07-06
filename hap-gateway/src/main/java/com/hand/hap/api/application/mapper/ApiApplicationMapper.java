package com.hand.hap.api.application.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.api.application.dto.ApiApplication;
import com.hand.hap.api.gateway.dto.ApiServer;
import com.hand.hap.security.oauth.dto.Oauth2ClientDetails;

import java.util.List;
import java.util.Map;

/**
 * 应用 mapper.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/15.
 **/

public interface ApiApplicationMapper extends Mapper<ApiApplication>{

    /**
     * 获取应用.
     *
     * @param applicationId
     * @return
     */
    ApiApplication getById(Long applicationId);

    /**
     * 查询应用.
     *
     * @param record
     * @return
     */
    List<ApiApplication> selectApplications(ApiApplication record);


}
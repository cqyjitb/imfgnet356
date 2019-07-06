package com.hand.hap.api.application.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.api.application.dto.ApiAccessLimit;

import java.util.List;

/**
 * 访问限制 mapper.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/15.
 **/

public interface ApiAccessLimitMapper extends Mapper<ApiAccessLimit>{

    /**
     * 根据客户端ID和服务代码删除限制记录.
     *
     * @param apiAccessLimit
     * @return
     */
    int removeByClientIdAndServerCode(ApiAccessLimit apiAccessLimit);

    /**
     * 根据客户端ID和服务代码查询限制记录.
     *
     * @param apiAccessLimit
     * @return
     */
    List<ApiAccessLimit> selectByClientIdAndServerCode(ApiAccessLimit apiAccessLimit);

    /**
     * 查询服务接口的访问限制.
     *
     * @param apiAccessLimit
     * @return
     */
    List<ApiAccessLimit> selectList(ApiAccessLimit apiAccessLimit);
}
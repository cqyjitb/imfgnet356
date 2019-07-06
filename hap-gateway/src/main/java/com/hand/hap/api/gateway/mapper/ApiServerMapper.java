package com.hand.hap.api.gateway.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.api.gateway.dto.ApiServer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 服务 mapper.
 * 
 * @author xiangyu.qi@hand-china.com
 * @date: 2017/9/21.
 **/

public interface ApiServerMapper extends Mapper<ApiServer>{

    /**
     * 根据服务代码获取服务集合.
     * 
     * @param codeList
     * @return
     */
    List<ApiServer> selectByCodes(@Param("codeList") List<String> codeList);

    /**
     * 根据服务Id获取服务（包括接口）.
     *
     * @param serverId
     * @return
     */
    ApiServer getServerAndInterfaceByServerId(@Param(value = "serverId") Long serverId);

    /**
     * 查询应用未关联的服务.
     * 
     * @param params
     * @return
     */
    List<ApiServer> selectNotExistsServerByApp(Map<String, Object> params);
}
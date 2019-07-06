package com.hand.hap.intergration;

import com.hand.hap.core.IRequest;
import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;

import java.util.List;

/**
 * @author xiangyu.qi@hand-china.com on 2017/9/23.
 */
public interface InvokeLogStrategy {


    /**
     *  记录入站请求记录
     * @param inbound 入站请求相关信息
     * @return
     */
    void logInbound(HapInterfaceInbound inbound);

    /**
     *  记录出产请求记录
     * @param outbound 出站请求相关信息
     * @return
     */
    void logOutbound(HapInterfaceOutbound outbound);

    /**
     *  查询入站请求记录
     * @param request Irequest
     * @param condition 查询条件
     * @param pageNum  分页信息
     * @param pageSize
     * @return
     */
    List<HapInterfaceInbound> queryInbound(IRequest request, HapInterfaceInbound condition, int pageNum, int pageSize);


    /**
     *  查询出站请求记录
     * @param request
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<HapInterfaceOutbound> queryOutbound(IRequest request,HapInterfaceOutbound condition, int pageNum, int pageSize);
}

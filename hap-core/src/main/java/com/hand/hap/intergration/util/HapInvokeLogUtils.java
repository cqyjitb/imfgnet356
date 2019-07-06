package com.hand.hap.intergration.util;

import com.google.common.base.Throwables;
import com.hand.hap.core.components.UserLoginInfoCollection;
import com.hand.hap.intergration.beans.HapInvokeInfo;
import com.hand.hap.intergration.beans.HapinterfaceBound;
import com.hand.hap.intergration.controllers.HapInvokeRequestBodyAdvice;
import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.message.components.InvokeLogManager;
import org.apache.cxf.message.Message;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Qixiangyu on 2017/2/21.
 */
public class HapInvokeLogUtils {


    /**
     *  截取root cause
     *  @return
     */
    public static String getRootCauseStackTrace(Throwable throwable){
        // 获取异常堆栈
        Throwable t = Throwables.getRootCause(throwable);
        String stackTrace = Throwables.getStackTraceAsString(t);
        return stackTrace;
    }

    public static void processRequestInfo(HapInterfaceInbound inbound, HttpServletRequest request){
        inbound.setUserAgent(request.getHeader("User-Agent"));
        if (inbound.getRequestMethod() == null)
            inbound.setRequestMethod(request.getMethod());
        if (inbound.getInterfaceUrl() == null)
            inbound.setInterfaceUrl(request.getServletPath());
        if (inbound.getRequestHeaderParameter() == null)
            inbound.setRequestHeaderParameter(request.getQueryString());
        if (inbound.getRequestBodyParameter() == null)
            inbound.setRequestBodyParameter(HapInvokeRequestBodyAdvice.getAndRemoveBody());
        inbound.setIp(UserLoginInfoCollection.getIpAddress(request));
    }

    public static void processExceptionInfo(HapInterfaceInbound inbound,Throwable throwable){
        if (throwable != null) {
            // 获取异常堆栈
            inbound.setStackTrace(HapInvokeLogUtils.getRootCauseStackTrace(throwable));
            inbound.setRequestStatus(HapInvokeInfo.REQUEST_FAILURE);
        }
    }

    public static void processExceptionInfo(HapInterfaceOutbound outbound, Throwable throwable){
        if (throwable != null) {
            // 获取异常堆栈
            outbound.setStackTrace(HapInvokeLogUtils.getRootCauseStackTrace(throwable));
            outbound.setRequestStatus(HapInvokeInfo.REQUEST_FAILURE);
        }
    }


    /**
     * 用与CXF Interceptor
     * this function should be write on another class
     * @param message
     */
   public static void processCxfHandleFault(Message message, IMessagePublisher messagePublisher){
       HapInterfaceOutbound outbound = (HapInterfaceOutbound) message.getExchange().get(HapInvokeInfo.INVOKE_INFO_OUTBOUND);
       HapInterfaceInbound inbound = (HapInterfaceInbound) message.getExchange().get(HapInvokeInfo.INVOKE_INFO_INBOUND);
       Exception fault = message.getContent(Exception.class);
       if (inbound != null) {
           inbound.setRequestStatus(HapInvokeInfo.REQUEST_FAILURE);
           inbound.setResponseTime(System.currentTimeMillis() - inbound.getRequestTime().getTime());
           HapInvokeLogUtils.processExceptionInfo(inbound,fault);
           messagePublisher.message(InvokeLogManager.CHANNEL_INBOUND,new HapinterfaceBound(inbound));
       } else if (outbound != null) {
           HapInvokeLogUtils.processExceptionInfo(outbound,fault);
           outbound.setResponseTime(System.currentTimeMillis() - outbound.getRequestTime().getTime());
           messagePublisher.message(InvokeLogManager.CHANNEL_OUTBOUND,new HapinterfaceBound(outbound));
       }
   }

}

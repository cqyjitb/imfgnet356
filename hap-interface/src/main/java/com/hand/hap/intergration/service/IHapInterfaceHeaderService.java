package com.hand.hap.intergration.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.intergration.dto.HapInterfaceHeader;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * @author jiguang.sun@hand-china.com
 * @version 2016/7/21.
 */
public interface IHapInterfaceHeaderService extends IBaseService<HapInterfaceHeader>, ProxySelf<IHapInterfaceHeaderService> {


    List<HapInterfaceHeader> getAllHeader(IRequest requestContext ,HapInterfaceHeader interfaceHeader,int page, int pagesize);

    List<HapInterfaceHeader> getHeaderAndLineList(IRequest requestContext ,HapInterfaceHeader interfaceHeader);

    HapInterfaceHeader getHeaderAndLine(String sysName, String apiName);

    List<HapInterfaceHeader> getAllHeaderAndLine();

    List<HapInterfaceHeader> getAllHeaderAndLine(int page, int pagesize);

    List<HapInterfaceHeader> getHeaderByHeaderId(IRequest requestContext,HapInterfaceHeader HapInterfaceHeader);

    HapInterfaceHeader getHeaderAndLineByLineId(HapInterfaceHeader HapInterfaceHeader);

    int updateHeader(IRequest request, HapInterfaceHeader hmsInterfaceHeader);

    void createInterface(IRequest iRequest, HapInterfaceHeader interfaceHeader);

    void updateInterface(IRequest iRequest, HapInterfaceHeader interfaceHeader);

}

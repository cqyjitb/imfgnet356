package com.hand.hap.intergration.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.intergration.dto.HapInterfaceHeader;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.intergration.dto.HapInterfaceLine;

import java.util.List;

/**
 * Created by user on 2016/7/26.
 */
public interface IHapInterfaceLineService extends IBaseService<HapInterfaceLine>,ProxySelf<IHapInterfaceLineService> {

    List<HapInterfaceLine> getLineAndLineTl(IRequest request, HapInterfaceLine hapInterfaceLine);

    List<HapInterfaceLine> getLinesByHeaderId(IRequest request,HapInterfaceLine lineAndLineTlDTO,int page,int pagesize);

    int insertLine(IRequest request, HapInterfaceLine hapInterfaceLine);

    int updateLine(IRequest request, HapInterfaceLine hapInterfaceLine);

    int batchDeleteByHeaders( IRequest request,List<HapInterfaceHeader> lists);
}

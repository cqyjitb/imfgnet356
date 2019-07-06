package com.hand.hap.intergration.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;

import java.util.List;

public interface HapInterfaceOutboundMapper extends Mapper<HapInterfaceOutbound>{

    List<HapInterfaceOutbound> select (HapInterfaceOutbound outbound);
}
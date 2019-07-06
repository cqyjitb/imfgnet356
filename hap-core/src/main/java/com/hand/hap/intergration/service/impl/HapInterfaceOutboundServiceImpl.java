package com.hand.hap.intergration.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.intergration.mapper.HapInterfaceOutboundMapper;
import com.hand.hap.intergration.service.IHapInterfaceOutboundService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HapInterfaceOutboundServiceImpl extends BaseServiceImpl<HapInterfaceOutbound>
        implements IHapInterfaceOutboundService {

    @Autowired
    private HapInterfaceOutboundMapper outboundMapper;

    @Override
    public List<HapInterfaceOutbound> select(IRequest request, HapInterfaceOutbound condition, int pageNum,
            int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return outboundMapper.select(condition);
    }

    @Override
    public int outboundInvoke(HapInterfaceOutbound outbound) {
        return outboundMapper.insertSelective(outbound);
    }
}
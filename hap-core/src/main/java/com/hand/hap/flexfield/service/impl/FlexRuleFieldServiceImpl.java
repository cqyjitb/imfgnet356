package com.hand.hap.flexfield.service.impl;

import com.hand.hap.flexfield.mapper.FlexRuleFieldMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.flexfield.dto.FlexRuleField;
import com.hand.hap.flexfield.service.IFlexRuleFieldService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlexRuleFieldServiceImpl extends BaseServiceImpl<FlexRuleField> implements IFlexRuleFieldService {

    @Autowired
    FlexRuleFieldMapper flexRuleFieldMapper;

    @Override
    public List<FlexRuleField> queryFlexRuleField(FlexRuleField flexRuleField) {
        return flexRuleFieldMapper.queryFlexField(flexRuleField);
    }

}
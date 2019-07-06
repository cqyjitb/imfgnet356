package com.hand.hap.flexfield.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.flexfield.dto.FlexRuleField;

import java.util.List;

public interface FlexRuleFieldMapper extends Mapper<FlexRuleField> {

    /** 根据 rule查询对应ruleField
     * @param flexRuleField
     * @return
     */
    List<FlexRuleField> queryFlexField(FlexRuleField flexRuleField);

}
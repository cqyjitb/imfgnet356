package com.hand.hap.flexfield.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.flexfield.dto.FlexRule;

import java.util.List;

public interface FlexRuleMapper extends Mapper<FlexRule> {

    /** 匹配规则
     * @param ruleSetCode
     * @return
     */
    List<FlexRule> matchingRule(String ruleSetCode);

}
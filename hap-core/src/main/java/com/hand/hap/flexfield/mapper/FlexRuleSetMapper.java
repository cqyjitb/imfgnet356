package com.hand.hap.flexfield.mapper;

import com.hand.hap.flexfield.dto.FlexModel;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.flexfield.dto.FlexRuleSet;

import java.util.List;

public interface FlexRuleSetMapper extends Mapper<FlexRuleSet> {


    /** 查询规则集
     * @param model
     * @return
     */
    List<FlexRuleSet> queryFlexRuleSet(FlexRuleSet model);

}
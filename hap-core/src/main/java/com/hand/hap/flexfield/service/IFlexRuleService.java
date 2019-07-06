package com.hand.hap.flexfield.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.flexfield.dto.FlexRule;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFlexRuleService extends IBaseService<FlexRule>, ProxySelf<IFlexRuleService> {

    /** 匹配规则
     * @param ruleSetCode 规则集代码
     * @param model 进行匹配的数据
     * @param iRequest IRequest环境
     * @return 匹配成功的弹性域
     */
    ResponseData matchingRule(String ruleSetCode, Set<Map.Entry<String, String>> model, IRequest iRequest);

    /**删除弹性域规则
     * @param flexRules
     */
    void deleteRule(List<FlexRule> flexRules);

    /** 获取弹性域LOV字段相关值放入dto
     * @param ruleSetCode 规则集代码
     * @param model 进行匹配的数据
     * @param o 当前的数据将查询到的LOV值放入该数据中
     * @param iRequest IRequest环境
     * @throws IllegalAccessException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    void matchingLovField(String ruleSetCode, Set<Map.Entry<String, String>> model,Object o,IRequest iRequest) throws IllegalAccessException, IOException, InvocationTargetException, NoSuchMethodException;

}
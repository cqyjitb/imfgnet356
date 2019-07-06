/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import java.util.List;

import com.hand.hap.job.dto.TriggerDto;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface TriggerMapper {
    TriggerDto selectByPrimaryKey(TriggerDto key);

    List<TriggerDto> selectTriggers(TriggerDto example);

}
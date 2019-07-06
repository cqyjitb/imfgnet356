/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.SimpleTriggerDto;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface SimpleTriggerMapper {

    SimpleTriggerDto selectByPrimaryKey(SimpleTriggerDto key);
}
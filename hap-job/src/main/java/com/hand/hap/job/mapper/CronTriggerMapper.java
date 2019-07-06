/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.CronTriggerDto;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface CronTriggerMapper {
    CronTriggerDto selectByPrimaryKey(CronTriggerDto key);
}
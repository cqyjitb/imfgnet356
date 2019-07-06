/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import java.util.List;

import com.hand.hap.job.dto.SchedulerDto;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface SchedulerMapper {

    SchedulerDto selectByPrimaryKey(SchedulerDto key);

    List<SchedulerDto> selectSchedulers(SchedulerDto example);

}
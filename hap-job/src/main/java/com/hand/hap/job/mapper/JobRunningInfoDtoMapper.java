/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.JobRunningInfoDto;
import com.hand.hap.mybatis.common.Mapper;

/**
 *
 * @author liyan.shi@hand-china.com
 */
public interface JobRunningInfoDtoMapper extends Mapper<JobRunningInfoDto> {

    void deleteByNameGroup(JobRunningInfoDto example);

}
/*
 * #{copyright}#
 */

package com.hand.hap.job.mapper;

import java.util.List;

import com.hand.hap.job.dto.JobDetailDto;
import com.hand.hap.job.dto.JobInfoDetailDto;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface JobDetailMapper {
    JobDetailDto selectByPrimaryKey(JobDetailDto key);

    List<JobDetailDto> selectJobDetails(JobDetailDto example);

    List<JobInfoDetailDto> selectJobInfoDetails(JobDetailDto example);
}
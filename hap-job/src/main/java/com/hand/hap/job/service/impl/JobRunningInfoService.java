/*
 * #{copyright}#
 */
package com.hand.hap.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.job.dto.JobRunningInfoDto;
import com.hand.hap.job.mapper.JobRunningInfoDtoMapper;
import com.hand.hap.job.service.IJobRunningInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiliyan
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobRunningInfoService implements IJobRunningInfoService {

    @Autowired
    private JobRunningInfoDtoMapper jobRunningInfoDtoMapper;

    @Override
    public List<JobRunningInfoDto> queryJobRunningInfo(IRequest request, JobRunningInfoDto example, int page,
                                                       int pagesize) {
        PageHelper.startPage(page, pagesize);
        return jobRunningInfoDtoMapper.select(example);
    }

    @Override
    public void createJobRunningInfo(JobRunningInfoDto jobCreateDto) {
        jobRunningInfoDtoMapper.insertSelective(jobCreateDto);
    }

    @Override
    public void delete(JobRunningInfoDto jobCreateDto) {
        jobRunningInfoDtoMapper.deleteByNameGroup(jobCreateDto);
    }

}

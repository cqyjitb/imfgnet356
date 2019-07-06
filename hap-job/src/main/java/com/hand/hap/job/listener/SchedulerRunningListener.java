/*
 * #{copyright}#
 */
package com.hand.hap.job.listener;

import com.hand.hap.job.service.IJobRunningInfoService;
import org.quartz.JobKey;
import org.quartz.listeners.SchedulerListenerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hand.hap.job.dto.JobRunningInfoDto;

/**
 * @author shiliyan
 *
 */
public class SchedulerRunningListener extends SchedulerListenerSupport {

    private static final String JOB_INFO_HAS_DELETED = "Job Info [{}.{}] has deleted.";
    private static final String JOB_WAS_DELETED_FROM_SCHEDULER = "Job [{}.{}] was deleted from Scheduler.";
    private final ApplicationContext applicationContext;

    public SchedulerRunningListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see DefaultSchedulerListener#jobDeleted(org.quartz.JobKey)
     */
    @Override
    public void jobDeleted(JobKey jobKey) {
        JobRunningInfoDto dto = new JobRunningInfoDto();
        String group = jobKey.getGroup();
        String name = jobKey.getName();
        logInfo(JOB_WAS_DELETED_FROM_SCHEDULER, group, name);
        dto.setJobName(name);
        dto.setJobGroup(group);
        deleteJobInfo(dto);
        logInfo(JOB_INFO_HAS_DELETED, group, name);
    }

    private void deleteJobInfo(JobRunningInfoDto jobCreateDto) {
        IJobRunningInfoService jobRunningInfoService = applicationContext.getBean(IJobRunningInfoService.class);
        jobRunningInfoService.delete(jobCreateDto);
    }

    protected void logInfo(String info, Object... para) {
        if (getLog().isInfoEnabled()) {
            getLog().info(info, para);
        }
    }

    protected void logInfo(String info) {
        if (getLog().isInfoEnabled()) {
            getLog().info(info);
        }
    }

}

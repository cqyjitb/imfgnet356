package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yj.core.werbserver_crhd.components.SyncCrhdWebserviceUtil;

import java.util.Date;

public class SyncCrhdJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(SyncCrhdJob.class);
    public SyncCrhdJob(){

    }
    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "Hello World! - . jobKey:" + key + ", triggerKey:" + triggerKey + ", execTime:" + new Date();
        if (log.isInfoEnabled()) {
            log.info(msg);
        }
        SyncCrhdWebserviceUtil sync = new SyncCrhdWebserviceUtil();
        sync.receiveConfirmation();

    }
}

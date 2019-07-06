package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import com.hand.hap.job.dto.SimpleTriggerDto;
import com.hand.hap.job.service.IQuartzService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.kanb.wipngrecord.dto.NgRecord;
import yj.kanb.wipngrecord.service.INgRecordService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NgRecordJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(NgRecordJob.class);
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private INgRecordService ngRecodeService;
    @Autowired
    private IQuartzService quartzService;
    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return false;
    }
    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "KanbGetDataJob Test<insertNewData>! - . jobKey:" + key + ", triggerKey:" + triggerKey + ", execTime:" + new Date();
        log.info(msg);
        SimpleTriggerDto simpleTriggerDto = quartzService.getSimpleTrigger(triggerKey.getName(),triggerKey.getGroup());
        int minute = simpleTriggerDto.getRepeatInterval().intValue();
        minute = minute / 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = context.getFireTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        if (minute == 20*60){
            String startDate2 = sdf2.format(endDate) + " 00:00:00";
            String endDate2 = sdf.format(endDate);
            List<InOutRecord> list = inOutRecordService.selectByNgRecode(startDate2,endDate2);
            if(list.size() > 0){
                for (int i=0;i<list.size();i++){
                    InOutRecord inOutRecord = list.get(i);
                    inOutRecord.setCreationDateBefore(startDate2);
                    inOutRecord.setCreationDateAfter(endDate2);
                    NgRecord ngRecord = new NgRecord();
                    ngRecord.setWerks(inOutRecord.getWerks());
                    ngRecord.setLineId(inOutRecord.getLineId());
                    ngRecord.setDeptId(inOutRecord.getDeptId());
                    ngRecord.setMatnr(inOutRecord.getMatnr2());
                    ngRecord.setMaktx(inOutRecord.getMaktx());
                    ngRecord.setGmein(inOutRecord.getGmein());
                    ngRecord.setZissuetxt(inOutRecord.getZissuetxt());
                    ngRecord.setZotype(inOutRecord.getZotype());
                    ngRecord.setZtext(inOutRecord.getZtext());
                    ngRecord.setErdat(new Date());
                    List<NgRecord> list2 = ngRecodeService.selectNgRecord(ngRecord);
                    List<InOutRecord> list1 = inOutRecordService.zissuetxtCount(inOutRecord);
                    ngRecord.setQty(list1.size());
                    if (list2.size() > 0){
                        ngRecord.setLastUpdatedBy(10001L);
                        ngRecord.setLastUpdateDate(new Date());
                        ngRecodeService.updateNgRecord(ngRecord);
                    }else{
                        ngRecord.setCreatedBy(10001L);
                        ngRecord.setCreationDate(new Date());
                        ngRecodeService.insertNgRecord(ngRecord);
                    }
                }
            }
        }else if (minute == 24*60*60){
            cal.add(Calendar.DATE,-7);
            while(cal.getTime().before(endDate)) {
                String startDate2 = sdf2.format(cal.getTime()) + " 00:00:00";
                String endDate2 = sdf2.format(cal.getTime()) + " 23:59:59";
                List<InOutRecord> list = inOutRecordService.selectByNgRecode(startDate2,endDate2);
                if(list.size() > 0){
                    for (int i=0;i<list.size();i++){
                        InOutRecord inOutRecord = list.get(i);
                        inOutRecord.setCreationDateBefore(startDate2);
                        inOutRecord.setCreationDateAfter(endDate2);
                        NgRecord ngRecord = new NgRecord();
                        ngRecord.setWerks(inOutRecord.getWerks());
                        ngRecord.setLineId(inOutRecord.getLineId());
                        ngRecord.setDeptId(inOutRecord.getDeptId());
                        ngRecord.setMatnr(inOutRecord.getMatnr2());
                        ngRecord.setMaktx(inOutRecord.getMaktx());
                        ngRecord.setGmein(inOutRecord.getGmein());
                        ngRecord.setZissuetxt(inOutRecord.getZissuetxt());
                        ngRecord.setZotype(inOutRecord.getZotype());
                        ngRecord.setZtext(inOutRecord.getZtext());
                        ngRecord.setErdat(cal.getTime());
                        List<NgRecord> list2 = ngRecodeService.selectNgRecord(ngRecord);
                        List<InOutRecord> list1 = inOutRecordService.zissuetxtCount(inOutRecord);
                        ngRecord.setQty(list1.size());
                        if (list2.size() > 0){
                            ngRecord.setLastUpdateDate(new Date());
                            ngRecord.setLastUpdatedBy(10001L);
                            ngRecodeService.updateNgRecord(ngRecord);
                        }else{
                            ngRecord.setCreatedBy(10001L);
                            ngRecord.setCreationDate(new Date());
                            ngRecodeService.insertNgRecord(ngRecord);
                        }
                    }
                }
                cal.add(Calendar.DATE,1);
            }
        }
    }
}

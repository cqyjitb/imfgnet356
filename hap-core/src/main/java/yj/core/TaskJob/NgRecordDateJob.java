package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.kanb.wipdateclass.dto.DateClass;
import yj.kanb.wipdateclass.service.IDateClassService;
import yj.kanb.wipngrecord.dto.NgRecord;
import yj.kanb.wipngrecord.service.INgRecordService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NgRecordDateJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(NgRecordDateJob.class);
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private INgRecordService ngRecodeService;
    @Autowired
    private IDateClassService dateClassService;
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
        List<DateClass> list2 = dateClassService.selectFromPage("NgRecordDateJob");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try {
            Date startDate = sdf2.parse(list2.get(0).getStartDate());
            Date endDate = sdf2.parse(list2.get(0).getEndDate());
            cal.setTime(endDate);
            cal2.setTime(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(cal.DATE, 1);
        while(cal2.getTime().before(cal.getTime())) {
            String startDate = sdf2.format(cal2.getTime()) + " 00:00:00";
            String endDate = sdf2.format(cal2.getTime()) + " 23:59:59";
            List<InOutRecord> list = inOutRecordService.selectByNgRecode(startDate,endDate);
            if(list.size() > 0){
                for (int i=0;i<list.size();i++){
                    InOutRecord inOutRecord = list.get(i);
                    inOutRecord.setCreationDateBefore(startDate);
                    inOutRecord.setCreationDateAfter(endDate);
                    NgRecord ngRecord = new NgRecord();
                    ngRecord.setWerks(inOutRecord.getWerks());
                    ngRecord.setDeptId(inOutRecord.getDeptId());
                    ngRecord.setLineId(inOutRecord.getLineId());
                    ngRecord.setMatnr(inOutRecord.getMatnr2());
                    ngRecord.setMaktx(inOutRecord.getMaktx());
                    ngRecord.setErdat(cal2.getTime());
                    ngRecord.setGmein(inOutRecord.getGmein());
                    ngRecord.setZissuetxt(inOutRecord.getZissuetxt());
                    ngRecord.setZotype(inOutRecord.getZotype());
                    ngRecord.setZtext(inOutRecord.getZtext());
                    List<NgRecord> list3 = ngRecodeService.selectNgRecord(ngRecord);
                    List<InOutRecord> list1 = inOutRecordService.zissuetxtCount(inOutRecord);
                    ngRecord.setQty(list1.size());
                    if (list3.size() > 0){
                        ngRecord.setLastUpdateDate(new Date());
                        ngRecord.setLastUpdatedBy(10001L);
                        ngRecodeService.updateNgRecord(ngRecord);
                    }else{
                        ngRecord.setCreationDate(new Date());
                        ngRecord.setCreatedBy(10001L);
                        ngRecodeService.insertNgRecord(ngRecord);
                    }
                }
            }
            cal2.add(Calendar.DATE,1);
        }

    }
}

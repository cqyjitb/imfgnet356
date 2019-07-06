package com.hand.hap.job;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.OracleConn.OracleConn;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.kanb.wipdateclass.dto.DateClass;
import yj.kanb.wipdateclass.service.IDateClassService;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.service.IPassRateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PassRateDateJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(PassRateDateJob.class);
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IPassRateService passRateService;
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
        List<DateClass> list2 = dateClassService.selectFromPage("PassRateDateJob");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try {
            Date startDate = sdf2.parse(list2.get(0).getStartDate());
            cal2.setTime(startDate);
            Date endDate = sdf2.parse(list2.get(0).getEndDate());
            cal.setTime(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(cal.DATE, 1);
        while(cal2.getTime().before(cal.getTime())) {
            String startDate = sdf2.format(cal2.getTime()) + " 00:00:00";
            String endDate = sdf2.format(cal2.getTime()) + " 23:59:59";
            List<InOutRecord> list = inOutRecordService.selectByCreateDate(startDate, endDate);
            if (list.size() > 0) {
                List<InOutRecord> list1 = new ArrayList<InOutRecord>();
                for (int i = 0; i < list.size(); i++) {
                    PassRate passRate = new PassRate();
                    passRate.setWerks(list.get(i).getWerks());
                    passRate.setDeptId(list.get(i).getDeptId());
                    passRate.setLineId(String.valueOf(list.get(i).getLineId()));
                    passRate.setErdat(cal2.getTime());
                    passRate.setMatnr(list.get(i).getMatnr2());
                    passRate.setDateStart(sdf2.format(cal.getTime()));
                    passRate.setDateEnd(sdf2.format(cal.getTime()));
                    List<PassRate> list3 = passRateService.queryPassRate(passRate);
                    passRate.setMaktx(list.get(i).getMaktx());
                    passRate.setGmein(list.get(i).getGmein());
                    Integer gmnga = 0, xmnga = 0, rmnga = 0;
                    list1 = inOutRecordService.XmngaCount(passRate.getLineId(), passRate.getMatnr(), startDate, endDate);
                    if (list1.size() > 0) {
                        xmnga = list1.size();
                    }
                    list1 = inOutRecordService.RmngaCount(passRate.getLineId(), passRate.getMatnr(), startDate, endDate);
                    if (list1.size() > 0) {
                        rmnga = list1.size();
                    }
                    ServerSetting serverSetting = serverSettingService.selectByLineId(passRate.getWerks(), passRate.getLineId());
                    if (serverSetting != null) {
                        WebServerHelp webServerHelp = new WebServerHelp();
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(), webServerHelp.getMesOraUserName(), webServerHelp.getMesOraPass(), webServerHelp.getMesOraDriver());
                        String sqlzx = "select ba.* from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                        String where = "where pa.line_id = " + "'" + passRate.getLineId() + "' and ba.item_code = '" + passRate.getMatnr() + "' and ba.creation_date >= '"
                                + startDate + "' and ba.creation_date < '" + endDate + "' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                        String sql = sqlzx + where;
                        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
                        try {
                            listMap = oracleConn.select(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (listMap.size() > 0) {
                            gmnga = list.size();
                        }
                    }
                    passRate.setGmnga(gmnga);
                    passRate.setXmnga(xmnga);
                    passRate.setRmnga(rmnga);
                    if(list3.size() > 0){
                        passRate.setLastUpdateDate(new Date());
                        passRate.setLastUpdatedBy(10001L);
                        passRateService.updatePassRate(passRate);
                    }else{
                        passRate.setCreationDate(new Date());
                        passRate.setCreatedBy(10001L);
                        passRateService.insertPassRate(passRate);
                    }
                }
            }
            cal2.add(Calendar.DATE, 1);
        }
    }
}

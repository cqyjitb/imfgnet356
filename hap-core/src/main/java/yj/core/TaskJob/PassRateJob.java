package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import com.hand.hap.job.dto.SimpleTriggerDto;
import com.hand.hap.job.service.IQuartzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.OracleConn.OracleConn;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.service.IPassRateService;

import java.text.SimpleDateFormat;
import java.util.*;

public class PassRateJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(PassRateJob.class);
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IPassRateService passRateService;
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
        minute = minute/1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = context.getFireTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        if (minute == 20*60){
            String startDate2 = sdf2.format(endDate) + " 00:00:00";
            String endDate2 = sdf.format(endDate);
            List<InOutRecord> list = inOutRecordService.selectByCreateDate(startDate2,endDate2);
            if (list.size() > 0){
                List<InOutRecord> list1 = new ArrayList<InOutRecord>();
                for (int i=0;i<list.size();i++){
                    PassRate passRate = new PassRate();
                    passRate.setWerks(list.get(i).getWerks());
                    passRate.setDeptId(list.get(i).getDeptId());
                    passRate.setLineId(String.valueOf(list.get(i).getLineId()));
                    passRate.setErdat(new Date());
                    passRate.setMatnr(list.get(i).getMatnr2());
                    passRate.setMaktx(list.get(i).getMaktx());
                    passRate.setDateStart(sdf2.format(endDate));
                    passRate.setDateEnd(sdf2.format(endDate));
                    List<PassRate> list2 = passRateService.queryPassRate(passRate);
                    passRate.setGmein(list.get(i).getGmein());
                    Integer gmnga = 0,xmnga = 0,rmnga = 0;
                    list1 = inOutRecordService.XmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                    if(list1.size() > 0){
                        xmnga = list1.size();
                    }
                    list1 = inOutRecordService.RmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                    if(list1.size() > 0){
                        rmnga = list1.size();
                    }
                    ServerSetting serverSetting = serverSettingService.selectByLineId(passRate.getWerks(),passRate.getLineId());
                    if(serverSetting != null){
                        WebServerHelp webServerHelp = new WebServerHelp();
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select ba.* from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                        String where = "where pa.line_id = "+ "'"+ passRate.getLineId() + "' and ba.item_code = '"+ passRate.getMatnr()+"' and ba.creation_date >= '"
                                + startDate2 + "' and ba.creation_date < '" + endDate2 +"' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                        String sql = sqlzx + where;
                        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
                        try {
                            listMap = oracleConn.select(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (listMap.size() > 0){
                            gmnga = list.size();
                        }
                    }
                    passRate.setRmnga(rmnga);
                    passRate.setGmnga(gmnga);
                    passRate.setXmnga(xmnga);
                    if(list2.size() > 0){
                        passRate.setLastUpdatedBy(10001L);
                        passRate.setLastUpdateDate(new Date());
                        passRateService.updatePassRate(passRate);
                    }else{
                        passRate.setCreatedBy(10001L);
                        passRate.setCreationDate(new Date());
                        passRateService.insertPassRate(passRate);
                    }
                }
            }
        }else if (minute == 24*60*60){
            cal.add(Calendar.DATE,-7);
            while(cal.getTime().before(endDate)){
                String startDate2 = sdf2.format(cal.getTime()) + " 00:00:00";
                String endDate2 = sdf2.format(cal.getTime()) + " 23:59:59";
                List<InOutRecord> list = inOutRecordService.selectByCreateDate(startDate2,endDate2);
                if (list.size() > 0){
                    List<InOutRecord> list1 = new ArrayList<InOutRecord>();
                    for (int i=0;i<list.size();i++){
                        PassRate passRate = new PassRate();
                        passRate.setDeptId(list.get(i).getDeptId());
                        passRate.setWerks(list.get(i).getWerks());
                        passRate.setLineId(String.valueOf(list.get(i).getLineId()));
                        passRate.setErdat(cal.getTime());
                        passRate.setMatnr(list.get(i).getMatnr2());
                        passRate.setMaktx(list.get(i).getMaktx());
                        passRate.setGmein(list.get(i).getGmein());
                        passRate.setDateStart(sdf2.format(cal.getTime()));
                        passRate.setDateEnd(sdf2.format(cal.getTime()));
                        List<PassRate> list2 = passRateService.queryPassRate(passRate);
                        Integer gmnga = 0,xmnga = 0,rmnga = 0;
                        list1 = inOutRecordService.XmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                        if(list1.size() > 0){
                            xmnga = list1.size();
                        }
                        list1 = inOutRecordService.RmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                        if(list1.size() > 0){
                            rmnga = list1.size();
                        }
                        ServerSetting serverSetting = serverSettingService.selectByLineId(passRate.getWerks(),passRate.getLineId());
                        if(serverSetting != null){
                            WebServerHelp webServerHelp = new WebServerHelp();
                            OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                            String sqlzx = "select ba.* from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                            String where = "where pa.line_id = "+ "'"+ passRate.getLineId() + "' and ba.item_code = '"+ passRate.getMatnr()+"' and ba.creation_date >= '"
                                    + startDate2 + "' and ba.creation_date < '" + endDate2 +"' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                            String sql = sqlzx + where;
                            List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
                            try {
                                listMap = oracleConn.select(sql);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (listMap.size() > 0){
                                gmnga = list.size();
                            }
                        }
                        passRate.setRmnga(rmnga);
                        passRate.setGmnga(gmnga);
                        passRate.setXmnga(xmnga);
                        if(list2.size() > 0){
                            passRate.setLastUpdateDate(new Date());
                            passRate.setLastUpdatedBy(10001L);
                            passRateService.updatePassRate(passRate);
                        }else{
                            passRate.setCreatedBy(10001L);
                            passRate.setCreationDate(new Date());
                            passRateService.insertPassRate(passRate);
                        }
                    }
                }
                cal.add(Calendar.DATE,1);
            }
        }
    }
}

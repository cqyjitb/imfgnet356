package yj.kanb.wipngrecord.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.wipngrecord.dto.NgRecord;
import yj.kanb.wipngrecord.service.INgRecordService;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.service.IPassRateService;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class NgRecordControllers extends BaseController {
    @Autowired
    private INgRecordService service;
    @Autowired
    private IPassRateService passRateService;

    /**
     * 终检&过程&GP12&走样不良柏拉图数据查询 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "wip/ng/record/queryNgRecord")
    @ResponseBody
    public ResponseData queryNgRecord(HttpServletRequest request, NgRecord dto) {
        String btn = request.getParameter("btn");
        String btnDate = request.getParameter("btnDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        if("1".equals(btn)){
            dto.setDateStart(btnDate);
            dto.setDateEnd(btnDate);
        }
        if("2".equals(btn)){
            Calendar cal2 = Calendar.getInstance();
            String stDate = btnDate.substring(0,7) + "-01";
            Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            try {
                Date dateStart = sdf.parse(stDate);
                Date dateEnd = sdf.parse(btnDate.substring(0,7) + "-" + day);
                cal.setTime(dateStart);
                cal2.setTime(dateEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer start = cal.get(Calendar.DAY_OF_WEEK);
            if(start >= 2){
                start --;
            }else if(start == 1){
                start = 7;
            }
            int week = Integer.valueOf(btnDate.substring(8,9));
            if (1 == week){
                cal.add(cal.DATE, (7-start));
                dto.setDateStart(stDate);
                dto.setDateEnd(sdf.format(cal.getTime()));
            }else if ((week >=2)&& (week <= 4)){
                cal.add(cal.DATE, week*7 - start);
                dto.setDateEnd(sdf.format(cal.getTime()));
                cal.add(cal.DATE, -6);
                dto.setDateStart(sdf.format(cal.getTime()));
            }else if (week == 5){
                cal.add(cal.DATE, 29 - start);
                dto.setDateStart(sdf.format(cal.getTime()));
                cal.add(cal.DATE,6);
                if (cal.getTime().after(cal2.getTime())){
                    dto.setDateEnd(sdf.format(cal2.getTime()));
                }else{
                    dto.setDateEnd(sdf.format(cal.getTime()));
                }
            }else if (week == 6){
                cal.add(cal.DATE, 36 - start);
                dto.setDateStart(sdf.format(cal.getTime()));
                dto.setDateEnd(sdf.format(cal2.getTime()));
            }
        }
        if("3".equals(btn)){
            try {
                Date dateStart = sdf2.parse(btnDate);
                cal.setTime(dateStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            dto.setDateStart(btnDate + "-01");
            dto.setDateEnd(btnDate + "-" + day);
        }
        List<NgRecord> list = service.queryNgRecord(dto);
        if(list.size() > 0){
            Integer sum = service.queryNgRecordByQty(dto);
            dto.setQty(null);
            DecimalFormat df = new DecimalFormat("#0.00");
            Integer qtySum = 0;
            for (int i=0;i<list.size();i++){
                NgRecord ngRecord = list.get(i);
                dto.setZissuetxt(ngRecord.getZissuetxt());
                Integer qty = service.queryByQty(dto);
                qtySum = qtySum + qty;
                ngRecord.setQty(qty);
                String scrapRate = df.format((qty*100/(float)sum));
                String scrapRateSum = df.format((qtySum*100/(float)sum));
                ngRecord.setScrapRate(scrapRate);
                ngRecord.setScrapRateSum(scrapRateSum);
            }
        }
        return new ResponseData(list);
    }

    @RequestMapping(value = "wip/ng/record/queryByZotype")
    @ResponseBody
    public ResponseData queryByZotype(HttpServletRequest request, NgRecord dto){
        String btn = request.getParameter("btn");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        DecimalFormat df = new DecimalFormat("#0.00");
        Calendar cal2 = Calendar.getInstance();
        List<NgRecord> list = new ArrayList<NgRecord>();
        PassRate passRate = new PassRate();
        passRate.setWerks(dto.getWerks());
        passRate.setDeptId(dto.getDeptId());
        passRate.setLineId(dto.getLineId());
        passRate.setMatnr(dto.getMatnr());
        if ("1".equals(btn)){
            String dateStart = dto.getDateStart().replaceAll("/","-");
            String dateEnd = dto.getDateEnd().replaceAll("/","-");
            try {
                Date startTime = sdf.parse(dateStart);
                Date endTime = sdf.parse(dateEnd);
                cal.setTime(startTime);
                cal2.setTime(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal2.add(cal.DATE, 1);
            while(cal.getTime().before(cal2.getTime())){
                NgRecord ngRecord = new NgRecord();
                dto.setDateStart(sdf.format(cal.getTime()));
                dto.setDateEnd(sdf.format(cal.getTime()));
                int qty = service.queryByQty(dto);
                if (qty > 0){
                    passRate.setDateStart(sdf.format(cal.getTime()));
                    passRate.setDateEnd(sdf.format(cal.getTime()));
                    List<PassRate> list1 = passRateService.queryPassRate(passRate);
                    int gmnga = 0,xmnga = 0,rmnga = 0;
                    if(list1.size() > 0){
                        gmnga = list1.get(0).getGmnga();
                        xmnga = list1.get(0).getXmnga();
                        rmnga = list1.get(0).getRmnga();
                    }
                    int tmnga = gmnga + xmnga + rmnga;
                    String scrapRate = df.format((qty*100/(float)tmnga));
                    ngRecord.setScrapRate(scrapRate);
                }else {
                    ngRecord.setScrapRate("0.00");
                }
                String erdat = sdf.format(cal.getTime());
                ngRecord.setDateStart(erdat);
                list.add(ngRecord);
                cal.add(cal.DATE, 1);
            }
        }
        if ("2".equals(btn)){
            String dateStart = dto.getDateStart();
            Date dateStart2 = null;
            try {
                dateStart2 = sdf.parse(dateStart + "-01");
                cal.setTime(dateStart2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            try {
                Date dateEnd = sdf.parse(dateStart.substring(0,7) + "-" + day);
                cal2.setTime(dateEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int i=1;i<=6;i++){
                NgRecord ngRecord = new NgRecord();
                Integer start = cal.get(Calendar.DAY_OF_WEEK);
                if(start == 1){
                    start = 7;
                }else if(start > 1){
                    start--;
                }
                if (i == 1){
                    cal.add(cal.DATE, (7-start));
                    dto.setDateStart(dateStart + "-01");
                    dto.setDateEnd(sdf.format(cal.getTime()));
                }else if ((i >=2)&& (i <= 4)){
                    cal.add(cal.DATE, 1);
                    dto.setDateStart(sdf.format(cal.getTime()));
                    cal.add(cal.DATE, 6);
                    dto.setDateEnd(sdf.format(cal.getTime()));
                }else if (i == 5){
                    cal.add(cal.DATE, 1);
                    dto.setDateStart(sdf.format(cal.getTime()));
                    cal.add(cal.DATE,6);
                    if (cal.getTime().after(cal2.getTime())){
                        dto.setDateEnd(sdf.format(cal.getTime()));
                    }else{
                        dto.setDateEnd(sdf.format(cal.getTime()));
                    }
                }else if (i == 6){
                    cal.add(cal.DATE,1);
                    if(cal.getTime().after(cal2.getTime())){
                        break;
                    }else {
                        dto.setDateEnd(sdf.format(cal2.getTime()));
                        dto.setDateStart(sdf.format(cal.getTime()));
                    }
                }
                int qty = service.queryByQty(dto);
                if (qty > 0){
                    passRate.setDateStart(dto.getDateStart());
                    passRate.setDateEnd(dto.getDateEnd());
                    List<PassRate> list1 = passRateService.queryByMouth(passRate);
                    int gmnga = 0,xmnga = 0,rmnga = 0;
                    if(list1.size() > 0){
                        gmnga = list1.get(0).getGmnga();
                        xmnga = list1.get(0).getXmnga();
                        rmnga = list1.get(0).getRmnga();
                    }
                    int tmnga = gmnga + xmnga + rmnga;
                    String scrapRate = df.format((qty*100/(float)tmnga));
                    ngRecord.setScrapRate(scrapRate);
                }else {
                    ngRecord.setScrapRate("0.00");
                }
                ngRecord.setDateStart("第" + i + "周");
                list.add(ngRecord);
            }
        }
        if ("3".equals(btn)){
            String dateStart = dto.getDateStart();
            Integer yearStart = Integer.parseInt(dateStart.substring(0,4));
            Integer mouthStart = Integer.parseInt(dateStart.substring(5,7));
            String dateEnd = dto.getDateEnd();
            Integer yearEnd = Integer.parseInt(dateEnd.substring(0,4));
            Integer mouthEnd = Integer.parseInt(dateEnd.substring(5,7)) + 1;
            Integer result = (yearEnd-yearStart)*12 + (mouthEnd-mouthStart);
            try {
                Date dateStart2 = sdf.parse(dateStart + "-01");
                cal.setTime(dateStart2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int i=0;i<result;i++){
                NgRecord ngRecord = new NgRecord();
                Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                dto.setDateStart(sdf2.format(cal.getTime()) + "-01");
                dto.setDateEnd(sdf2.format(cal.getTime()) + "-" + day);
                int qty = service.queryByQty(dto);
                if (qty > 0){
                    passRate.setDateEnd(dto.getDateEnd());
                    passRate.setDateStart(dto.getDateStart());
                    List<PassRate> list1 = passRateService.queryByMouth(passRate);
                    int gmnga = 0,xmnga = 0,rmnga = 0;
                    if(list1.size() > 0){
                        gmnga = list1.get(0).getGmnga();
                        xmnga = list1.get(0).getXmnga();
                        rmnga = list1.get(0).getRmnga();
                    }
                    int tmnga = gmnga + xmnga + rmnga;
                    String scrapRate = df.format((qty*100/(float)tmnga));
                    ngRecord.setScrapRate(scrapRate);
                }else {
                    ngRecord.setScrapRate("0.00");
                }
                ngRecord.setDateStart(sdf2.format(cal.getTime()));
                list.add(ngRecord);
                cal.add(cal.DATE,day);
            }
        }
        return new ResponseData(list);
    }

    /**
     * GP12统计PPM报表数据查询页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "wip/ng/record/queryByZissuetxt")
    @ResponseBody
    public ResponseData queryByZissuetxt(HttpServletRequest request, NgRecord dto){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        DecimalFormat df = new DecimalFormat("#0.00");
        List<NgRecord> list = new ArrayList<NgRecord>();
        List<PassRate> list1 = new ArrayList<PassRate>();
        String dateStart = dto.getDateStart();
        Integer year = Integer.parseInt(dateStart.substring(0,4));
        try {
            Date dateStart2 = sdf.parse(year + "-01-01");
            cal.setTime(dateStart2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PassRate passRate = new PassRate();
        passRate.setWerks(dto.getWerks());
        passRate.setLineId(dto.getLineId());
        passRate.setMatnr(dto.getMatnr());
        for (int i=1;i<=12;i++){
            NgRecord ngRecord = new NgRecord();
            Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            dto.setDateStart(sdf2.format(cal.getTime()) + "-01");
            dto.setDateEnd(sdf2.format(cal.getTime()) + "-" + day);
            passRate.setDateStart(sdf2.format(cal.getTime()) + "-01");
            passRate.setDateEnd(sdf2.format(cal.getTime()) + "-" + day);
            Integer qty = service.queryNgRecordByQty(dto);
            list1 = passRateService.queryByMouth(passRate);
            Double ppm = 0.00;
            if(qty > 0 && (list1.get(0).getGmnga()) > 0){
                ppm = Double.parseDouble(df.format(((float)qty/1000000/(list1.get(0).getGmnga()))));
            }
            ngRecord.setDateStart(i + "月");
            ngRecord.setQty(qty);
            ngRecord.setGmnga(list1.get(0).getGmnga());
            ngRecord.setPpm(ppm);
            list.add(ngRecord);
            cal.add(cal.DATE,day);
        }
        return new ResponseData(list);
    }
}

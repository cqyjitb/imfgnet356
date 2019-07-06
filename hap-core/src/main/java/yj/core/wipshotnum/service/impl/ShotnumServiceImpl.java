package yj.core.wipshotnum.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.crhd.dto.Crhd;
import yj.core.crhd.mapper.CrhdMapper;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.mouldcavity.mapper.MouldcavityMapper;
import yj.core.shiftstime.dto.Shiftstime;
import yj.core.shiftstime.mapper.ShiftstimeMapper;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.mapper.ShotnumMapper;
import yj.core.wipshotnum.service.IShotnumService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ShotnumServiceImpl extends BaseServiceImpl<Shotnum> implements IShotnumService {

    @Autowired
    private ShotnumMapper shotnumMapper;
    @Autowired
    private InputLogMapper inputLogMapper;
    @Autowired
    private MouldcavityMapper mouldcavityMapper;
    @Autowired
    private ShiftstimeMapper shiftstimeMapper;
    @Autowired
    private MarcMapper marcMapper;
    private Logger logger = LoggerFactory.getLogger(ShotnumServiceImpl.class);
    @Autowired
    private CrhdMapper crhdMapper;

    @Override
    public List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext) {

        List<Shotnum> list1 = shotnumMapper.selectShotnum(dto.getWerks(),dto.getFevor(),null,null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list2 = new ArrayList<Shotnum>();
        List<InputLog> inputLog = new ArrayList<InputLog>();
        Shotnum shotnum = new Shotnum();
        Marc marc = new Marc();
        Shiftstime shiftstime = new Shiftstime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
        Calendar sTime = Calendar.getInstance();
        Calendar eTime = Calendar.getInstance();
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("#0.00");
        if(list1.size() > 0) {
            if ("Y".equals(dto.getTotal())) {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if (list1.get(i).getArbpl().equals(list1.get(j).getArbpl())) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(), shotnum.getFevor(), null,
                            shotnum.getArbpl(), dto.getPrdDateAfter(), dto.getPrdDateBefore());
                    String minTime = list2.get(0).getPrdDate();
                    String maxTime = list2.get(0).getPrdDate();
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum != null && mdnum > 1) {
                            Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * (mdnum - 1));
                            shotNum = shotNum + shotNum1;
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + shotNum1 * 2 * marc.getBrgew();
                            }
                        } else {
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + ((list2.get(a).getShotEnd() - list2.get(a).getShotStart())) * marc.getBrgew();
                            }
                        }
                        if (minTime.compareTo(list2.get(a).getPrdDate()) > 0) {
                            minTime = list2.get(a).getPrdDate();
                        } else {
                            maxTime = list2.get(a).getPrdDate();
                        }
                        if (list2.get(a).getShotStart() < startMin) {
                            startMin = list2.get(a).getShotStart();
                        }
                        if (list2.get(a).getShotEnd() > endMax) {
                            endMax = list2.get(a).getShotEnd();
                        }
                    }
                    shotnum.setPrdDateAfter(minTime);
                    shotnum.setPrdDateBefore(maxTime);
                    shotNum = (int) (shotNum + (endMax - startMin));
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    shiftstime = shiftstimeMapper.selectByShift("1");
                    Shiftstime shiftstime2 = shiftstimeMapper.selectByShift("3");
                    String startDate = (minTime + " " + shiftstime.getBgsTime());
                    try {
                        cal.setTime(sf.parse(maxTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cal.add(cal.DATE, 1);
                    String endDate = sf.format(cal.getTime()) + " " + shiftstime2.getBgeTime();
                    yeild = yeild + inputLogMapper.selectByOrderno(shotnum.getArbpl(), startDate, endDate);
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(shotNum - yeild);
                    /*if (shotnum.getShifts().equals("1")) {
                        shotnum.setShifts("白班");
                    } else if (shotnum.getShifts().equals("2")) {
                        shotnum.setShifts("中班");
                    } else if (shotnum.getShifts().equals("3")) {
                        shotnum.setShifts("夜班");
                    }*/
                }
            } else {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if ((list1.get(i).getArbpl().equals(list1.get(j).getArbpl())) && (list1.get(i).getPrdDate().equals(list1.get(j).getPrdDate())) &&
                                (list1.get(i).getShifts().equals(list1.get(j).getShifts()))) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(), shotnum.getFevor(), shotnum.getShifts(),
                            shotnum.getArbpl(), shotnum.getPrdDate(), shotnum.getPrdDate());
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum == null) {
                            mdnum = 1;
                        }
                        Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * mdnum);
                        shotNum = shotNum + shotNum1;
                        if (marc != null) {
                            if (marc.getBrgew() == null) {
                                marc.setBrgew(0.0);
                            }
                            grgew = grgew + (shotNum1 * marc.getBrgew());
                        }
                        if (a > 0) {
                            if (list2.get(a).getShotEnd() > endMax) {
                                endMax = list2.get(a).getShotEnd();
                            }
                            if (list2.get(a).getShotStart() < startMin) {
                                startMin = list2.get(a).getShotStart();
                            }
                        }
                    }
                    shotnum.setPrdDateAfter(shotnum.getPrdDate());
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    shiftstime = shiftstimeMapper.selectByShift(shotnum.getShifts());
                    /*String date = null;
                    try {
                        date = sfWeek.format(sf.parse(shotnum.getPrdDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/
                    try {
                        cal2.setTime(sf.parse(shotnum.getPrdDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //if("星期日".equals(date)){
                    Integer week = cal2.get(Calendar.WEEK_OF_YEAR) % 2;
                    if ((cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)&&((shotnum.getShiftSeq().equals("0"))
                            ||((shotnum.getShiftSeq().equals("1"))&&(week == 1))||((shotnum.getShiftSeq().equals("2"))&&(week == 0)))) {
                        logger.debug(shotnum.getZpgdbar() + " ******this is sunday*****");
                        String startDate = shotnum.getPrdDate() + " " + shiftstime.getZbgsTime();
                        String endDate = shotnum.getPrdDate() + " " + shiftstime.getZbgeTime();
                        try {
                            sTime.setTime(st.parse(shiftstime.getZbgsTime()));
                            eTime.setTime(st.parse(shiftstime.getZbgeTime()));
                            cal.setTime(sf.parse(shotnum.getPrdDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        cal.add(cal.DATE, 1);
                        if((sTime.after(eTime))&&(shotnum.getShifts().equals("2"))){
                            endDate = sf.format(cal.getTime()) + " " + shiftstime.getZbgeTime();
                        }else if((sTime.before(eTime))&&(shotnum.getShifts().equals("3"))){
                            startDate = sf.format(cal.getTime()) + " " + shiftstime.getZbgsTime();
                            endDate = sf.format(cal.getTime()) + " " + shiftstime.getZbgeTime();
                        }else if((sTime.after(eTime))&&(shotnum.getShifts().equals("3"))){
                            endDate = sf.format(cal.getTime()) + " " + shiftstime.getZbgeTime();
                        }
                        yeild = yeild + inputLogMapper.selectByOrderno(shotnum.getArbpl(), startDate, endDate);
                    } else {
                        String startDate = shotnum.getPrdDate() + " " + shiftstime.getBgsTime();
                        String endDate = shotnum.getPrdDate();
                        if (shotnum.getShifts().equals("3")) {
                            try {
                                cal.setTime(sf.parse(endDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            cal.add(cal.DATE, 1);
                            endDate = sf.format(cal.getTime()) + " " + shiftstime.getBgeTime();
                        } else {
                            endDate = endDate + " " + shiftstime.getBgeTime();
                        }
                        yeild = yeild + inputLogMapper.selectByOrderno(shotnum.getArbpl(), startDate, endDate);
                    }
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(shotNum - yeild);
                    /*if (shotnum.getShifts().equals("1")) {
                        shotnum.setShifts("白班");
                    } else if (shotnum.getShifts().equals("3")) {
                        shotnum.setShifts("夜班");
                    } else if (shotnum.getShifts().equals("2")) {
                        shotnum.setShifts("中班");
                    }*/
                }
                List<Crhd> crhds = crhdMapper.selectByVeran(dto.getWerks(),dto.getFevor(),null);
                Integer num = list.size();
                Date prdDateBefore = null;
                Date prdDateAfter = null;
                try {
                    prdDateBefore = sf.parse(dto.getPrdDateAfter());
                    prdDateAfter = sf.parse(dto.getPrdDateBefore());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal2.setTime(prdDateBefore);
                cal.setTime(prdDateAfter);
                cal.add(cal.DATE, 1);
                Date endTime = cal.getTime();
                while(cal2.getTime().before(endTime)){
                    Integer shift = 2;
                    Calendar cal3 = Calendar.getInstance();
                    Integer week = cal2.get(Calendar.WEEK_OF_YEAR) % 2;
                    if ((cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)&&((crhds.get(0).getShiftSeq().equals("0"))
                            ||((crhds.get(0).getShiftSeq().equals("1"))&&(week == 1))||((crhds.get(0).getShiftSeq().equals("2"))&&(week == 0)))) {
                        shift = 3;
                    }
                    for (int a = 0; a < shift; a++) {
                        List<Crhd> crhd = new ArrayList<Crhd>();
                        String shifts = null;
                        if (a == 0) {
                            shifts = "1";
                        } else if (a == 1) {
                            shifts = "3";
                        } else if (a == 2) {
                            shifts = "2";
                        }
                        if(num > 0){
                            for (int i = 0; i < crhds.size(); i++) {
                                int j;
                                for(j=0;j<num;j++){
                                    if((crhds.get(i).getArbpl().equals(list.get(j).getArbpl()))&&(shifts.equals(list.get(j).getShifts()))
                                            &&(sf.format(cal2.getTime()).equals(list.get(j).getPrdDate()))){
                                        break;
                                    }
                                }
                                if(j == num){
                                    crhd.add(crhds.get(i));
                                }
                            }
                        }else{
                            crhd.addAll(crhds);
                        }
                        if(crhd.size() > 0) {
                            shiftstime = shiftstimeMapper.selectByShift(shifts);
                            for (int i = 0; i < crhd.size(); i++) {
                                Integer yeild = 0;
                                Shotnum shotnum1 = new Shotnum();
                                shotnum1.setWerks(dto.getWerks());
                                shotnum1.setFevor(dto.getFevor());
                                shotnum1.setTxt(crhd.get(i).getTxt());
                                shotnum1.setArbpl(crhd.get(i).getArbpl());
                                shotnum1.setKtext(crhd.get(i).getKetxt());
                                shotnum1.setPrdDateAfter(sf.format(cal2.getTime()));
                                shotnum1.setShifts(shifts);
                                shotnum1.setShotStart(0L);
                                shotnum1.setShotEnd(0L);
                                shotnum1.setShotNum(0);
                                shotnum1.setBrgew("0.00");
                                if (shift == 3) {
                                    String startDate = shotnum.getPrdDate() + " " + shiftstime.getZbgsTime();
                                    String endDate = shotnum.getPrdDate() + " " + shiftstime.getZbgeTime();
                                    try {
                                        sTime.setTime(st.parse(shiftstime.getZbgsTime()));
                                        eTime.setTime(st.parse(shiftstime.getZbgeTime()));
                                        cal3.setTime(cal2.getTime());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    cal3.add(cal.DATE, 1);
                                    if((sTime.after(eTime))&&(shifts.equals("2"))){
                                        endDate = sf.format(cal3.getTime()) + " " + shiftstime.getZbgeTime();
                                    }else if(shifts.equals("3")){
                                        endDate = sf.format(cal3.getTime()) + " " + shiftstime.getZbgeTime();
                                        if(sTime.before(eTime)){
                                            startDate = sf.format(cal3.getTime()) + " " + shiftstime.getZbgsTime();
                                        }
                                    }
                                    yeild = inputLogMapper.selectByOrderno(crhd.get(i).getArbpl(), startDate, endDate);
                                } else {
                                    String startDate = sf.format(cal2.getTime()) + " " + shiftstime.getBgsTime();
                                    String endDate = sf.format(cal2.getTime()) + " " + shiftstime.getBgeTime();
                                    if (shifts.equals("3")) {
                                        cal3.setTime(cal2.getTime());
                                        cal3.add(cal3.DATE, 1);
                                        endDate = sf.format(cal3.getTime()) + " " + shiftstime.getBgeTime();
                                    }
                                    yeild = inputLogMapper.selectByOrderno(crhd.get(i).getArbpl(), startDate, endDate);
                                }
                                shotnum1.setYeild(yeild);
                                shotnum1.setWasteNum(0-yeild);
                                /*if (shifts.equals("1")) {
                                    shotnum1.setShifts("白班");
                                } else if (shifts.equals("3")) {
                                    shotnum1.setShifts("夜班");
                                } else if (shifts.equals("2")) {
                                    shotnum1.setShifts("中班");
                                }*/
                                list.add(shotnum1);
                            }
                        }
                    }
                    cal2.add(cal2.DATE, 1);
                }
            }
        }
        return list;
    }

    @Override
    public int insertRow(Shotnum shot) {
        return shotnumMapper.insertRow(shot);
    }

    @Override
    public List<Shotnum> isExit(String werks, String arbpl, String prd_date, String shifts) {
        return shotnumMapper.isExit(werks,arbpl,prd_date,shifts);
    }

    @Override
    public List<Shotnum> queryShotnum(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list = shotnumMapper.queryShotnum(dto);
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                Shotnum shotnum = list.get(i);
                if(shotnum.getMdno() == null || shotnum.getMdno().equals("")){
                    shotnum.setMdnum(1);
                }else{
                    Integer mdnum = mouldcavityMapper.selectByMatnr(shotnum.getMatnr(),shotnum.getMdno());
                    if(mdnum == null){
                        mdnum = 1;
                    }
                    shotnum.setMdnum(mdnum);
                }
            }
        }
        return list;
    }

    @Override
    public String updateShotnum(IRequest requestContext, List<Shotnum> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Shotnum shotnum = dto.get(i);
                shotnum.setLastUpdatedBy(Long.valueOf(userId));
                shotnum.setLastUpdateDate(new Date());
                shotnumMapper.updateShotnum(shotnum);
            }
        }
        return null;
    }

    @Override
    public String deleteShotnum(List<Shotnum> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                shotnumMapper.deleteShotnum(dto.get(i));
            }
        }
        return null;
    }

    @Override
    public List<Shotnum> selectShotnum2(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list1 = shotnumMapper.selectShotnum(dto.getWerks(),dto.getFevor(),null,null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list2 = new ArrayList<Shotnum>();
        Shotnum shotnum = new Shotnum();
        Marc marc = new Marc();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sTime = Calendar.getInstance();
        Calendar eTime = Calendar.getInstance();
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("#0.00");
        if(list1.size() > 0) {
            if ("Y".equals(dto.getTotal())) {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if (list1.get(i).getArbpl().equals(list1.get(j).getArbpl())) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0,wasteNum = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(), shotnum.getFevor(), null,
                            shotnum.getArbpl(), dto.getPrdDateAfter(), dto.getPrdDateBefore());
                    String minTime = list2.get(0).getPrdDate();
                    String maxTime = list2.get(0).getPrdDate();
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum != null && mdnum > 1) {
                            Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * (mdnum - 1));
                            shotNum = shotNum + shotNum1;
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + shotNum1 * 2 * marc.getBrgew();
                            }
                        } else {
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + ((list2.get(a).getShotEnd() - list2.get(a).getShotStart())) * marc.getBrgew();
                            }
                        }
                        if (minTime.compareTo(list2.get(a).getPrdDate()) > 0) {
                            minTime = list2.get(a).getPrdDate();
                        } else{
                            maxTime = list2.get(a).getPrdDate();
                        }
                        if (list2.get(a).getShotEnd() > endMax) {
                            endMax = list2.get(a).getShotEnd();
                        }
                        if (list2.get(a).getShotStart() < startMin) {
                            startMin = list2.get(a).getShotStart();
                        }

                    }
                    shotnum.setPrdDateAfter(minTime);
                    shotnum.setPrdDateBefore(maxTime);
                    shotNum = (int) (shotNum + (endMax - startMin));
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    Date startDate = null;
                    try {
                        startDate = sf.parse(minTime);
                        eTime.setTime(sf.parse(maxTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    eTime.add(eTime.DATE, 1);
                    Date endDate = eTime.getTime();
                    sTime.setTime(startDate);
                    while(sTime.getTime().before(endDate)){
                        InputLog inputLog1 = inputLogMapper.selectByOrderno2(shotnum.getWerks(),shotnum.getArbpl(),null, sf.format(sTime.getTime()));
                        yeild = yeild + inputLog1.getYeild().intValue();
                        wasteNum = wasteNum + inputLog1.getWorkScrap().intValue()+inputLog1.getRowScrap().intValue();
                        sTime.add(sTime.DATE, 1);
                    }
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(wasteNum);
                    shotnum.setDifferentNum(yeild + wasteNum - shotnum.getShotNum());
                }
            } else {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if((list1.get(i).getArbpl().equals(list1.get(j).getArbpl()))&&(list1.get(i).getShifts().equals(list1.get(j).getShifts()))
                                && (list1.get(i).getPrdDate().equals(list1.get(j).getPrdDate()))) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(), shotnum.getFevor(), shotnum.getShifts(),
                            shotnum.getArbpl(), shotnum.getPrdDate(), shotnum.getPrdDate());
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum == null) {
                            mdnum = 1;
                        }
                        Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * mdnum);
                        shotNum = shotNum + shotNum1;
                        if (marc != null) {
                            if (marc.getBrgew() == null) {
                                marc.setBrgew(0.0);
                            }
                            grgew = grgew + (shotNum1 * marc.getBrgew());
                        }
                        if (a > 0) {
                            if (list2.get(a).getShotStart() < startMin) {
                                startMin = list2.get(a).getShotStart();
                            }
                            if (list2.get(a).getShotEnd() > endMax) {
                                endMax = list2.get(a).getShotEnd();
                            }
                        }
                    }
                    shotnum.setPrdDateAfter(shotnum.getPrdDate());
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    InputLog inputLog1 = inputLogMapper.selectByOrderno2(shotnum.getWerks(),shotnum.getArbpl(),shotnum.getShifts(), shotnum.getPrdDate());
                    shotnum.setYeild(inputLog1.getYeild().intValue());
                    shotnum.setWasteNum(inputLog1.getWorkScrap().intValue()+inputLog1.getRowScrap().intValue());
                    shotnum.setDifferentNum(shotnum.getYeild() + shotnum.getWasteNum() - shotnum.getShotNum());
                }
                List<Crhd> crhds = crhdMapper.selectByVeran(dto.getWerks(),dto.getFevor(),null);
                Integer num = list.size();
                Date prdDateBefore = null;
                Date prdDateAfter = null;
                try {
                    prdDateBefore = sf.parse(dto.getPrdDateAfter());
                    prdDateAfter = sf.parse(dto.getPrdDateBefore());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal2.setTime(prdDateBefore);
                cal.setTime(prdDateAfter);
                cal.add(cal.DATE, 1);
                Date endTime = cal.getTime();
                while(cal2.getTime().before(endTime)){
                    Integer shift = 2;
                    Integer week = cal2.get(Calendar.WEEK_OF_YEAR) % 2;
                    if ((cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)&&((crhds.get(0).getShiftSeq().equals("0"))
                            ||((crhds.get(0).getShiftSeq().equals("1"))&&(week == 1))||((crhds.get(0).getShiftSeq().equals("2"))&&(week == 0)))) {
                        shift = 3;
                    }
                    for (int a = 0; a < shift; a++) {
                        List<Crhd> crhd = new ArrayList<Crhd>();
                        String shifts = null;
                        if (a == 0) {
                            shifts = "1";
                        } else if (a == 2) {
                            shifts = "2";
                        } else if (a == 1) {
                            shifts = "3";
                        }
                        if (num > 0) {
                            for (int i = 0; i < crhds.size(); i++) {
                                int j;
                                for (j = 0; j < num; j++) {
                                    if ((sf.format(cal2.getTime()).equals(list.get(j).getPrdDate()))&&(shifts.equals(list.get(j).getShifts()))
                                                &&(crhds.get(i).getArbpl().equals(list.get(j).getArbpl()))) {
                                        break;
                                    }
                                }
                                if (j == num) {
                                    crhd.add(crhds.get(i));
                                }
                            }
                        } else {
                            crhd.addAll(crhds);
                        }
                        if (crhd.size() > 0) {
                            for (int i = 0; i < crhd.size(); i++) {
                                InputLog inputLog1 = new InputLog();
                                Shotnum shotnum1 = new Shotnum();
                                shotnum1.setWerks(dto.getWerks());
                                shotnum1.setFevor(dto.getFevor());
                                shotnum1.setTxt(crhd.get(i).getTxt());
                                shotnum1.setArbpl(crhd.get(i).getArbpl());
                                shotnum1.setKtext(crhd.get(i).getKetxt());
                                shotnum1.setPrdDateAfter(sf.format(cal2.getTime()));
                                shotnum1.setShifts(shifts);
                                shotnum1.setShotStart(0L);
                                shotnum1.setShotEnd(0L);
                                shotnum1.setShotNum(0);
                                shotnum1.setBrgew("0.00");
                                inputLog1 = inputLogMapper.selectByOrderno2(shotnum1.getWerks(), crhd.get(i).getArbpl(), shifts, sf.format(cal2.getTime()));
                                shotnum1.setYeild(inputLog1.getYeild().intValue());
                                shotnum1.setWasteNum(inputLog1.getWorkScrap().intValue() + inputLog1.getRowScrap().intValue());
                                shotnum1.setDifferentNum(shotnum1.getYeild() + shotnum1.getWasteNum());
                                list.add(shotnum1);
                            }
                        }
                    }
                    cal2.add(cal2.DATE, 1);
                }
            }
        }
        return list;
    }

    @Override
    public List<Shotnum> selectShotnum3(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list1 = shotnumMapper.selectByPrdDate(dto.getWerks(),null,null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list2 = new ArrayList<Shotnum>();
        Shotnum shotnum = new Shotnum();
        Marc marc = new Marc();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sTime = Calendar.getInstance();
        Calendar eTime = Calendar.getInstance();
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("#0.00");
        if(list1.size() > 0) {
            if ("Y".equals(dto.getTotal())) {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if (list1.get(i).getArbpl().equals(list1.get(j).getArbpl()) && list1.get(i).getFevor().equals(list1.get(j).getFevor())) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0,wasteNum = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectByPrdDate(shotnum.getWerks(),shotnum.getFevor(),shotnum.getArbpl(),dto.getPrdDateAfter(),dto.getPrdDateBefore());
                    String minTime = list2.get(0).getPrdDate();
                    String maxTime = list2.get(0).getPrdDate();
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum != null && mdnum > 1) {
                            Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * (mdnum - 1));
                            shotNum = shotNum + shotNum1;
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + shotNum1 * 2 * marc.getBrgew();
                            }
                        } else {
                            if (marc != null) {
                                if (marc.getBrgew() == null) {
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + ((list2.get(a).getShotEnd() - list2.get(a).getShotStart())) * marc.getBrgew();
                            }
                        }
                        if (list2.get(a).getShotEnd() > endMax) {
                            endMax = list2.get(a).getShotEnd();
                        }
                        if (list2.get(a).getShotStart() < startMin) {
                            startMin = list2.get(a).getShotStart();
                        }
                        if (minTime.compareTo(list2.get(a).getPrdDate()) > 0) {
                            minTime = list2.get(a).getPrdDate();
                        } else{
                            maxTime = list2.get(a).getPrdDate();
                        }
                    }
                    shotnum.setPrdDateAfter(minTime);
                    shotnum.setPrdDateBefore(maxTime);
                    shotNum = (int) (shotNum + (endMax - startMin));
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    Date startDate = null;
                    try {
                        startDate = sf.parse(minTime);
                        eTime.setTime(sf.parse(maxTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    eTime.add(eTime.DATE, 1);
                    Date endDate = eTime.getTime();
                    sTime.setTime(startDate);
                    while(sTime.getTime().before(endDate)){
                        InputLog inputLog1 = inputLogMapper.selectByOrderno2(shotnum.getWerks(),shotnum.getArbpl(),null, sf.format(sTime.getTime()));
                        wasteNum = wasteNum + inputLog1.getWorkScrap().intValue()+inputLog1.getRowScrap().intValue();
                        yeild = yeild + inputLog1.getYeild().intValue();
                        sTime.add(sTime.DATE, 1);
                    }
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(wasteNum);
                    shotnum.setDifferentNum(yeild + wasteNum - shotnum.getShotNum());
                }
            } else {
                for (int i = 0; i < list1.size(); i++) {
                    list.add(list1.get(i));
                    for (int j = i + 1; j < list1.size(); j++) {
                        if((list1.get(i).getArbpl().equals(list1.get(j).getArbpl()))&&(list1.get(i).getShifts().equals(list1.get(j).getShifts()))
                                && (list1.get(i).getPrdDate().equals(list1.get(j).getPrdDate())) &&(list1.get(i).getFevor().equals(list1.get(j).getFevor()))) {
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    Integer mdnum = 1, shotNum = 0, yeild = 0;
                    Double grgew = 0.00;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(), shotnum.getFevor(), shotnum.getShifts(),
                            shotnum.getArbpl(), shotnum.getPrdDate(), shotnum.getPrdDate());
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for (int a = 0; a < list2.size(); a++) {
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(), list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if (mdnum == null) {
                            mdnum = 1;
                        }
                        Integer shotNum1 = ((int) (list2.get(a).getShotEnd() - list2.get(a).getShotStart()) * mdnum);
                        shotNum = shotNum + shotNum1;
                        if (a > 0) {
                            if (startMin > list2.get(a).getShotStart()) {
                                startMin = list2.get(a).getShotStart();
                            }
                            if (endMax < list2.get(a).getShotEnd()) {
                                endMax = list2.get(a).getShotEnd();
                            }
                        }
                        if (marc != null) {
                            if (marc.getBrgew() == null) {
                                marc.setBrgew(0.0);
                            }
                            grgew = grgew + (shotNum1 * marc.getBrgew());
                        }
                    }
                    shotnum.setPrdDateAfter(shotnum.getPrdDate());
                    shotnum.setShotEnd(endMax);
                    shotnum.setShotStart(startMin);
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(df.format(grgew));
                    InputLog inputLog1 = inputLogMapper.selectByOrderno2(shotnum.getWerks(),shotnum.getArbpl(),shotnum.getShifts(), shotnum.getPrdDate());
                    shotnum.setYeild(inputLog1.getYeild().intValue());
                    shotnum.setWasteNum(inputLog1.getWorkScrap().intValue()+inputLog1.getRowScrap().intValue());
                    shotnum.setDifferentNum(shotnum.getYeild() + shotnum.getWasteNum() - shotnum.getShotNum());
                }
            }
            for (int i=0;i<list.size();i++){
                if (list.get(i).getDifferentNum() == 0){
                    list.remove(i);
                    i--;
                }
            }
        }
        return list;
    }
}
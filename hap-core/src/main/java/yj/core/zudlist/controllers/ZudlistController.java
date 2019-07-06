package yj.core.zudlist.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.OracleConn.OracleConn;
import yj.core.dftdtl.dto.Dftdtl;
import yj.core.dftdtl.service.IDftdtlService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.service.IZudlistService;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ZudlistController extends BaseController {

    @Autowired
    private IZudlistService service;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IDftdtlService dftdtlService;

    @RequestMapping(value = "/wip/zudlist/query")
    @ResponseBody
    public ResponseData query(Zudlist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/zudlist/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Zudlist> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zudlist/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Zudlist> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     * 处理不合格品审理单1查询页面请求 918100064
     *
     * @param dto
     * @param request
     * @return
     */

    @RequestMapping(value = "/wip/zudlist/selectZudlist")
    @ResponseBody
    public ResponseData selectZudlist(Zudlist dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Zudlist zudlist = resultFormat(dto);
        return new ResponseData(service.selectZudlist(requestContext, zudlist));
    }

    /**
     * 修改创建日期的方法 918100064
     *
     * @param dto
     * @return
     */
    public Zudlist resultFormat(Zudlist dto) {
        if (dto.getCreationDateBefore() != null) {
            String cdBefore = dto.getCreationDateBefore().replace("00:00:00", "23:59:59");
            dto.setCreationDateBefore(cdBefore);
        }
        return dto;
    }

    @RequestMapping(value = "/wip/zudlist/balutChartReport")
    @ResponseBody
    public ResponseData selectForBalutChartReport(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String werks = request.getParameter("werks");
        String lineId = request.getParameter("line_id");
        String deptId = request.getParameter("deptId");
        String matnr = request.getParameter("pmatnr");
        String datestart = request.getParameter("datestart").replaceAll("/", "-");
        String dateend = "";
        String classgrp = request.getParameter("classgrp");
        DecimalFormat df3 = new DecimalFormat("###.00");
        List result = new ArrayList();
        //根据统计范围 计算查询截止日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dates = sdf.parse(datestart);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dates);
            if (classgrp.equals("D")){
                dateend = datestart;
            }

            if (classgrp.equals("W")){
                rightNow.add(Calendar.DAY_OF_YEAR,7);
                Date datee=rightNow.getTime();
                dateend = sdf.format(datee);
            }

            if (classgrp.equals("M")){
                rightNow.add(Calendar.MONTH,1);
                Date datee=rightNow.getTime();
                dateend = sdf.format(datee);
            }

            List<Zudlist> list = service.selectForBaltuChart(lineId,matnr,datestart,dateend);
            List<Dftdtl> listdftdtl = dftdtlService.selectbyWerksAndMatnr(werks,matnr);
            if (list.size() == 0){
                rs.setSuccess(false);
                rs.setMessage("统计范围内没有符合条件的数据！");
                return rs;
            }else{
                String xiangmu[] = new String[list.size()];
                String num[] = new String[list.size()];
                Double rate[] = new Double[list.size()];
                String rateall[] = new String[list.size()];
                double sum = 0D;
                Double ratealltmp = 0D;
                for (int i=0;i<list.size();i++){
                    sum = sum + list.get(i).getZdnum();
                }
                for (int i=0;i<list.size();i++){
                    //准备项目数据
                    for (int j=0;j<listdftdtl.size();j++){
                        if (listdftdtl.get(j).getTlevelcode().equals(list.get(i).getZissuetxt())){
                            xiangmu[i] = listdftdtl.get(j).getZtext();
                            break;
                        }
                    }

                    //准备数量
                    num[i] = list.get(i).getZdnum().toString();

                    //准备不合格率
                    rate[i] = list.get(i).getZdnum() / sum * 100;
                    ratealltmp = ratealltmp + rate[i];
                    rateall[i] = df3.format(ratealltmp);

                }
                result.add(xiangmu);
                result.add(num);
                result.add(rateall);
                result.add(rate);
                result.add(sum);
                rs.setRows(result);
                rs.setSuccess(true);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @RequestMapping(value = "/wip/zudlist/passrateReport")
    @ResponseBody
    public ResponseData selectForPassReport(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String werks = request.getParameter("werks");
        String lineId = request.getParameter("line_id");
        String deptId = request.getParameter("deptId");
        String datestart = request.getParameter("datestart").replaceAll("/", "-");
        String dateend = request.getParameter("dateend").replaceAll("/", "-");
        String matnr = request.getParameter("pmatnr");
        String lineIdlike = lineId.substring(0, 11);
        //根据日期范围查询工废数据
        List<Zudlist> listgf = new ArrayList<>();
        List<Zudlist> listlf = new ArrayList<>();

        listgf = service.selectForPassrateReportgf(lineId, matnr, datestart, dateend);
        listlf = service.selectForPassrateReportlf(lineId, matnr, datestart, dateend);

        List listdate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateend1 = "";
        try {
            Date start_date = sdf.parse(datestart);
            Date end_date = sdf.parse(dateend);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start_date);
            Calendar tempEnd = Calendar.getInstance();
            Calendar tmpEnd2 = Calendar.getInstance();
            tempEnd.setTime(end_date);
            tmpEnd2.setTime(end_date);

            tmpEnd2.add(Calendar.DATE, 1);
            dateend1 = sdf.format(tmpEnd2.getTime()) + " 08:00:00";
            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
                listdate.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            rs.setMessage(e.getMessage());
            rs.setSuccess(false);
        }

        ServerSetting serverSetting = new ServerSetting();
        if (!lineId.equals("")) {

            serverSetting = serverSettingService.selectByLineId(werks, lineId);
            if (serverSetting == null) {
                rs.setSuccess(false);
                rs.setMessage("未能获取到产线的服务器配置数据！");
                return rs;
            }
        }

        //根据日期范围查询装箱数据
        String datestart1 = datestart + " 08:00:00";

        WebServerHelp webServerHelp = new WebServerHelp();
        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(), webServerHelp.getMesOraUserName(), webServerHelp.getMesOraPass(), webServerHelp.getMesOraDriver());
        String sql = "select a.barcode_id,a.status,b.item_code,a.CREATION_DATE from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel a inner join " + serverSetting.getDbUsername() + ".Mtl_Barcode b on a.barcode_id = b.barcode_id";
        String wheresql = " where a.status = '0' and a.CREATION_DATE between to_date('" + datestart1 + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + dateend1 + "','yyyy-mm-dd hh24:mi:ss') and b.item_code = '" + matnr + "'";
        sql = sql + wheresql;
        List<Map<String, Object>> listora = new ArrayList<Map<String, Object>>();
        try {
            listora = oracleConn.select(sql);

        } catch (Exception e) {
            e.printStackTrace();
            rs.setMessage(e.getMessage());
            rs.setSuccess(false);
        }
        String date[] = new String[listdate.size()];
        double jjrate[] = new double[listdate.size()];
        double yzrate[] = new double[listdate.size()];
        double rate[] = new double[listdate.size()];
        for (int i = 0; i < listdate.size(); i++) {
            date[i] = listdate.get(i).toString();
            double numgf = 0D;
            double numlf = 0D;
            double numzx = 0D;
            double numtl = 0D;
            //按天工废汇总
            for (int j1 = 0; j1 < listgf.size(); j1++) {
                if (listgf.get(j1).getGstrp().equals(listdate.get(i).toString())) {
                    numgf = numgf + listgf.get(j1).getZdnum();
                }
            }
            //按天料废汇总
            for (int j2 = 0; j2 < listlf.size(); j2++) {
                if (listlf.get(j2).getGstrp().equals(listdate.get(i).toString())) {
                    numgf = numgf + listlf.get(j2).getZdnum();
                }
            }

            //按天投料数量汇总
            for (int j3 = 0; j3 < listora.size(); j3++) {
                Calendar tempcal = Calendar.getInstance();
                Calendar tmps = Calendar.getInstance();
                Calendar tmpe = Calendar.getInstance();
                try {
                    String cal = listora.get(j3).get("CREATION_DATE").toString().substring(0, 19);
                    tempcal.setTime(sdf2.parse(cal));
                    String time = listora.get(j3).get("CREATION_DATE").toString();
                    time = listdate.get(i).toString().substring(0, 10) + " 08:00:00";
                    tmps.setTime(sdf2.parse(time));
                    tmpe.setTime(sdf2.parse(time));
                    tmpe.add(Calendar.DATE, 1);

                    if (tmps.before(tempcal)) {

                        if (tempcal.before(tmpe)) {
                            numzx = numzx + 1;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    rs.setMessage(e.getMessage());
                    rs.setSuccess(false);
                }
            }
            numtl = numzx + numlf + numgf;
            if (numtl == 0) {
                jjrate[i] = 0;
                yzrate[i] = 0;
                rate[i] = 0;
            } else {
                DecimalFormat df3 = new DecimalFormat("###.00");
                df3.format((numzx + numlf) / numtl * 100);
                jjrate[i] = Double.parseDouble(df3.format((numzx + numlf) / numtl * 100));
                yzrate[i] = Double.parseDouble(df3.format((numzx + numgf) / numtl * 100));
                rate[i] = Double.parseDouble(df3.format(numzx / numtl * 100));
            }

        }
        List result = new ArrayList();
//            result.add(datestr);
//            result.add(jjratestr);
//            result.add(yzratestr);
//            result.add(ratestr);
        result.add(date);
        result.add(jjrate);
        result.add(yzrate);
        result.add(rate);
        rs.setSuccess(true);
        rs.setRows(result);

        return rs;
    }
}
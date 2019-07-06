package yj.core.fevor.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.HanaCon.HanaCon;
import yj.core.OracleConn.OracleConn;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.dto.Zwipqhz;
import yj.core.fevor.service.IFevorService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.service.IZwipqService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FevorController extends BaseController {
    @Autowired
    private IFevorService service;
    @Autowired
    private IZwipqService zwipqService;
    @Autowired
    private IServerSettingService serverSettingService;

    @RequestMapping(value = {"/sap/fevor/selectFevor2"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectfevor2(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        List<Fevor> list = new ArrayList<>();
        list = service.selectFevor2(null);

        if (list.size() > 0){
            for (int i =0;i<list.size();i++){
                list.get(i).setTxt(list.get(i).getTxt().replaceAll("汽车机加",""));
            }
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     * 产线在制队列汇总查询页面请求 918100064
     * @param request
     * @return
     */
    @RequestMapping(value = "/sap/fevor/queryZwipqhz")
    @ResponseBody
    public ResponseData queryZwipqhz(HttpServletRequest request) {
        String unitCode = request.getParameter("unitCode");
        String lineId = request.getParameter("lineId");
        String total = request.getParameter("total");
        IRequest requestContext = createRequestContext(request);
        List<Zwipqhz> list = new ArrayList<Zwipqhz>();
        List<Zwipqhz> list1 = service.queryZwipqhz(requestContext,unitCode,lineId);
        if(list1.size() > 0){
            for(int i=0;i<list1.size();i++){
                List<Zwipq> zwipq = new ArrayList<Zwipq>();
                List<Zwipq> zwipq2 = new ArrayList<Zwipq>();
                Zwipq zwipq3 = new Zwipq();
                Zwipq zwipq4 = new Zwipq();
                String werks = list1.get(i).getWerks();
                String pkgLineId = list1.get(i).getLineId();
                String matnr = list1.get(i).getMatnr2();
                ServerSetting serverSetting = serverSettingService.selectByLineId(werks,pkgLineId);
                if("Y".equals(total)){
                    zwipq = zwipqService.selectcharg(pkgLineId,matnr);
                    if(zwipq.size() > 0){
                        for(int j= 0;j<zwipq.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg(zwipq.get(j).getCharg());
                            Integer num = zwipqService.selectByzsxnum(pkgLineId,matnr,zwipq.get(j).getCharg()) + zwipqService.selectByzsxnum1(pkgLineId,matnr,zwipq.get(j).getCharg());
                            zwipqhz.setZsxnum(num + "");
                            list.add(zwipqhz);
                        }
                    }
                    zwipq2 = zwipqService.selectcharg2(pkgLineId,matnr);
                    if(zwipq2.size() > 0){
                        for(int j= 0;j<zwipq2.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg2(zwipq2.get(j).getCharg());
                            zwipqhz.setZsxnum2(zwipqService.selectByzsxnum2(pkgLineId,matnr,zwipq2.get(j).getCharg())+"");
                            list.add(zwipqhz);
                        }
                    }
                    if(serverSetting != null){
                        WebServerHelp webServerHelp = new WebServerHelp();
                        HanaCon hanaCon = new HanaCon(webServerHelp.getHanaUrl(),webServerHelp.getHanaUserName(),webServerHelp.getHanaPass(),webServerHelp.getHanaDRIVER());
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select distinct ba.carton_code, pa.zsxjlh from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                        String where = "where pa.line_id = "+ "'"+ pkgLineId +"' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                        String sql = sqlzx + where;
                        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
                        try {
                            listMap = oracleConn.select(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(listMap.size() > 0){
                            List<Zwipq> list2 = new ArrayList<Zwipq>();
                            List<Zwipq> list3 = new ArrayList<Zwipq>();
                            for(int j=0;j<listMap.size();j++){
                                zwipq3 = zwipqService.selectcharg3(listMap.get(j).get("ZSXJLH").toString(),pkgLineId,matnr);
                                if(zwipq3 != null){
                                    int k;
                                    for(k=0;k<list2.size();k++){
                                        if(zwipq3.getCharg().equals(list2.get(k).getCharg())){
                                            break;
                                        }
                                    }
                                    zwipq3.setZtpbat(listMap.get(j).get("CARTON_CODE").toString());
                                    if(k == list2.size()) {
                                        list3.add(zwipq3);
                                    }
                                    list2.add(zwipq3);
                                }
                            }
                            if(list3.size() > 0){
                                for(int j= 0;j<list3.size();j++){
                                    Zwipqhz zwipqhz = new Zwipqhz();
                                    Integer zsxnum = 0;
                                    zwipqhz.setWerks(werks);
                                    zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                                    zwipqhz.setTxt(list1.get(i).getTxt());
                                    zwipqhz.setLineId(pkgLineId);
                                    zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                                    zwipqhz.setMatnr2(matnr);
                                    zwipqhz.setMaktx(list1.get(i).getMaktx());
                                    zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                                    zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                                    zwipqhz.setCartonCode(list3.get(j).getZtpbat());
                                    zwipqhz.setCharg3(list3.get(j).getCharg());
                                    for(int k=0;k<list2.size();k++){
                                        if(list3.get(j).getCharg().equals(list2.get(k).getCharg())){
                                            if(list2.get(k).getZsxnum() == null || "".equals(list2.get(k).getZsxnum())){
                                                list2.get(k).setZsxnum(0.0);
                                            }
                                            double num = list2.get(k).getZsxnum();
                                            zsxnum = zsxnum + (int) num;
                                            list2.remove(k);
                                            k--;
                                        }
                                    }
                                    zwipqhz.setZsxnum3(zsxnum + "");
                                    list.add(zwipqhz);
                                }
                            }
                        }
                        String where2 = "where pa.line_id = "+ "'"+ pkgLineId +"' and pa.jb_status = 1 and pa.barcode_id = ba.barcode_id ";
                        String sql2 = sqlzx + where2;
                        List<Map<String, Object>> listMap2 = new ArrayList<Map<String,Object>>();
                        try {
                            listMap2 = oracleConn.select(sql2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(listMap2.size() > 0){
                            List<Zwipq> list2 = new ArrayList<Zwipq>();
                            List<Zwipq> list3 = new ArrayList<Zwipq>();
                            for(int j=0;j<listMap2.size();j++) {
                                List<Map<String, Object>> listztbc0002 = new ArrayList<Map<String, Object>>();
                                String ztbc0002sql = "SELECT * FROM SAPABAP1.ZTBC0002 where ZTPZT =" + 1 +
                                        " AND ZTPBAR =" + "'" + listMap2.get(j).get("CARTON_CODE").toString() + "'" +
                                        " AND MANDT = '" + webServerHelp.getMandt() + "' AND WERKS = '" + list1.get(i).getWerks() + "'";
                                try {
                                    listztbc0002 = hanaCon.select(ztbc0002sql);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (listztbc0002.size() > 0) {
                                    zwipq4 = zwipqService.selectcharg3(listMap2.get(j).get("ZSXJLH").toString(), pkgLineId, matnr);
                                    if (zwipq4 != null) {
                                        int k;
                                        for (k = 0; k < list2.size(); k++) {
                                            if (zwipq4.getCharg().equals(list2.get(k).getCharg())) {
                                                break;
                                            }
                                        }
                                        zwipq4.setZtpbat(listMap2.get(j).get("CARTON_CODE").toString());
                                        if (k == list2.size()) {
                                            list3.add(zwipq4);
                                        }
                                        list2.add(zwipq4);
                                    }
                                }
                            }
                            if(list3.size() > 0){
                                for(int j= 0;j<list3.size();j++){
                                    Zwipqhz zwipqhz = new Zwipqhz();
                                    Integer zsxnum = 0;
                                    zwipqhz.setWerks(werks);
                                    zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                                    zwipqhz.setTxt(list1.get(i).getTxt());
                                    zwipqhz.setLineId(pkgLineId);
                                    zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                                    zwipqhz.setMatnr2(matnr);
                                    zwipqhz.setMaktx(list1.get(i).getMaktx());
                                    zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                                    zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                                    zwipqhz.setCartonCode2(list3.get(j).getZtpbat());
                                    zwipqhz.setCharg4(list3.get(j).getCharg());
                                    for(int k=0;k<list2.size();k++){
                                        if(list3.get(j).getCharg().equals(list2.get(k).getCharg())){
                                            if(list2.get(k).getZsxnum() == null || "".equals(list2.get(k).getZsxnum())){
                                                list2.get(k).setZsxnum(0.0);
                                            }
                                            double num = list2.get(k).getZsxnum();
                                            zsxnum = zsxnum + (int) num;
                                            list2.remove(k);
                                            k--;
                                        }
                                    }
                                    zwipqhz.setZsxnum4(zsxnum + "");
                                    list.add(zwipqhz);
                                }
                            }
                        }
                    }
                }else {
                    Zwipqhz zwipqhz = list1.get(i);
                    Integer zsxnum = zwipqService.selectByzsxnum(pkgLineId,matnr,null) + zwipqService.selectByzsxnum1(pkgLineId,matnr,null);
                    Integer zsxnum2 = zwipqService.selectByzsxnum2(pkgLineId,matnr,null);
                    Integer zsxnum3 = 0;
                    Integer zsxnum4 = 0;
                    if(serverSetting != null){
                        WebServerHelp webServerHelp = new WebServerHelp();
                        HanaCon hanaCon = new HanaCon(webServerHelp.getHanaUrl(),webServerHelp.getHanaUserName(),webServerHelp.getHanaPass(),webServerHelp.getHanaDRIVER());
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select distinct ba.carton_code, pa.zsxjlh from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                        String where = "where pa.line_id = "+ "'"+ pkgLineId +"' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                        String sql = sqlzx + where;
                        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
                        try {
                            listMap = oracleConn.select(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(listMap.size() > 0) {
                            for (int j = 0; j < listMap.size(); j++) {
                                zwipq3 = zwipqService.selectcharg3(listMap.get(j).get("ZSXJLH").toString(), pkgLineId, matnr);
                                if (zwipq3 != null) {
                                    if(zwipq3.getZsxnum() == null || "".equals(zwipq3.getZsxnum())){
                                        zwipq3.setZsxnum(0.0);
                                    }
                                    double num = zwipq3.getZsxnum();
                                    zsxnum3 = zsxnum3 + (int) num;
                                }
                            }
                        }
                        String where2 = "where pa.line_id = "+ "'"+ pkgLineId +"' and pa.jb_status = 1 and pa.barcode_id = ba.barcode_id ";
                        String sql2 = sqlzx + where2;
                        List<Map<String, Object>> listMap2 = new ArrayList<Map<String,Object>>();
                        try {
                            listMap2 = oracleConn.select(sql2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(listMap2.size() > 0) {
                            for (int j = 0; j < listMap2.size(); j++) {
                                List<Map<String, Object>> listztbc0002 = new ArrayList<Map<String, Object>>();
                                String ztbc0002sql = "SELECT * FROM SAPABAP1.ZTBC0002 where ZTPZT =" + 1 +
                                        " AND ZTPBAR =" + "'" + listMap2.get(j).get("CARTON_CODE").toString() + "'" +
                                        " AND MANDT = '" + webServerHelp.getMandt() + "' AND WERKS = '" + list1.get(i).getWerks() + "'";
                                try {
                                    listztbc0002 = hanaCon.select(ztbc0002sql);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (listztbc0002.size() > 0) {
                                    zwipq4 = zwipqService.selectcharg3(listMap2.get(j).get("ZSXJLH").toString(), pkgLineId, matnr);
                                    if (zwipq4 != null) {
                                        if(zwipq4.getZsxnum() == null || "".equals(zwipq4.getZsxnum())){
                                            zwipq4.setZsxnum(0.0);
                                        }
                                        double num = zwipq4.getZsxnum();
                                        zsxnum4 = zsxnum4 + (int) num;
                                    }
                                }
                            }
                        }
                    }
                    if((zsxnum !=0) || (zsxnum2 !=0) || (zsxnum3 !=0) || (zsxnum4 !=0)){
                        zwipqhz.setWerks(werks);
                        zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                        zwipqhz.setTxt(list1.get(i).getTxt());
                        zwipqhz.setLineId(pkgLineId);
                        zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                        zwipqhz.setMatnr2(matnr);
                        zwipqhz.setMaktx(list1.get(i).getMaktx());
                        zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                        zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                        zwipqhz.setZsxnum(zsxnum+"");
                        zwipqhz.setZsxnum2(zsxnum2+"");
                        zwipqhz.setZsxnum3(zsxnum3+"");
                        zwipqhz.setZsxnum4(zsxnum4+"");
                        zwipqhz.setTotal((zsxnum+zsxnum2+zsxnum3+zsxnum4)+"");
                        list.add(zwipqhz);
                    }
                }
            }
        }
        return new ResponseData(list);
    }
}

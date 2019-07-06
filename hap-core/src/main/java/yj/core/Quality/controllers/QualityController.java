package yj.core.Quality.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.HanaCon.HanaCon;
import yj.core.OracleConn.OracleConn;
import yj.core.Quality.dto.QualityParam;
import yj.core.Quality.dto.QualityTree;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.cust.dto.Cust;
import yj.core.cust.service.ICustService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.service.IZtbc0002Service;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.service.IZwipqService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class QualityController extends BaseController {
    //质量追溯报表
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IZwipqService zwipqService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICustService custService;
    @Autowired
    private IZtbc0002Service ztbc0002Service;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private ICardtService cardtService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/wip/Qaulity/quertQaulityReport")
    @ResponseBody
    public ResponseData queryReport(HttpServletRequest request, QualityParam param) {
        ResponseData rs = new ResponseData();
        String unitCode = param.getUnitCode();
        String lineId = param.getLineId();
        String matnr = param.getMatnr();
        String tpcode = param.getTpcode();
        String zgjbar = param.getZgjbar();
        String jjgstrpbefor = param.getJjgstrpbefor();
        String jjgstrpafter = param.getJjgstrpafter();
        String jjbanz = param.getJjbanz();
        String jjcharg = param.getJjcharg();
        String yzgstrpbefor = param.getYzgstrpbefor();
        String yzgstrpafter = param.getYzgstrpafter();
        String yzbanz = param.getYzbanz();
        String yzcharg = param.getYzcharg();
        String werks = param.getWerks();
        String id = param.getId() == null ?"": param.getId();
        String project = param.getProject() == null ?"": param.getProject();
        String parentId = param.getParentId() == null ?"": param.getParentId();
        ServerSetting serverSetting = new ServerSetting();
        if (!lineId.equals("")){

            serverSetting = serverSettingService.selectByLineId(werks,lineId);
            if (serverSetting == null){
                rs.setSuccess(false);
                rs.setMessage("未能获取到产线的服务器配置数据！");
                return rs;
            }
        }
        WebServerHelp webServerHelp = new WebServerHelp();
        HanaCon hanaCon = new HanaCon(webServerHelp.getHanaUrl(),webServerHelp.getHanaUserName(),webServerHelp.getHanaPass(),webServerHelp.getHanaDRIVER());
        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
        //根据条件获取装箱段段数据
        String sqlzx = "select a.main_id,a.item_code,a.barcode,c.carton_code,b.zpgdbar,b.zxhbar,b.rsnum,b.rspos,b.zsxjlh,b.line_id,b.created_by from "+serverSetting.getDbUsername()+".wip_main_data  a"
                +" inner join  "+serverSetting.getDbUsername()+".wip_pallet_sn_rel  b on a.main_id = b.main_id"
                +" inner join  "+serverSetting.getDbUsername()+".mtl_barcode c on b.barcode_id = c.barcode_id";
        String where = " where b.line_id = " + "'" + param.getLineId() + "' and c.status = 0 and b.status = 0 and a.ENABLE_FLAG = '1' ";
        if (param.getMatnr() != null){
            where = where + "and a.item_code = " + "'" + param.getMatnr() + "' " ;
        }

        if (param.getTpcode() != null){
            where = where + "and c.carton_code = " + "'" + param.getTpcode() + "' ";
        }

        if (param.getZgjbar() != null){
            where = where + "and a.serial_no = " + "'" + param.getZgjbar() + "' ";
        }

        String sql = sqlzx + where;
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            list = oracleConn.select(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.size()>0){
            rs.setSuccess(true);
        }else{
            rs.setSuccess(true);
        }


        //根据装箱数据来获取报工平台上的部分数据
        //1 物料描述
        List<Marc> listmarc = new ArrayList<>();
        List<Zwipq> listzwipq = new ArrayList<>();
        List<Cardh> listcardhjj = new ArrayList<>();
        List<Cardh> listcardhyz = new ArrayList<>();
        List<Xhcard> listxhcard = new ArrayList<>();
        List<ProductsCfg> listcfg = new ArrayList<>();

          for (int i=0;i<list.size();i++){
              Marc marc = new Marc();
              marc = marcService.selectByMatnr(list.get(i).get("ITEM_CODE").toString());
              if (marc != null){
                  list.get(i).put("MAKTX2",marc.getMaktx() == null ? "": marc.getMaktx());//机加物料描述
                  if (!listmarc.contains(marc)){
                      listmarc.add(marc);
                  }
              }else{
                  list.get(i).put("MAKTX2","");//机加物料描述
                  if (!listmarc.contains(marc)){
                      listmarc.add(marc);
                  }
              }


              Zwipq zwipq = new Zwipq();
              zwipq = zwipqService.selectById(list.get(i).get("ZSXJLH") == null ?"":list.get(i).get("ZSXJLH").toString());
              if (zwipq != null){
                  //机加生产日期
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  String datejj = sdf.format(zwipq.getCreationDate());
                  list.get(i).put("BUDATJJ",datejj);

                  list.get(i).put("SHIFTJJ",zwipq.getShift() == null?"":zwipq.getShift());
                  //机加班组
                  list.get(i).put("USERIDJJ",zwipq.getCreatedBy() == null ?"":zwipq.getCreatedBy());
                  if (zwipq.getCreatedBy() != null){
//                      Employee employee =  employeeService.queryById(zwipq.getCreatedBy());
                      User tmp = new User();
                      tmp.setUserId(zwipq.getCreatedBy());
                      IRequest requestContext = createRequestContext(request);
                      User user = userService.selectByPrimaryKey(requestContext,tmp);
                      if (user == null){
                          list.get(i).put("USERCODEJJ","");
                      }else{
                          list.get(i).put("USERCODEJJ",user.getUserName() == null ?"":user.getUserName());
                      }

                  }else{
                      list.get(i).put("USERCODEJJ","");
                  }
                  listzwipq.add(zwipq);
              }else{
                  //机加生产日期
                  list.get(i).put("BUDATJJ","");
                  list.get(i).put("SHIFTJJ","");
                  list.get(i).put("USERCODEJJ","");
                  //机加班组
                  listzwipq.add(zwipq);
              }

              Cardh cardhjj = new Cardh();
              cardhjj = cardhService.selectByBarcode(list.get(i).get("ZPGDBAR") == null ?"":list.get(i).get("ZPGDBAR").toString());
              if (cardhjj != null){
                  list.get(i).put("ZBANZJJ",cardhjj.getShift() == null ?"":cardhjj.getShift());
                  list.get(i).put("AUFNRJJ",cardhjj.getAufnr() == null ?"":cardhjj.getAufnr());
                  listcardhjj.add(cardhjj);
              }else{
                  list.get(i).put("ZBANZJJ","");
                  listcardhjj.add(cardhjj);
              }


              Xhcard xhcard = new Xhcard();
              xhcard = xhcardService.selectByBacode(list.get(i).get("ZXHBAR").toString());
              listxhcard.add(xhcard);

              Cardh cardhyz = new Cardh();
              if (xhcard != null){
                  cardhyz = cardhService.selectByZxhbar(xhcard.getAufnr(),xhcard.getZxhnum());
                  list.get(i).put("ZPGDBARYZ",cardhyz.getZpgdbar() == null ?"":cardhyz.getZpgdbar());//压铸流转卡
                  list.get(i).put("MATNRYZ",cardhyz.getMatnr() == null ?"":cardhyz.getMatnr());//毛坯物料号
                  list.get(i).put("ZMNUM",xhcard.getZmnum() == null ?"":xhcard.getZmnum());//模号
                  list.get(i).put("ZBANZYZ",xhcard.getZscbc() == null ?"":xhcard.getZscbc());//压铸班组
                  list.get(i).put("ZSCXYZ",xhcard.getZscx() == null ?"":xhcard.getZscx());//压铸产线
                  list.get(i).put("ZBANB",cardhyz.getSfflg() == null ?"":cardhyz.getSfflg());//压铸班标
                  list.get(i).put("MTLBD",cardhyz.getMtlbd() == null ?"":cardhyz.getMtlbd());//铝材牌号

                  list.get(i).put("FSDAT",cardhyz.getActgstrp() == null ?"":cardhyz.getActgstrp());//首工序报工日期
                  list.get(i).put("FSTIM",cardhyz.getActst() == null ?"":cardhyz.getActst());//首工序报工时间

                  list.get(i).put("LTDAT",cardhyz.getActgltrp() == null ?"":cardhyz.getActgltrp());//末工序报工时间
                  list.get(i).put("LTTIM",cardhyz.getActdt() == null ?"":cardhyz.getActdt());//末工序报工日期

                  InputLog inputLog = new InputLog();
                  InputLog dto = new InputLog();
                  dto.setDispatch(cardhyz.getZpgdbar());
                  dto.setOperation("0010");
                  inputLog = inputLogService.queryByDispatchAndOperation(dto);
                  if (inputLog != null){
                      list.get(i).put("USERCODEYZ1",inputLog.getAttr1() == null ?"":inputLog.getAttr1());
                      list.get(i).put("USERCODEYZ2",inputLog.getAttr2() == null ?"":inputLog.getAttr2());
                      list.get(i).put("RSNUMYZ",inputLog.getConfirmationNo() == null ?"":inputLog.getConfirmationNo());
                      list.get(i).put("RSPOSYZ",inputLog.getConfirmationPos() == null ?"":inputLog.getConfirmationPos());
                      Employee employee1 = new Employee();
                      if (inputLog.getAttr1() != null){
                          employee1 = employeeService.queryByCode(employee1.getEmployeeCode());
                          if (employee1 != null){
                              list.get(i).put("USERIDYZ1",employee1.getEmployeeId() == null ?"":employee1.getEmployeeId());
                          }else{
                              list.get(i).put("USERIDYZ1","");
                          }

                      }

                      Employee employee2 = new Employee();
                      if (inputLog.getAttr2() != null){
                          employee2 = employeeService.queryByCode(employee2.getEmployeeCode());
                          if (employee2 != null){
                              list.get(i).put("USERIDYZ2",employee2.getEmployeeId() == null ?"":employee2.getEmployeeId());
                          }else{
                              list.get(i).put("USERIDYZ2","");
                          }

                      }

                  }else{
                      list.get(i).put("USERCODEYZ1","");
                      list.get(i).put("USERCODEYZ2","");
                      list.get(i).put("USERIDYZ1","");
                      list.get(i).put("USERIDYZ2","");
                  }

              }else{

                  list.get(i).put("ZPGDBARYZ","");//压铸流转卡
                  list.get(i).put("MATNRYZ","");//毛坯物料号
                  list.get(i).put("ZMNUM","");//模号
                  list.get(i).put("ZBANZYZ","");//压铸班组
                  list.get(i).put("ZSCXYZ","");//压铸产线
                  list.get(i).put("ZBANB","");//压铸班标
                  list.get(i).put("MTLBD","");//铝材牌号

                  list.get(i).put("FSDAT","");//首工序报工日期
                  list.get(i).put("FSTIM","");//首工序报工时间

                  list.get(i).put("LTDAT","");//末工序报工时间
                  list.get(i).put("LTTIM","");//末工序报工日期
              }

              if (cardhyz != null){
                  Cardt cardtyz = new Cardt();
                  cardtyz = cardtService.selectByZpgdbarAndVornr(cardhyz.getZpgdbar(),"0010");
                  if (cardtyz != null){
                      list.get(i).put("KTEXT",cardtyz.getKtext());
                  }else{
                      list.get(i).put("KTEXT","");
                  }
              }




              Marc marcYz = new Marc();
              marcYz = marcService.selectByMatnr(cardhyz.getMatnr());
              if (marcYz != null){
                  list.get(i).put("MAKTX",marcYz.getMaktx() == null ?"":marcYz.getMaktx());
                  list.get(i).put("AUFNRYZ",cardhyz.getAufnr() == null ?"":cardhyz.getAufnr());
                  list.get(i).put("CHARGYZ",xhcard.getChargkc() == null ?"":xhcard.getChargkc());
                  listcardhyz.add(cardhyz);
              }else{
                  list.get(i).put("MAKTX","");
                  list.get(i).put("AUFNRYZ","");
                  list.get(i).put("CHARGYZ","");
                  listcardhyz.add(cardhyz);
              }


              ProductsCfg productsCfg = productsCfgService.selectByLineidAndPMatnr(list.get(i).get("LINE_ID").toString(),list.get(i).get("ITEM_CODE").toString());

              //客户 客户名称
              list.get(i).put("KUNNR",productsCfg.getKunnr() == null?"":productsCfg.getKunnr());
              Cust cust = new Cust();
              String kunnr = productsCfg.getKunnr() == null?"":productsCfg.getKunnr();
              if    (kunnr != null){
                  cust = custService.selectByKunnr(kunnr);
                  if (cust != null){
                      list.get(i).put("NAME1",cust.getName1() == null?"":cust.getName1());
                  }
              }else{
                  list.get(i).put("NAME1","");
              }



              //托盘信息
              Ztbc0002 ztbc0002 = new Ztbc0002();
              ztbc0002 = ztbc0002Service.selectByTpcode(list.get(i).get("CARTON_CODE").toString(),param.getWerks());
              if (ztbc0002 != null){
                  if (ztbc0002.getZtpzt2() == null){
                      list.get(i).put("ZTPBZ","");
                  }else{
                      list.get(i).put("ZTPBZ",ztbc0002.getZtxt2());
                  }
              }else{
                  list.get(i).put("ZTPBZ","");
              }

              //根据托盘码 查询SAP 成品入库时间

              List<Map<String, Object>> listztbc0004 = new ArrayList<Map<String,Object>>();
              String ztbc0004sql = "select * from SAPABAP1.ZTBC0004 where ZDATE =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(0,8) + "'" +
                      " AND ZTPNUM =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(9,13) + "'" +
                      " AND MANDT = '"+webServerHelp.getMandt()+"' AND ZTYPE = '2'";
              try {
                  listztbc0004 = hanaCon.select(ztbc0004sql);
                  if (listztbc0004.size() > 0){
                      list.get(i).put("BUDATJJRK",listztbc0004.get(0).get("BUDAT").toString());
                      list.get(i).put("ZPTIMEJJRK",listztbc0004.get(0).get("ZPTIME").toString());

                      //入库物料凭证
                      list.get(i).put("MBLNRJJRK",listztbc0004.get(0).get("MBLNR").toString());
                      list.get(i).put("MJAHRJJRK",listztbc0004.get(0).get("MJAHR").toString());
                  }else{
                      list.get(i).put("BUDATJJRK","");
                      list.get(i).put("ZPTIMEJJRK","");
                      list.get(i).put("MBLNRJJRK","");
                      list.get(i).put("MJAHRJJRK","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }

              //根据托盘条码获取出库信息
              List<Map<String, Object>> listztbc0005 = new ArrayList<Map<String,Object>>();
              String ztbc0005sql = "select * from SAPABAP1.ZTBC0005 where ZDATE =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(0,8) + "'" +
                      " AND ZTPNUM =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(9,13) + "'" +
                      " AND MANDT = '"+webServerHelp.getMandt()+"' AND ZGZBS = '3' AND ZZDEL = ''";

              try {
                  listztbc0005 = hanaCon.select(ztbc0005sql);
                  if (listztbc0005.size() > 0){
                      list.get(i).put("VBELNJJ",listztbc0005.get(0).get("VBELN").toString());
                      list.get(i).put("BUDATJJCK",listztbc0005.get(0).get("CHDAT").toString());
                      list.get(i).put("ZPTIMEJJCK",listztbc0005.get(0).get("CHTIM").toString());
                  }else{
                      list.get(i).put("VBELNJJ","");
                      list.get(i).put("BUDATJJCK","");
                      list.get(i).put("ZPTIMEJJCK","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }

              //根据机加报确认号 计数器获取托盘条码批次
              if (list.get(i).get("RSNUM") == null){
                  list.get(i).put("CHARGJJ","");
              }else{
                  List<Map<String, Object>> listztbc0018 = new ArrayList<Map<String,Object>>();
                  String ztbc0018sql = "select * from SAPABAP1.ZTBC0018 where RUECK = " + "'" + list.get(i).get("RSNUM").toString()+ "'" +
                          " and RMZHL = " + "'" + list.get(i).get("RSPOS").toString() + "' and mandt = '"+webServerHelp.getMandt()+"' and ZTRAN_TYPE = '01'";

                  try {
                      listztbc0018 = hanaCon.select(ztbc0018sql);
                      if (listztbc0018.size() > 0){
                          list.get(i).put("CHARGJJ",listztbc0018.get(0).get("CHARG").toString());

                      }else{
                          list.get(i).put("CHARGJJ","");
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }


              List<Map<String, Object>> listztbc0014 = new ArrayList<Map<String,Object>>();

              String ztbc0014sql = "select  CAST(ZKHBAR1 AS varchar) as ZKHBAR1 from SAPABAP1.ZTBC0014 where ZTPBAR = " + "'" + list.get(i).get("CARTON_CODE").toString()+ "'" +
                       " and mandt = '"+webServerHelp.getMandt()+"' and werks = '"+werks+"'";

              try {
                  listztbc0014 = hanaCon.select(ztbc0014sql);
                  if (listztbc0014.size() > 0){
                      list.get(i).put("ZKHBAR1",listztbc0014.get(0).get("ZKHBAR1").toString());
                  }else{
                      list.get(i).put("ZKHBAR1","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }


          }
        //2 客户编码 客户名称

        //3 机加生产订单

        //4
        List<QualityTree> treeList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            QualityTree qualityTree = new QualityTree();
            qualityTree.setId(list.get(i).get("BARCODE").toString() + i);
            qualityTree.setProject("产品二维码:"+list.get(i).get("BARCODE").toString());
            treeList.add(qualityTree);

            QualityTree qualityTree3 = new QualityTree();
            qualityTree3.setId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree3.setParentId(list.get(i).get("BARCODE").toString()+i);
            qualityTree3.setProject("机加信息");
            treeList.add(qualityTree3);


            QualityTree qualityTree31 = new QualityTree();
            qualityTree31.setId(list.get(i).get("BARCODE").toString()+i+"31");
            qualityTree31.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree31.setContent(list.get(i).get("CARTON_CODE").toString());
            qualityTree31.setProject("托盘码");
            treeList.add(qualityTree31);

            QualityTree qualityTree35 = new QualityTree();
            qualityTree35.setId(list.get(i).get("BARCODE").toString()+i+"35");
            qualityTree35.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree35.setContent(list.get(i).get("ITEM_CODE").toString());
            qualityTree35.setProject("产品编码");
            treeList.add(qualityTree35);

            QualityTree qualityTree38 = new QualityTree();
            qualityTree38.setId(list.get(i).get("BARCODE").toString()+i+"38");
            qualityTree38.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree38.setContent(list.get(i).get("MAKTX2").toString());
            qualityTree38.setProject("产品名称");
            treeList.add(qualityTree38);

//
//            QualityTree qualityTree32 = new QualityTree();
//            qualityTree32.setId(list.get(i).get("BARCODE").toString()+"32");
//            qualityTree32.setParentId(list.get(i).get("BARCODE").toString()+"3");
//            qualityTree32.setContent(list.get(i).get("ZSXJLH").toString());
//            qualityTree32.setProject("上线记录号");
//            treeList.add(qualityTree32);



            QualityTree qualityTree36 = new QualityTree();
            qualityTree36.setId(list.get(i).get("BARCODE").toString()+i+"36");
            qualityTree36.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree36.setContent(list.get(i).get("AUFNRJJ").toString());
            qualityTree36.setProject("机加生产订单");
            treeList.add(qualityTree36);

            QualityTree qualityTree39 = new QualityTree();
            qualityTree39.setId(list.get(i).get("BARCODE").toString()+i+"39");
            qualityTree39.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree39.setContent(list.get(i).get("BUDATJJ").toString());
            qualityTree39.setProject("机加生产日期");
            treeList.add(qualityTree39);

            QualityTree qualityTree401 = new QualityTree();
            qualityTree401.setId(list.get(i).get("BARCODE").toString()+i+"401");
            qualityTree401.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree401.setContent(list.get(i).get("SHIFTJJ").toString());
            qualityTree401.setProject("机加班组");
            treeList.add(qualityTree401);

            QualityTree qualityTree402 = new QualityTree();
            qualityTree402.setId(list.get(i).get("BARCODE").toString()+i+"402");
            qualityTree402.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree402.setContent(list.get(i).get("CHARGJJ").toString());
            qualityTree402.setProject("机加批次");
            treeList.add(qualityTree402);


            QualityTree qualityTree34 = new QualityTree();
            qualityTree34.setId(list.get(i).get("BARCODE").toString()+i+"34");
            qualityTree34.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree34.setContent(list.get(i).get("USERCODEJJ").toString());
            qualityTree34.setProject("机加人员");
            treeList.add(qualityTree34);

            QualityTree qualityTree37 = new QualityTree();
            qualityTree37.setId(list.get(i).get("BARCODE").toString()+i+"37");
            qualityTree37.setParentId(list.get(i).get("BARCODE").toString()+i+"3");
            qualityTree37.setContent(list.get(i).get("CREATED_BY").toString());
            qualityTree37.setProject("检验人员");
            treeList.add(qualityTree37);


//
//            QualityTree qualityTree33 = new QualityTree();
//            qualityTree33.setId(list.get(i).get("BARCODE").toString()+"33");
//            qualityTree33.setParentId(list.get(i).get("BARCODE").toString()+"3");
//            qualityTree33.setContent(list.get(i).get("ZPGDBAR").toString());
//            qualityTree33.setProject("机加流转卡");
//            treeList.add(qualityTree33);
//



            QualityTree qualityTree2 = new QualityTree();
            qualityTree2.setId(list.get(i).get("BARCODE").toString()+i+"2");
            qualityTree2.setParentId(list.get(i).get("BARCODE").toString()+i);
            qualityTree2.setProject("压铸信息");
            treeList.add(qualityTree2);

            QualityTree qualityTree25 = new QualityTree();
            qualityTree25.setId(list.get(i).get("BARCODE").toString()+i+"25");
            qualityTree25.setParentId(list.get(i).get("BARCODE").toString()+i+"2");
            qualityTree25.setContent(list.get(i).get("AUFNRYZ").toString());
            qualityTree25.setProject("压铸生产订单");
            treeList.add(qualityTree25);

            QualityTree qualityTree21 = new QualityTree();
            qualityTree21.setId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree21.setParentId(list.get(i).get("BARCODE").toString()+i+"2");
            qualityTree21.setContent(list.get(i).get("ZPGDBARYZ").toString());
            qualityTree21.setProject("压铸流转卡信息");
            treeList.add(qualityTree21);
//
            QualityTree qualityTree211 = new QualityTree();
            qualityTree211.setId(list.get(i).get("BARCODE").toString()+i+"211");
            qualityTree211.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree211.setContent(list.get(i).get("KTEXT").toString());
            qualityTree211.setProject("压铸机台号");
            treeList.add(qualityTree211);
//

            QualityTree qualityTree212 = new QualityTree();
            qualityTree212.setId(list.get(i).get("BARCODE").toString()+i+"212");
            qualityTree212.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree212.setContent(list.get(i).get("ZMNUM").toString());
            qualityTree212.setProject("模号");
            treeList.add(qualityTree212);
//


            QualityTree qualityTree213 = new QualityTree();
            qualityTree213.setId(list.get(i).get("BARCODE").toString()+i+"213");
            qualityTree213.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree213.setContent(list.get(i).get("ZBANZYZ").toString());
            qualityTree213.setProject("压铸班组");
            treeList.add(qualityTree213);
//

            QualityTree qualityTree214 = new QualityTree();
            qualityTree214.setId(list.get(i).get("BARCODE").toString()+i+"214");
            qualityTree214.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree214.setContent(list.get(i).get("ZBANB").toString());
            qualityTree214.setProject("压铸班标");
            treeList.add(qualityTree214);
//


            QualityTree qualityTree215 = new QualityTree();
            qualityTree215.setId(list.get(i).get("BARCODE").toString()+i+"215");
            qualityTree215.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree215.setContent(list.get(i).get("MTLBD").toString());
            qualityTree215.setProject("铝材牌号");
            treeList.add(qualityTree215);
//

//            QualityTree qualityTree216 = new QualityTree();
//            qualityTree216.setId(list.get(i).get("BARCODE").toString()+"216");
//            qualityTree216.setParentId(list.get(i).get("BARCODE").toString()+"21");
//            qualityTree216.setContent(list.get(i).get("FSDAT").toString()+" "+list.get(i).get("FSTIM").toString());
//            qualityTree216.setProject("首工序报工时间");
//            treeList.add(qualityTree216);
//

//
//            QualityTree qualityTree217 = new QualityTree();
//            qualityTree217.setId(list.get(i).get("BARCODE").toString()+"217");
//            qualityTree217.setParentId(list.get(i).get("BARCODE").toString()+"21");
//            qualityTree217.setContent(list.get(i).get("LTDAT").toString()+ " " + list.get(i).get("LTTIM").toString());
//            qualityTree217.setProject("末工序报工时间");
//            treeList.add(qualityTree217);



            QualityTree qualityTree218 = new QualityTree();
            qualityTree218.setId(list.get(i).get("BARCODE").toString()+i+"218");
            qualityTree218.setParentId(list.get(i).get("BARCODE").toString()+i+"21");
            qualityTree218.setContent(list.get(i).get("USERCODEYZ1").toString() +"" + list.get(i).get("USERCODEYZ2"));
            qualityTree218.setProject("压铸人员");
            treeList.add(qualityTree218);
//
//
            QualityTree qualityTree22 = new QualityTree();
            qualityTree22.setId(list.get(i).get("BARCODE").toString()+i+"22");
            qualityTree22.setParentId(list.get(i).get("BARCODE").toString()+i+"2");
            qualityTree22.setContent(list.get(i).get("MATNRYZ").toString()+" ("+list.get(i).get("MAKTX")+")");
            qualityTree22.setProject("毛坯物料");
            treeList.add(qualityTree22);



//

            QualityTree qualityTree24 = new QualityTree();
            qualityTree24.setId(list.get(i).get("BARCODE").toString()+i+"24");
            qualityTree24.setParentId(list.get(i).get("BARCODE").toString()+i+"2");
            qualityTree24.setContent(list.get(i).get("CHARGYZ").toString());
            qualityTree24.setProject("压铸批次");
            treeList.add(qualityTree24);

            QualityTree qualityTree5 = new QualityTree();
            qualityTree5.setId(list.get(i).get("BARCODE").toString()+i+"5");
            qualityTree5.setParentId(list.get(i).get("BARCODE").toString()+i);
            qualityTree5.setProject("分零件信息");
            treeList.add(qualityTree5);

            QualityTree qualityTree42 = new QualityTree();
            qualityTree42.setId(list.get(i).get("BARCODE").toString()+"42");
            qualityTree42.setParentId(list.get(i).get("BARCODE").toString()+i);
            qualityTree42.setProject("原材料信息");
            treeList.add(qualityTree42);

            QualityTree qualityTree4 = new QualityTree();
            qualityTree4.setId(list.get(i).get("BARCODE").toString()+i+"4");
            qualityTree4.setParentId(list.get(i).get("BARCODE").toString()+i);
            qualityTree4.setProject("物流信息");
            treeList.add(qualityTree4);

//            QualityTree qualityTree42 = new QualityTree();
//            qualityTree42.setId(list.get(i).get("BARCODE").toString()+"42");
//            qualityTree42.setParentId(list.get(i).get("BARCODE").toString()+i + "4");
//            qualityTree42.setProject("原材料部分");
//            treeList.add(qualityTree42);

            //根据压铸生产订单取
            String aufnryz = list.get(i).get("AUFNRYZ").toString();
            List<Map<String, Object>> listresbyz = new ArrayList<Map<String,Object>>();
            List<Map<String, Object>> listmseg1yz = new ArrayList<Map<String,Object>>();
            List<Map<String, Object>> listmseg2yz = new ArrayList<Map<String,Object>>();
            if (aufnryz != ""){
                if (aufnryz.length() < 12){
                    for (int m=0;m<=12 - aufnryz.length();m++){
                        aufnryz = "0" + aufnryz;
                    }
                }
                String sqlresbyz = "select DISTINCT r.matnr, mk.maktx ,m.groes ,m.matkl from sapabap1.resb r " +
                        "inner join sapabap1.mara m on r.MATNR = m.MATNR " +
                        " INNER JOIN SAPABAP1.MAKT mk ON r.matnr = mk.matnr " +
                        " INNER JOIN SAPABAP1.AFKO AF ON af.aufpl = r.aufpl " +
                        " where af.aufnr =" + "'" + aufnryz + "'" + " AND m.MATKL in ('3101','4801') and af.mandt = '"+webServerHelp.getMandt()+"' and m.mandt = '"+webServerHelp.getMandt()+"'";

                String sqlmseg1yz = "select DISTINCT matbf,charg_sid from sapabap1.matdoc m inner join sapabap1.afru  a on a.WABLNR = m.mblnr" +
                        " where a.rueck = '" + list.get(i).get("RSNUMYZ").toString() +"'" +
                        "  and a.rmzhl = '" + list.get(i).get("RSPOSYZ").toString() +"'" +
                        "  and a.mandt = '"+webServerHelp.getMandt()+"'";

                String sqlmseg2yz = "select DISTINCT matbf,charg_sid from sapabap1.matdoc m inner join sapabap1.afwi  a on a.mblnr = m.mblnr" +
                        " where a.rueck = '" + list.get(i).get("RSNUMYZ").toString() +"'" +
                        "  and a.rmzhl = '" + list.get(i).get("RSPOSYZ").toString() +"'" +
                        "  and a.mandt = '"+webServerHelp.getMandt()+"'";

                try {
                    listresbyz = hanaCon.select(sqlresbyz);
                    listmseg1yz = hanaCon.select(sqlmseg1yz);

                    if (listmseg1yz.size() == 0){
                        listmseg2yz = hanaCon.select(sqlmseg2yz);
                    }

                    if (listresbyz.size() > 0){
                        String l1 = "0";
                        for (int k =0;k<listresbyz.size();k++){

                            if (listmseg1yz.size() > 0){
                                for (int h=0;h<listmseg1yz.size();h++){
                                    if (listmseg1yz.get(h).get("MATBF").toString().equals(listresbyz.get(k).get("MATNR").toString())){
                                        System.out.println(listmseg2yz.get(h).get("MATBF").toString());
                                        System.out.println(listresbyz.get(k).get("MATNR").toString());
                                        QualityTree qualityTreetmp = new QualityTree();
                                        qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42" + "GROES");
                                        qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                                        qualityTreetmp.setProject("铝液信息");
                                        qualityTreetmp.setProject("牌号");
//                                        qualityTreetmp.setContent("【铝液牌号】:" + listresbyz.get(k).get("GROES").toString() +
//                                                " 【铝液批次】:" + listmseg1yz.get(h).get("CHARG_SID").toString() +
//                                                " 【铝液编码】:" +listresbyz.get(k).get("MATNR").toString() + " 【铝液描述】:" + listresbyz.get(k).get("MAKTX").toString() );
                                        qualityTreetmp.setContent(listresbyz.get(k).get("GROES").toString());
                                        list.get(i).put("MATNRLY",listresbyz.get(k).get("MATNR").toString());
                                        list.get(i).put("CHARGLY",listmseg1yz.get(h).get("CHARG_SID").toString());
                                        l1 = "1";
                                        if(!treeList.contains(qualityTreetmp)){
                                            treeList.add(qualityTreetmp);
                                        }
                                        QualityTree qualityTreetmp2 = new QualityTree();
                                        qualityTreetmp2.setId(list.get(i).get("BARCODE").toString()+"42" + "CHARGLY");
                                        qualityTreetmp2.setParentId(list.get(i).get("BARCODE").toString()+"42");
                                        qualityTreetmp2.setProject("批次");
                                        qualityTreetmp2.setContent(listmseg1yz.get(h).get("CHARG_SID").toString());
                                        if(!treeList.contains(qualityTreetmp2)){
                                            treeList.add(qualityTreetmp2);
                                        }
                                    }
                                }
                            }else if (listmseg2yz.size() > 0){
                                for (int h=0;h<listmseg2yz.size();h++){
                                    if (listmseg2yz.get(h).get("MATBF").toString().equals(listresbyz.get(k).get("MATNR").toString())){
                                        System.out.println(listmseg2yz.get(h).get("MATBF").toString());
                                        System.out.println(listresbyz.get(k).get("MATNR").toString());
                                        QualityTree qualityTreetmp = new QualityTree();
                                        qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42" + "GROES");
                                        qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                                        qualityTreetmp.setProject("铝液信息");
                                        qualityTreetmp.setProject("牌号");
//                                        qualityTreetmp.setContent("【铝液牌号】:" + listresbyz.get(k).get("GROES").toString() +
//                                                " 【铝液批次】:" + listmseg2yz.get(h).get("CHARG_SID").toString() +
//                                                " 【铝液编码】:" +listresbyz.get(k).get("MATNR").toString() + " 【铝液描述】:" + listresbyz.get(k).get("MAKTX").toString() );
                                        qualityTreetmp.setContent(listresbyz.get(k).get("GROES").toString());
                                        list.get(i).put("CHARGLY",listmseg2yz.get(h).get("CHARG_SID").toString());
                                        list.get(i).put("MATNRLY",listresbyz.get(k).get("MATNR").toString());
                                        l1 = "1";
                                        if(!treeList.contains(qualityTreetmp)){
                                            treeList.add(qualityTreetmp);
                                        }
                                        QualityTree qualityTreetmp2 = new QualityTree();
                                        qualityTreetmp2 = new QualityTree();
                                        qualityTreetmp2.setId(list.get(i).get("BARCODE").toString()+"42" + "CHARGLY");
                                        qualityTreetmp2.setParentId(list.get(i).get("BARCODE").toString()+"42");
                                        qualityTreetmp2.setProject("批次");
                                        qualityTreetmp2.setContent(listmseg2yz.get(h).get("CHARG_SID").toString());
                                        if(!treeList.contains(qualityTreetmp2)){
                                            treeList.add(qualityTreetmp2);
                                        }
                                    }
                                }
                            }else{
                                l1 = "1";
                                QualityTree qualityTreetmp = new QualityTree();
                                qualityTreetmp = new QualityTree();
                                qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42" + "GROES");
                                qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                                qualityTreetmp.setProject("铝液信息");
                                qualityTreetmp.setProject("牌号");
//                                qualityTreetmp.setContent("【铝液牌号】:" + listresbyz.get(k).get("GROES").toString() + " 【铝液编码】:" +listresbyz.get(k).get("MATNR").toString() + " 【铝液描述】:" + listresbyz.get(k).get("MAKTX").toString() );
                                qualityTreetmp.setContent(listresbyz.get(k).get("GROES").toString());
                                if(!treeList.contains(qualityTreetmp)){
                                    treeList.add(qualityTreetmp);
                                }

                                list.get(i).put("CHARGLY","");
                                QualityTree qualityTreetmp2 = new QualityTree();
                                qualityTreetmp2.setId(list.get(i).get("BARCODE").toString()+"42" + "CHARGLY");
                                qualityTreetmp2.setParentId(list.get(i).get("BARCODE").toString()+"42");
                                qualityTreetmp2.setProject("批次");
                                qualityTreetmp2.setContent("");
                                if(!treeList.contains(qualityTreetmp2)){
                                    treeList.add(qualityTreetmp2);
                                }


                            }


                        }

                        if (l1.equals("0")){
                            QualityTree qualityTreetmp = new QualityTree();
                            qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42" + "GROES");
                            qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
                            qualityTreetmp.setProject("牌号");
                            qualityTreetmp.setContent(listresbyz.get(0).get("GROES").toString());
//                            qualityTreetmp.setProject("铝液信息");
//                            qualityTreetmp.setContent("【铝液牌号】:" + listresbyz.get(0).get("GROES").toString() + " 【铝液编码】:" +listresbyz.get(0).get("MATNR").toString() + " 【铝液描述】:" + listresbyz.get(0).get("MAKTX").toString() );
                            list.get(i).put("CHARGLY","");
                            if(!treeList.contains(qualityTreetmp)){
                                treeList.add(qualityTreetmp);
                            }
                            QualityTree qualityTreetmp2 = new QualityTree();
                            qualityTreetmp2.setId(list.get(i).get("BARCODE").toString()+"42" + "CHARGLY");
                            qualityTreetmp2.setParentId(list.get(i).get("BARCODE").toString()+"42");
                            qualityTreetmp2.setProject("批次");
                            qualityTreetmp2.setContent("");
                            if(!treeList.contains(qualityTreetmp2)){
                                treeList.add(qualityTreetmp2);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

//            if (!list.get(i).get("CHARGLY").toString().equals("")){
//                //熔炼炉号 熔炼时间
//                String sqlztpp0017 = "select mandt,werks,charg1,ztllh,bldat,cputm from sapabap1.ZTPP0017 where mandt = '"+webServerHelp.getMandt()+"'" +
//                        " and werks = '" + werks + "'" + " and charg1 = '" + list.get(i).get("CHARGLY").toString() + "'";
//
//                List<Map<String, Object>> listztpp0017 = new ArrayList<Map<String,Object>>();
//                List<Map<String, Object>> listztpp0017_2 = new ArrayList<Map<String,Object>>();
//
//                try {
//                    listztpp0017 = hanaCon.select(sqlztpp0017);
//                    if (listztpp0017.size() > 0){
//                        for (int k = 0;k<listztpp0017.size();k++){
//                            QualityTree qualityTreetmp = new QualityTree();
//                            qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+list.get(i).get("CHARGLY").toString() + k);
//                            qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                            qualityTreetmp.setProject("熔炼信息");
//                            qualityTreetmp.setContent("【熔炼炉号】:" + listztpp0017.get(k).get("ZTLLH").toString() + " 【熔炼时间】:" + listztpp0017.get(k).get("BLDAT").toString() + " " + listztpp0017.get(k).get("CPUTM").toString());
//                            treeList.add(qualityTreetmp);
//                        }
//
//                    }else{
//                        QualityTree qualityTreetmp = new QualityTree();
//                        qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+list.get(i).get("CHARGLY").toString() + "RLXX");
//                        qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                        qualityTreetmp.setProject("熔炼信息");
//                        treeList.add(qualityTreetmp);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                if (listresbyz.get(0).get("MATKL").toString().equals("3101")){
//                    String sqlztpp00017_2 = "select mandt,werks,charg1,ztllh,bldat,cputm from sapabap1.ZTPP0017 where mandt = '"+webServerHelp.getMandt()+"'" +
//                            " and werks = '" + werks + "'" + " and charg1 = '" + list.get(i).get("CHARGLY").toString() + "' and matnr2 = '" +
//                            listresbyz.get(0).get("MATNR").toString() + "'";
//                    try {
//                        listztpp0017_2 = hanaCon.select(sqlztpp00017_2);
//                        if (listztpp0017_2.size() > 0){
//                            QualityTree qualityTreetmp = new QualityTree();
//                            qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+list.get(i).get("CHARGLY").toString() + "LDXX");
//                            qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                            qualityTreetmp.setProject("铝锭信息");
//                            for (int m =0;m<listztpp0017_2.size();m++){
//                                list.get(i).put("CHARGLD",listztpp0017_2.get(m).get("CHARG2").toString());
//                                list.get(i).put("MATNRLD",listztpp0017_2.get(m).get("MATNR2").toString());
//                                qualityTreetmp.setContent("【铝锭牌号】:" + listresbyz.get(0).get("GROES").toString() +
//                                        " 【铝锭批次】:" + listztpp0017_2.get(m).get("CHARG").toString() +
//                                        " 【铝锭编码】:" + listresbyz.get(0).get("MATNR").toString() + " 【铝液描述】:" + listresbyz.get(0).get("MATKX").toString());
//                                treeList.add(qualityTreetmp);
//                            }
//
//                        }else{
//                            QualityTree qualityTreetmp = new QualityTree();
//                            qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+list.get(i).get("CHARGLY").toString() + "LDXX");
//                            qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                            qualityTreetmp.setProject("铝锭信息");
//                            list.get(i).put("CHARGLD","");
//                            list.get(i).put("MATNRLD","");
//                            treeList.add(qualityTreetmp);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    QualityTree qualityTreetmp = new QualityTree();
//                    qualityTreetmp = new QualityTree();
//                    qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+ "LDXX");
//                    qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                    qualityTreetmp.setProject("铝锭信息");
//                    list.get(i).put("CHARGLD","");
//                    list.get(i).put("MATNRLD","");
//                    treeList.add(qualityTreetmp);
//                }
//            }else{
//
//                QualityTree qualityTreetmp = new QualityTree();
//                qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"41" + "RLXX");
//                qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString() + "4");
//                qualityTreetmp.setProject("熔炼信息");
//                treeList.add(qualityTreetmp);
//
//                 qualityTreetmp = new QualityTree();
//                qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+ "LDXX");
//                qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
//                qualityTreetmp.setProject("铝锭信息");
//                list.get(i).put("CHARGLD","");
//                list.get(i).put("MATNRLD","");
//                treeList.add(qualityTreetmp);
//            }

            //获取供应商
            //根据铝锭批次号 铝液批次号
            String sqlmcha = "";
            List<Map<String, Object>> listmcha = new ArrayList<Map<String,Object>>();
            if (!list.get(i).get("CHARGLY").toString().equals("")){
                 sqlmcha = "select matnr,charg,m.lifnr,licha ,f.sortl from sapabap1.mcha m inner join sapabap1.lfa1 f on f.lifnr = m.lifnr" +
                        " where m.mandt = '"+webServerHelp.getMandt()+"' and charg = '" + list.get(i).get("CHARGLY").toString() + "' and matnr = '" +
                        list.get(i).get("MATNRLY").toString() +"' and f.mandt = '"+webServerHelp.getMandt()+"'";
            }else if (!list.get(i).get("CHARGLD").toString().equals("")){
                sqlmcha = "select matnr,charg,m.lifnr,licha ,f.sortl from sapabap1.mcha m inner join sapabap1.lfa1 f on f.lifnr = m.lifnr" +
                        " where m.mandt = '"+webServerHelp.getMandt()+"' and charg = '" + list.get(i).get("CHARGLD").toString() + "' and matnr = '" +
                        list.get(i).get("MATNRLD").toString() +"' and f.mandt = '"+webServerHelp.getMandt()+"'";
            }

            if (!sqlmcha.equals("")){

                try {
                    listmcha = hanaCon.select(sqlmcha);
                    if (listmcha.size() > 0){
                        for (int j = 0;j<listmcha.size();j++){
                            QualityTree qualityTreetmp = new QualityTree();
                            qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+ "QYSXX");
                            qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
                            qualityTreetmp.setProject("供应商信息");
                            qualityTreetmp.setContent("【供应商编号】:" + listmcha.get(j).get("LIFNR").toString() + " 【供应商名称】:" + listmcha.get(j).get("SORTL").toString() + " 【供应商炉批号】:" + listmcha.get(j).get("LICHA").toString());
                            treeList.add(qualityTreetmp);
                        }
                    }else{
                        QualityTree qualityTreetmp = new QualityTree();
                        qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+ "QYSXX");
                        qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
                        qualityTreetmp.setProject("供应商信息");
                        treeList.add(qualityTreetmp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                QualityTree qualityTreetmp = new QualityTree();
                qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+"42"+ "QYSXX");
                qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+"42");
                qualityTreetmp.setProject("供应商信息");
                treeList.add(qualityTreetmp);
            }



            QualityTree qualityTree41 = new QualityTree();
            qualityTree41.setId(list.get(i).get("BARCODE").toString()+i+"41");
            qualityTree41.setParentId(list.get(i).get("BARCODE").toString()+i + "4");
            qualityTree41.setProject("成品部分");
            treeList.add(qualityTree41);

            QualityTree qualityTree416 = new QualityTree();
            qualityTree416.setId(list.get(i).get("BARCODE").toString()+i+"416");
            qualityTree416.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree416.setProject("客户编码");
            qualityTree416.setContent(list.get(i).get("KUNNR").toString()+i );
            treeList.add(qualityTree416);

            QualityTree qualityTree417 = new QualityTree();
            qualityTree417.setId(list.get(i).get("BARCODE").toString()+i+"417");
            qualityTree417.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree417.setProject("客户名称");
            qualityTree417.setContent(list.get(i).get("NAME1").toString());
            treeList.add(qualityTree417);

            QualityTree qualityTree418 = new QualityTree();
            qualityTree418.setId(list.get(i).get("BARCODE").toString()+i+"418");
            qualityTree418.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree418.setProject("客户条码");
            qualityTree418.setContent(list.get(i).get("ZKHBAR1").toString());
            treeList.add(qualityTree418);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sd2 = new SimpleDateFormat("yyyyMMdd");

            SimpleDateFormat sd3 = new SimpleDateFormat("HHmmSS");
            SimpleDateFormat sd4 = new SimpleDateFormat("HH:mm:SS");

            QualityTree qualityTree411 = new QualityTree();
            qualityTree411.setId(list.get(i).get("BARCODE").toString()+i+"411");
            qualityTree411.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree411.setProject("成品入库时间");
            if (!list.get(i).get("BUDATJJRK").toString().equals("")){
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = sd2.parse(list.get(i).get("BUDATJJRK").toString());
                    date2 = sd3.parse(list.get(i).get("ZPTIMEJJRK").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                qualityTree411.setContent("日期:" + sd.format(date1) + " "+"时间:" + sd4.format(date2));
            }else{
                qualityTree411.setContent("日期:" + list.get(i).get("BUDATJJRK").toString() + " "+"时间:" + list.get(i).get("ZPTIMEJJRK").toString());
            }


            treeList.add(qualityTree411);

            QualityTree qualityTree412 = new QualityTree();
            qualityTree412.setId(list.get(i).get("BARCODE").toString()+i+"412");
            qualityTree412.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree412.setProject("成品入库凭证");
            qualityTree412.setContent("凭证年度："+list.get(i).get("MJAHRJJRK").toString() + " " + "凭证号:"+list.get(i).get("MBLNRJJRK").toString());
            treeList.add(qualityTree412);

            QualityTree qualityTree413 = new QualityTree();
            qualityTree413.setId(list.get(i).get("BARCODE").toString()+i+"413");
            qualityTree413.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree413.setProject("成品出库时间");
            qualityTree413.setContent("日期:" + list.get(i).get("BUDATJJCK").toString() + " " + "时间:" + list.get(i).get("ZPTIMEJJCK").toString());
            treeList.add(qualityTree413);

            QualityTree qualityTree414 = new QualityTree();
            qualityTree414.setId(list.get(i).get("BARCODE").toString()+i+"414");
            qualityTree414.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree414.setProject("成品交货单");
            qualityTree414.setContent(list.get(i).get("VBELNJJ").toString());
            treeList.add(qualityTree414);

            QualityTree qualityTree415 = new QualityTree();
            qualityTree415.setId(list.get(i).get("BARCODE").toString()+i+"415");
            qualityTree415.setParentId(list.get(i).get("BARCODE").toString()+i + "41");
            qualityTree415.setProject("成品批次");
            qualityTree415.setContent(list.get(i).get("CHARGJJ").toString());
            treeList.add(qualityTree415);



            String aufnrjj = list.get(i).get("AUFNRJJ").toString();
            if (aufnrjj.length()< 12){
                for (int j=0;j<=12-aufnrjj.length();j++){
                    aufnrjj = "0" + aufnrjj;
                }
            }

            if (!aufnrjj.equals("")){
                if (list.get(i).get("RSNUM") == null){

                }else{
                    String sqlflj = "select DISTINCT r.matnr, mk.maktx from sapabap1.resb r " +
                            "inner join sapabap1.mara m on r.MATNR = m.MATNR " +
                            " INNER JOIN SAPABAP1.MAKT mk ON r.matnr = mk.matnr " +
                            " INNER JOIN SAPABAP1.AFKO AF ON af.aufpl = r.aufpl " +
                            " where af.aufnr =" + "'" + aufnrjj + "'" + " AND m.MATKL = '3102' and af.mandt = '"+webServerHelp.getMandt()+"' and m.mandt = '"+webServerHelp.getMandt()+"'";

                    String sqlmseg1 = "select matbf,charg_sid from sapabap1.matdoc m inner join sapabap1.afru  a on a.WABLNR = m.mblnr" +
                            " where a.rueck = '" + list.get(i).get("RSNUM").toString() +"'" +
                            "  and a.rmzhl = '" + list.get(i).get("RSPOS").toString() +"'" +
                            "and a.mandt = '"+webServerHelp.getMandt()+"'";

                    String sqlmseg2 = "select matbf,charg_sid from sapabap1.matdoc m inner join sapabap1.afwi  a on a.mblnr = m.mblnr" +
                            " where a.rueck = '" + list.get(i).get("RSNUM").toString() +"'" +
                            "  and a.rmzhl = '" + list.get(i).get("RSPOS").toString() +"'" +
                            "and a.mandt = '"+webServerHelp.getMandt()+"'";


                    List<Map<String, Object>> listresb = new ArrayList<Map<String,Object>>();
                    List<Map<String, Object>> listmseg1 = new ArrayList<Map<String,Object>>();
                    List<Map<String, Object>> listmseg2 = new ArrayList<Map<String,Object>>();
                    try {
                        listresb = hanaCon.select(sqlflj);
                        listmseg1 = hanaCon.select(sqlmseg1);

                        if (listmseg1.size() == 0){
                            listmseg2 = hanaCon.select(sqlmseg2);
                        }

                        if (listresb.size() > 0){
                            for (int k =0;k < listresb.size();k ++){

                                QualityTree qualityTreetmp = new QualityTree();
                                qualityTreetmp.setId(list.get(i).get("BARCODE").toString()+i+"5" + k);
                                qualityTreetmp.setParentId(list.get(i).get("BARCODE").toString()+i+"5");

                                if (listmseg1.size() > 0){
                                    for (int h=0;h<listmseg1.size();h++){
                                        if (listmseg1.get(h).get("MATBF").toString().equals(listresb.get(k).get("MATNR").toString())){
                                            qualityTreetmp.setContent("【物料编码】: " + listresb.get(k).get("MATNR").toString() + "     【批次】:"+ listmseg1.get(h).get("CHARG_SID").toString()+"     【描述】:" + listresb.get(k).get("MAKTX").toString());
                                        }
                                    }
                                }else if (listmseg2.size() > 0){
                                    for (int h = 0;h<listmseg2.size();h++){
                                        if (listmseg2.get(h).get("MATBF").toString().equals(listresb.get(k).get("MATNR").toString())){
                                            qualityTreetmp.setContent("【物料编码】: " + listresb.get(k).get("MATNR").toString() + "     【批次】:"+ listmseg2.get(h).get("CHARG_SID").toString()+"     【描述】:" + listresb.get(k).get("MAKTX").toString());
                                        }
                                    }

                                }else{
                                    qualityTreetmp.setContent("【物料编码】: " + listresb.get(k).get("MATNR").toString() + "     【描述】:" + listresb.get(k).get("MAKTX").toString());
                                }
                                qualityTreetmp.setProject("组件" + (k+1));
                                treeList.add(qualityTreetmp);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        }
        rs.setRows(treeList);
        return rs;
    }
}

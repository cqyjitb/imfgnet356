package yj.kanb.viewdataschemaline;//package yj.kanb.viewdataschemaline;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.web.context.ContextLoaderListener;
//import yj.core.OracleConn.OracleConn;
//import yj.core.cardh.dto.Cardh;
//import yj.core.cardh.service.ICardhService;
//import yj.core.inoutrecord.dto.InOutRecord;
//import yj.core.inoutrecord.service.IInOutRecordService;
//import yj.core.marc.dto.Marc;
//import yj.core.marc.service.IMarcService;
//import yj.core.seversetting.dto.ServerSetting;
//import yj.core.seversetting.service.IServerSettingService;
//import yj.core.util.WebServerHelp;
//import yj.core.wipcurlzk.dto.Curlzk;
//import yj.core.wiplines.dto.Lines;
//import yj.core.wiplines.service.ILinesService;
//import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
//import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//public class GetDataThread implements Runnable {
//    //@Autowired
//    private static ICardhService cardhService;
//    //@Autowired
//    private static IMarcService marcService;
//    //@Autowired
//    private static IServerSettingService serverSettingService;
//    //@Autowired
//    private static IInOutRecordService inOutRecordService;
//    //@Autowired
//    private static ILinesService linesService;
//    //@Autowired
//    private static IViewdataschemalineService viewdataschemalineService;
//    private Thread t;
//    private String threadName ;
//    private String matnr;
//    private String bukrs;
//    private String werks;
//    private String deptId;
//    private List<Curlzk> list;
//    private String groupId;
//    private static ApplicationContext ctx = null;
//    static{
//        ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
//        cardhService = (ICardhService)ctx.getBean("cardhService");
//        marcService = (IMarcService)ctx.getBean("marcService");
//        serverSettingService = (IServerSettingService)ctx.getBean("serverSettingService");
//        inOutRecordService = (IInOutRecordService)ctx.getBean("inOutRecordService");
//        linesService = (ILinesService)ctx.getBean("linesService");
//        viewdataschemalineService = (IViewdataschemalineService)ctx.getBean("viewdataschemalineService");
//    }
//
//    public GetDataThread(String name, String matnr, String bukrs, String werks, String deptId, List<Curlzk> list, String groupId){
//        this.threadName = name;
//        this.matnr = matnr;
//        this.bukrs = bukrs;
//        this.deptId = deptId;
//        this.werks = werks;
//        this.list = list;
//        this.groupId = groupId;
//
//    }
//
//    @Override
//    public void run() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Viewdataschemaline viewdata = new Viewdataschemaline();
//        Double outnum = 0D;
//        Double actnum = 0D;
//        Double plqty = 0D;
//        Double takt_time = 0D;
//        String l_error= "E";
//        for (int i=0;i<list.size();i++){
//            if (list.get(i).getDeptId().equals(deptId) && list.get(i).getMatnrjj().equals(matnr)){
//                System.out.println("进入循环**********************************");
//                viewdata.setClassgrp(list.get(i).getShift());//班组
//                //1:根据生产线当前流转卡号 获取流转卡数据
//                Cardh cardh = new Cardh();
//                String zpgdbar = list.get(i).getZpgdbar();
//                cardh = cardhService.selectByBarcode(zpgdbar);
//
//                if (cardh == null){
//                    System.out.println("没有获取到当前流转卡信息 跳出循环**********************************");
//                    continue;//如果没有获取到当前流转卡信息 跳出循环
//                }else{
//                    plqty = plqty + cardh.getPlqty();
//                }
//                //2：获取产品信息
//                Marc marc= new Marc();
//                marc = marcService.selectByMatnr(cardh.getMatnr());
//                viewdata.setMatnr(marc.getMatnr());
//                viewdata.setMaktx(marc.getMaktx());
//                System.out.println("获取物料信息**********************************");
//
//                //2.1 计算班次时间    白班 08:00:00-15:59:59   中班 16:00-23:59:59 夜班 00:00:00-07:59:59
//                System.out.println("计算班次 及班次开始日期**********************************");
//                Date lastupdate = list.get(i).getLastUpdateDate();//当前流转卡切换的时间
//                String ds1 = sdf.format(lastupdate).substring(0,10) + "08:00:00";
//                Date d1 = null;
//                try {
//                    d1 = sdf.parse(ds1);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String ds2 = sdf.format(lastupdate).substring(0,10) + "15:59:59";
//                Date d2 = null;
//                try {
//                    d2 = sdf.parse(ds2);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String ds3 = sdf.format(lastupdate).substring(0,10) + "16:00:00";
//                Date d3 = null;
//                try {
//                    d3 = sdf.parse(ds3);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String ds4 = sdf.format(lastupdate).substring(0,10) + "23:59:59";
//                Date d4 = null;
//                try {
//                    d4 = sdf.parse(ds4);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String ds5 = sdf.format(lastupdate).substring(0,10) + "00:00:00";
//                Date d5 = null;
//                try {
//                    d5 = sdf.parse(ds5);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String ds6 = sdf.format(lastupdate).substring(0,10) + "07:59:59";
//                Date d6 = null;
//                try {
//                    d6 = sdf.parse(ds6);
//                } catch (ParseException e){
//                    e.printStackTrace();
//                }
//
//                if (lastupdate.getTime() >= d1.getTime() && lastupdate.getTime() <= d2.getTime()){
//                    viewdata.setShift("1");
//                    viewdata.setShiftdes("白班");
//                    viewdata.setShifttimebegin(ds1);
//                }
//
//                if (lastupdate.getTime() >= d3.getTime() && lastupdate.getTime() <= d4.getTime()){
//                    viewdata.setShift("2");
//                    viewdata.setShiftdes("中班");
//                    viewdata.setShifttimebegin(ds1);
//                }
//
//                if (lastupdate.getTime() >= d5.getTime() && lastupdate.getTime() <= d6.getTime()){
//                    viewdata.setShift("2");
//                    viewdata.setShiftdes("夜班");
//                    viewdata.setShifttimebegin(ds1);
//                }
//                //3：去装箱数据
//                ServerSetting serverSetting = new ServerSetting();
//                serverSetting = serverSettingService.selectByLineId(werks,list.get(i).getLineId());
//                WebServerHelp webServerHelp = new WebServerHelp();
//                OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
//                String sqlzx = "select a.main_id,a.item_code,a.barcode,c.carton_code,b.zpgdbar,b.zxhbar,b.rsnum,b.rspos,b.zsxjlh,b.line_id,b.created_by from "+serverSetting.getDbUsername()+".wip_main_data  a"
//                        +" inner join  "+serverSetting.getDbUsername()+".wip_pallet_sn_rel  b on a.main_id = b.main_id"
//                        +" inner join  "+serverSetting.getDbUsername()+".mtl_barcode c on b.barcode_id = c.barcode_id";
//                String where = " where b.line_id = " + "'" + list.get(i).getLineId() + "' and c.status = 0 and b.status = 0 and a.ENABLE_FLAG = '1' ";
//
//                where = where + "and a.item_code = " + "'" + matnr + "' " ;
//
//
//                if (viewdata.getShift().equals("1")){
//                    where = where + " and a.creation_date >= to_date('"+ds1+"','yyyy-mm-dd hh24:mi:ss')";
//                    where = where + " and a.creation_date <= to_date('"+ds2+"','yyyy-mm-dd hh24:mi:ss')";
//                }
//
//                if (viewdata.getShift().equals("2")){
//                    where = where + " and a.creation_date >= to_date('"+ds3+"','yyyy-mm-dd hh24:mi:ss')";
//                    where = where + " and a.creation_date <= to_date('"+ds4+"','yyyy-mm-dd hh24:mi:ss')";
//                }
//
//                if (viewdata.getShift().equals("3")){
//                    where = where + " and a.creation_date >= to_date('"+ds5+"','yyyy-mm-dd hh24:mi:ss')";
//                    where = where + " and a.creation_date <= to_date('"+ds6+"','yyyy-mm-dd hh24:mi:ss')";
//                }
//
//                String sql = sqlzx + where;
//                List<Map<String, Object>> listresult = new ArrayList<Map<String,Object>>();
//
//                try {
//                    listresult = oracleConn.select(sql);
//                    actnum = actnum + listresult.size();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //3.1 取件数量
//                InOutRecord inOutRecord = new InOutRecord();
//                List<InOutRecord> listio = inOutRecordService.selectforKanb(werks,list.get(i).getLineId(),matnr);
//                outnum = outnum + listio.size();
//
//                //4:取产线配置数据
//                Lines lines = new Lines();
//                lines = linesService.selectById(Long.parseLong(list.get(i).getLineId()));
//                takt_time = takt_time + lines.getTaktTime();
//                //5:进度差缺
//
//                //6:差缺数量
//
//
//                l_error = "";
//            }
//        }
//        if (l_error == ""){
//            takt_time = takt_time / list.size();//平均生产节拍
//            viewdata.setPlanqty(plqty);//计划产量
//            viewdata.setActqty(actnum);//实际产量
//            viewdata.setCycletime(takt_time);//平均节拍
//            viewdata.setGroupId(groupId);//产线组ID
//            try {
//                Date startDate = sdf.parse(viewdata.getShifttimebegin());
//                Long times = new Double(viewdata.getPlanqty() * viewdata.getCycletime()).longValue();
//                times = startDate.getTime() + times;
//                viewdata.setShifttimeend(sdf.format(new Date(times)));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            //viewdata.setShifttimeend();
//            viewdata.setInsufqty(viewdata.getActqty() - viewdata.getPlanqty());//差缺数量
//            Double qcrate = ( viewdata.getActqty() / viewdata.getActqty() + outnum ) * 100;
//            Double oeerate = ( viewdata.getActqty() / viewdata.getActqty() + outnum ) * 100;
//            viewdata.setOeeRate(0D);//oee
//            viewdata.setQcRate(qcrate);//合格率
//
//            try {
//                viewdata.setJdcqqty(viewdata.getActqty() - (new Date().getTime() - sdf.parse(viewdata.getShifttimebegin()).getTime()) / viewdata.getCycletime());//进度差缺
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            Viewdataschemaline viewdatatmp = viewdataschemalineService.selectforKanb(groupId,matnr,deptId,bukrs,werks);
//            if (viewdatatmp != null){
//                viewdataschemalineService.updateforKanb(viewdata);
//            }else{
//                viewdataschemalineService.insertforKanb(viewdata);
//            }
//        }
//
//    }
//
//    public void start(){
//        if (t == null){
//            t = new Thread(this,threadName);
//            t.start();
//        }
//    }
//}

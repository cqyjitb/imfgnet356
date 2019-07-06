package yj.core.zudhead.controllers;//package yj.core.zudhead.controllers;
//
//import net.sf.json.JSON;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.apache.bcel.generic.IF_ACMPEQ;
//import org.apache.poi.ss.formula.functions.T;
//import org.apache.regexp.RE;
//import org.springframework.stereotype.Controller;
//import com.hand.hap.system.controllers.BaseController;
//import com.hand.hap.core.IRequest;
//import com.hand.hap.system.dto.ResponseData;
//import org.springframework.web.bind.annotation.*;
//import yj.core.cardh.dto.Cardh;
//import yj.core.cardh.service.ICardhService;
//import yj.core.cardt.dto.Cardt;
//import yj.core.cardt.service.ICardtService;
//import yj.core.dispatch.dto.InputLog;
//import yj.core.dispatch.dto.Log;
//import yj.core.dispatch.dto.Result;
//import yj.core.dispatch.service.IInputLogService;
//import yj.core.dispatch.service.ILogService;
//import yj.core.dispatch.service.IResultService;
//import yj.core.inoutrecord.dto.InOutRecord;
//import yj.core.inoutrecord.service.IInOutRecordService;
//import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
//import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
//import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
//import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
//import yj.core.webservice_queryXhcard.receiver.DTQUERYXHCARDRes;
//import yj.core.xhcard.dto.Xhcard;
//import yj.core.xhcard.service.IXhcardService;
//import yj.core.zudhead.dto.ParamAndQjjlh;
//import yj.core.zudhead.dto.Zudhead;
//import yj.core.zudhead.dto.recPageData;
//import yj.core.zudhead.service.IZudheadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import yj.core.zudlist.dto.Zudlist;
//import yj.core.zudlist.service.IZudlistService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//
//@Controller
//public class ZudheadControllerbak extends BaseController {
//
//    @Autowired
//    private IZudheadService service;
//    @Autowired
//    private ICardhService cardhService;
//    @Autowired
//    private IXhcardService xhcardService;
//    @Autowired
//    private IInputLogService inputLogService;
//    @Autowired
//    private ILogService logService;
//    @Autowired
//    private IResultService resultService;
//    @Autowired
//    private IInOutRecordService iInOutRecordService;
//    @Autowired
//    private IZudlistService zudlistService;
//    @Autowired
//    private ICardtService cardtService;
//
//
//    @RequestMapping(value = "/wip/zudhead/query")
//    @ResponseBody
//    public ResponseData query(Zudhead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
//                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
//        IRequest requestContext = createRequestContext(request);
//        return new ResponseData(service.select(requestContext, dto, page, pageSize));
//    }
//
//    @RequestMapping(value = "/wip/zudhead/submit")
//    @ResponseBody
//    public ResponseData update(HttpServletRequest request, @RequestBody List<Zudhead> dto) {
//        IRequest requestCtx = createRequestContext(request);
//        return new ResponseData(service.batchUpdate(requestCtx, dto));
//    }
//
//    @RequestMapping(value = "/wip/zudhead/remove")
//    @ResponseBody
//    public ResponseData delete(HttpServletRequest request, @RequestBody List<Zudhead> dto) {
//        service.batchDelete(dto);
//        return new ResponseData();
//    }
//
//    @RequestMapping(value = {"/wip/zudhead/createZud"}, method = {RequestMethod.POST})
//    @ResponseBody
//    public ResponseData createZud(HttpServletRequest request,@RequestBody List<recPageData> a) {
//        ResponseData responseData = new ResponseData();
//        String createdBy = "" + request.getSession().getAttribute("userId");
//        List<Zudlist> listitem = new ArrayList<>();
//        List<DTBAOGONGParameters> listparam = new ArrayList<>();
//
//        if (a.size() > 0) {
//            //创建不合格审理单表头
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
//            Zudhead zudhead = new Zudhead();
//            //根据创建日期查询最大序号
//            String maxno = service.selectMaxNo(curdate);
//            String zudnum = "";
//            if (maxno == null) {
//                zudnum = "J" + curdate.substring(2, 8) + "001";
//            } else {
//                String no = maxno.substring(7, 10);
//                Integer num = Integer.valueOf(no);
//                num = num + 1;
//                no = num.toString();
//                int times = 3 - no.length();
//                for (int i = 0; i < times; i++) {
//                    no = "0" + no;
//                }
//                zudnum = "J" + curdate.substring(2, 8) + no;
//            }
//            zudhead.setZudnum(zudnum);
//            zudhead.setUdtype("1");
//            zudhead.setLineId(a.get(0).getPlineId());
//            zudhead.setArbpr(a.get(0).getArbpr());
//            zudhead.setCrdat(curdate);
//            zudhead.setCreatedBy(Long.valueOf(createdBy));
//            zudhead.setCreationDate(new Date());
//            List<ParamAndQjjlh> listparamQjjlh = new ArrayList<>();
//            //准备审理单行数据 和 报工数据
//            for (int i = 0; i < a.size(); i++) {
//                //审理单行数据
//                Zudlist zudlist = new Zudlist();
//                zudlist.setZudnum(zudhead.getZudnum());
//                zudlist.setItem(Integer.valueOf(i + 1).toString());
//                zudlist.setLineId(a.get(i).getLineId());
//                zudlist.setArbpr(a.get(i).getArbpr());
//                zudlist.setZqjjlh(a.get(i).getZqjjlh());
//                zudlist.setZpgdbar(a.get(i).getZpgdbar());
//                zudlist.setVornr(a.get(i).getVornr());
//                zudlist.setZxhbar(a.get(i).getZxhbar());
//                zudlist.setZpgdbar2(a.get(i).getZpgdbar2());
//                zudlist.setGstrp(a.get(i).getGstrp());
//                zudlist.setMatnr(a.get(i).getMatnr());
//                zudlist.setMatnr2(a.get(i).getMatnr2());
//                zudlist.setZgjbar(a.get(i).getZgjbar());
//                zudlist.setZdnum(Long.valueOf(a.get(i).getZdnum()));
//                zudlist.setGmein(a.get(i).getGmein());
//                zudlist.setCharg2(a.get(i).getCharg2());
//                zudlist.setCharg(a.get(i).getCharg());
//                zudlist.setDiecd(a.get(i).getDiecd());
//                zudlist.setSfflg(a.get(i).getSfflg());
//                zudlist.setZqxdm(a.get(i).getZqxdm());
//                zudlist.setZissuetxt(a.get(i).getZissuetxt());
//                zudlist.setZbpjc(a.get(i).getZbpjc());
//                zudlist.setZoplo(a.get(i).getZoplo());
//                zudlist.setZbanc(a.get(i).getZbanc());
//                zudlist.setZbanz(a.get(i).getZbanz());
//                zudlist.setRspart(a.get(i).getRspart());
//                zudlist.setRsname(a.get(i).getRsname());
//                zudlist.setReviewc("F");
//                zudlist.setMark(a.get(i).getMark());
//                zudlist.setCreatedBy(Long.valueOf(createdBy));
//                zudlist.setReviewc(a.get(i).getReviewc());
//                zudlist.setCreationDate(new Date());
//                if (a.get(i).getVornr() == null){
//                    zudlist.setVornr("");
//                }else{
//                    zudlist.setVornr(a.get(i).getVornr());
//                }
//
//                if ( a.get(i).getVornr_old() == null){
//                    zudlist.setVornr_old("");
//                }else{
//                    zudlist.setVornr_old(a.get(i).getVornr_old());
//                }
//
//                listitem.add(zudlist);
//
//
//                //报工数据
//                Cardh cardhjj = new Cardh();
//                cardhjj = cardhService.selectByBarcode(a.get(i).getZpgdbar());
//                if (listparam.size() > 0) {
//                    DTBAOGONGParameters parameters = new DTBAOGONGParameters();
//                    String flg = "";
//
//                    for (int j = 0; j < listparam.size(); j++) {
//
//                        if (listparam.get(j).getZPGDBAR().equals(a.get(i).getZpgdbar())) {
//                            //重复机加流转卡取件记录
//                            //数量直接累计
//                            flg = "X";
//                            if (a.get(i).getZqxdm().substring(0, 1).equals("M")) {
//                                Double num = Double.valueOf(listparam.get(j).getRMNGA()) + 1;
//                                listparam.get(j).setRMNGA(num.toString());
//                            } else {
//                                Double num = Double.valueOf(listparam.get(j).getXMNGA()) + 1;
//                                listparam.get(j).setXMNGA(num.toString());
//                            }
//                            ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
//                            paramAndQjjlh.setNum(listparam.size() - 1);
//                            paramAndQjjlh.setQjjlh(a.get(i).getZqjjlh());
//                            listparamQjjlh.add(paramAndQjjlh);
//                        }
//                    }
//                    if (flg.equals("")) {
//                        parameters.setPWERK("1001");
//                        parameters.setAUFNR(cardhjj.getAufnr());
//                        //获取机加装箱工序
//                        Cardt cardt = new Cardt();
//                        cardt.setZpgdbar(cardhjj.getZpgdbar());
//                        cardt.setKtsch("21001");
//                        cardt = cardtService.selectByBarcodeAndKtsch(cardt);
//                        parameters.setVORNR(cardt.getVornr());//工序号
//                        parameters.setBUDAT(curdate);
//                        parameters.setDATUM(curdate);
//                        parameters.setGMNGA("0");
//                        parameters.setRMNGA("0");
//                        parameters.setXMNGA("0");
//                        if (a.get(i).getZqxdm().substring(0, 1).equals("M")) {
//                            parameters.setRMNGA("1");
//                        } else {
//                            parameters.setXMNGA("1");
//                        }
//                        parameters.setZSCBC("");
//                        parameters.setZSCX("");
//                        parameters.setZMNUM("");
//                        parameters.setZPGDBAR(cardhjj.getZpgdbar());
//                        parameters.setZPGDBH(cardhjj.getZpgdbh());
//                        parameters.setRSNUM("");
//                        parameters.setRSPOS("");
//                        parameters.setREVERSE("");
//                        parameters.setATTR1(createdBy);
//                        parameters.setATTR2("");
//                        parameters.setATTR3("");
//                        parameters.setATTR4("");//报工类别
//                        parameters.setATTR5("");
//                        parameters.setATTR6("");
//                        parameters.setATTR7("");
//                        parameters.setUSERNAME(createdBy);
//                        parameters.setZTPBAR("");
//                        parameters.setLSTVOR("X");
//                        parameters.setFSTVOR("");
//                        parameters.setZPRTP("4");
//                        parameters.setAUART(cardhjj.getAuart());
//                        parameters.setARBPL(cardt.getArbpl());
//                        parameters.setCHARG("");
//                        listparam.add(parameters);
//                        ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
//                        paramAndQjjlh.setNum(listparam.size() - 1);
//                        paramAndQjjlh.setQjjlh(a.get(i).getZqjjlh());
//                        listparamQjjlh.add(paramAndQjjlh);
//                    }
//
//                } else {
//                    DTBAOGONGParameters parameters = new DTBAOGONGParameters();
//                    parameters.setPWERK("1001");
//                    parameters.setAUFNR(cardhjj.getAufnr());
//                    //获取机加装箱工序
//                    Cardt cardt = new Cardt();
//                    cardt.setZpgdbar(cardhjj.getZpgdbar());
//                    cardt.setKtsch("21001");
//                    cardt = cardtService.selectByBarcodeAndKtsch(cardt);
//                    parameters.setVORNR(cardt.getVornr());//工序号
//                    parameters.setBUDAT(curdate);
//                    parameters.setDATUM(curdate);
//                    parameters.setGMNGA("0");
//                    parameters.setRMNGA("0");
//                    parameters.setXMNGA("0");
//                    if (a.get(i).getZqxdm().substring(0, 1).equals("M")) {
//                        parameters.setRMNGA("1");
//                    } else {
//                        parameters.setXMNGA("1");
//                    }
//                    parameters.setZSCBC("");
//                    parameters.setZSCX("");
//                    parameters.setZMNUM("");
//                    parameters.setZPGDBAR(cardhjj.getZpgdbar());
//                    parameters.setZPGDBH(cardhjj.getZpgdbh());
//                    parameters.setRSNUM("");
//                    parameters.setRSPOS("");
//                    parameters.setREVERSE("");
//                    parameters.setATTR1(createdBy);
//                    parameters.setATTR2("");
//                    parameters.setATTR3("");
//                    parameters.setATTR4("");//报工类别
//                    parameters.setATTR5("");
//                    parameters.setATTR6("");
//                    parameters.setATTR7("");
//                    parameters.setUSERNAME(createdBy);
//                    parameters.setZTPBAR("");
//                    parameters.setLSTVOR("X");
//                    parameters.setFSTVOR("");
//                    parameters.setZPRTP("4");
//                    parameters.setAUART(cardhjj.getAuart());
//                    parameters.setARBPL(cardt.getArbpl());
//                    parameters.setCHARG("");
//                    listparam.add(parameters);
//                    ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
//                    paramAndQjjlh.setNum(listparam.size() - 1);
//                    paramAndQjjlh.setQjjlh(a.get(i).getZqjjlh());
//                    listparamQjjlh.add(paramAndQjjlh);
//
//                }
//            }
//            ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();
//            String isbg = "";//是否有报工成功的记录
//            String iserr = "";//是否有失败的报工记录
//            if (listparam.size() > 0) {
//                for (int i = 0; i < listparam.size(); i++) {
//
//                    List<DTBAOGONGParametersitem> parametersitems = new ArrayList<>();
//                    List<InOutRecord> listinoutRecord = new ArrayList<>();
//                    for (int j = 0; j < a.size(); j++) {
//                        String flg = "";
//                        //准备领料明细数据
//                        String pZpgdbar = listparam.get(i).getZPGDBAR();
//                        String recZpgdbar = a.get(j).getZpgdbar();
//                        if (listparam.get(i).getZPGDBAR().equals(a.get(j).getZpgdbar())) {
//                            //取箱号信息
//                            DTBAOGONGParametersitem parametersitem = new DTBAOGONGParametersitem();
//                            Xhcard xhcard = new Xhcard();
//                            xhcard = xhcardService.selectByBacode(a.get(j).getZxhbar());
//                            if (parametersitems.size() > 0) {
//                                for (int y = 0; y < parametersitems.size(); y++) {
//                                    if (parametersitems.get(y).getCHARG().equals(xhcard.getChargkc())) {
//                                        flg = "X";
//                                        Integer num = Integer.valueOf(parametersitems.get(y).getBDMNG()) + 1;
//                                        parametersitems.get(y).setBDMNG(num.toString());
//                                    }
//                                }
//
//                                if (flg.equals("")) {
//                                    parametersitem.setSUBRSNUM("");
//                                    parametersitem.setSUBRSPOS("");
//                                    parametersitem.setBDMNG("1");
//                                    parametersitem.setMATNR(xhcard.getMatnr());
//                                    parametersitem.setCHARG(xhcard.getChargkc());
//                                    parametersitem.setLGORT(xhcard.getLgort());
//                                    parametersitem.setMEINS(xhcard.getMeins());
//                                    parametersitem.setWERKS(xhcard.getWerks());
//                                    parametersitems.add(parametersitem);
//                                }
//
//                            } else {
//                                parametersitem.setSUBRSNUM("");
//                                parametersitem.setSUBRSPOS("");
//                                parametersitem.setBDMNG("1");
//                                parametersitem.setMATNR(xhcard.getMatnr());
//                                parametersitem.setCHARG(xhcard.getChargkc());
//                                parametersitem.setLGORT(xhcard.getLgort());
//                                parametersitem.setMEINS(xhcard.getMeins());
//                                parametersitem.setWERKS(xhcard.getWerks());
//                                parametersitems.add(parametersitem);
//                            }
//
//                        }
//
//                    }
//                    DTBAOGONGReturnResult rs = new DTBAOGONGReturnResult();
//                    rs = confirmationWebserviceUtilNew.receiveConfirmation(listparam.get(i), parametersitems);
//
//                    if (rs.getMSGTY().equals("S")) {
//                        isbg = "X";
//                        //1,更新wip_in_out_record    //更新 wip_zudlist
//                        List<Zudlist> listsaveZudlist = new ArrayList<>();
//                        for (int x = 0; x < listparamQjjlh.size(); x++) {
//                            if (listparamQjjlh.get(x).getNum() == i) {
//                                InOutRecord inOutRecord = new InOutRecord();
//                                inOutRecord = iInOutRecordService.selectById(listparamQjjlh.get(x).getQjjlh());
//                                inOutRecord.setReflag(2L);
//                                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
//                                inOutRecord.setLastUpdateDate(new Date());
//                                listinoutRecord.add(inOutRecord);
//
//                                //写不合格审理单行项目
//                                for (int y = 0; y < listitem.size(); y++) {
//                                    if (listitem.get(y).getZqjjlh().equals(listparamQjjlh.get(x).getQjjlh())) {
//                                        listitem.get(y).setRueck(rs.getRSNUM());
//                                        listitem.get(y).setRmzhl(rs.getRSPOS());
//                                        listsaveZudlist.add(listitem.get(y));
//                                    }
//                                }
//                            }
//                        }
//
//                        iInOutRecordService.batchUpdateReflag(listinoutRecord);
//                        zudlistService.insertItem(listsaveZudlist);
//                        //2，更新 cardh
//                        Cardh cardhjj = new Cardh();
//                        cardhjj = cardhService.selectByBarcode(listparam.get(i).getZPGDBAR());
//                        if (cardhjj.getQtysp() != null){
//                            cardhjj.setQtysp(Double.valueOf(listparam.get(i).getXMNGA()) + cardhjj.getQtysp());//?
//                        }else{
//                            cardhjj.setQtysp(Double.valueOf(listparam.get(i).getXMNGA()));
//                        }
//
//                        if (cardhjj.getQtysm() != null){
//                            cardhjj.setQtysm(Double.valueOf(listparam.get(i).getRMNGA()) + cardhjj.getQtysm());//?
//                        }else{
//                            cardhjj.setQtysm(Double.valueOf(listparam.get(i).getRMNGA()));
//                        }
//                        cardhjj.setLastUpdatedBy(Long.valueOf(createdBy));
//                        cardhjj.setLastUpdatedDate(new Date());
//                        List<Cardh> listcardh = new ArrayList<>();
//                        listcardh.add(cardhjj);
//                        cardhService.updateCardhStatus(listcardh);
//
//                    } else {
//                        iserr = "X";
//                    }
//                    Log log = new Log();
//                    Result result = new Result();
//                    InputLog inputLog = new InputLog();
//                    inputLog.setBarcode(listparam.get(i).getZPGDBAR());
//                    inputLog.setOrderno(listparam.get(i).getAUFNR());
//                    inputLog.setDispatch(listparam.get(i).getZPGDBAR());
//                    inputLog.setOperation(listparam.get(i).getVORNR());
//                    inputLog.setYeild(Double.parseDouble(listparam.get(i).getGMNGA()));
//                    inputLog.setWorkScrap(Double.parseDouble(listparam.get(i).getXMNGA()));
//                    inputLog.setRowScrap(Double.parseDouble(listparam.get(i).getRMNGA()));
//                    inputLog.setClassgrp(listparam.get(i).getZSCBC());
//                    inputLog.setLine(listparam.get(i).getZSCX());
//                    inputLog.setModelNo("");
//                    inputLog.setPlant(listparam.get(i).getPWERK());
//                    inputLog.setPostingDate(listparam.get(i).getBUDAT());
//                    inputLog.setDispatchLogicID(listparam.get(i).getZPGDBH());
//                    inputLog.setCreated_by(listparam.get(i).getUSERNAME());
//                    inputLog.setAttr1(listparam.get(i).getATTR1());
//                    inputLog.setAttr2(listparam.get(i).getATTR2());
//                    inputLog.setAttr3(listparam.get(i).getATTR3());
//                    inputLog.setAttr4(listparam.get(i).getATTR4());
//                    inputLog.setAttr5(listparam.get(i).getATTR5());
//                    inputLog.setAttr6(listparam.get(i).getATTR6());
//                    inputLog.setAttr7(listparam.get(i).getATTR7());
//                    inputLog.setUserName(listparam.get(i).getUSERNAME());
//                    inputLog.setMaterial(rs.getMATNR());
//                    inputLog.setMatDesc(rs.getMAKTX());
//                    inputLogService.insertInputLog(inputLog);
//                    Long id = inputLogService.selectNextId();
//                    result.setPlant(inputLog.getPlant());
//                    result.setInputId(id);
//                    result.setIsReversed("0");
//                    result.setMaterial(inputLog.getMaterial());
//                    result.setMatDesc(inputLog.getMatDesc());
//                    result.setCreated_by(inputLog.getCreated_by());
//                    result.setConfirmationNo(rs.getRSNUM());
//                    result.setConfirmationPos(rs.getRSPOS());
//                    result.setFevor(rs.getFEVOR());
//                    result.setFevorTxt(rs.getTXT());
//                    result.setOperationDesc(rs.getLTXA1());
//                    log.setMsgty(rs.getMSGTY());
//                    log.setMsgtx(rs.getMESSAGE());
//                    log.setTranType("0");
//                    log.setRefId(id);
//                    log.setCreated_by(inputLog.getCreated_by());
//                    resultService.insertResult(result);
//                    logService.insertLog(log);
//                }
//            }
//            //保存不合格品审理单
//            if (isbg.equals("X") && iserr.equals("")) {
//                service.insertHead(zudhead);
//                responseData.setCode("S");
//                responseData.setMessage("不合格品处理单创建成功！");
//                responseData.setSuccess(true);
//            } else if (isbg.equals("") && iserr.equals("X")) {
//                responseData.setCode("S");
//                responseData.setMessage("不合格品处理单创建失败！");
//                responseData.setSuccess(true);
//            } else if (isbg.equals("X") && iserr.equals("X")) {
//                service.insertHead(zudhead);
//                responseData.setCode("S");
//                responseData.setMessage("不合格品处理单创建成功！但存在报工失败的记录行，请查询报工日志！");
//                responseData.setSuccess(true);
//            }
//
//        }
//        return responseData;
//    }
//}

package yj.core.zudhead.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.service.IInputLogService;
import yj.core.dispatch.service.ILogService;
import yj.core.dispatch.service.IResultService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.zudhead.dto.ParamAndQjjlh;
import yj.core.zudhead.dto.Zudhead;
import yj.core.zudhead.dto.recPageData;
import yj.core.zudhead.service.IZudheadService;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.service.IZudlistService;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.service.IZudlogService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
public class ZudheadController extends BaseController {

    @Autowired
    private IZudheadService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private ILogService logService;
    @Autowired
    private IResultService resultService;
    @Autowired
    private IInOutRecordService iInOutRecordService;
    @Autowired
    private IZudlistService zudlistService;
    @Autowired
    private ICardtService cardtService;
    @Autowired
    private IZudlogService zudlogService;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IDftrghlistService dftrghlistService;


    @RequestMapping(value = "/wip/zudhead/query")
    @ResponseBody
    public ResponseData query(Zudhead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/zudhead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Zudhead> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zudhead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Zudhead> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/zudhead/createZud"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData createZud(HttpServletRequest request, @RequestBody List<recPageData> a) {
        ResponseData responseData = new ResponseData();
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<Zudlist> listitem = new ArrayList<>();
        List<DTBAOGONGParameters> listparam = new ArrayList<>();

        if (a.size() > 0) {
            //创建不合格审理单表头
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
            Zudhead zudhead = new Zudhead();
            //根据创建日期查询最大序号
            String maxno = service.selectMaxNo(curdate);
            String zudnum = "";
            if (maxno == null) {
                zudnum = "J" + curdate.substring(2, 8) + "001";
            } else {
                String no = maxno.substring(7, 10);
                Integer num = Integer.valueOf(no);
                num = num + 1;
                no = num.toString();
                int times = 3 - no.length();
                for (int i = 0; i < times; i++) {
                    no = "0" + no;
                }
                zudnum = "J" + curdate.substring(2, 8) + no;
            }
            zudhead.setZudnum(zudnum);
            zudhead.setUdtype("1");
            zudhead.setLineId(a.get(0).getPlineId());
            zudhead.setArbpr(a.get(0).getArbpr());
            zudhead.setCrdat(curdate);
            zudhead.setCreatedBy(Long.valueOf(createdBy));
            zudhead.setStatus("0");
            zudhead.setCreationDate(new Date());
            List<ParamAndQjjlh> listparamQjjlh = new ArrayList<>();
            List<InOutRecord> listinout = new ArrayList<>();
            List<Dftrghlist> listdf = new ArrayList<>();
            String l_error = "";
            //准备审理单行数据 和 报工数据
            for (int i = 0; i < a.size(); i++) {
                //审理单行数据
                Zudlist zudlist = new Zudlist();
                zudlist.setZudnum(zudhead.getZudnum());
                zudlist.setItem(Integer.valueOf(i + 1).toString());
                zudlist.setLineId(a.get(i).getLineId());
                zudlist.setArbpr(a.get(i).getArbpr());
                zudlist.setZqjjlh(a.get(i).getZqjjlh());
                zudlist.setZpgdbar(a.get(i).getZpgdbar());
                zudlist.setVornr(a.get(i).getVornr());
                zudlist.setZxhbar(a.get(i).getZxhbar());
                zudlist.setZpgdbar2(a.get(i).getZpgdbar2());
                zudlist.setGstrp(a.get(i).getGstrp());
                zudlist.setMatnr(a.get(i).getMatnr());
                zudlist.setMatnr2(a.get(i).getMatnr2());
                zudlist.setZgjbar(a.get(i).getZgjbar());
                zudlist.setZdnum(Long.valueOf(a.get(i).getZdnum()));
                zudlist.setGmein(a.get(i).getGmein());
                zudlist.setCharg2(a.get(i).getCharg2());
                zudlist.setCharg(a.get(i).getCharg());
                zudlist.setDiecd(a.get(i).getDiecd());
                zudlist.setSfflg(a.get(i).getSfflg());
                zudlist.setZqxdm(a.get(i).getZqxdm());
                zudlist.setZissuetxt(a.get(i).getZissuetxt());
                zudlist.setZbpjc(a.get(i).getZbpjc());
                zudlist.setZoplo(a.get(i).getZoplo());
                zudlist.setZbanc(a.get(i).getZbanc());
                zudlist.setZbanz(a.get(i).getZbanz());
                zudlist.setRspart(a.get(i).getRspart());
                zudlist.setRsname(a.get(i).getRsname());
                zudlist.setZctype(a.get(i).getZctype());
                zudlist.setStatus("0");
                zudlist.setReviewc("");
                zudlist.setMark(a.get(i).getMark());
                zudlist.setCreatedBy(Long.valueOf(createdBy));
                zudlist.setReviewc(a.get(i).getReviewc());
                zudlist.setCreationDate(new Date());
                if (a.get(i).getVornr() == null) {
                    zudlist.setVornr("");
                } else {
                    zudlist.setVornr(a.get(i).getVornr());
                }

                if (a.get(i).getVornr_old() == null) {
                    zudlist.setVornr_old("");
                } else {
                    zudlist.setVornr_old(a.get(i).getVornr_old());
                }

                listitem.add(zudlist);
                if (zudlist.getZctype().equals("1")){
                    Dftrghlist dftrghlist = new Dftrghlist();
                    String[] array = zudlist.getZqjjlh().split("_");
                    dftrghlist = dftrghlistService.selectByIdAndItem(array[1],Long.parseLong(array[2]));
                    if (!dftrghlist.getCancelFlag().equals("0")){
                        responseData.setMessage("选择的取件记录中存在状态已经变更的行，请重新查询后创建不合格品审理单！");
                        l_error = "E";
                        break;
                    }
                    dftrghlist.setCancelFlag("2");
                    dftrghlist.setLastUpdatedBy(Long.valueOf(createdBy));
                    dftrghlist.setLastUpdateDate(new Date());
                    listdf.add(dftrghlist);
                }else{
                    InOutRecord inOutRecord = new InOutRecord();
                    inOutRecord = iInOutRecordService.selectById(zudlist.getZqjjlh());
                    if  (inOutRecord.getReflag() != 0){
                        responseData.setMessage("选择的取件记录中存在状态已经变更的行，请重新查询后创建不合格品审理单！");
                        l_error = "E";
                        break;
                    }
                    inOutRecord.setReflag(2L);
                    inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                    inOutRecord.setLastUpdateDate(new Date());
                    listinout.add(inOutRecord);
                }

            }

            if (l_error.equals("E")){
                responseData.setSuccess(false);
            }else{
                //保存不合格品审理单1 表头
                if (zudhead != null){
                    int num = service.insertHead(zudhead);
                    if (num == 1){
                        num = zudlistService.insertItem(listitem);
                        if (num != listitem.size()){
                            responseData.setCode("E");
                            responseData.setSuccess(false);
                            responseData.setMessage("创建不合格品审理单1行失败！");
                        }else{
                            iInOutRecordService.batchUpdateReflag(listinout);

                            dftrghlistService.batchUpdateCancelflag(listdf);

                            responseData.setCode("S");
                            responseData.setSuccess(true);
                            responseData.setMessage("创建不合格品审理单1成功！单号：" + zudhead.getZudnum());
                        }
                    }else{
                        responseData.setCode("E");
                        responseData.setSuccess(false);
                        responseData.setMessage("创建不合格品审理单1表头失败！");
                    }
                }
            }

        }
        return responseData;
    }

    @RequestMapping(value = {"/wip/zudhead/processZud"}, method = {RequestMethod.POST})
    @ResponseBody
    ResponseData processZud(HttpServletRequest request, @RequestBody List<Zudlist> listtmp){
        ResponseData rs = new ResponseData();
        if (listtmp.size() == 0){
            rs.setSuccess(false);
            rs.setMessage("请选择需要处理的记录！");
            return rs;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<Zudhead> headlist = new ArrayList<>();
        List<DTBAOGONGParameters> listparam = new ArrayList<>();
        List<ParamAndQjjlh> listparamQjjlh = new ArrayList<>();
        List<Zudlist> list = new ArrayList<>();
        for (int i=0;i<listtmp.size();i++){
            Zudhead zudhead = new Zudhead();
            zudhead = service.selectByZudnum(listtmp.get(i).getZudnum());
            if (headlist.size() == 0){
                headlist.add(zudhead);
            }else{
                if (!headlist.contains(zudhead)){
                    headlist.add(zudhead);
                }
            }
            Zudlist zudlist = new Zudlist();
            zudlist = zudlistService.selectByIdAndItem(listtmp.get(i).getZudnum(),listtmp.get(i).getItem());
            zudlist.setReviewc(listtmp.get(i).getReviewc());
            String lineId = zudlist.getLineId();
            Lines lines = linesService.selectById(Long.parseLong(lineId));
            Long PlineId = lines.getPlineId();
            if (PlineId == null){
                PlineId = Long.parseLong(lineId);
            }
            //处理回到取还件记录表的数据记录
            if (zudlist.getReviewc().equals("Q")){
                zudlist.setStatus("1");
                zudlist.setLastUpdateDate(new Date());
                zudlist.setLastUpdatedBy(Long.valueOf(createdBy));
                zudlistService.updateItem(zudlist);

                if (zudlist.getZctype().equals("1")){
                    Dftrghlist dftrghlist = new Dftrghlist();
                    String[] strs = zudlist.getZqjjlh().split("_");
                    dftrghlist = dftrghlistService.selectByIdAndItem(strs[1],Long.parseLong(strs[2]));
                    dftrghlist.setCancelFlag("0");
                    dftrghlist.setLastUpdatedBy(Long.valueOf(createdBy));
                    dftrghlist.setLastUpdateDate(new Date());
                    List<Dftrghlist> listdf = new ArrayList<>();
                    listdf.add(dftrghlist);
                    dftrghlistService.batchUpdateCancelflag(listdf);
                }else{
                    InOutRecord inOutRecord = iInOutRecordService.selectById(zudlist.getZqjjlh());
                    inOutRecord.setLastUpdateDate(new Date());
                    inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                    inOutRecord.setReflag(0L);
                    iInOutRecordService.updateReflag(inOutRecord);
                }


                Zudlog zudlog = new Zudlog();
                UUID uuid = UUID.randomUUID();
                String uuidstr = uuid.toString().replaceAll("-", "");
                zudlog.setId(uuidstr);
                zudlog.setZudnum(zudlist.getZudnum());
                zudlog.setItem(zudlist.getItem());
                zudlog.setMsgtx("还回取还件队列");
                zudlog.setStatus("1");
                zudlog.setCreatedBy(Long.valueOf(createdBy));
                zudlog.setCreationDate(new Date());
                zudlogService.insertLog(zudlog);
            }else{
                //准备报工数据
                Cardh cardhjj = new Cardh();
                cardhjj = cardhService.selectByBarcode(zudlist.getZpgdbar());
                if (listparam.size() > 0){
                    DTBAOGONGParameters parameters = new DTBAOGONGParameters();
                    String flg = "";
                    for (int j = 0; j < listparam.size(); j++) {
                        if (listparam.get(j).getZPGDBAR().equals(zudlist.getZpgdbar())) {
                            //重复机加流转卡取件记录
                            //数量直接累计
                            flg = "X";
                            if (zudlist.getZqxdm().substring(0, 1).equals("M")) {
                                Double num = Double.valueOf(listparam.get(j).getRMNGA()) + zudlist.getZdnum();
                                listparam.get(j).setRMNGA(num.toString());
                            }else{
                                Double num = Double.valueOf(listparam.get(j).getXMNGA()) + zudlist.getZdnum();
                                listparam.get(j).setXMNGA(num.toString());
                            }
                            ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
                            //paramAndQjjlh.setNum(listparam.size() - 1);
                            paramAndQjjlh.setNum(j);
                            paramAndQjjlh.setQjjlh(zudlist.getZqjjlh());
                            listparamQjjlh.add(paramAndQjjlh);
                        }
                    }
                    if (flg.equals("")) {
                        parameters.setPWERK("1001");
                        parameters.setAUFNR(cardhjj.getAufnr());
                        //获取机加装箱工序
                        Cardt cardt = new Cardt();
                        cardt.setZpgdbar(cardhjj.getZpgdbar());
                        cardt.setKtsch("21001");
                        cardt = cardtService.selectByBarcodeAndKtsch(cardt);
                        parameters.setVORNR(cardt.getVornr());//工序号
                        parameters.setBUDAT(curdate);
                        parameters.setDATUM(curdate);
                        parameters.setGMNGA("0");
                        parameters.setRMNGA("0");
                        parameters.setXMNGA("0");
                        if (zudlist.getZqxdm().substring(0, 1).equals("M")) {
                            parameters.setRMNGA(zudlist.getZdnum().toString());
                        } else {
                            parameters.setXMNGA(zudlist.getZdnum().toString());
                        }
                        parameters.setZSCBC("");
                        parameters.setZSCX(PlineId.toString());
                        parameters.setZMNUM("");
                        parameters.setZPGDBAR(cardhjj.getZpgdbar());
                        parameters.setZPGDBH(cardhjj.getZpgdbh());
                        parameters.setRSNUM("");
                        parameters.setRSPOS("");
                        parameters.setREVERSE("");
                        parameters.setATTR1(createdBy);
                        parameters.setATTR2("");
                        parameters.setATTR3("");
                        parameters.setATTR4("");//报工类别
                        parameters.setATTR5("");
                        parameters.setATTR6("");
                        parameters.setATTR7("");
                        parameters.setATTR8("");
                        parameters.setATTR9("");
                        parameters.setATTR10("");
                        parameters.setATTR11("");
                        parameters.setATTR12("");
                        parameters.setATTR13("");
                        parameters.setATTR14("");
                        parameters.setATTR15("");
                        parameters.setUSERNAME(createdBy);
                        parameters.setZTPBAR("");
                        parameters.setLSTVOR("X");
                        parameters.setFSTVOR("");
                        parameters.setZPRTP("4");
                        parameters.setAUART(cardhjj.getAuart());
                        parameters.setARBPL(cardt.getArbpl());
                        parameters.setCHARG("");
                        UUID uuid2 = UUID.randomUUID();
                        parameters.setBGUUID(uuid2.toString());
                        listparam.add(parameters);
                        ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
                        //
                        for (int j = 0; j < listparam.size(); j++) {
                            if (listparam.get(j).getZPGDBAR().equals(zudlist.getZpgdbar())) {
                                paramAndQjjlh.setNum(j);
                            }
                        }

                        paramAndQjjlh.setQjjlh(zudlist.getZqjjlh());
                        listparamQjjlh.add(paramAndQjjlh);
                    }


                }else{
                    DTBAOGONGParameters parameters = new DTBAOGONGParameters();
                    parameters.setPWERK("1001");
                    parameters.setAUFNR(cardhjj.getAufnr());
                    //获取机加装箱工序
                    Cardt cardt = new Cardt();
                    cardt.setZpgdbar(cardhjj.getZpgdbar());
                    cardt.setKtsch("21001");
                    cardt = cardtService.selectByBarcodeAndKtsch(cardt);
                    parameters.setVORNR(cardt.getVornr());//工序号
                    parameters.setBUDAT(curdate);
                    parameters.setDATUM(curdate);
                    parameters.setGMNGA("0");
                    parameters.setRMNGA("0");
                    parameters.setXMNGA("0");
                    if (zudlist.getZqxdm().substring(0, 1).equals("M")) {
                        parameters.setRMNGA(zudlist.getZdnum().toString());
                    } else {
                        parameters.setXMNGA(zudlist.getZdnum().toString());
                    }
                    parameters.setZSCBC("");
                    parameters.setZSCX(PlineId.toString());
                    parameters.setZMNUM("");
                    parameters.setZPGDBAR(cardhjj.getZpgdbar());
                    parameters.setZPGDBH(cardhjj.getZpgdbh());
                    parameters.setRSNUM("");
                    parameters.setRSPOS("");
                    parameters.setREVERSE("");
                    parameters.setATTR1(createdBy);
                    parameters.setATTR2("");
                    parameters.setATTR3("");
                    parameters.setATTR4("");//报工类别
                    parameters.setATTR5("");
                    parameters.setATTR6("");
                    parameters.setATTR7("");
                    parameters.setATTR8("");
                    parameters.setATTR9("");
                    parameters.setATTR10("");
                    parameters.setATTR11("");
                    parameters.setATTR12("");
                    parameters.setATTR13("");
                    parameters.setATTR14("");
                    parameters.setATTR15("");
                    parameters.setUSERNAME(createdBy);
                    parameters.setZTPBAR("");
                    parameters.setLSTVOR("X");
                    parameters.setFSTVOR("");
                    parameters.setZPRTP("4");
                    parameters.setAUART(cardhjj.getAuart());
                    parameters.setARBPL(cardt.getArbpl());
                    parameters.setCHARG("");
                    UUID uuid2 = UUID.randomUUID();
                    parameters.setBGUUID(uuid2.toString());
                    listparam.add(parameters);
                    ParamAndQjjlh paramAndQjjlh = new ParamAndQjjlh();
                    paramAndQjjlh.setNum(0);
                    paramAndQjjlh.setQjjlh(zudlist.getZqjjlh());
                    listparamQjjlh.add(paramAndQjjlh);
                }
                list.add(zudlist);
            }

        }
        //开始报工
        ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();

        int iserr = 0;//是否有失败的报工记录
        if (listparam.size() > 0) {
            for (int i = 0; i < listparam.size(); i++) {
                List<DTBAOGONGParametersitem> parametersitems = new ArrayList<>();
                List<InOutRecord> listinoutRecord = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    String flg = "";
                    //准备领料明细数据
                    String pZpgdbar = listparam.get(i).getZPGDBAR();
                    String recZpgdbar = list.get(j).getZpgdbar();
                    if (listparam.get(i).getZPGDBAR().equals(list.get(j).getZpgdbar())) {
                        //取箱号信息
                        DTBAOGONGParametersitem parametersitem = new DTBAOGONGParametersitem();
                        Xhcard xhcard = new Xhcard();
                        xhcard = xhcardService.selectByBacode(list.get(j).getZxhbar());
                        if (parametersitems.size() > 0) {
                            for (int y = 0; y < parametersitems.size(); y++) {
                                if (parametersitems.get(y).getCHARG().equals(xhcard.getChargkc())) {
                                    flg = "X";
                                    Integer num = Integer.valueOf(parametersitems.get(y).getBDMNG()) + list.get(j).getZdnum().intValue();
                                    parametersitems.get(y).setBDMNG(num.toString());
                                }
                            }
                            if (flg.equals("")) {
                                parametersitem.setSUBRSNUM("");
                                parametersitem.setSUBRSPOS("");
                                parametersitem.setBDMNG(list.get(j).getZdnum().toString());
                                parametersitem.setMATNR(xhcard.getMatnr());
                                parametersitem.setCHARG(xhcard.getChargkc());
                                parametersitem.setLGORT(xhcard.getLgort());
                                parametersitem.setMEINS(xhcard.getMeins());
                                parametersitem.setWERKS(xhcard.getWerks());
                                parametersitems.add(parametersitem);
                            }
                        }else{
                            parametersitem.setSUBRSNUM("");
                            parametersitem.setSUBRSPOS("");
                            parametersitem.setBDMNG(list.get(j).getZdnum().toString());
                            parametersitem.setMATNR(xhcard.getMatnr());
                            parametersitem.setCHARG(xhcard.getChargkc());
                            parametersitem.setLGORT(xhcard.getLgort());
                            parametersitem.setMEINS(xhcard.getMeins());
                            parametersitem.setWERKS(xhcard.getWerks());
                            parametersitems.add(parametersitem);
                        }
                    }
                }


                DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
                returnResult = confirmationWebserviceUtilNew.receiveConfirmation(listparam.get(i), parametersitems);
                Date inDate = new Date();
                Log log = new Log();
                Result result = new Result();
                InputLog inputLog = new InputLog();
                inputLog.setBarcode(listparam.get(i).getZPGDBAR());
                inputLog.setOrderno(listparam.get(i).getAUFNR());
                inputLog.setDispatch(listparam.get(i).getZPGDBAR());
                inputLog.setOperation(listparam.get(i).getVORNR());
                inputLog.setYeild(Double.parseDouble(listparam.get(i).getGMNGA()));
                inputLog.setWorkScrap(Double.parseDouble(listparam.get(i).getXMNGA()));
                inputLog.setRowScrap(Double.parseDouble(listparam.get(i).getRMNGA()));
                inputLog.setClassgrp(listparam.get(i).getZSCBC());
                inputLog.setLine(listparam.get(i).getZSCX());
                inputLog.setModelNo("");
                inputLog.setPlant(listparam.get(i).getPWERK());
                inputLog.setPostingDate(listparam.get(i).getBUDAT());
                inputLog.setDispatchLogicID(listparam.get(i).getZPGDBH());
                inputLog.setCreated_by(listparam.get(i).getUSERNAME());
                inputLog.setAttr1(listparam.get(i).getATTR1());
                inputLog.setAttr2(listparam.get(i).getATTR2());
                inputLog.setAttr3(listparam.get(i).getATTR3());
                inputLog.setAttr4(listparam.get(i).getATTR4());
                inputLog.setAttr5(listparam.get(i).getATTR5());
                inputLog.setAttr6(listparam.get(i).getATTR6());
                inputLog.setAttr7(listparam.get(i).getATTR7());
                inputLog.setUserName(listparam.get(i).getUSERNAME());
                inputLog.setMaterial(returnResult.getMATNR());
                inputLog.setMatDesc(returnResult.getMAKTX());
                inputLog.setBguuid(listparam.get(i).getBGUUID());
                inputLogService.insertInputLog(inputLog);
                Long id = inputLogService.selectNextId();
                result.setPlant(inputLog.getPlant());
                result.setInputId(id);
                result.setIsReversed("0");
                result.setMaterial(inputLog.getMaterial());
                result.setMatDesc(inputLog.getMatDesc());
                result.setCreated_by(inputLog.getCreated_by());
                result.setConfirmationNo(returnResult.getRSNUM());
                result.setConfirmationPos(returnResult.getRSPOS());
                result.setFevor(returnResult.getFEVOR());
                result.setFevorTxt(returnResult.getTXT());
                result.setOperationDesc(returnResult.getLTXA1());
                log.setMsgty(returnResult.getMSGTY());
                log.setMsgtx(returnResult.getMESSAGE());
                log.setTranType("0");
                log.setRefId(id);
                log.setCreationDate(inDate);
                log.setLastUpdateDate(new Date());
                log.setCreated_by(inputLog.getCreated_by());
                resultService.insertResult(result);
                logService.insertLog(log);
                List<Zudlist> listsaveZudlist = new ArrayList<>();
                if (returnResult.getMSGTY().equals("S")) {

                    //1,更新wip_in_out_record //更新 wip_zudlist

                    for (int x = 0; x < listparamQjjlh.size(); x++) {
                        if (listparamQjjlh.get(x).getNum() == i) {

                            InOutRecord inOutRecord = new InOutRecord();
                            inOutRecord = iInOutRecordService.selectById(listparamQjjlh.get(x).getQjjlh());
                            if (inOutRecord != null){
                                inOutRecord.setReflag(2L);
                                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                                inOutRecord.setLastUpdateDate(new Date());
                                listinoutRecord.add(inOutRecord);
                            }
                            //写不合格审理单行项目
                            for (int y = 0; y < list.size(); y++) {
                                if (list.get(y).getZqjjlh().equals(listparamQjjlh.get(x).getQjjlh())) {
                                    list.get(y).setRueck(returnResult.getRSNUM());
                                    list.get(y).setRmzhl(returnResult.getRSPOS());
                                    list.get(y).setStatus("1");//已处理
                                    list.get(y).setLastUpdatedBy(Long.valueOf(createdBy));
                                    list.get(y).setLastUpdateDate(new Date());
                                    zudlistService.updateItem(list.get(y));
                                    //listsaveZudlist.add(list.get(y));

                                    UUID uuid = UUID.randomUUID();
                                    String uuidstr = uuid.toString().replaceAll("-", "");
                                    Zudlog zudlog = new Zudlog();
                                    zudlog.setInputlogid(id);
                                    zudlog.setId(uuidstr);
                                    zudlog.setZudnum(list.get(y).getZudnum());
                                    zudlog.setItem(list.get(y).getItem());
                                    zudlog.setMsgtx(returnResult.getMESSAGE());
                                    zudlog.setStatus("1");
                                    zudlog.setCreatedBy(Long.valueOf(createdBy));
                                    zudlog.setCreationDate(new Date());
                                    zudlogService.insertLog(zudlog);
                                }
                            }
                        }
                    }

                    //iInOutRecordService.batchUpdateReflag(listinoutRecord);
                    //                            //2，更新 cardh
                    Cardh cardhjj = new Cardh();
                    cardhjj = cardhService.selectByBarcode(listparam.get(i).getZPGDBAR());
                    if (cardhjj.getQtysp() != null){
                        cardhjj.setQtysp(Double.valueOf(listparam.get(i).getXMNGA()) + cardhjj.getQtysp());//?
                    }else{
                        cardhjj.setQtysp(Double.valueOf(listparam.get(i).getXMNGA()));
                    }

                    if (cardhjj.getQtysm() != null){
                        cardhjj.setQtysm(Double.valueOf(listparam.get(i).getRMNGA()) + cardhjj.getQtysm());//?
                    }else{
                        cardhjj.setQtysm(Double.valueOf(listparam.get(i).getRMNGA()));
                    }
                    cardhjj.setLastUpdatedBy(Long.valueOf(createdBy));
                    cardhjj.setLastUpdatedDate(new Date());
                    List<Cardh> listcardh = new ArrayList<>();
                    listcardh.add(cardhjj);
                    cardhService.updateCardhStatus(listcardh);
                } else{
                    iserr = iserr + 1;
                    for (int x = 0; x < listparamQjjlh.size(); x++) {
                        if (listparamQjjlh.get(x).getNum() == i) {
                            //写不合格审理单行项目
                            for (int y = 0; y < list.size(); y++) {
                                if (list.get(y).getZqjjlh().equals(listparamQjjlh.get(x).getQjjlh())) {
                                    list.get(y).setStatus("0");//未处理
                                    list.get(y).setLastUpdatedBy(Long.valueOf(createdBy));
                                    list.get(y).setLastUpdateDate(new Date());
                                    zudlistService.updateItem(list.get(y));
                                    //listsaveZudlist.add(list.get(y));

                                    UUID uuid = UUID.randomUUID();
                                    String uuidstr = uuid.toString().replaceAll("-", "");
                                    Zudlog zudlog = new Zudlog();
                                    zudlog.setInputlogid(id);
                                    zudlog.setId(uuidstr);
                                    zudlog.setZudnum(list.get(y).getZudnum());
                                    zudlog.setItem(list.get(y).getItem());
                                    zudlog.setMsgtx(returnResult.getMESSAGE());
                                    zudlog.setStatus("2");
                                    zudlog.setCreatedBy(Long.valueOf(createdBy));
                                    zudlog.setCreationDate(new Date());
                                    zudlogService.insertLog(zudlog);
                                }
                            }
                        }
                    }
                }


            }


        }

        //更新表头
        for (int i= 0;i<headlist.size();i++){
            //根据表头单号 查询是否存在未处理的表行
            List<Zudlist> listunprocess = zudlistService.selectByZudnumForUnprocess(headlist.get(i).getZudnum());
            if (listunprocess.size() == 0){
                //所有行均已处理 更新表头状态
                headlist.get(i).setStatus("1");
                headlist.get(i).setLastUpdateDate(new Date());
                headlist.get(i).setLastUpdatedBy(Long.valueOf(createdBy));
                service.updateHead(headlist.get(i));
            }
        }

        if (iserr > 0){
            rs.setSuccess(true);
            rs.setMessage("不合格品审理单处理完成，处理过程中有错误产生！请查询日志！");
        }else{
            rs.setSuccess(true);
            rs.setMessage("不合格品审理单处理完成!");
        }
        return rs;
    }
}
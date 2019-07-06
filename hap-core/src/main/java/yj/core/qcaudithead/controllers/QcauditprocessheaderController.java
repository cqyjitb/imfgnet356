package yj.core.qcaudithead.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.dto.Qcauditprocessheader;
import yj.core.qcaudithead.service.IQcauditheadService;
import yj.core.qcaudithead.service.IQcauditprocessheaderService;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;
import yj.core.qcauditlist.dto.recPageData;
import yj.core.qcauditlist.service.IQcauditlistService;
import yj.core.qcauditlist.service.IQcauditprocessdtlService;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
    public class QcauditprocessheaderController extends BaseController {

    @Autowired
    private IQcauditprocessheaderService service;
    @Autowired
    private IQcauditlistService qcauditlistService;
    @Autowired
    private IQcauditheadService qcauditheadService;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private IQcauditprocessdtlService qcauditprocessdtlService;
    @Autowired
    private ICardtService cardtService;
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
    private IInOutRecordService inOutRecordService;

    @RequestMapping(value = "/wip/qcauditprocessheader/query")
    @ResponseBody
    public ResponseData query(Qcauditprocessheader dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcauditprocessheader/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Qcauditprocessheader> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcauditprocessheader/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Qcauditprocessheader> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/wip/qcauditprocessheader/queryHeadAndDtl")
        @ResponseBody
        public ResponseData queryHeadAndDtl(HttpServletRequest request){
            ResponseData rs = new ResponseData();
            String werks = request.getParameter("werks");
            String recordid = request.getParameter("recordid");
            Qcauditprocessheader qh = new Qcauditprocessheader();
            qh = service.selectById(werks,recordid);
            Qcaudithead qcaudithead = new Qcaudithead();
            qcaudithead = qcauditheadService.selectById(werks,recordid).get(0);
            List list = new ArrayList();
            List<Qcauditprocessdtl> listql = new ArrayList<>();
            List<Qcauditprocessdtl> listbf = new ArrayList<>();
            listbf = qcauditprocessdtlService.selectById(werks,recordid,"1");
            listql = qcauditprocessdtlService.selectById(werks,recordid,null);

            if (qcaudithead != null){
                qh.setWerks(qcaudithead.getWerks());
                qh.setRecordid(qcaudithead.getRecordid());
                qh.setAttr1(qcaudithead.getLineid());
                qh.setAttr2(qcaudithead.getShift());
                qh.setAttr3(qcaudithead.getMatnr());
                qh.setAttr4(qcaudithead.getMatnr2());
                list.add(qh);
                rs.setCode("S");
            }else{
                rs.setCode("E");
            }

            if (listql.size() > 0){
                list.add(listql);
            }
            Integer bf = listbf.size();
            rs.setRows(list);
            rs.setSuccess(true);
            rs.setCode(bf.toString());
            return rs;
        }


        @RequestMapping(value = {"/wip/qcauditprocessheader/saveData"}, method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData saveData(HttpServletRequest request , @RequestBody List<recPageData> a){
            ResponseData rs = new ResponseData();
            List<Qcauditlist> list = new ArrayList<>();
            List<Qcauditprocessdtl> list2 = new ArrayList<>();
            Qcauditprocessheader qcauditprocessheader = new Qcauditprocessheader();
            Double confirmnum = a.get(0).getConfirmnum();

            if (a.size() > 0) {
                for (int i = 0; i < a.size(); i++) {
                    Qcauditlist qcauditlist = new Qcauditlist();
                    qcauditlist.setRecordid(a.get(i).getRecordid());
                    qcauditlist.setWerks(a.get(i).getWerks());
                    qcauditlist.setItem(a.get(i).getItem());
                    list.add(qcauditlist);
                }
            }

            //准备行数据
            if (list.size() > 0){
                Qcauditlist qcauditlist = new Qcauditlist();

                for (int i=0;i< list.size();i++){
                    Qcauditprocessdtl qcauditprocessdtl = new Qcauditprocessdtl();
                    if (list.get(i).getItem().equals("")){
                        break;
                    }
                    qcauditlist = qcauditlistService.selectBatch(list.get(i).getWerks(),list.get(i).getRecordid(),list.get(i).getItem());
                    qcauditprocessdtl.setGmein(qcauditlist.getGmein());
                    qcauditprocessdtl.setDiecd(qcauditlist.getDiecd());
                    qcauditprocessdtl.setDfectQty(qcauditlist.getDfectQty());
                    qcauditprocessdtl.setCharg(qcauditlist.getCharg());
                    qcauditprocessdtl.setCode(qcauditlist.getCode());
                    qcauditprocessdtl.setGstrp(qcauditlist.getGstrp());
                    qcauditprocessdtl.setItem(qcauditlist.getItem());
                    qcauditprocessdtl.setMatnr(qcauditlist.getMatnr());
                    qcauditprocessdtl.setMatnr2(qcauditlist.getMatnr2());
                    qcauditprocessdtl.setMenge(qcauditlist.getMenge());
                    qcauditprocessdtl.setRecordid(qcauditlist.getRecordid());
                    qcauditprocessdtl.setReflag(qcauditlist.getReflag());
                    qcauditprocessdtl.setSfflg(qcauditlist.getSfflg());
                    qcauditprocessdtl.setShift(qcauditlist.getShift());
                    qcauditprocessdtl.setTlevelcode(qcauditlist.getTlevelcode());
                    qcauditprocessdtl.setWerks(qcauditlist.getWerks());
                    qcauditprocessdtl.setYcharg(qcauditlist.getYcharg());
                    qcauditprocessdtl.setYgstrp(qcauditlist.getYgstrp());
                    qcauditprocessdtl.setYshift(qcauditlist.getYshift());
                    qcauditprocessdtl.setYzbanc(qcauditlist.getYzbanc());
                    qcauditprocessdtl.setZbanc(qcauditlist.getZbanc());
                    qcauditprocessdtl.setZgjbar(qcauditlist.getZgjbar());
                    qcauditprocessdtl.setZpgdbar(qcauditlist.getZpgdbar());
                    qcauditprocessdtl.setZqjjlh(qcauditlist.getZqjjlh());
                    qcauditprocessdtl.setZxhbar(qcauditlist.getZxhbar());
                    qcauditprocessdtl.setStatus(qcauditlist.getStatus());
                    list2.add(qcauditprocessdtl);
                }

                //准备表头数据
                Qcaudithead qcaudithead = qcauditheadService.selectById(list.get(0).getWerks(),list.get(0).getRecordid()).get(0);
                qcauditprocessheader = new Qcauditprocessheader();
                qcauditprocessheader.setWerks(list.get(0).getWerks());
                qcauditprocessheader.setConfirmQty(confirmnum);
                qcauditprocessheader.setRecordid(list.get(0).getRecordid());
                qcauditprocessheader.setAttr1(qcaudithead.getLineid());//产线
                qcauditprocessheader.setAttr2(qcaudithead.getShift());//班组
            }

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            //将信息更新到表中
            try {
                int num1 = 0;
                int num2 = 0;
                int num3 = 0;
                num1 = service.updateData(qcauditprocessheader);

                num3 = qcauditprocessdtlService.deleteById(qcauditprocessheader.getWerks(),qcauditprocessheader.getRecordid());
                if (list2.size() > 0){
                    num2 = qcauditprocessdtlService.insertData(list2);
                    if (num2 == list2.size() && num1 == 1) {
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                        rs.setMessage("合格品审理单2数据保存成功！");
                    } else {
                        transactionManager.rollback(status);
                        rs.setMessage("合格品审理单2数据保存失败！");
                        rs.setSuccess(false);
                    }
                }else{
                    if (num1 == 1){
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                        rs.setMessage("合格品审理单2数据保存成功！");
                    }else{
                        transactionManager.rollback(status);
                        rs.setMessage("合格品审理单2数据保存失败！");
                        rs.setSuccess(false);
                    }
                }
            } catch (Exception e) {
                transactionManager.rollback(status);
                rs.setMessage("合格品审理单2数据保存失败！");
                rs.setSuccess(false);
            }
            return rs;
        }

        @RequestMapping(value = "/wip/qcauditprocessheader/sumbitBf")
        @ResponseBody
        public ResponseData sumbitBf(HttpServletRequest request){
            ResponseData rs = new ResponseData();
            String werks = request.getParameter("werks");
            String recordid = request.getParameter("recordid");
            String aufnr = request.getParameter("aufnr");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
            String createdBy = "" + request.getSession().getAttribute("userId");
//            String confirmnum_str = request.getParameter("confirmnum");
//            String baofeinum_str = request.getParameter("baofeinum");
//            String selectednum_str = request.getParameter("selectednum");
//
//            Double confirmnum = Double.parseDouble(confirmnum_str);
//            Double baofeinum = 0D;
//            if (!baofeinum_str.equals("")){
//                baofeinum = Double.parseDouble(baofeinum_str);
//            }
//            Double selectednum = Double.parseDouble(selectednum_str);

            //查询已经保存的数据
            Qcauditprocessheader qph = new Qcauditprocessheader();
            qph = service.selectById(werks,recordid);
            Qcauditprocessdtl qpdtl = new Qcauditprocessdtl();
            List<Qcauditprocessdtl> listdtl = new ArrayList<>();

            listdtl = qcauditprocessdtlService.selectById(werks,recordid,null);
            Cardh cardjj = new Cardh();
            cardjj = cardhService.selectByAufnr(aufnr).get(0);

            //  准备报工数据

            DTBAOGONGParameters parameters = new DTBAOGONGParameters();
            parameters.setPWERK(werks);
            parameters.setAUFNR(aufnr);
            Cardt cardt = new Cardt();
            cardt = cardtService.selectByAufnrAndKtsch(aufnr,"21001");//取机加装箱工序号
            parameters.setVORNR(cardt.getVornr());
            parameters.setBUDAT(curdate);
            parameters.setDATUM(curdate);
            parameters.setGMNGA("0");
            parameters.setRMNGA("1");
            parameters.setXMNGA("0");
            parameters.setZSCBC("");

            parameters.setZSCX(qph.getAttr1());
            parameters.setZMNUM("");
            parameters.setZPGDBAR(cardjj.getZpgdbar());
            parameters.setZPGDBH(cardjj.getZpgdbh());
            parameters.setRSNUM("");
            parameters.setRSPOS("");
            parameters.setREVERSE("");
            parameters.setATTR1(createdBy);
            parameters.setATTR2("");
            parameters.setATTR3("");
            parameters.setATTR4("");//报工类别
            parameters.setATTR5("");
            parameters.setATTR6("");
            parameters.setATTR8("");
            parameters.setATTR9("");
            parameters.setATTR10("");
            parameters.setATTR11("");
            parameters.setATTR12("");
            parameters.setATTR13("");
            parameters.setATTR14("");
            parameters.setATTR15("");
            UUID uuid2 = UUID.randomUUID();
            parameters.setBGUUID(uuid2.toString());
            parameters.setUSERNAME(createdBy);
            parameters.setZTPBAR("");
            parameters.setLSTVOR("X");
            parameters.setFSTVOR("");
            parameters.setZPRTP("5");
            parameters.setAUART(cardjj.getAuart());
            parameters.setARBPL(cardt.getArbpl());
            parameters.setCHARG("");

            List<DTBAOGONGParametersitem> parametersitems = new ArrayList<>();
            //准备领料数据
            for (int i=0;i<listdtl.size();i++){
                String flg = "";
                DTBAOGONGParametersitem parametersitem = new DTBAOGONGParametersitem();
                if (parametersitems.size() > 0){
                    for (int j =0;j<parametersitems.size();j++){
                        if (parametersitems.get(j).getCHARG().equals(listdtl.get(i).getYcharg())){
                            Integer num = Integer.valueOf(parametersitems.get(j).getBDMNG()) + 1;
                            parametersitems.get(j).setBDMNG(num.toString());
                            flg = "X";
                        }
                    }
                    if (flg.equals("")){
                        Xhcard xhcard = new Xhcard();
                        xhcard = xhcardService.selectByBacode(listdtl.get(i).getZxhbar());
                        parametersitem.setSUBRSNUM("");
                        parametersitem.setSUBRSPOS("");
                        parametersitem.setBDMNG("1");
                        parametersitem.setMATNR(xhcard.getMatnr());
                        parametersitem.setCHARG(xhcard.getChargkc());
                        parametersitem.setLGORT(xhcard.getLgort());
                        parametersitem.setMEINS(xhcard.getMeins());
                        parametersitem.setWERKS(xhcard.getWerks());
                        parametersitems.add(parametersitem);
                    }
                }else{
                    Xhcard xhcard = new Xhcard();
                    xhcard = xhcardService.selectByBacode(listdtl.get(i).getZxhbar());
                    parametersitem.setSUBRSNUM("");
                    parametersitem.setSUBRSPOS("");
                    parametersitem.setBDMNG("1");
                    parametersitem.setMATNR(xhcard.getMatnr());
                    parametersitem.setCHARG(xhcard.getChargkc());
                    parametersitem.setLGORT(xhcard.getLgort());
                    parametersitem.setMEINS(xhcard.getMeins());
                    parametersitem.setWERKS(xhcard.getWerks());
                    parametersitems.add(parametersitem);
                }

            }
            ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();
            DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
            returnResult = confirmationWebserviceUtilNew.receiveConfirmation(parameters, parametersitems);
            Date inDate = new Date();
            Log log = new Log();
            Result result = new Result();
            InputLog inputLog = new InputLog();
            inputLog.setBarcode(parameters.getZPGDBAR());
            inputLog.setOrderno(parameters.getAUFNR());
            inputLog.setDispatch(parameters.getZPGDBAR());
            inputLog.setOperation(parameters.getVORNR());
            inputLog.setYeild(Double.parseDouble(parameters.getGMNGA()));
            inputLog.setWorkScrap(Double.parseDouble(parameters.getXMNGA()));
            inputLog.setRowScrap(Double.parseDouble(parameters.getRMNGA()));
            inputLog.setClassgrp(parameters.getZSCBC());
            inputLog.setLine(parameters.getZSCX());
            inputLog.setModelNo("");
            inputLog.setPlant(parameters.getPWERK());
            inputLog.setPostingDate(parameters.getBUDAT());
            inputLog.setDispatchLogicID(parameters.getZPGDBH());
            inputLog.setCreated_by(parameters.getUSERNAME());
            inputLog.setAttr1(parameters.getATTR1());
            inputLog.setAttr2(parameters.getATTR2());
            inputLog.setAttr3(parameters.getATTR3());
            inputLog.setAttr4(parameters.getATTR4());
            inputLog.setAttr5(parameters.getATTR5());
            inputLog.setAttr6(parameters.getATTR6());
            inputLog.setAttr7(parameters.getATTR7());
            inputLog.setBguuid(parameters.getBGUUID());
            inputLog.setUserName(parameters.getUSERNAME());
            inputLog.setMaterial(returnResult.getMATNR());
            inputLog.setMatDesc(returnResult.getMAKTX());
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
            log.setCreationDate(inDate);
            log.setLastUpdateDate(new Date());
            log.setMsgty(returnResult.getMSGTY());
            log.setMsgtx(returnResult.getMESSAGE());
            log.setTranType("0");
            log.setRefId(id);
            log.setCreated_by(inputLog.getCreated_by());
            resultService.insertResult(result);
            logService.insertLog(log);
            if (returnResult.getMSGTY().equals("S")) {
                //1:更新wip_in_out_record
                List<InOutRecord> listinoutRecord = new ArrayList<>();
                List<Qcauditlist> qcauditlistList = new ArrayList<>();
                for (int i=0;i<listdtl.size();i++){
                    InOutRecord inOutRecord = new InOutRecord();
                    inOutRecord = inOutRecordService.selectById(listdtl.get(i).getZqjjlh());
                    inOutRecord.setReflag(2L);
                    inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                    inOutRecord.setLastUpdateDate(new Date());
                    listinoutRecord.add(inOutRecord);

                    //修改中间处理表明细
                    listdtl.get(i).setStatus("1");

                    //修改不合格品审理单2行项目表
                    Qcauditlist qcauditlist = qcauditlistService.selectBatch(listdtl.get(i).getWerks(),listdtl.get(i).getRecordid(),listdtl.get(i).getItem());
                    qcauditlist.setStatus("1");
                    qcauditlistList.add(qcauditlist);

                }

                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try{
                    int num = inOutRecordService.batchUpdateReflag(listinoutRecord);

                    int num2 = qcauditlistService.updateStatus(qcauditlistList);

                    int num3 = qcauditprocessdtlService.updateStatus(listdtl);

                    if (num == listinoutRecord.size() && num2 == qcauditlistList.size() && num3 == listdtl.size()){
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                    }else{
                        transactionManager.rollback(status);
                        rs.setSuccess(false);
                    }
                }catch (Exception e){
                        rs.setSuccess(false);
                        rs.setMessage("不合格品审理单2报废，数据库操作失败！");
                }

            }else{
                rs.setSuccess(false);
                rs.setMessage(returnResult.getMESSAGE());
            }
            return rs;
        }
    }
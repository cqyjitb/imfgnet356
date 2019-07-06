package yj.core.dispatch.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.appidconf.dto.Appidconf;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.cardhlock.dto.Cardhlock;
import yj.core.cardhlock.mapper.CardhlockMapper;
import yj.core.cardt.dto.Cardt;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.dispatch.service.IInputLogService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.mapper.OutsrgissueMapper;
import yj.core.webservice.components.ConfirmationWebserviceUtil;
import yj.core.webservice.dto.DTPP001Parameters;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService {

    @Autowired
    private ConfirmationWebserviceUtil webserviceUtil;

    @Autowired
    private ConfirmationWebserviceUtilNew webserviceUtilNew;

    @Autowired
    private InputLogMapper inputLogMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private CardhMapper cardhMapper;

    @Autowired
    private OutsrgissueMapper outsrgissueMapper;
    @Autowired
    private CardhlockMapper cardhlockMapper;

    DateFormat df = new SimpleDateFormat("yyyyMMdd");

    //报工冲销页面数据查询
    @Override
    public List<InputLog> queryAllWriteOff(IRequest iRequest, InputLog inputLog, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        //Long before = System.currentTimeMillis();
        //List<InputLog> inputLogs = inputLogMapper.queryAllWriteOff(inputLog);
        //Long after = System.currentTimeMillis();
        //System.out.println(after-before);

        return inputLogMapper.queryAllWriteOff(inputLog);

    }

    //报工结果
    public List<InputLog> queryAllResult(IRequest iRequest, InputLog inputLog, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        /*Long before = System.currentTimeMillis();
        List<InputLog> inputLogs = inputLogMapper.queryAllResult(inputLog);
        Long after = System.currentTimeMillis();
        System.out.println(after-before);*/
        return inputLogMapper.queryAllResult(inputLog);
    }

    ;

    public List<InputLog> queryFirstResult(IRequest iRequest, String dispatch, String operation) {
        System.out.println(dispatch + "/" + operation);

        return this.inputLogMapper.queryFirstResult(dispatch, operation);
    }

    @Override
    public List<InputLog> queryBeforeResult(IRequest iRequest, String dispatch, String operation) {
        return inputLogMapper.queryBeforeResult(dispatch, operation);
    }

    //报工日志界面数据查询
    public List<InputLog> queryAllLog(IRequest iRequest, InputLog inputLog, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
       /* Long before = System.currentTimeMillis();
        List<InputLog> inputLogs = inputLogMapper.queryAllLog(inputLog);
        Long after = System.currentTimeMillis();
        System.out.println(after-before);*/
        return inputLogMapper.queryAllLog(inputLog);
    }

    //插入一条信息到confirmation_input_log
    public int insertInputLog(InputLog inputLog) {
        return inputLogMapper.insertInputLog(inputLog);
    }

    ;

    //根据ID查询表格confirmation_input_log
    public int queryInputLogById(Long id) {
        return inputLogMapper.queryInputLogById(id);
    }


    @Override
    public void nextId(InputLog input) {
        inputLogMapper.insert(input);
        Long id = inputLogMapper.selectNextId();
        System.out.println(id);
    }

    @Override
    public DTPP001ReturnResult inputDispatch(InputLog input) {
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setMESSAGE("报工失败！当前工序报工数量大于前工序合格数量。");
        returnResult.setMSGTY("E");
        Double currentInputSum = 0d;
        Double historyMaxOperationYeildSum = 0d;
        String beforeMaxOpera = "";
        /*add furong.tang*/
        Long IOrderno = Long.parseLong(input.getOrderno());
        //查询当前条码是否存在未冲销数据
        List<InputLog> existReversedInputLogs = inputLogMapper.queryBybarcodeAndisReversed(input);
        /*add furong.tang*/
        List<InputLog> inputLogs = inputLogMapper.confirmationInfoByOrdernoAndOperation(input);
        beforeMaxOpera = inputLogMapper.confirmationBeforeMaxOperation(input);
        if (inputLogs.size() > 0) {
            for (InputLog inputLog : inputLogs) {
                currentInputSum += inputLog.getYeild() + inputLog.getRowScrap() + inputLog.getWorkScrap();
            }
        }
        currentInputSum = currentInputSum + input.getYeild() + input.getRowScrap() + input.getWorkScrap();

        /*update furong.tang*/
        //生产订单号的号码范围为：1000000000至2999999999，或9300000000至9499999999
        if ((IOrderno >= 1000000000L && IOrderno <= 2999999999L) || (IOrderno >= 9300000000L && IOrderno <= 9499999999L)) {
            //此派工单是否是未冲销的
            if (existReversedInputLogs.size() == 0 || existReversedInputLogs == null) {
                /*before*/
                return before(input, returnResult, currentInputSum, historyMaxOperationYeildSum, beforeMaxOpera);
                /*before*/
            } else {
                returnResult.setMESSAGE("此派工单的该工序已经报工，请勿重复报工");
                return returnResult;
            }

        } else {
            return before(input, returnResult, currentInputSum, historyMaxOperationYeildSum, beforeMaxOpera);
        }
        /*update furong.tang*/

    }

    public DTPP001ReturnResult before(InputLog input, DTPP001ReturnResult returnResult, Double currentInputSum, Double historyMaxOperationYeildSum, String beforeMaxOpera) {
         /*before*/
        Long IOrderno = Long.valueOf(Long.parseLong(input.getOrderno()));
        if (("".equals(beforeMaxOpera)) || (beforeMaxOpera == null)) {
            return returnResultAndUpdateConfirmation(input);
        }
        List<InputLog> maxInputLogs = this.inputLogMapper.confirmationExistMaxOperaInfo(input.getDispatch(), beforeMaxOpera);
        if (maxInputLogs.size() > 0) {
            for (InputLog inputLog : maxInputLogs) {
                historyMaxOperationYeildSum = Double.valueOf(historyMaxOperationYeildSum.doubleValue() + inputLog.getYeild().doubleValue());
            }
            if ((IOrderno.longValue() >= 1000000000L) && (IOrderno.longValue() <= 2999999999L)) {
                if (historyMaxOperationYeildSum.equals(currentInputSum)) {
                    return returnResultAndUpdateConfirmation(input);
                }
                returnResult.setMESSAGE("报工失败！压铸订单后工序报工总数不等于前工序合格品数量！");
                return returnResult;
            }
            if (historyMaxOperationYeildSum.doubleValue() >= currentInputSum.doubleValue()) {
                return returnResultAndUpdateConfirmation(input);
            }
            return returnResult;
        }
        returnResult.setMESSAGE("存在前置工序未报工");
        return returnResult;
        /*before*/
    }

    @Override
    public DTBAOGONGReturnResult inputDispatchNewWX(InputLog input, Cardh cardh, Cardt cardt, String isfirst) {
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        Appidconf appidconf = new Appidconf();
        returnResult = checkBarcode(cardh, appidconf, cardt, returnResult, input, isfirst);
        if (returnResult.getMSGTY().equals("C")) {
            input.setAttr5(cardh.getCharg());//设置流转卡日期序列
            return returnResultAndUpdateConfirmationNew(input);
        } else {
            return returnResult;
        }
    }

    @Override
    public DTBAOGONGReturnResult inputDispatchNew(InputLog input, Cardh cardh, Cardt cardt, Appidconf appidconf, String isfirst) {
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        returnResult = checkBarcode(cardh, appidconf, cardt, returnResult, input, isfirst);
        if (returnResult.getMSGTY().equals("C")) {
            input.setAttr5(cardh.getCharg());//设置流转卡日期序列
            return returnResultAndUpdateConfirmationNew(input);
        } else {
            return returnResult;
        }

    }

    public DTBAOGONGReturnResult checkBarcode(Cardh cardh, Appidconf appidconf, Cardt cardt, DTBAOGONGReturnResult returnResult, InputLog inputLog, String isfirst) {
        String beforeMaxOpera = "";
        Double currentInputSum = 0d;
        Double historyMaxOperationYeildSum = 0d;
        //检验流转卡号的合法性
        returnResult.setMSGTY("C");
        if (cardh == null) {
            returnResult.setMESSAGE("流转卡号错误，或流转卡不存在，请确认！");
            returnResult.setMSGTY("E");
            return returnResult;

        }


        if (cardh.getLgort() != null && cardh.getLgort().length() > 3) {
            returnResult.setMESSAGE("流转卡目前处于暂存区：" + cardh.getLgort() + "中,不允许报工。");
            returnResult.setMSGTY("E");
            return returnResult;
        }


        //检验流转卡的状态
        if (cardh.getStatus().equals("HOLD") || cardh.getStatus().equals("CLOS")) {
            returnResult.setMESSAGE("流转卡状态为:" + cardh.getStatus() + ",不允许报工。");
            returnResult.setMSGTY("E");
            return returnResult;

        }


        if (cardt == null) {
            returnResult.setMESSAGE("工序标准值码错误，或工序记录不存在！");
            returnResult.setMSGTY("E");
            return returnResult;
        }

        //检查当前报工工序是否为外协工序
        if (cardt.getSteus().equals("ZP02")){
            Outsrgissue outsrgissue = new Outsrgissue();
            outsrgissue = outsrgissueMapper.selectByBarcode(cardh.getZpgdbar(),"0");
            String appid = appidconf.getAppid();
            if (outsrgissue != null && appid != null){
                returnResult.setMESSAGE("当前外协工序已发料，请使用外协收货进行报工！");
                returnResult.setMSGTY("E");
                return returnResult;
            }
        }

        if (appidconf.getAppid() != null){
            if (appidconf.getAppid().equals("app0001")) {
                currentInputSum = currentInputSum + inputLog.getYeild();
                if (cardt.getConfirmed().equals("X")) {
                    returnResult.setMESSAGE("当前工序已经报工，不允许重复报工！");
                    returnResult.setMSGTY("E");
                    return returnResult;

                }
                //标准装框 + 标准装框 * 浮动报工率 / 100
                if (currentInputSum > cardh.getPlqty() + cardh.getPlqty() * (cardh.getFlgrg() / 100)) {
                    returnResult.setMESSAGE("报工数量不能超过标准装框量！请核对报工数量！");
                    returnResult.setMSGTY("E");
                    return returnResult;
                }

            } else if (appidconf.getAppid().equals("app0002") || appidconf.getAppid().equals("app0003")) {
                if (cardt.getConfirmed().equals("X")) {
                    returnResult.setMESSAGE("当前工序已经报工，不允许重复报工！");
                    returnResult.setMSGTY("E");
                    return returnResult;

                }
                if (!isfirst.equals("FIRST")) {//如果是首工序 不获取前工序报工数据 直接报工
                    //获取前工序报工数据
                    beforeMaxOpera = inputLogMapper.confirmationBeforeMaxOperation(inputLog);
                    List<InputLog> maxInputLogs = this.inputLogMapper.confirmationExistMaxOperaInfo(inputLog.getDispatch(), beforeMaxOpera);
                    if (maxInputLogs.size() > 0) {
                        currentInputSum = currentInputSum + inputLog.getYeild() + inputLog.getRowScrap() + inputLog.getWorkScrap();
                        for (InputLog inputLogtmp : maxInputLogs) {
                            historyMaxOperationYeildSum = Double.valueOf(historyMaxOperationYeildSum.doubleValue() + inputLogtmp.getYeild().doubleValue());
                        }
                        if (!currentInputSum.equals(historyMaxOperationYeildSum)) {
                            returnResult.setMESSAGE("后工序报工总数不等于前工序合格品数量！");
                            returnResult.setMSGTY("E");
                            return returnResult;
                        }
                    } else {
                        returnResult.setMESSAGE("存在前置工序未报工！");
                        returnResult.setMSGTY("E");
                        return returnResult;

                    }

                    if (currentInputSum > cardh.getPlqty()) {
                        returnResult.setMESSAGE("报工数量不能超过标准装框量！请核对报工数量！");
                        returnResult.setMSGTY("E");
                        return returnResult;
                    }
                }
            }
        }else{
            //工序外协报工
            if (isfirst.equals("X")){
                currentInputSum = currentInputSum + inputLog.getYeild();
                if (cardt.getConfirmed().equals("X")) {
                    returnResult.setMESSAGE("当前工序已经报工，不允许重复报工！");
                    returnResult.setMSGTY("E");
                    return returnResult;

                }
                //标准装框 + 标准装框 * 浮动报工率 / 100
                if (currentInputSum > cardh.getPlqty() + cardh.getPlqty() * (cardh.getFlgrg() / 100)) {
                    returnResult.setMESSAGE("报工数量不能超过标准装框量！请核对报工数量！");
                    returnResult.setMSGTY("E");
                    return returnResult;
                }
            }else{
                if (cardt.getConfirmed().equals("X")) {
                    returnResult.setMESSAGE("当前工序已经报工，不允许重复报工！");
                    returnResult.setMSGTY("E");
                    return returnResult;

                }
                if (!isfirst.equals("X")) {//如果是首工序 不获取前工序报工数据 直接报工
                    //获取前工序报工数据
                    beforeMaxOpera = inputLogMapper.confirmationBeforeMaxOperation(inputLog);
                    List<InputLog> maxInputLogs = this.inputLogMapper.confirmationExistMaxOperaInfo(inputLog.getDispatch(), beforeMaxOpera);
                    if (maxInputLogs.size() > 0) {
                        currentInputSum = currentInputSum + inputLog.getYeild() + inputLog.getRowScrap() + inputLog.getWorkScrap();
                        for (InputLog inputLogtmp : maxInputLogs) {
                            historyMaxOperationYeildSum = Double.valueOf(historyMaxOperationYeildSum.doubleValue() + inputLogtmp.getYeild().doubleValue());
                        }
                        if (!currentInputSum.equals(historyMaxOperationYeildSum)) {
                            returnResult.setMESSAGE("后工序报工总数不等于前工序合格品数量！");
                            returnResult.setMSGTY("E");
                            return returnResult;
                        }
                    } else {
                        returnResult.setMESSAGE("存在前置工序未报工！");
                        returnResult.setMSGTY("E");
                        return returnResult;

                    }

                    if (currentInputSum > cardh.getPlqty()) {
                        returnResult.setMESSAGE("报工数量不能超过标准装框量！请核对报工数量！");
                        returnResult.setMSGTY("E");
                        return returnResult;
                    }
                }
            }
        }

        if (cardh != null){

            Cardhlock lock = cardhlockMapper.selectByZpgdbar(cardh.getZpgdbar(),cardt.getVornr());
            if (lock != null){
                returnResult.setMESSAGE("系统正在处理当前流转卡其他报工信息，请稍后提交！");
                returnResult.setMSGTY("E");
                return returnResult;
            }else{
                Cardhlock cardhlock = new Cardhlock();
                cardhlock.setZpgdbar(cardh.getZpgdbar());
                cardhlock.setVornr(cardt.getVornr());
                cardhlock.setCreatedBy(inputLog.getCreatedBy());
                cardhlock.setCreationDate(new Date());
                try{
                    int num = cardhlockMapper.insertCardhlock(cardhlock);
                    if (num != 1){
                        returnResult.setMESSAGE("流转卡："+cardh.getZpgdbar()+"加锁失败，请联系管理员！");
                        returnResult.setMSGTY("E");
                        return returnResult;
                    }
                }catch (Exception e){
                    if (e.getMessage().contains("Duplicate entry")){
                        returnResult.setMESSAGE("系统正在处理当前流转卡其他报工信息，请稍后提交！");
                    }else{
                        returnResult.setMESSAGE("流转卡："+cardh.getZpgdbar()+"加锁失败，请联系管理员！");
                    }

                    returnResult.setMSGTY("E");
                    return returnResult;
                }

            }
        }

        return returnResult;
    }

    public DTPP001ReturnResult returnResultAndUpdateConfirmation(InputLog inputLog) {

        DTPP001Parameters param = new DTPP001Parameters();
        param.setPWERK(inputLog.getPlant());
        param.setAUFNR(inputLog.getOrderno());
        param.setVORNR(inputLog.getOperation());
        param.setBUDAT(inputLog.getPostingDate().replaceAll("-", ""));
        param.setGMNGA(inputLog.getYeild().toString());
        param.setXMNGA(inputLog.getWorkScrap().toString());
        param.setRMNGA(inputLog.getRowScrap().toString());
        param.setZSCBC(inputLog.getClassgrp() == null ? "" : inputLog.getClassgrp());
        param.setZSCX(inputLog.getLine() == null ? "" : inputLog.getLine());
        param.setZMNUM(inputLog.getModelNo() == null ? "" : inputLog.getModelNo());
        param.setDATUM(this.df.format(new Date()));
        param.setZPGDBAR(inputLog.getDispatch());
        param.setZPGDBH(inputLog.getDispatchLogicID() == null ? "" : inputLog.getDispatchLogicID());
        param.setRSNUM("");
        param.setRSPOS("");
        param.setREVERSE("");
        param.setATTR1(inputLog.getAttr1());
        param.setATTR2(inputLog.getAttr2());
        param.setATTR3(inputLog.getAttr3());
        param.setATTR4(inputLog.getAttr4());
        param.setATTR5(inputLog.getAttr5());
        param.setATTR6(inputLog.getAttr6().replaceAll("-", ""));
        param.setATTR7(inputLog.getAttr7());
        param.setUSERNAME(inputLog.getUserName());

        /*//test furong.tang
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();*/

        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation(param);

        Log log = new Log();
        Result result = new Result();
        inputLog.setMaterial(returnResult.getMATNR());
        inputLog.setMatDesc(returnResult.getMAKTX());
        inputLogMapper.insertInputLog(inputLog);
        Long id = inputLogMapper.selectNextId();
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
        log.setCreated_by(inputLog.getCreated_by());
        resultMapper.insertResult(result);
        logMapper.insertLog(log);
        return returnResult;

    }

    public DTBAOGONGReturnResult returnResultAndUpdateConfirmationNew(InputLog inputLog) {
        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        DTBAOGONGParameters param = new DTBAOGONGParameters();
        param.setPWERK(inputLog.getPlant());
        param.setAUFNR(inputLog.getOrderno());
        param.setVORNR(inputLog.getOperation());
        param.setBUDAT(inputLog.getPostingDate().replaceAll("-", ""));
        param.setGMNGA(inputLog.getYeild().toString());
        param.setXMNGA(inputLog.getWorkScrap().toString());
        param.setRMNGA(inputLog.getRowScrap().toString());
        param.setZSCBC(inputLog.getClassgrp() == null ? "" : inputLog.getClassgrp());
        param.setZSCX(inputLog.getLine() == null ? "" : inputLog.getLine());
        param.setZMNUM(inputLog.getModelNo() == null ? "" : inputLog.getModelNo());
        param.setDATUM(this.df.format(new Date()));
        param.setZPGDBAR(inputLog.getDispatch());
        param.setZPGDBH(inputLog.getDispatchLogicID() == null ? "" : inputLog.getDispatchLogicID());
        param.setRSNUM("");
        param.setRSPOS("");
        param.setREVERSE("");
        param.setATTR1(inputLog.getAttr1());
        param.setATTR2(inputLog.getAttr2());
        param.setATTR3(inputLog.getAttr3());
        param.setATTR4(inputLog.getAttr4());
        param.setATTR5(inputLog.getAttr5());
        param.setATTR6(inputLog.getAttr6().replaceAll("-", ""));
        param.setATTR7(inputLog.getAttr7());
        param.setATTR8(inputLog.getAttr8());
        param.setATTR9(inputLog.getAttr9());
        param.setATTR10(inputLog.getAttr10());
        param.setATTR11(inputLog.getAttr11());
        param.setATTR12(inputLog.getAttr12());
        param.setATTR13(inputLog.getAttr13());
        param.setATTR14(inputLog.getAttr14());
        param.setATTR15(inputLog.getAttr15());
        param.setBGUUID(uuidstr);
        param.setUSERNAME(inputLog.getUserName());
        param.setARBPL(inputLog.getArbpl());
        param.setLSTVOR(inputLog.getLstvor());
        param.setFSTVOR(inputLog.getFstvor());
        param.setCHARG(inputLog.getCharg());
        param.setAUART(inputLog.getAuart());
        param.setZPRTP(inputLog.getZprtp());
        param.setZTPBAR(inputLog.getZtpbar());

        DTBAOGONGParametersitem paramitem = new DTBAOGONGParametersitem();
        List<DTBAOGONGParametersitem> list = new ArrayList<DTBAOGONGParametersitem>();


        /*//test furong.tang
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();*/

        // 写入流转卡锁
//        Cardhlock lock = new Cardhlock();
//        lock.setZpgdbar(inputLog.getDispatch());
//        lock.setAttr1(uuidstr);
//        lock.setCreatedBy(inputLog.getCreatedBy());
//        lock.setCreationDate(new Date());

            DTBAOGONGReturnResult returnResult = webserviceUtilNew.receiveConfirmation(param, list);
            Date indate = new Date();
            if (returnResult.getMSGTY().equals("S")) {//报工成功
                //获取报工流转卡的信息，然后更新 班标，和模号
                Cardh cardh = new Cardh();
                cardh = cardhMapper.selectByBarcode(inputLog.getDispatch());
                cardh.setShift(inputLog.getClassgrp());
                cardh.setDiecd(inputLog.getModelNo());
                cardh.setSfflg(inputLog.getAttr7());
                cardh.setCharg2(returnResult.getCHARG());//反写批次
                cardhMapper.updateCardhShiftAndSfflg(cardh);
            }
            Log log = new Log();
            Result result = new Result();
            inputLog.setMaterial(returnResult.getMATNR());
            inputLog.setMatDesc(returnResult.getMAKTX());
            inputLog.setBguuid(uuidstr);
            inputLogMapper.insertInputLog(inputLog);
            //根据UUID 查询日志记录 获取ID
            InputLog inputLogSaved = new InputLog();
            inputLogSaved = inputLogMapper.selectByBgUuid(inputLog.getBguuid());
            Long id = inputLogSaved.getId();
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
            log.setCreated_by(inputLog.getCreated_by());
            log.setCreationDate(indate);
            log.setLastUpdateDate(new Date());
            resultMapper.insertResult(result);
            logMapper.insertLog(log);
            cardhlockMapper.deleteCardhlock(inputLog.getDispatch(),inputLog.getOperation());
            return returnResult;

    }

    @Override
    public DTBAOGONGReturnResult writeOffDispatchNew(InputLog input) {
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        returnResult.setMESSAGE("冲销失败！当前工序存在后续工序已报工。");
        returnResult.setMSGTY("E");
        List<InputLog> inputLogs = inputLogMapper.queryAllGTOperation(input);
        if (inputLogs.size() > 0) {
            return returnResult;
        }
        return returnWriteOffResultAndUpdateConfirmationNew(input);
    }

    @Override
    public DTPP001ReturnResult writeOffDispatch(InputLog input) {
        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setMESSAGE("冲销失败！当前工序存在后续工序已报工。");
        returnResult.setMSGTY("E");
        List<InputLog> inputLogs = inputLogMapper.queryAllGTOperation(input);
        if (inputLogs.size() > 0) {
            return returnResult;
        }
        return returnWriteOffResultAndUpdateConfirmation(input);
    }

    public DTBAOGONGReturnResult returnWriteOffResultAndUpdateConfirmationNew(InputLog inputLog) {
        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        DTBAOGONGParameters param = new DTBAOGONGParameters();
        DTBAOGONGParametersitem paramitem = new DTBAOGONGParametersitem();
        List<DTBAOGONGParametersitem> list = new ArrayList<>();
        param.setPWERK(inputLog.getPlant());
        param.setAUFNR(inputLog.getOrderno());
        param.setVORNR(inputLog.getOperation());
        param.setBUDAT(inputLog.getPostingDate().replaceAll("-", ""));
        param.setGMNGA("-" + inputLog.getYeild().toString());
        param.setXMNGA("-" + inputLog.getWorkScrap().toString());
        param.setRMNGA("-" + inputLog.getRowScrap().toString());
        param.setZSCBC(inputLog.getClassgrp() == null ? "" : inputLog.getClassgrp());
        param.setZSCX(inputLog.getLine() == null ? "" : inputLog.getLine());
        param.setZMNUM(inputLog.getModelNo() == null ? "" : inputLog.getModelNo());
        param.setDATUM(df.format(new Date()));
        param.setZPGDBAR(inputLog.getDispatch() == null ? "" : inputLog.getDispatch());
        //param.setZPGDBH(inputLog.getDispatchLogicID() == null ? "" : inputLog.getDispatchLogicID());
        param.setZPGDBH(inputLog.getDispatch().substring(inputLog.getDispatch().length() - 4, inputLog.getDispatch().length()));
        param.setRSNUM(inputLog.getConfirmationNo());
        param.setRSPOS(inputLog.getConfirmationPos());
        param.setREVERSE("X");
        param.setATTR1(inputLog.getAttr1());
        param.setATTR2(inputLog.getAttr2());
        param.setATTR3(inputLog.getAttr3());
        param.setATTR4(inputLog.getAttr4());
        param.setATTR5(inputLog.getAttr5());
        param.setATTR6(inputLog.getAttr6().replaceAll("-", ""));
        param.setATTR7(inputLog.getAttr7());
        param.setATTR8(inputLog.getAttr8());
        param.setATTR9(inputLog.getAttr9());
        param.setATTR10(inputLog.getAttr10());
        param.setATTR11(inputLog.getAttr11());
        param.setATTR12(inputLog.getAttr12());
        param.setATTR13(inputLog.getAttr13());
        param.setATTR14(inputLog.getAttr14());
        param.setATTR15(inputLog.getAttr15());
        param.setBGUUID(uuidstr);
        param.setUSERNAME(inputLog.getUserName());
        param.setAUART(inputLog.getAuart());
        param.setARBPL("");
        param.setLSTVOR(inputLog.getLstvor());
        param.setFSTVOR(inputLog.getFstvor());
        param.setCHARG("");
        if (inputLog.getAttr15().equals("5")){
            param.setZPRTP(inputLog.getAttr15());
        }else{
            param.setZPRTP("");
        }
        param.setZTPBAR("");

        DTBAOGONGReturnResult returnResult = webserviceUtilNew.receiveConfirmation(param, list);
        Date inDate = new Date();
        inputLog.setCxuuid(uuidstr);
        inputLogMapper.updateCxuuid(inputLog);
        InputLog inputLogSaved = new InputLog();
        inputLogSaved = inputLogMapper.selectByCxUuid(inputLog.getCxuuid());
        Log log = new Log();
        log.setMsgtx(returnResult.getMESSAGE());
        log.setMsgty(returnResult.getMSGTY());
        log.setTranType("1");
        log.setRefId(inputLogSaved.getId());
        log.setCreated_by(inputLog.getCreated_by());
        log.setCreationDate(inDate);
        log.setLastUpdateDate(new Date());
        logMapper.insertLog(log);
        if ("S".equals(returnResult.getMSGTY())) {
            resultMapper.updateReveseByInputId(inputLogSaved.getId());
        }

        return returnResult;

    }

    public DTPP001ReturnResult returnWriteOffResultAndUpdateConfirmation(InputLog inputLog) {

        DTPP001Parameters param = new DTPP001Parameters();
        param.setPWERK(inputLog.getPlant());
        param.setAUFNR(inputLog.getOrderno());
        param.setVORNR(inputLog.getOperation());
        param.setBUDAT(inputLog.getPostingDate().replaceAll("-", ""));
        param.setGMNGA("-" + inputLog.getYeild().toString());
        param.setXMNGA("-" + inputLog.getWorkScrap().toString());
        param.setRMNGA("-" + inputLog.getRowScrap().toString());
        param.setZSCBC(inputLog.getClassgrp() == null ? "" : inputLog.getClassgrp());
        param.setZSCX(inputLog.getLine() == null ? "" : inputLog.getLine());
        param.setZMNUM(inputLog.getModelNo() == null ? "" : inputLog.getModelNo());
        param.setDATUM(df.format(new Date()));
        param.setZPGDBAR(inputLog.getDispatch() == null ? "" : inputLog.getDispatch());
        //param.setZPGDBH(inputLog.getDispatchLogicID() == null ? "" : inputLog.getDispatchLogicID());
        param.setZPGDBH(inputLog.getDispatch().substring(inputLog.getDispatch().length() - 4, inputLog.getDispatch().length()));
        param.setRSNUM(inputLog.getConfirmationNo());
        param.setRSPOS(inputLog.getConfirmationPos());
        param.setREVERSE("X");
        param.setATTR1(inputLog.getAttr1());
        param.setATTR2(inputLog.getAttr2());
        param.setATTR3(inputLog.getAttr3());
        param.setATTR4(inputLog.getAttr4());
        param.setATTR5(inputLog.getAttr5());
        param.setATTR6(inputLog.getAttr6().replaceAll("-", ""));
        param.setATTR7(inputLog.getAttr7());
        param.setUSERNAME(inputLog.getUserName());

        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation(param);
        Log log = new Log();
        log.setMsgtx(returnResult.getMESSAGE());
        log.setMsgty(returnResult.getMSGTY());
        log.setTranType("1");
        log.setRefId(inputLog.getId());
        log.setCreated_by(inputLog.getCreated_by());


        logMapper.insertLog(log);
        if ("S".equals(returnResult.getMSGTY())) {
            resultMapper.updateReveseByInputId(inputLog.getId());
        }

        return returnResult;
    }

    /**
     * 新增查询指定流转卡 指定工序的报工成功记录
     */
    @Override
    public InputLog selectConfirmationSuccess(InputLog inputLog) {
        return inputLogMapper.selectConfirmationSuccess(inputLog);
    }

    @Override
    public Long selectNextId() {
        return inputLogMapper.selectNextId();
    }

    @Override
    public String queryDispatchMaxOperation(String dispatch) {
        return inputLogMapper.queryDispatchMaxOperation(dispatch);
    }

    @Override
    public InputLog queryByDispatchAndOperation(InputLog inputLog) {
        return inputLogMapper.queryByDispatchAndOperation(inputLog);
    }

    @Override
    public InputLog querySumInputlogForShotnum(String werks, String matnr, String arbpl, String attr6, String attr4) {
        return inputLogMapper.querySumInputlogForShotnum(werks,matnr,arbpl,attr6,attr4);
    }
}
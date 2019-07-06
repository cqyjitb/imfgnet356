package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.afko.dto.Afko;
import yj.core.afko.mapper.AfkoMapper;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 917110140 on 2018/9/17.
 */
@WebService
public class JjbgImpl implements IJjbg {

    @Override
    public DTBAOGONGReturnResult callbg(DTBAOGONGParameters params, List<DTBAOGONGParametersitem> list)  {
        DTBAOGONGReturnResult rs = new DTBAOGONGReturnResult();
        ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();
        CardhMapper cardhMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(CardhMapper.class);
        InputLogMapper inputLogMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(InputLogMapper.class);
        ResultMapper resultMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResultMapper.class);
        LogMapper logMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(LogMapper.class);
        AfkoMapper afkoMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(AfkoMapper.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf1.parse(params.getATTR6());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try{
            params.setATTR8("");
            params.setATTR9("");
            params.setATTR10("");
            params.setATTR11("");
            params.setATTR12("");
            params.setATTR13("");
            params.setATTR14("");
            params.setATTR15("");
            rs = confirmationWebserviceUtilNew.receiveConfirmation(params,list);
            Date indate = new Date();
            if (rs.getMSGTY().equals("S")){

                Cardh cardh = new Cardh();
                cardh = cardhMapper.selectByBarcode(params.getZPGDBAR());
                cardh.setLastUpdatedDate(new Date());
                cardh.setLastUpdatedBy(Long.valueOf(params.getUSERNAME()));
                cardh.setEprddat(params.getBUDAT());
                cardh.setActgltrp(curdate);
                cardh.setActdt(curtim);
                cardh.setQtysm(Double.valueOf(params.getRMNGA()));
                cardh.setQtysp(Double.valueOf(params.getXMNGA()));
                cardh.setShift(params.getZSCBC());
                cardh.setAlqty(Double.valueOf(params.getGMNGA()));
                cardh.setStatus2(cardh.getStatus());
                cardh.setStatus("ECNF");
                cardh.setAttr4(params.getATTR4());//班次
                cardhMapper.updateCardhStatus(cardh);
            }
            Result result = new Result();
            Log log = new Log();
            InputLog inputLog = new InputLog();
            if (params.getREVERSE().equals("X")){
                result.setIsReversed("1");//冲销
                inputLog.setOperation(params.getVORNR());
                inputLog.setDispatch(params.getZPGDBAR());
                inputLog.setConfirmationNo(params.getRSNUM());
                inputLog.setConfirmationPos(params.getRSPOS());
                InputLog inputLog2 = new InputLog();
                inputLog2 = inputLogMapper.queryAllGTOperationJj(inputLog);
                inputLog2.setCxuuid(params.getBGUUID());
                inputLogMapper.updateCxuuid(inputLog2);

                log.setCreationDate(indate);
                log.setLastUpdateDate(new Date());
                log.setMsgtx(rs.getMESSAGE());
                log.setMsgty(rs.getMSGTY());
                log.setTranType("1");
                log.setRefId(inputLog2.getId());
                log.setCreated_by("10001");

                logMapper.insertLog(log);
                if ("S".equals(rs.getMSGTY())) {
                    resultMapper.updateReveseByInputId(inputLog2.getId());
                }
                return rs;
            }else{
                result.setIsReversed("0");//报工
                inputLog.setBarcode(params.getZPGDBAR());
                inputLog.setOrderno(params.getAUFNR());
                inputLog.setDispatch(params.getZPGDBAR());
                inputLog.setOperation(params.getVORNR());
                inputLog.setYeild(Double.parseDouble(params.getGMNGA()));
                inputLog.setWorkScrap(Double.parseDouble(params.getRMNGA()));
                inputLog.setRowScrap(Double.parseDouble(params.getXMNGA()));
                inputLog.setClassgrp(params.getZSCBC());
                inputLog.setLine(params.getZSCX());
                inputLog.setModelNo("");
                inputLog.setPlant(params.getPWERK());
                inputLog.setPostingDate(params.getBUDAT());
                inputLog.setDispatchLogicID(params.getZPGDBH());
                inputLog.setCreated_by(params.getUSERNAME());
                inputLog.setAttr1(params.getATTR1());
                inputLog.setAttr2(params.getATTR2());
                inputLog.setAttr3(params.getATTR3());
                inputLog.setAttr4(params.getATTR4());
                inputLog.setAttr5(params.getATTR5());
                String attr6 = sdf2.format(date);
                inputLog.setAttr6(attr6);
                inputLog.setAttr7(params.getATTR7());
                inputLog.setUserName(params.getUSERNAME());
                inputLog.setMaterial(rs.getMATNR());
                inputLog.setMatDesc(rs.getMAKTX());
                inputLog.setAttr15(params.getATTR15());//保存机加报工的UUID
                inputLog.setBguuid(params.getBGUUID());
                inputLogMapper.insertInputLog(inputLog);
                Long id = inputLogMapper.selectNextId();
                result.setPlant(inputLog.getPlant());
                result.setInputId(id);
                result.setMaterial(inputLog.getMaterial());
                result.setMatDesc(inputLog.getMatDesc());
                result.setCreated_by(inputLog.getCreated_by());
                result.setConfirmationNo(rs.getRSNUM());
                result.setConfirmationPos(rs.getRSPOS());
                result.setFevor(rs.getFEVOR());
                result.setFevorTxt(rs.getTXT());
                result.setOperationDesc(rs.getLTXA1());
                log.setMsgty(rs.getMSGTY());
                log.setMsgtx(rs.getMESSAGE());
                if (params.getREVERSE().equals("X")){

                    log.setTranType("1");
                }else{

                    log.setTranType("0");
                }
                log.setRefId(id);
                log.setCreated_by(inputLog.getCreated_by());
                log.setCreationDate(indate);
                log.setLastUpdateDate(new Date());
                resultMapper.insertResult(result);
                logMapper.insertLog(log);
                return  rs;
            }

        }catch (Exception e){
            Date indate = new Date();
            Log log = new Log();
            Result result = new Result();
            InputLog inputLog = new InputLog();
            inputLog.setBarcode(params.getZPGDBAR());
            inputLog.setOrderno(params.getAUFNR());
            inputLog.setDispatch(params.getZPGDBAR());
            inputLog.setOperation(params.getVORNR());
            inputLog.setYeild(Double.parseDouble(params.getGMNGA()));
            inputLog.setWorkScrap(Double.parseDouble(params.getRMNGA()));
            inputLog.setRowScrap(Double.parseDouble(params.getXMNGA()));
            inputLog.setClassgrp(params.getZSCBC());
            inputLog.setLine(params.getZSCX());
            inputLog.setModelNo("");
            inputLog.setPlant(params.getPWERK());
            inputLog.setPostingDate(params.getBUDAT());
            inputLog.setDispatchLogicID(params.getZPGDBH());
            inputLog.setCreated_by(params.getUSERNAME());
            inputLog.setAttr1(params.getATTR1());
            inputLog.setAttr2(params.getATTR2());
            inputLog.setAttr3(params.getATTR3());
            inputLog.setAttr4(params.getATTR4());
            inputLog.setAttr5(params.getATTR5());
            String attr6 = sdf2.format(date);
            inputLog.setAttr6(attr6);
            inputLog.setAttr7(params.getATTR7());
            inputLog.setUserName(params.getUSERNAME());
            inputLog.setMaterial("");
            inputLog.setMatDesc("");
            inputLog.setAttr15(params.getATTR15());//保存机加报工的UUID
            if (params.getREVERSE().equals("X")){
               inputLog.setCxuuid(params.getBGUUID());
            }else{
                inputLog.setBguuid(params.getBGUUID());
             }
            inputLogMapper.insertInputLog(inputLog);

            Long id = inputLogMapper.selectNextId();
            result.setPlant(inputLog.getPlant());
            result.setInputId(id);
            if (params.getREVERSE().equals("X")){
                result.setIsReversed("1");//冲销
            }else{
                result.setIsReversed("0");//报工
            }
            Afko afko = new Afko();
            afko = afkoMapper.selectByAufnr(params.getAUFNR());
            if (afko != null){
                result.setMaterial(afko.getPlnbez());
                result.setMatDesc(afko.getMaktx());
            }else{
                result.setMaterial("");
                result.setMatDesc("");
            }
            result.setCreated_by(inputLog.getCreated_by());
            result.setConfirmationNo(params.getRSNUM());
            result.setConfirmationPos(params.getRSPOS());
            result.setFevor("");
            result.setFevorTxt("");
            result.setOperationDesc("");
            log.setMsgty("E");
            log.setMsgtx(e.getMessage().toString());
            if (params.getREVERSE().equals("X")){
                log.setTranType("1");
            }else{

                log.setTranType("0");
            }
            log.setRefId(id);
            log.setCreated_by(inputLog.getCreated_by());
            resultMapper.insertResult(result);
            log.setCreationDate(indate);
            log.setLastUpdateDate(new Date());
            logMapper.insertLog(log);
            return  rs;
        }


    }
}

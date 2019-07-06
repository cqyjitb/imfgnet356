package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.mapper.LogMapper;
import yj.core.dispatch.mapper.ResultMapper;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.webservice_readbglog.components.ReadbglogWebsrviceUtil;
import yj.core.webservice_readbglog.dto.ReadlogParam;
import yj.core.webservice_server.dto.QueryLogParam;
import yj.core.webservice_server.dto.ReturnQueryjjbg;

import javax.jws.WebService;

@WebService
public class QueryJJbglogimpl implements IQueryJJbglog {
    @Override
    public ReturnQueryjjbg querybglog(QueryLogParam queryLogParamparam) {
        ReturnQueryjjbg rs = new ReturnQueryjjbg();
        InputLogMapper inputLogMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(InputLogMapper.class);
        ResultMapper resultMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResultMapper.class);
        LogMapper logMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(LogMapper.class);
        InputLog inputLog = new InputLog();
        ReadbglogWebsrviceUtil readbglogWebsrviceUtil = new ReadbglogWebsrviceUtil();
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        ReadlogParam param = new ReadlogParam();
        if (queryLogParamparam.getReverse().equals("")){
            param.setReverse("");
            param.setUuid(queryLogParamparam.getUuid());
            returnResult = readbglogWebsrviceUtil.receiveConfirmation(param);
            if (returnResult.getMSGTY().equals("S")){
                rs.setMSGTY(returnResult.getMSGTY());
                rs.setMESSAGE(returnResult.getMESSAGE());
                rs.setAUFNR(returnResult.getAUFNR());
                rs.setMATNR(returnResult.getMATNR());
                rs.setMAKTX(returnResult.getMAKTX());
                rs.setRSNUM(returnResult.getRSNUM());
                rs.setRSPOS(returnResult.getRSPOS());
                rs.setFEVOR(returnResult.getFEVOR());
                rs.setTXT(returnResult.getTXT());
                inputLog = inputLogMapper.queryInputlogByJjuuidbg(queryLogParamparam.getUuid());
                if (inputLog != null){
                    rs.setYEILD(inputLog.getYeild());
                    rs.setWORKSCRP(inputLog.getWorkScrap());
                    rs.setROWSCRAP(inputLog.getRowScrap());
                    //检查日志是否与SAP一致不一致 修改一致
                    Log log = logMapper.queryLogByRefidbg(inputLog.getId());
                    if (log != null){
                        if (!log.getMsgty().equals("S")){
                            log.setMsgty("S");
                            log.setMsgtx("成功");
                            logMapper.updateMsgty(log);
                        }
                    }else{
                        log.setRefId(inputLog.getId());
                        log.setTranType("0");
                        log.setMsgty("S");
                        log.setCreated_by("10001");
                        log.setMsgtx("成功");
                        logMapper.insertLog(log);
                    }

                    Result con_result = resultMapper.selectByInputId(inputLog.getId());
                    if (con_result != null){
                        con_result.setConfirmationNo(rs.getRSNUM());
                        con_result.setConfirmationPos(rs.getRSPOS());
                        resultMapper.updateRsnumAndRspos(con_result);
                    }

                }
            }else{
                    rs.setMSGTY("E");
                    rs.setMESSAGE(inputLog.getMsgtx());
            }
        }else{
            param.setReverse("X");
            param.setUuid(queryLogParamparam.getUuid());
            returnResult = readbglogWebsrviceUtil.receiveConfirmation(param);
            if (returnResult.getMSGTY().equals("S")){
                rs.setMSGTY(returnResult.getMSGTY());
                rs.setMESSAGE(returnResult.getMESSAGE());
                rs.setAUFNR(returnResult.getAUFNR());
                rs.setMATNR(returnResult.getMATNR());
                rs.setMAKTX(returnResult.getMAKTX());
                rs.setRSNUM(returnResult.getRSNUM());
                rs.setRSPOS(returnResult.getRSPOS());
                rs.setFEVOR(returnResult.getFEVOR());
                rs.setTXT(returnResult.getTXT());

                inputLog = inputLogMapper.queryInputlogByJjuuidcx(queryLogParamparam.getUuid());
                if (inputLog != null){
                    rs.setYEILD(inputLog.getYeild());
                    rs.setWORKSCRP(inputLog.getWorkScrap());
                    rs.setROWSCRAP(inputLog.getRowScrap());
                    //检查日志是否与SAP一致不一致 修改一致
                    Log log = logMapper.queryLogByRefidcx(inputLog.getId());
                    if (log != null){
                        if (!log.getMsgty().equals("S")){
                            log.setMsgty("S");
                            log.setMsgtx("冲销成功");
                            logMapper.updateMsgty(log);
                        }
                    }else{
                        log.setRefId(inputLog.getId());
                        log.setTranType("1");
                        log.setMsgty("S");
                        log.setCreated_by("10001");
                        log.setMsgtx("冲销成功");
                        logMapper.insertLog(log);
                    }

                    Result con_result = resultMapper.selectByInputId(inputLog.getId());
                    if (con_result != null){
                        resultMapper.updateReveseByInputId(inputLog.getId());
                    }
                }
            }else{
                rs.setMSGTY("E");
                rs.setMESSAGE(inputLog.getMsgtx());
            }
        }
        return rs;
    }
}

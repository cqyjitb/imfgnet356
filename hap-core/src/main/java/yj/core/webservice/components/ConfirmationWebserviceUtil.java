package yj.core.webservice.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice.dto.DTPP001Parameters;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice.receiver.DTPP001SendRes;
import yj.core.webservice.sender.DTPP001SendReq;
import yj.core.webservice.sender.SIPP001SenderSync;
import yj.core.webservice.sender.SIPP001SenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by TFR on 2017/6/14.
 */
@Component
public class ConfirmationWebserviceUtil{
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/Sender", "SI_PP001_Sender_SyncService");

    public ConfirmationWebserviceUtil() {
    }
    public DTPP001ReturnResult receiveConfirmation(DTPP001Parameters params){
        URL wsdlURL = SIPP001SenderSyncService.WSDL_LOCATION;
        SIPP001SenderSyncService ss = new SIPP001SenderSyncService(wsdlURL, SERVICE_NAME);
        SIPP001SenderSync port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        //pro
//        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "HAPUSER");
//        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "YJhap201707@CQ");

        //dev
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);
//        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "HAPUSER");
//        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "Yjsap123@CQ");
        DTPP001SendReq.ITEM item = new DTPP001SendReq.ITEM();
        /*item.setPWERK("");
        item.setAUFNR("");
        item.setVORNR("");
        item.setBUDAT("");
        item.setGMNGA("");
        item.setXMNGA("");
        item.setRMNGA("");
        item.setZSCBC("");
        item.setZSCX("");
        item.setZMNUM("");
        item.setDATUM("");
        item.setZPGDBAR("");
        item.setZPGDBH("");*/

        item.setPWERK(params.getPWERK());
        item.setAUFNR(params.getAUFNR());
        item.setVORNR(params.getVORNR());
        item.setBUDAT(params.getBUDAT());
        item.setGMNGA(params.getGMNGA());
        item.setXMNGA(params.getXMNGA());
        item.setRMNGA(params.getRMNGA());
        item.setZSCBC(params.getZSCBC());
        item.setZSCX(params.getZSCX());
        item.setZMNUM(params.getZMNUM());
        item.setDATUM(params.getDATUM());
        item.setZPGDBAR(params.getZPGDBAR());
        item.setZPGDBH(params.getZPGDBH());
        item.setRSPOS(params.getRSPOS());
        item.setRSNUM(params.getRSNUM());
        item.setREVERSE(params.getREVERSE());
        item.setAttr1(params.getATTR1());
        item.setAttr2(params.getATTR2());
        item.setAttr3(params.getATTR3());
        item.setAttr4(params.getATTR4());
        item.setAttr5(params.getATTR5());
        item.setAttr6(params.getATTR6());
        item.setAttr7(params.getATTR7());
        item.setUserName(params.getUSERNAME());

        /*item.setPWERK(inputLog.getPlant());
        item.setAUFNR(inputLog.getOrderno());
        item.setVORNR(inputLog.getOperation());
        item.setBUDAT(inputLog.getPostingDate().replaceAll("-",""));
        item.setGMNGA(inputLog.getYeild().toString());
        item.setXMNGA(inputLog.getWorkScrap().toString());
        item.setRMNGA(inputLog.getRowScrap().toString());
        item.setZSCBC(inputLog.getClassgrp());
        item.setZSCX(inputLog.getLine());
        item.setZMNUM(inputLog.getModelNo());
        item.setDATUM(df.format(new Date()));
        item.setZPGDBAR(inputLog.getDispatch());
        item.setZPGDBH(inputLog.getDispatchLogicID());*/

        DTPP001SendReq _siPP001SenderSync_mtPP001SendReq = new DTPP001SendReq();

        //传入参数
        _siPP001SenderSync_mtPP001SendReq.getITEM().add(item);

        DTPP001SendRes _siPP001SenderSync__return = port.siPP001SenderSync(_siPP001SenderSync_mtPP001SendReq);
       // System.out.println(_siPP001SenderSync__return.getDETAIL().get(0).getMESSAGE());
        String aufnr = _siPP001SenderSync__return.getDETAIL().get(0).getAUFNR();//订单号
        String matnr = _siPP001SenderSync__return.getDETAIL().get(0).getMATNR();//物料号
        String msgTy = _siPP001SenderSync__return.getDETAIL().get(0).getMSGTY();//信息状态  S(成功)/E(失败)
        String msgNo = _siPP001SenderSync__return.getDETAIL().get(0).getMSGNO();//消息号
        String msgId = _siPP001SenderSync__return.getDETAIL().get(0).getMSGID();//消息ID
        String msgV1 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV1();
        String msgV2 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV2();
        String msgV3 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV3();
        String msgV4 = _siPP001SenderSync__return.getDETAIL().get(0).getMSGV4();
        String rsnum = _siPP001SenderSync__return.getDETAIL().get(0).getRSNUM();
        String rspos = _siPP001SenderSync__return.getDETAIL().get(0).getRSPOS();
        String fevor = _siPP001SenderSync__return.getDETAIL().get(0).getFEVOR();//工序管理员
        String txt = _siPP001SenderSync__return.getDETAIL().get(0).getTXT();//工序管理员描述
        String message = _siPP001SenderSync__return.getDETAIL().get(0).getMESSAGE();//信息
        String maktx = "";
        String ltxa1 = "";
        if(_siPP001SenderSync__return.getDETAIL().get(0).getMAKT().size() > 0){
            maktx = _siPP001SenderSync__return.getDETAIL().get(0).getMAKT().get(0).getMAKTX();
        }
        if(_siPP001SenderSync__return.getDETAIL().get(0).getAFVC().size() > 0){
            ltxa1 = _siPP001SenderSync__return.getDETAIL().get(0).getAFVC().get(0).getLTXA1();
        }

        msgTy = _siPP001SenderSync__return.getRETURN().getMSGTY();//信息状态  S(成功)/E(失败)
        message = _siPP001SenderSync__return.getDETAIL().get(0).getMESSAGE();//信息
        for(int i = 1 ; i < _siPP001SenderSync__return.getDETAIL().size() ; i++){
            message = message + "/" + _siPP001SenderSync__return.getDETAIL().get(i).getMESSAGE();
        }

        DTPP001ReturnResult returnResult = new DTPP001ReturnResult();
        returnResult.setAUFNR(aufnr);
        returnResult.setMATNR(matnr);
        returnResult.setMSGTY(msgTy);
        returnResult.setMSGNO(msgNo);
        returnResult.setMSGID(msgId);
        returnResult.setMSGV1(msgV1);
        returnResult.setMSGV2(msgV2);
        returnResult.setMSGV3(msgV3);
        returnResult.setMSGV4(msgV4);
        returnResult.setMAKTX(maktx);
        returnResult.setMESSAGE(message);
        returnResult.setRSNUM(rsnum);
        returnResult.setRSPOS(rspos);
        returnResult.setFEVOR(fevor);
        returnResult.setTXT(txt);
        returnResult.setLTXA1(ltxa1);
        return returnResult;

    }
}

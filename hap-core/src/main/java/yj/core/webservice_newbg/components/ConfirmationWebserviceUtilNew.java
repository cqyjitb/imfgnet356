package yj.core.webservice_newbg.components;

import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.wsdl.WSDLManager;
import org.apache.cxf.wsdl11.WSDLManagerImpl;
import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.webservice_newbg.receiver.DTBAOGONGSendRes;
import yj.core.webservice_newbg.sender.DTBAOGONGSendReq;
import yj.core.webservice_newbg.sender.SIBAOGONGSenderSync;
import yj.core.webservice_newbg.sender.SIBAOGONGSenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by 917110140 on 2018/8/15.
 */
@Component
public class ConfirmationWebserviceUtilNew {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/BAOGONG/Sender", "SI_BAOGONG_Sender_SyncService");
    public ConfirmationWebserviceUtilNew() {
    }
    public DTBAOGONGReturnResult receiveConfirmation(DTBAOGONGParameters params, List<DTBAOGONGParametersitem> list){
        URL wsdlURL = SIBAOGONGSenderSyncService.WSDL_LOCATION;
        WSDLManager wsdlManager =  CXFBusFactory.getThreadDefaultBus().getExtension(WSDLManager.class);
        ((WSDLManagerImpl)wsdlManager).setDisableSchemaCache(true);
        SIBAOGONGSenderSyncService ss = new SIBAOGONGSenderSyncService(wsdlURL,SERVICE_NAME);
        SIBAOGONGSenderSync port = ss.getHTTPPort();
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

        DTBAOGONGSendReq.ITEM item = new DTBAOGONGSendReq.ITEM();
        DTBAOGONGSendReq  dtbaogongSendReq = new DTBAOGONGSendReq();
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
        item.setATTR1(params.getATTR1());
        item.setATTR2(params.getATTR2());
        item.setATTR3(params.getATTR3());
        item.setATTR4(params.getATTR4());//报工班次
        item.setATTR5(params.getATTR5());
        item.setATTR6(params.getATTR6());
        item.setATTR7(params.getATTR7());
        item.setATTR8(params.getATTR8());
        item.setATTR9(params.getATTR9());
        item.setATTR10(params.getATTR10());
        item.setATTR11(params.getATTR11());
        item.setATTR12(params.getATTR12());
        item.setATTR13(params.getATTR13());
        item.setATTR14(params.getATTR14());
        item.setATTR15(params.getATTR15());
        item.setUUID(params.getBGUUID());
        item.setUSERNAME(params.getUSERNAME());
        item.setLSTVOR(params.getLSTVOR());
        item.setAUART(params.getAUART());
        item.setFSTVOR(params.getFSTVOR());
        item.setARBPL(params.getARBPL());
        item.setCHARG(params.getCHARG());
        item.setZPRTP(params.getZPRTP());//报工类型 1,2,3
        item.setLSTVOR(params.getLSTVOR());
        item.setZTPBAR(params.getZTPBAR());

        //领料明细
        for(int i= 0;i<list.size();i++){
            DTBAOGONGSendReq.ITEMQP itemqp = new DTBAOGONGSendReq.ITEMQP();
            itemqp.setSUBRSNUM(list.get(i).getSUBRSNUM());
            itemqp.setSUBRSPOS(list.get(i).getSUBRSPOS());
            itemqp.setMATNR(list.get(i).getMATNR());
            itemqp.setBDMNG(list.get(i).getBDMNG());
            itemqp.setCHARG(list.get(i).getCHARG());
            itemqp.setLGORT(list.get(i).getLGORT());
            itemqp.setMEINS(list.get(i).getMEINS());
            itemqp.setWERKS(list.get(i).getWERKS());
            dtbaogongSendReq.getITEMQP().add(itemqp);
        }

        dtbaogongSendReq.getITEM().add(item);
        DTBAOGONGSendRes dtbaogongSendRes = port.siBAOGONGSenderSync(dtbaogongSendReq);

        String aufnr = dtbaogongSendRes.getDETAIL().get(0).getAUFNR();//订单号
        String matnr = dtbaogongSendRes.getDETAIL().get(0).getMATNR();//物料号
        String msgTy = dtbaogongSendRes.getDETAIL().get(0).getMSGTY();//信息状态  S(成功)/E(失败)
        String msgNo = dtbaogongSendRes.getDETAIL().get(0).getMSGNO();//消息号
        String msgId = dtbaogongSendRes.getDETAIL().get(0).getMSGID();//消息ID
        String msgV1 = dtbaogongSendRes.getDETAIL().get(0).getMSGV1();
        String msgV2 = dtbaogongSendRes.getDETAIL().get(0).getMSGV2();
        String msgV3 = dtbaogongSendRes.getDETAIL().get(0).getMSGV3();
        String msgV4 = dtbaogongSendRes.getDETAIL().get(0).getMSGV4();
        String rsnum = dtbaogongSendRes.getDETAIL().get(0).getRSNUM();
        String rspos = dtbaogongSendRes.getDETAIL().get(0).getRSPOS();
        String fevor = dtbaogongSendRes.getDETAIL().get(0).getFEVOR();//工序管理员
        String txt = dtbaogongSendRes.getDETAIL().get(0).getTXT();//工序管理员描述
        String charg = dtbaogongSendRes.getDETAIL().get(0).getCHARG();//返回批次
        String message = dtbaogongSendRes.getDETAIL().get(0).getMESSAGE();//信息
        String maktx = "";
        String ltxa1 = "";
        String mblnr = dtbaogongSendRes.getDETAIL().get(0).getMBLNR();
        String mjahr = dtbaogongSendRes.getDETAIL().get(0).getMJAHR();
        if(dtbaogongSendRes.getDETAIL().get(0).getMAKT().size() > 0){
            maktx = dtbaogongSendRes.getDETAIL().get(0).getMAKT().get(0).getMAKTX();
        }
        if(dtbaogongSendRes.getDETAIL().get(0).getAFVC().size() > 0){
            ltxa1 = dtbaogongSendRes.getDETAIL().get(0).getAFVC().get(0).getLTXA1();
        }

        msgTy = dtbaogongSendRes.getRETURN().getMSGTY();//信息状态  S(成功)/E(失败)
        message = dtbaogongSendRes.getDETAIL().get(0).getMESSAGE();//信息
        for(int i = 1 ; i < dtbaogongSendRes.getDETAIL().size() ; i++){
            message = message + "/" + dtbaogongSendRes.getDETAIL().get(i).getMESSAGE();
        }

        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
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
        returnResult.setCHARG(charg);
        returnResult.setMJAHR(mjahr);
        returnResult.setMBLNR(mblnr);
        return returnResult;
    }
}

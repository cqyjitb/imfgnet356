package yj.core.webserver_weidu.components;

import yj.core.util.WebServerHelp;
import yj.core.webserver_weidu.dto.DTWEIDUParam;
import yj.core.webserver_weidu.dto.DTWEIDUReturn;
import yj.core.webserver_weidu.receiver.DTWEIDURes;
import yj.core.webserver_weidu.sender.DTWEIDUReq;
import yj.core.webserver_weidu.sender.SIWEIDUSenderSyn;
import yj.core.webserver_weidu.sender.SIWEIDUSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by 917110140 on 2018/9/29.
 */
public class WeiduWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/WEIDU/Sender", "SI_WEIDU_Sender_SynService");
    public WeiduWebserviceUtil(){

    }

    public DTWEIDUReturn receiveConfirmation(DTWEIDUParam param){
        URL wsdlURL = SIWEIDUSenderSynService.WSDL_LOCATION;
        SIWEIDUSenderSynService ss = new SIWEIDUSenderSynService(wsdlURL, SERVICE_NAME);
        SIWEIDUSenderSyn port = ss.getHTTPPort();
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

        DTWEIDUReq.ITEM item = new DTWEIDUReq.ITEM();
        item.setMATNR(param.getMATNR());
        item.setWERKS(param.getWERKS());
        item.setZBANB(param.getZBANB());
        item.setZMODEL(param.getZMODEL());
        item.setZXHBAR(param.getZXHBAR());

        DTWEIDUReq dtweiduReq = new DTWEIDUReq();
        dtweiduReq.setITEM(item);

        DTWEIDURes dtweiduRes = port.siWEIDUSenderSyn(dtweiduReq);
        DTWEIDUReturn dtweiduReturn = new DTWEIDUReturn();
        dtweiduReturn.setMTMSG(dtweiduRes.getMTMSG());
        dtweiduReturn.setMTYPE(dtweiduRes.getMTYPE());
        dtweiduReturn.setWEIDUFLG(dtweiduRes.getWEIDUFLG());
        if (dtweiduReturn.getMTYPE().equals("S")){
            if (dtweiduRes.getTZTPP0012().size() > 0 ){
                dtweiduReturn.setBEIZ(dtweiduRes.getTZTPP0012().get(0).getBEIZ());
                dtweiduReturn.setCONNAM(dtweiduRes.getTZTPP0012().get(0).getCONNAM());
                dtweiduReturn.setMAKTX(dtweiduRes.getTZTPP0012().get(0).getMAKTX());
                dtweiduReturn.setMATNR(dtweiduRes.getTZTPP0012().get(0).getMATNR());
                dtweiduReturn.setMEINS(dtweiduRes.getTZTPP0012().get(0).getMEINS());
                dtweiduReturn.setSTATUS(dtweiduRes.getTZTPP0012().get(0).getSTATUS());
                dtweiduReturn.setSTATXT(dtweiduRes.getTZTPP0012().get(0).getSTATXT());
                dtweiduReturn.setWERKS(dtweiduRes.getTZTPP0012().get(0).getWERKS());
                dtweiduReturn.setZBANB(dtweiduRes.getTZTPP0012().get(0).getZBANB());
                dtweiduReturn.setZMODEL(dtweiduRes.getTZTPP0012().get(0).getZMODEL());
            }

            if (dtweiduRes.getTZTPP0013().size() > 0 ){
                dtweiduReturn.setZBANB(dtweiduRes.getTZTPP0013().get(0).getZBANB());
                dtweiduReturn.setWERKS(dtweiduRes.getTZTPP0013().get(0).getWERKS());
                dtweiduReturn.setMATNR(dtweiduRes.getTZTPP0013().get(0).getMATNR());
                dtweiduReturn.setZMODEL(dtweiduRes.getTZTPP0013().get(0).getZMODEL());
                dtweiduReturn.setZXHBAR(dtweiduRes.getTZTPP0013().get(0).getZXHBAR());
            }
        }

        return dtweiduReturn;


    }
}

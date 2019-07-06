package yj.core.webservice_createtp.components;

import yj.core.util.WebServerHelp;
import yj.core.webservice_createtp.dto.DTPARAM;
import yj.core.webservice_createtp.dto.DTRETURN;
import yj.core.webservice_createtp.receiver.DTCREATPRes;
import yj.core.webservice_createtp.sender.DTCREATPReq;
import yj.core.webservice_createtp.sender.SICREATPSenderSyn;
import yj.core.webservice_createtp.sender.SICREATPSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by 917110140 on 2018/9/20.
 */
public class CreateTpWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/CREATP/Sender", "SI_CREATP_Sender_SynService");
    public CreateTpWebserviceUtil(){

    }
    public DTRETURN receiveConfirmation(DTPARAM dtparam){
        URL wsdlURL = SICREATPSenderSynService.WSDL_LOCATION;
        SICREATPSenderSynService ss = new SICREATPSenderSynService(wsdlURL, SERVICE_NAME);
        SICREATPSenderSyn port = ss.getHTTPPort();
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

        DTCREATPReq.ITEM item = new DTCREATPReq.ITEM();
        item.setDATUM(dtparam.getDATUM());
        item.setMATNR(dtparam.getMATNR());
        item.setWERKS(dtparam.getWERKS());
        item.setMENGE(dtparam.getMENGE());
        item.setZBQBD(dtparam.getZBQBD());
        item.setZTPBAR(dtparam.getZTPBAR());
        item.setZMNUM(dtparam.getZMNUM());
        item.setZTXT2(dtparam.getZTXT2());

        DTCREATPReq dtcreatpReq = new DTCREATPReq();
        dtcreatpReq.setITEM(item);

        DTCREATPRes dtcreatpRes = port.siCREATPSenderSyn(dtcreatpReq);
        DTRETURN dtreturn = new DTRETURN();
        dtreturn.setMTYPE(dtcreatpRes.getRETURN().getZFLAG());
        dtreturn.setZMESSAGE(dtcreatpRes.getRETURN().getZMESSAGE());
        dtreturn.setZTPBAR(dtcreatpRes.getRETURN().getZTPBAR());

        return dtreturn;

    }
}

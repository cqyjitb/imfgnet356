package yj.core.webservicepd.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservicepd.dto.DTPANDIANParam;
import yj.core.webservicepd.dto.DTPANDIANRenturn;
import yj.core.webservicepd.receiver.DTPANDIANRes;
import yj.core.webservicepd.sender.DTPANDIANReq;
import yj.core.webservicepd.sender.SIPANDIANSenderSyn;
import yj.core.webservicepd.sender.SIPANDIANSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by 917110140 on 2018/7/25.
 */
@Component
public class ConfirmationWebservicePdUtil {

    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PANDIAN/Sender","SI_PANDIAN_Sender_SynService");
    public DTPANDIANRenturn receiveConfirmation(DTPANDIANParam param){
        URL wsdlURL = SIPANDIANSenderSynService.WSDL_LOCATION;
        SIPANDIANSenderSynService ss = new SIPANDIANSenderSynService(wsdlURL,SERVICE_NAME);
        SIPANDIANSenderSyn port = ss.getHTTPPort();
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

        DTPANDIANReq.ITEM item = new DTPANDIANReq.ITEM();
        item.setAUFNR(param.getAUFNR());
        item.setDATUM(param.getDATUM());
        item.setGAMNG(param.getGAMNG());
        item.setMATNR(param.getMATNR());
        item.setPDDAT(param.getPDDAT());
        item.setPDTIM(param.getPDTIM());
        item.setQRMNG(param.getQRMNG());
        item.setVORNR(param.getVORNR());
        item.setWERKS(param.getWERKS());
        item.setZCFWZ(param.getZCFWZ());
        item.setZLYLX(param.getZLYLX());
        item.setZPGDBAR(param.getZPGDBAR());
        item.setZPGDBH(param.getZPGDBH());
        DTPANDIANReq sendreq = new DTPANDIANReq();
        sendreq.getITEM().add(item);
        DTPANDIANRes returnrs = port.siPANDIANSenderSyn(sendreq);

        DTPANDIANRenturn rs = new DTPANDIANRenturn();
        rs.setMSGTY(returnrs.getRETURN().getMSGTY());
        rs.setMESSAGE(returnrs.getRETURN().getMESSAGE());
        return rs;
    }
}

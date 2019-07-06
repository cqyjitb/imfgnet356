package yj.core.webserver_readtp.components;

import yj.core.util.WebServerHelp;
import yj.core.webserver_readtp.receiver.DTREADTPRes;
import yj.core.webserver_readtp.sender.DTREADTPReq;
import yj.core.webserver_readtp.sender.SIREADTPSenderSyn;
import yj.core.webserver_readtp.sender.SIREADTPSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by 917110140 on 2018/10/6.
 */
public class ReadTpWebserviceUtil {

    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/READTP/Sender", "SI_READTP_Sender_SynService");
    public  ReadTpWebserviceUtil(){

    }

    public DTREADTPRes receiveConfirmation(DTREADTPReq req){
        URL wsdlURL = SIREADTPSenderSynService.WSDL_LOCATION;
        SIREADTPSenderSynService ss = new SIREADTPSenderSynService(wsdlURL, SERVICE_NAME);
        SIREADTPSenderSyn port = ss.getHTTPPort();
        Map<String,Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
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

        DTREADTPRes res = new DTREADTPRes();
        res = port.siREADTPSenderSyn(req);
        return res;

    }
}

package yj.core.webservice_xhcard.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_xhcard.dto.XhcardParameters;
import yj.core.webservice_xhcard.dto.XhcardReturnResult;
import yj.core.webservice_xhcard.receiver.DTXHCARDSENDXRES;
import yj.core.webservice_xhcard.sender.DTXHCARDSENDXREQ;
import yj.core.webservice_xhcard.sender.DTXHCARDSENDXREQ.ITEM;
import yj.core.webservice_xhcard.sender.SIXHCARDSENDXSYNC;
import yj.core.webservice_xhcard.sender.SIXHCARDSENDXSYNCService;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Map;

@Component
public class XhcardSyncWebserviceUtil
{
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/xhcardSender", "SI_XHCARD_SENDX_SYNCService");

    public XhcardReturnResult receiveConfirmation(XhcardParameters params) throws UnsupportedEncodingException {
        URL wsdlURL = SIXHCARDSENDXSYNCService.WSDL_LOCATION;
        SIXHCARDSENDXSYNCService ss = new SIXHCARDSENDXSYNCService(wsdlURL, SERVICE_NAME);

        SIXHCARDSENDXSYNC port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((BindingProvider)port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        //pro
//        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "HAPUSER");
//        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "YJhap201707@CQ");

        //dev
        reqCtxt.put(BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(BindingProvider.PASSWORD_PROPERTY, password);
//        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, "HAPUSER");
//        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, "Yjsap123@CQ");

        ITEM item = new ITEM();

        item.setWERKS(params.getWerks());
        item.setMATNR(params.getMatnr());
        item.setCHARG(params.getCharg());
        item.setZXHNUM(params.getZxhnum());
        item.setZXHZT(params.getZxhzt());
        item.setZXHZT2(params.getZxhzt2());
        item.setLGORT(params.getLgort());
        item.setMENGE(params.getMenge());
        item.setMEINS(params.getMeins());
        item.setZXHWZ(params.getZxhwz());
        item.setAUFNR(params.getAufnr());
        item.setZXHBAR(params.getZxhbar());
        item.setZJYY(params.getZjyy());
        item.setZSCBC(params.getZscbc());
        item.setZSCX(params.getZscx());
        item.setZMNUM(params.getZmnum());
        item.setZSCTPTM(params.getZsctptm());
        item.setZTXT(params.getZtxt());
        item.setZBQBD(params.getZbqbd());
        item.setCHARGKC(params.getChargkc());
        DTXHCARDSENDXREQ dtxhcardsendxreq = new DTXHCARDSENDXREQ();
        dtxhcardsendxreq.getITEM().add(item);

        DTXHCARDSENDXRES dtxhcardsendxres = port.siXHCARDSENDXSYNC(dtxhcardsendxreq);
        String msgTy = dtxhcardsendxres.getRETURN().getMSGTY();
        String msgId = dtxhcardsendxres.getRETURN().getMSGID();
        String message = dtxhcardsendxres.getRETURN().getMESSAGE();
        String msgV1 = dtxhcardsendxres.getRETURN().getMSGV1();
        String msgV2 = dtxhcardsendxres.getRETURN().getMSGV2();
        String msgV3 = dtxhcardsendxres.getRETURN().getMSGV3();
        String msgV4 = dtxhcardsendxres.getRETURN().getMSGV4();
        String msgNo = dtxhcardsendxres.getRETURN().getMSGNO();

        XhcardReturnResult result = new XhcardReturnResult();
        result.setMESSAGE(message);
        result.setMSGID(msgId);
        result.setMSGNO(msgNo);
        result.setMSGTY(msgTy);
        result.setMSGV1(msgV1);
        result.setMSGV2(msgV2);
        result.setMSGV3(msgV3);
        result.setMSGV4(msgV4);
        return result;
    }
}

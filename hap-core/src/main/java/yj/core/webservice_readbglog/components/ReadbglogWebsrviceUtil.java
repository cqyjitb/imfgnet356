package yj.core.webservice_readbglog.components;

import yj.core.util.WebServerHelp;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.webservice_readbglog.dto.ReadlogParam;
import yj.core.webservice_readbglog.receiver.DTREADBGLOGRes;
import yj.core.webservice_readbglog.sender.DTREADBGLOGReq;
import yj.core.webservice_readbglog.sender.SIREADBGLOGSenderSyn;
import yj.core.webservice_readbglog.sender.SIREADBGLOGSenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

public class ReadbglogWebsrviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/READBGLOG/Sender","SI_READBGLOG_Sender_SynService");
    public ReadbglogWebsrviceUtil(){

    }

    public DTBAOGONGReturnResult receiveConfirmation(ReadlogParam param){
        DTBAOGONGReturnResult rs = new DTBAOGONGReturnResult();
        URL wsdlURL = SIREADBGLOGSenderSynService.WSDL_LOCATION;
        SIREADBGLOGSenderSynService ss = new SIREADBGLOGSenderSynService(wsdlURL, SERVICE_NAME);
        SIREADBGLOGSenderSyn port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();

        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);

        DTREADBGLOGReq dtreadbglogReq = new DTREADBGLOGReq();
        DTREADBGLOGReq.IDATA idata = new DTREADBGLOGReq.IDATA();
        idata.setZBZ1("");
        idata.setZBZ2("");
        idata.setOPTION("");
        idata.setHIGH("");
        idata.setFILED("UUID");
        idata.setSIGN("");
        idata.setLOW(param.getUuid());
        dtreadbglogReq.setREVERSE(param.getReverse());
        dtreadbglogReq.getIDATA().add(idata);

        DTREADBGLOGRes res = port.siREADBGLOGSenderSyn(dtreadbglogReq);
        if (res.getRETURN().getMSGTY().equals("E")){
            rs.setMSGTY(res.getRETURN().getMSGTY());
            rs.setMESSAGE(res.getRETURN().getMESSAGE());

        }else{
            rs.setMSGTY(res.getRETURN().getMSGTY());
            rs.setMESSAGE(res.getRETURN().getMESSAGE());
            rs.setAUFNR(res.getDETAIL().get(0).getAUFNR());
            rs.setMATNR(res.getDETAIL().get(0).getMATNR());
            rs.setRSNUM(res.getDETAIL().get(0).getRSNUM());
            rs.setRSPOS(res.getDETAIL().get(0).getRSPOS());
            rs.setFEVOR(res.getDETAIL().get(0).getFEVOR());
            rs.setTXT(res.getDETAIL().get(0).getTXT());
            rs.setZTPBAR(res.getDETAIL().get(0).getZTPBAR());
            rs.setCHARG(res.getDETAIL().get(0).getCHARG());
            rs.setMBLNR(res.getDETAIL().get(0).getMBLNR());
            rs.setMJAHR(res.getDETAIL().get(0).getMJAHR());
        }
        return rs;
    }
}

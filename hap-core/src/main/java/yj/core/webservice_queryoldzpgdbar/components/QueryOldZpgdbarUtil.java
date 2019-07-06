package yj.core.webservice_queryoldzpgdbar.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryParm;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryReturn;
import yj.core.webservice_queryoldzpgdbar.receiver.DTQUERYOLDZPGDBARSendRes;
import yj.core.webservice_queryoldzpgdbar.sender.DTQUERYOLDZPGDBARSendReq;
import yj.core.webservice_queryoldzpgdbar.sender.SIQUERYOLDZPGDBARSenderSync;
import yj.core.webservice_queryoldzpgdbar.sender.SIQUERYOLDZPGDBARSenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

@Component
public class QueryOldZpgdbarUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/QueryOldZpgdbar/Sender","SI_QUERYOLDZPGDBAR_Sender_SyncService");
    public QueryOldZpgdbarUtil(){

    }

    public DtqueryReturn receiveConfirmation(DtqueryParm parm){
        DtqueryReturn re = new DtqueryReturn();
        URL wsdlURL = SIQUERYOLDZPGDBARSenderSyncService.WSDL_LOCATION;
        SIQUERYOLDZPGDBARSenderSyncService ss = new SIQUERYOLDZPGDBARSenderSyncService(wsdlURL,SERVICE_NAME);
        SIQUERYOLDZPGDBARSenderSync port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);

        DTQUERYOLDZPGDBARSendReq.ITEM item = new DTQUERYOLDZPGDBARSendReq.ITEM();
        item.setZPGDBAR(parm.getZpgdbar());
        DTQUERYOLDZPGDBARSendReq req = new DTQUERYOLDZPGDBARSendReq();
        req.setITEM(item);
        DTQUERYOLDZPGDBARSendRes dtqueryoldzpgdbarSendRes = port.siQUERYOLDZPGDBARSenderSync(req);

        re.setArbpl(dtqueryoldzpgdbarSendRes.getRETURN().getARBPL());
        re.setArbpldesc(dtqueryoldzpgdbarSendRes.getRETURN().getARBPLDESC());
        re.setMessage(dtqueryoldzpgdbarSendRes.getRETURN().getMESSAGE());
        re.setMsgty(dtqueryoldzpgdbarSendRes.getRETURN().getMSGTY());
        re.setMsgid(dtqueryoldzpgdbarSendRes.getRETURN().getMSGID());
        re.setMsgno(dtqueryoldzpgdbarSendRes.getRETURN().getMSGNO());
        re.setMsgv1(dtqueryoldzpgdbarSendRes.getRETURN().getMSGV1());
        re.setMsgv2(dtqueryoldzpgdbarSendRes.getRETURN().getMSGV2());
        re.setMsgv3(dtqueryoldzpgdbarSendRes.getRETURN().getMSGV3());
        re.setMsgv4(dtqueryoldzpgdbarSendRes.getRETURN().getMSGV4());
        re.setWerks(dtqueryoldzpgdbarSendRes.getRETURN().getWERKS());
        re.setMatnr(dtqueryoldzpgdbarSendRes.getRETURN().getMATNR());
        re.setMaktx(dtqueryoldzpgdbarSendRes.getRETURN().getMAKTX());
        return re;
    }
}

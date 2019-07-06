package yj.core.webservice_migo.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_migo.dto.DTMIGOParam;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.webservice_migo.receiver.DTMIGOSendRes;
import yj.core.webservice_migo.sender.DTMIGOSendReq;
import yj.core.webservice_migo.sender.SIMIGOSenderSync;
import yj.core.webservice_migo.sender.SIMIGOSenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

/**
 * Created by 917110140 on 2018/8/31.
 */
@Component
public class MigoWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/MIGO/Sender", "SI_MIGO_Sender_SyncService");
    public MigoWebserviceUtil() {
    }
    public DTMIGOReturn receiveConfirmation(DTMIGOParam param){
        URL wsdlURL = SIMIGOSenderSyncService.WSDL_LOCATION;
        SIMIGOSenderSyncService ss = new SIMIGOSenderSyncService(wsdlURL, SERVICE_NAME);
        SIMIGOSenderSync port = ss.getHTTPPort();
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

        DTMIGOSendReq.ITEM item = new DTMIGOSendReq.ITEM();
        item.setACTION(param.getACTION());
        item.setREFDOC(param.getREFDOC());
        item.setAUFNR(param.getAUFNR());
        item.setBKTXT(param.getBKTXT());
        item.setBLDAT(param.getBLDAT());
        item.setBUDAT(param.getBUDAT());
        item.setBWART(param.getBWART());
        item.setCHARG(param.getCHARG());
        item.setAUFNR("");
        item.setERFME(param.getERFME());
        item.setLGORT(param.getLGORT());
        item.setERFMG(param.getERFMG());
        item.setMATNR(param.getMATNR());
        item.setRSNUM(param.getRSNUM());
        item.setRSPOS(param.getRSPOS());
        item.setSJAHR(param.getSJAHR());
        item.setSMBLN(param.getSMBLN());
        item.setSMBLP(param.getSMBLP());
        item.setTAKEIT(param.getTAKEIT());
        item.setUMCHA(param.getUMCHA());
        item.setUMLGO(param.getUMLGO());
        item.setWERKS(param.getWERKS());

        DTMIGOSendReq dtmigoSendReq = new DTMIGOSendReq();
        dtmigoSendReq.getITEM().add(item);
        DTMIGOSendRes dtmigoSendRes = port.siMIGOSenderSync(dtmigoSendReq);
        DTMIGOReturn rs = new DTMIGOReturn();
        rs.setMBLNR(dtmigoSendRes.getMBLNR());
        rs.setMJAHR(dtmigoSendRes.getMJAHR());
        rs.setMTMSG(dtmigoSendRes.getMTMSG());
        rs.setMTYPE(dtmigoSendRes.getMTYPE());
        return rs;
    }
}

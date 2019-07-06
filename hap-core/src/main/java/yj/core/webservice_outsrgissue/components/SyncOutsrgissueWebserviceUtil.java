package yj.core.webservice_outsrgissue.components;

import yj.core.util.WebServerHelp;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEhead;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEitem;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEreturn;
import yj.core.webservice_outsrgissue.receiver.DTOUTSRGISSUERes;
import yj.core.webservice_outsrgissue.sender.DTOUTSRGISSUEReq;
import yj.core.webservice_outsrgissue.sender.SIOUTSRGISSUESenderSync;
import yj.core.webservice_outsrgissue.sender.SIOUTSRGISSUESenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

public class SyncOutsrgissueWebserviceUtil {

    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/OUTSRGISSUE/Sender", "SI_OUTSRGISSUE_Sender_SyncService");
    public SyncOutsrgissueWebserviceUtil(){

    }
    public DTOUTSRGISSUEreturn receiveConfirmation(DTOUTSRGISSUEhead head, DTOUTSRGISSUEitem item){
        URL wsdlURL = SIOUTSRGISSUESenderSyncService.WSDL_LOCATION;
        SIOUTSRGISSUESenderSyncService ss = new SIOUTSRGISSUESenderSyncService(wsdlURL, SERVICE_NAME);
        SIOUTSRGISSUESenderSync port = ss.getHTTPPort();
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

        DTOUTSRGISSUEReq.ITEM itemreq = new DTOUTSRGISSUEReq.ITEM();
        itemreq.setDIECD(item.getDiecd());
        itemreq.setEBELN(item.getEbeln());
        itemreq.setEBELP(item.getEbelp());
        itemreq.setETENR(item.getEtenr());
        itemreq.setGMEIN(item.getGmein());
        itemreq.setISSUENM(item.getIssuenm());
        itemreq.setITEM(item.getItem().toString());
        itemreq.setKTSCH(item.getKtsch());
        itemreq.setLIFNR(item.getLifnr());
        itemreq.setMATKL(item.getMatkl());
        itemreq.setMATNR(item.getMatnr());
        itemreq.setMENGE(item.getMenge());
        itemreq.setSFFLG(item.getSfflg());
        itemreq.setTXZ01(item.getTxz01());
        itemreq.setVORNR(item.getVornr());
        itemreq.setVSNDA(item.getVsnda());
        itemreq.setWERKS(item.getWerks());
        itemreq.setZISNUM(item.getZisnum());
        itemreq.setZPGDBAR(item.getZpgdbar());
        itemreq.setCHARG(item.getCharg());
        itemreq.setSTATUS(item.getStatus());
        itemreq.setZISDAT(item.getZisdat());
        itemreq.setZISTIM(item.getZistim());
        itemreq.setZISUSER(item.getZisuser());

        DTOUTSRGISSUEReq.HEAD headreq = new DTOUTSRGISSUEReq.HEAD();
        if (head != null){
            headreq.setISSUENM(head.getIssuenm());
            headreq.setLIFNR(head.getLifnr());
            headreq.setMATNR(head.getMatnr());
            headreq.setPRTFLAG(head.getPrtflag());
            headreq.setSTATUS(head.getStatus());
            headreq.setTXZ01(head.getTxz01());
            headreq.setWERKS(head.getWerks());
            headreq.setZIPDAT(head.getZipdat());
            headreq.setZIPTIM(head.getZiptim());
            headreq.setZIPUSER(head.getZipuser());
        }else{
            headreq.setISSUENM("");
            headreq.setLIFNR("");
            headreq.setMATNR("");
            headreq.setPRTFLAG("");
            headreq.setSTATUS("");
            headreq.setTXZ01("");
            headreq.setWERKS("");
            headreq.setZIPUSER("");
            headreq.setZIPTIM("");
            headreq.setZIPUSER("");
        }


        DTOUTSRGISSUEReq dtoutsrgissueReq = new DTOUTSRGISSUEReq();
        dtoutsrgissueReq.getITEM().add(itemreq);
        dtoutsrgissueReq.setHEAD(headreq);

        DTOUTSRGISSUERes dtoutsrgissueRes = port.siOUTSRGISSUESenderSync(dtoutsrgissueReq);
        DTOUTSRGISSUEreturn rs = new DTOUTSRGISSUEreturn();
            if (dtoutsrgissueRes.getRETURN().getMSGTY().equals("E")){
                rs.setMSGTY(dtoutsrgissueRes.getRETURN().getMSGTY());
                rs.setMESSAGE(dtoutsrgissueRes.getRETURN().getMESSAGE());
            }else{
                rs.setMSGTY(dtoutsrgissueRes.getRETURN().getMSGTY());
            }



        return rs;
    }
}

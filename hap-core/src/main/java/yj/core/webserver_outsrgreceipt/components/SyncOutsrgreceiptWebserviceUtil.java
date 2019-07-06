package yj.core.webserver_outsrgreceipt.components;

import yj.core.util.WebServerHelp;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTHead;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTReturn;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTitem;
import yj.core.webserver_outsrgreceipt.receiver.DTOUTSRGRECEIPTRes;
import yj.core.webserver_outsrgreceipt.sender.DTOUTSRGRECEIPTReq;
import yj.core.webserver_outsrgreceipt.sender.SIOUTSRGRECEIPTSenderSync;
import yj.core.webserver_outsrgreceipt.sender.SIOUTSRGRECEIPTSenderSyncService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

public class SyncOutsrgreceiptWebserviceUtil {
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender","SI_OUTSRGRECEIPT_Sender_SyncService");
    public SyncOutsrgreceiptWebserviceUtil(){

    }
    public DTOUTSRGRECEIPTReturn receiveConfirmation(DTOUTSRGRECEIPTHead head, DTOUTSRGRECEIPTitem item){
        URL wsdlURL = SIOUTSRGRECEIPTSenderSyncService.WSDL_LOCATION;
        SIOUTSRGRECEIPTSenderSyncService ss = new SIOUTSRGRECEIPTSenderSyncService(wsdlURL, SERVICE_NAME);
        SIOUTSRGRECEIPTSenderSync port = ss.getHTTPPort();
        Map<String,Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);

        DTOUTSRGRECEIPTReq.ITEM itemreq = new DTOUTSRGRECEIPTReq.ITEM();
        itemreq.setRECEIPTNM(item.getReceiptnm());
        itemreq.setITEM(item.getItem());
        itemreq.setWERKS(item.getWerks());
        itemreq.setZPGDBAR(item.getZpgdbar());
        itemreq.setISSUENM(item.getIssuenm());
        itemreq.setISSUENMITEM(item.getIssuenmitem());
        itemreq.setZTHNUM(item.getZthnum());
        itemreq.setZSHNUM(item.getZshnum());
        itemreq.setZLOST(item.getZlost());
        itemreq.setZLFNUM(item.getZlfnum());
        itemreq.setZGFNUM(item.getZgfnum());
        itemreq.setZEILE(item.getZeile());
        itemreq.setZDSTIM(item.getZdstim());
        itemreq.setZDSDAT(item.getZdsdat());
        itemreq.setVSNDA(item.getVsnda());
        itemreq.setVORNR(item.getVornr());
        itemreq.setTXZ01(item.getTxz01());
        itemreq.setTTRECEIPTS(item.getTtreceipts());
        itemreq.setSTATUS(item.getStatus());
        itemreq.setSFFLG(item.getSfflg());
        itemreq.setRUECK(item.getRueck());
        itemreq.setRMZHL(item.getRmzhl());
        itemreq.setMJAHR(item.getMjahr());
        itemreq.setMENGE(item.getMenge());
        itemreq.setMBLNR(item.getMblnr());
        itemreq.setMATNR(item.getMatnr());
        itemreq.setMATKL(item.getMatkl());
        itemreq.setLIFNR(item.getLifnr());
        itemreq.setKTSCH(item.getKtsch());
        itemreq.setGMEIN(item.getGmein());
        itemreq.setEBELP(item.getEbelp());
        itemreq.setEBELN(item.getEbeln());
        itemreq.setDIECD(item.getDiecd());
        itemreq.setDEDUCTNTENM(item.getDeductntenm());
        itemreq.setCHARG(item.getCharg());
        itemreq.setZDSUSER(item.getZdsuser());

        DTOUTSRGRECEIPTReq.HEAD headreq = new DTOUTSRGRECEIPTReq.HEAD();
        if (head != null){
            headreq.setZIPUSER(head.getZipuser());
            headreq.setZIPTIM(head.getZiptim());
            headreq.setZIPDAT(head.getZipdat());
            headreq.setZDPUSER(head.getZdpuser().toString());
            headreq.setZDPTIM(head.getZdptim());
            headreq.setZDPDAT(head.getZdpdat());
            headreq.setWERKS(head.getWerks());
            headreq.setSTATUS(head.getStatus());
            headreq.setRECEIPTNM(head.getReceiptnm());
            headreq.setPRTFLAG(head.getPrtflag());
            headreq.setMATNR(head.getMatnr());
            headreq.setLIFNR(head.getLifnr());
        }else{
            headreq.setZIPUSER("");
            headreq.setZIPTIM("");
            headreq.setZIPDAT("");
            headreq.setZDPUSER("");
            headreq.setZDPTIM("");
            headreq.setZDPDAT("");
            headreq.setWERKS("");
            headreq.setSTATUS("");
            headreq.setRECEIPTNM("");
            headreq.setPRTFLAG("");
            headreq.setMATNR("");
            headreq.setLIFNR("");
        }

        DTOUTSRGRECEIPTReq dtoutsrgreceiptReq = new DTOUTSRGRECEIPTReq();
        dtoutsrgreceiptReq.setHEAD(headreq);
        dtoutsrgreceiptReq.getITEM().add(itemreq);

        DTOUTSRGRECEIPTRes dtoutsrgreceiptRes = port.siOUTSRGRECEIPTSenderSync(dtoutsrgreceiptReq);
        DTOUTSRGRECEIPTReturn dtoutsrgreceiptReturn = new DTOUTSRGRECEIPTReturn();
        if (dtoutsrgreceiptRes.getRETURN().getMSGTY().equals("E")){
            dtoutsrgreceiptReturn.setMSGTY("E");
            dtoutsrgreceiptReturn.setMESSAGE(dtoutsrgreceiptRes.getRETURN().getMESSAGE());
        }else{
            dtoutsrgreceiptReturn.setMSGTY("S");
        }
        return dtoutsrgreceiptReturn;
    }
}

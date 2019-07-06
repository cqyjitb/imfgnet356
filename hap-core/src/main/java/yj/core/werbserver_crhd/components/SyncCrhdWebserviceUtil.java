package yj.core.werbserver_crhd.components;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.crhd.dto.Crhd;
import yj.core.crhd.mapper.CrhdMapper;
import yj.core.util.WebServerHelp;
import yj.core.werbserver_crhd.receiver.DTZZ002Res;
import yj.core.werbserver_crhd.sender.DTZZ002Req;
import yj.core.werbserver_crhd.sender.SIZZ002SenderSyn;
import yj.core.werbserver_crhd.sender.SIZZ002SenderSynService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class SyncCrhdWebserviceUtil {

    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/PP001/SAP_CRHD/Sender","SI_ZZ002_Sender_SynService");
    public String receiveConfirmation(){
        URL wsdlURL = SIZZ002SenderSynService.WSDL_LOCATION;
        SIZZ002SenderSynService ss = new SIZZ002SenderSynService();
        SIZZ002SenderSyn port = ss.getHTTPPort();
        Map<String, Object> reqCtxt = ((javax.xml.ws.BindingProvider) port).getRequestContext();
        WebServerHelp webServerHelp = new WebServerHelp();
        String username = webServerHelp.getUsername();
        String password = webServerHelp.getPassword();
        reqCtxt.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY, username);
        reqCtxt.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY, password);

        DTZZ002Req req = new DTZZ002Req();
        DTZZ002Req.IDATA idata = new DTZZ002Req.IDATA();
        idata.setFILED("");
        idata.setHIGH("");
        idata.setLOW("");
        idata.setOPTION("");
        idata.setSIGN("");
        idata.setZBZ1("");
        idata.setZBZ2("");
        req.getIDATA().add(idata);
        int num1 = 0;
        int num2 = 0;
        DTZZ002Res res = port.siZZ002SenderSyn(req);
        if (res.getEMESSAGE().getTYPE().equals("S")){
            List<Crhd> listcrhdIns = new ArrayList<>();
            List<Crhd> listcrhdUpd = new ArrayList<>();

            CrhdMapper crhdMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(CrhdMapper.class);
            for (int i = 0;i<res.getTRETURN().size();i++){
                Crhd crhd = new Crhd();
                int num = crhdMapper.selectnumByObjid(res.getTRETURN().get(i).getOBJID());
                crhd.setAedat(res.getTRETURN().get(i).getAEDATGRND());
                crhd.setArbpl(res.getTRETURN().get(i).getARBPL());
                crhd.setVeran(res.getTRETURN().get(i).getVERAN());
                crhd.setWerks(res.getTRETURN().get(i).getWERKS());
                crhd.setVerwe(res.getTRETURN().get(i).getVERWE());
                crhd.setLvorm(res.getTRETURN().get(i).getLVORM());
                crhd.setKetxt(res.getTRETURN().get(i).getKTEXT());
                crhd.setObjid(res.getTRETURN().get(i).getOBJID());
                if (num == 1){
                    crhd.setCreatedBy(10001L);
                    crhd.setLastUpdateDate(new Date());
                    listcrhdUpd.add(crhd);
                }else{
                    crhd.setCreatedBy(10001L);
                    crhd.setCreationDate(new Date());
                    listcrhdIns.add(crhd);
                }

            }
            if (listcrhdUpd.size() > 0){
                for (int i = 0;i<listcrhdUpd.size();i++){
                    num1 = num1 + crhdMapper.updateRow(listcrhdUpd.get(i));
                }


            }

            if (listcrhdIns.size() > 0){
                for (int i = 0;i<listcrhdIns.size();i++) {
                    num2 = num2 + crhdMapper.insertRow(listcrhdIns.get(i));
                }
            }

            if (num1 + num2 == res.getTRETURN().size()){
                return "S";
            }else{
                return "E";
            }

        }else{
            return res.getEMESSAGE().getMESSAGE();
        }


    }
}

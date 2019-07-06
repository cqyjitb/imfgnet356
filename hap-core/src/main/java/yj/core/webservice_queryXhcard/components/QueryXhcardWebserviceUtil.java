package yj.core.webservice_queryXhcard.components;

import org.springframework.stereotype.Component;
import yj.core.util.WebServerHelp;
import yj.core.webservice_queryXhcard.dto.QueryXhcardParam;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_queryXhcard.receiver.DTQUERYXHCARDRes;
import yj.core.webservice_queryXhcard.sender.DTQUERYXHCARDReq;
import yj.core.webservice_queryXhcard.sender.SIQUERYXHCARDSenderSyn;
import yj.core.webservice_queryXhcard.sender.SIQUERYXHCARDSenderSynService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.xhcard.service.impl.XhcardServiceImpl;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 917110140 on 2018/8/24.
 */
@Component
public class QueryXhcardWebserviceUtil {
//    @Autowired
//    private IXhcardService xhcardService;
    private static final QName SERVICE_NAME = new QName("http://www.cq-yj.com/HAP/queryXhcard/Sender", "SI_QUERYXHCARD_Sender_SynService");
    public QueryXhcardWebserviceUtil() {
    }
    public QueryXhcardReturnResult receiveConfirmation(QueryXhcardParam params) {
        URL wsdlURL = SIQUERYXHCARDSenderSynService.WSDL_LOCATION;
        SIQUERYXHCARDSenderSynService ss = new SIQUERYXHCARDSenderSynService(wsdlURL, SERVICE_NAME);
        SIQUERYXHCARDSenderSyn port = ss.getHTTPPort();
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

        DTQUERYXHCARDReq dtqueryxhcardReq = new DTQUERYXHCARDReq();
        QueryXhcardReturnResult rs = new QueryXhcardReturnResult();
        dtqueryxhcardReq.setZXHBAR(params.getZxhbar());
        dtqueryxhcardReq.setMATNR(params.getMatnr());
        dtqueryxhcardReq.setLGORT(params.getLgort());
        dtqueryxhcardReq.setQTYPE(params.getQtype());
        List<Xhcard> list = new ArrayList<>();
        DTQUERYXHCARDRes dtqueryxhcardRes = port.siQUERYXHCARDSenderSyn(dtqueryxhcardReq);
        if (dtqueryxhcardRes.getXHCARD().size() > 0){
            for (int i = 0; i < dtqueryxhcardRes.getXHCARD().size(); i++) {
                Xhcard xhcard = new Xhcard();
                xhcard.setAufnr(dtqueryxhcardRes.getXHCARD().get(i).getAUFNR());
                xhcard.setZxhnum(dtqueryxhcardRes.getXHCARD().get(i).getZXHNUM());
                xhcard.setMatnr(dtqueryxhcardRes.getXHCARD().get(i).getMATNR());
                xhcard.setWerks(dtqueryxhcardRes.getXHCARD().get(i).getWERKS());
                xhcard.setCharg(dtqueryxhcardRes.getXHCARD().get(i).getCHARG());
                xhcard.setChargkc(dtqueryxhcardRes.getXHCARD().get(i).getCHARGKC());
                xhcard.setLgort(dtqueryxhcardRes.getXHCARD().get(i).getLGORT());
                xhcard.setMeins(dtqueryxhcardRes.getXHCARD().get(i).getMEINS());
                double menge = dtqueryxhcardRes.getXHCARD().get(i).getMENGE();
                xhcard.setMenge(String.valueOf(menge));
                //xhcard.setMenge(dtqueryxhcardRes.getXHCARD().getMENGE());
                xhcard.setZbqbd(dtqueryxhcardRes.getXHCARD().get(i).getZBQBD());
                xhcard.setZjyy(dtqueryxhcardRes.getXHCARD().get(i).getZJYY());
                xhcard.setZmnum(dtqueryxhcardRes.getXHCARD().get(i).getZMNUM());
                xhcard.setZscbc(dtqueryxhcardRes.getXHCARD().get(i).getZSCBC());
                xhcard.setZsctptm(dtqueryxhcardRes.getXHCARD().get(i).getZSCTPTM());
                xhcard.setZscx(dtqueryxhcardRes.getXHCARD().get(i).getZSCX());
                xhcard.setZtxt(dtqueryxhcardRes.getXHCARD().get(i).getZTXT());
                xhcard.setZxhbar(dtqueryxhcardRes.getXHCARD().get(i).getZXHBAR());
                xhcard.setZxhwz(dtqueryxhcardRes.getXHCARD().get(i).getZXHWZ());
                xhcard.setZxhzt(dtqueryxhcardRes.getXHCARD().get(i).getZXHZT());
                xhcard.setZxhzt2(dtqueryxhcardRes.getXHCARD().get(i).getZXHZT2());
                list.add(xhcard);

            }
            rs.setMSGTY("S");
            rs.setMESSAGE("箱号已经同步更新！");
            IXhcardService xhcardService = new XhcardServiceImpl();
            xhcardService.updateXhcardFromSap(list);

        }else{
            rs.setMSGTY("S");
            rs.setMESSAGE("未查找到符合条件的箱号！");
        }
        return rs;
    }
}

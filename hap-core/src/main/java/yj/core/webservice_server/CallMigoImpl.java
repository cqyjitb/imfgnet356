package yj.core.webservice_server;

import yj.core.webservice_migo.components.MigoWebserviceUtil;
import yj.core.webservice_migo.dto.DTMIGOParam;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.webservice_server.dto.Rec_callMigo;
import yj.core.webservice_server.dto.Req_callMigo;

import javax.jws.WebService;

/**
 * Created by 917110140 on 2018/9/8.
 */
@WebService
//@WebService(endpointInterface="yj.core.webservice_server.ICallMigo", serviceName="CallMigoImpl")
public class CallMigoImpl implements ICallMigo{
    @Override
    public Rec_callMigo callMigo(Req_callMigo req_callMigo) {
        Rec_callMigo rec_callMigo = new Rec_callMigo();
        DTMIGOReturn dtmigoReturn = new DTMIGOReturn();
        DTMIGOParam param = new DTMIGOParam();
        param.setWERKS(req_callMigo.getWERKS());
        param.setERFMG(req_callMigo.getERFMG());
        param.setUMLGO(req_callMigo.getUMLGO());
        param.setACTION(req_callMigo.getACTION());
        param.setSJAHR(req_callMigo.getSJAHR());
        param.setRSNUM(req_callMigo.getRSNUM());
        param.setREFDOC(req_callMigo.getREFDOC());
        param.setMATNR(req_callMigo.getMATNR());
        param.setLGORT(req_callMigo.getLGORT());
        param.setCHARG(req_callMigo.getCHARG());
        param.setERFME(req_callMigo.getERFME());
        param.setBWART(req_callMigo.getBWART());
        param.setBUDAT(req_callMigo.getBUDAT());
        param.setBLDAT(req_callMigo.getBLDAT());
        param.setBKTXT(req_callMigo.getBKTXT());
        param.setAUFNR(req_callMigo.getAUFNR());
        param.setRSPOS(req_callMigo.getRSPOS());
        param.setSMBLN(req_callMigo.getSMBLN());
        param.setSMBLP(req_callMigo.getSMBLP());
        param.setTAKEIT(req_callMigo.getTAKEIT());
        param.setUMCHA(req_callMigo.getUMCHA());
        MigoWebserviceUtil migoWebserviceUtil = new MigoWebserviceUtil();
        dtmigoReturn = migoWebserviceUtil.receiveConfirmation(param);
        rec_callMigo.setMBLNR(dtmigoReturn.getMBLNR());
        rec_callMigo.setMJAHR(dtmigoReturn.getMJAHR());
        rec_callMigo.setMTMSG(dtmigoReturn.getMTMSG());
        rec_callMigo.setMTYPE(dtmigoReturn.getMTYPE());
        return rec_callMigo;
    }
}

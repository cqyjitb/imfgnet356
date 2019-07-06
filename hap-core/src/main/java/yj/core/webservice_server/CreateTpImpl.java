package yj.core.webservice_server;

import yj.core.webservice_createtp.components.CreateTpWebserviceUtil;
import yj.core.webservice_createtp.dto.DTPARAM;
import yj.core.webservice_createtp.dto.DTRETURN;

/**
 * Created by 917110140 on 2018/9/20.
 */
public class CreateTpImpl implements ICreateTp{

    @Override
    public DTRETURN createTp(DTPARAM dtparam) {
        DTRETURN dtreturn = new DTRETURN();
        CreateTpWebserviceUtil createTpWebserviceUtil = new CreateTpWebserviceUtil();
        dtreturn = createTpWebserviceUtil.receiveConfirmation(dtparam);
        return dtreturn;
    }
}

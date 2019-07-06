package yj.core.webservice_server;

import yj.core.webserver_weidu.components.WeiduWebserviceUtil;
import yj.core.webserver_weidu.dto.DTWEIDUParam;
import yj.core.webserver_weidu.dto.DTWEIDUReturn;

/**
 * Created by 917110140 on 2018/10/6.
 */
public class WeiduImpl implements IWeidu {
    @Override
    public DTWEIDUReturn callWeidu(DTWEIDUParam param) {
        DTWEIDUReturn dtweiduReturn = new DTWEIDUReturn();
        WeiduWebserviceUtil weiduWebserviceUtil = new WeiduWebserviceUtil();
        dtweiduReturn =  weiduWebserviceUtil.receiveConfirmation(param);
        return dtweiduReturn;
    }
}

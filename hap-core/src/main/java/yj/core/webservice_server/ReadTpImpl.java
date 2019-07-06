package yj.core.webservice_server;

import yj.core.webserver_readtp.components.ReadTpWebserviceUtil;
import yj.core.webserver_readtp.receiver.DTREADTPRes;
import yj.core.webserver_readtp.sender.DTREADTPReq;

/**
 * Created by 917110140 on 2018/10/6.
 */
public class ReadTpImpl implements IReadTp {
    @Override
    public DTREADTPRes ReadTp(DTREADTPReq parm) {
        ReadTpWebserviceUtil readTpWebserviceUtil = new ReadTpWebserviceUtil();
        DTREADTPRes res = new DTREADTPRes();
        res = readTpWebserviceUtil.receiveConfirmation(parm);
        return res;
    }
}

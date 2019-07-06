package yj.core.webservice_server;

import yj.core.webserver_readtp.receiver.DTREADTPRes;
import yj.core.webserver_readtp.sender.DTREADTPReq;

/**
 * Created by 917110140 on 2018/10/6.
 */
public interface IReadTp {
    DTREADTPRes ReadTp(DTREADTPReq parm);
}

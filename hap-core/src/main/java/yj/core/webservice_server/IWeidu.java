package yj.core.webservice_server;

import yj.core.webserver_weidu.dto.DTWEIDUParam;
import yj.core.webserver_weidu.dto.DTWEIDUReturn;

/**
 * Created by 917110140 on 2018/10/6.
 */
public interface IWeidu {
    DTWEIDUReturn callWeidu(DTWEIDUParam param);
}

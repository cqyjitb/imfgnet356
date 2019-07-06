package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_callMigo;
import yj.core.webservice_server.dto.Req_callMigo;

import javax.jws.WebService;

/**
 * Created by 917110140 on 2018/9/8.
 */
@WebService
public interface ICallMigo {
    Rec_callMigo callMigo(Req_callMigo req_callMigo);
}

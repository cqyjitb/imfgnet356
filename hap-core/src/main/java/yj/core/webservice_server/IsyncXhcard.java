package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_xhcard;
import yj.core.webservice_server.dto.ReturnMessage;

import javax.jws.WebService;

/**
 * Created by 917110140 on 2018/10/27.
 */
@WebService
public interface IsyncXhcard {
    public ReturnMessage syncXhcard(Rec_xhcard rec_xhcard);
}

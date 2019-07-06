package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_Outsrgrfe;
import yj.core.webservice_server.dto.ReturnMessage;

import javax.jws.WebService;

@WebService
public interface IsyncOutsrgrfe {
    ReturnMessage sync(Rec_Outsrgrfe recOutsrgrfe);
}

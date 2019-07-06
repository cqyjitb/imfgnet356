package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_Outsrgreceipthead;
import yj.core.webservice_server.dto.ReturnMessage;

import javax.jws.WebService;

@WebService
public interface IsyncOutsrgreceiptHead {
    ReturnMessage sync(Rec_Outsrgreceipthead rec_outsrgreceipthead);
}

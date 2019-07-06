package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_Outsrgissuehead;
import yj.core.webservice_server.dto.ReturnMessage;

import javax.jws.WebService;

@WebService
public interface IsyncOutsrgissueHead {
    ReturnMessage sync(Rec_Outsrgissuehead rec_outsrgissuehead);
}

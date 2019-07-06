package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_User;
import yj.core.webservice_server.dto.ReturnMessage;

import javax.jws.WebService;

@WebService
public interface IuserLogin {
    public ReturnMessage loginView(Rec_User rec_user);
}

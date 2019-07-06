package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_queryoutsrgrfe;
import yj.core.webservice_server.dto.Req_queryoutsrgrfe;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface IqueryOutsrgrfe {
    List<Rec_queryoutsrgrfe> QueryOutsrgrfe(Req_queryoutsrgrfe req);
}

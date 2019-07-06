package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_queryCardh;
import yj.core.webservice_server.dto.ReturnQueryCardh;

import javax.jws.WebService;

@WebService
public interface IqueryCardh {
    public ReturnQueryCardh queryCardh(Rec_queryCardh rec_queryCardh);
}

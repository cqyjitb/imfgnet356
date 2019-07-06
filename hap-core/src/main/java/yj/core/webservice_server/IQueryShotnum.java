package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_queryShotnum;

import javax.jws.WebService;

@WebService
public interface IQueryShotnum {
     String QueryShotnum(Rec_queryShotnum rec);
}

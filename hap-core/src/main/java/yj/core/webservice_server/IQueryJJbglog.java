package yj.core.webservice_server;

import yj.core.webservice_server.dto.QueryLogParam;
import yj.core.webservice_server.dto.ReturnQueryjjbg;

import javax.jws.WebService;

@WebService
public interface IQueryJJbglog {
    ReturnQueryjjbg querybglog(QueryLogParam param);
}

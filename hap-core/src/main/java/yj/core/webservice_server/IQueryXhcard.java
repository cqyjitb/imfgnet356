package yj.core.webservice_server;

import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_server.dto.Rec_queryXhcard;

import javax.jws.WebService;

/**
 * Created by 917110140 on 2018/8/31.
 */
@WebService
public interface IQueryXhcard {
    QueryXhcardReturnResult QueryXhcard(Rec_queryXhcard rec_queryXhcard);

}

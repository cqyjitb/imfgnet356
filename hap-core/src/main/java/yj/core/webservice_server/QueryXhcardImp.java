package yj.core.webservice_server;

import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_server.dto.Rec_queryXhcard;

import javax.jws.WebService;


/**
 * Created by 917110140 on 2018/8/31.
 */
@WebService
//@WebService(endpointInterface="yj.core.webservice_server.IQueryXhcard", serviceName="QueryXhcardImp")
public class QueryXhcardImp implements IQueryXhcard{
    @Override
    public QueryXhcardReturnResult QueryXhcard(Rec_queryXhcard rec_queryXhcard) {
        QueryXhcardReturnResult rs = new QueryXhcardReturnResult();
//        QueryXhcardParam param = new QueryXhcardParam();
//        param.setZxhbar(rec_queryXhcard.getZxhbar());
//        param.setLgort(rec_queryXhcard.getLgort());
//        param.setMatnr(rec_queryXhcard.getMatnr());
//        param.setQtype(rec_queryXhcard.getQtype());
//        QueryXhcardWebserviceUtil queryXhcardWebserviceUtil = new QueryXhcardWebserviceUtil();
//        rs = queryXhcardWebserviceUtil.receiveConfirmation(param);
        rs.setMSGTY("S");
        return rs;
    }
}

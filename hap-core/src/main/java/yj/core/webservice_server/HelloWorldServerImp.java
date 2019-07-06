package yj.core.webservice_server;

import org.springframework.stereotype.Component;
import yj.core.webservice_queryXhcard.dto.QueryXhcardParam;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;

/**
 * Created by 917110140 on 2018/9/6.
 */
@Component
public class HelloWorldServerImp implements IHelloWorldServer {
//    @Autowired
//    QueryXhcardWebserviceUtil queryXhcardWebserviceUtil;
    @Override
    public String sayHello(String matnr, String lgort, String qtype, String zxhbar) {
        QueryXhcardReturnResult rs = new QueryXhcardReturnResult();
        QueryXhcardParam param = new QueryXhcardParam();
        param.setZxhbar(zxhbar);
        param.setLgort(lgort);
        param.setMatnr(matnr);
        param.setQtype(qtype);
//        QueryXhcardWebserviceUtil queryXhcardWebserviceUtil = new QueryXhcardWebserviceUtil();
//        rs = queryXhcardWebserviceUtil.receiveConfirmation(param);
//        if (rs.getMSGTY().equals("S")){
//            return "S";
//        }else{
//            return "E";
//        }
        return "S";
    }
}

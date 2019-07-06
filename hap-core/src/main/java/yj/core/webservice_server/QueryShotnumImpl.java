package yj.core.webservice_server;


import net.sf.json.JSONArray;
import org.apache.cxf.jaxws.context.WebServiceContextImpl;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.webservice_server.dto.Rec_queryShotnum;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.service.IShotnumService;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="yj.core.webservice_server.IQueryShotnum", serviceName="QueryShotnum")
public class QueryShotnumImpl implements IQueryShotnum{
    @Autowired
    private IShotnumService shotnumService;
    @Override
    public  String QueryShotnum(Rec_queryShotnum rec) {
        Shotnum shotnum = new Shotnum();
        shotnum.setWerks(rec.getWerks());
        shotnum.setFevor(rec.getFevor());
        shotnum.setPrdDateAfter(rec.getPrdDateAfter());
        shotnum.setPrdDateBefore(rec.getPrdDateBefore());
        WebServiceContext context = new WebServiceContextImpl();
        MessageContext ctx = context.getMessageContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);

        List<Shotnum> list = new ArrayList<>();

        list = shotnumService.selectShotnum(shotnum,null);
//        for (int i=0;i<list.size();i++){
//            try {
//                String ktext = new String(list.get(i).getKtext().getBytes("iso-8859-1"),"UTF-8");
//                String matkx = new String(list.get(i).getMaktx().getBytes("iso-8859-1"),"UTF-8");
//                String shifs = new String(list.get(i).getShifts().getBytes("iso-8859-1"),"UTF-8");
//                list.get(i).setKtext(ktext);
//                list.get(i).setMaktx(matkx);
//                list.get(i).setShifts(shifs);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//        }

        JSONArray jsonArray = JSONArray.fromObject(list);
        String str = jsonArray.toString();
        return str;
    }
}

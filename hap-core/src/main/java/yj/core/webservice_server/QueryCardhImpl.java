package yj.core.webservice_server;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.webservice_server.dto.Rec_queryCardh;
import yj.core.webservice_server.dto.ReturnQueryCardh;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="yj.core.webservice_server.IqueryCardh", serviceName="queryCardh")
public class QueryCardhImpl implements IqueryCardh {
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private ICardtService cardtService;
    @Autowired
    private ICardhService cardhService;
    @Override
    public ReturnQueryCardh queryCardh(Rec_queryCardh rec_queryCardh) {
        String werks = rec_queryCardh.getWerks().equals("")?null:rec_queryCardh.getWerks();
        String matnr = rec_queryCardh.getMatnr().equals("")?null:rec_queryCardh.getMatnr();
        String auart = rec_queryCardh.getAuart().equals("")?null:rec_queryCardh.getAuart();
        String aufnr = rec_queryCardh.getAufnr().equals("")?null:rec_queryCardh.getAufnr();
        String zxhbar = rec_queryCardh.getZxhbar().equals("")?null:rec_queryCardh.getZxhbar();
        String status = rec_queryCardh.getStatus().equals("")?null:rec_queryCardh.getStatus();
        String zdybs = rec_queryCardh.getZdybs().equals("")?null:rec_queryCardh.getZdybs();
        String zpgdbar = rec_queryCardh.getZpgdbar().equals("")?null:rec_queryCardh.getZpgdbar();
        ReturnQueryCardh rs = new ReturnQueryCardh();
        List<Cardh> list = new ArrayList<>();
        List<Cardt> list2 = new ArrayList<>();
        JSONArray cardhjsa = new JSONArray();
        JSONArray cardtjsa = new JSONArray();
        String cardhjstr = "";
        String cardtjstr = "";
        Cardh dto = new Cardh();
        if (zxhbar!= null && !zxhbar.equals("")){
            Xhcard xhcard = new Xhcard();
            xhcard = xhcardService.selectByBacode(zxhbar);
            if (xhcard != null){
                dto.setAufnr(xhcard.getAufnr());
                dto.setMatnr(xhcard.getMatnr());
                dto.setZxhnum(xhcard.getZxhnum());
                dto.setZdybs(zdybs);
                dto.setStatus(status);
                list = cardhService.queryAfterSortForClientPrint(dto);
                if (list.size() > 0){
                    cardhjsa = JSONArray.fromObject(list);//
                    cardhjstr = cardhjsa.toString();
                    cardtjsa = JSONArray.fromObject(list2);
                    cardtjstr = cardtjsa.toString();
                    rs.setCardh(cardhjsa);
                    rs.setCardt(cardtjsa);
                    rs.setFlag("S");
                }else{
                    rs.setFlag("E");
                    rs.setMessage("没有符合条件的数据记录！");
                    return rs;
                }
            }else{

                rs.setFlag("E");
                rs.setMessage("箱号不存在");
                return rs;
            }
        }else{
            dto.setWerks(werks);
            dto.setMatnr(matnr);
            dto.setAuart(auart);
            dto.setAufnr(aufnr);
            dto.setStatus(status);
            dto.setZdybs(zdybs);
            dto.setZpgdbar(zpgdbar);
            list = cardhService.queryAfterSortForClientPrint(dto);
            if (list.size() > 0){
                cardhjsa = JSONArray.fromObject(list);//
                cardhjstr = cardhjsa.toString();
                cardtjsa = JSONArray.fromObject(list2);
                cardtjstr = cardtjsa.toString();
                rs.setCardh(cardhjsa);
                rs.setCardt(cardtjsa);
                rs.setFlag("S");
            }else{
                rs.setFlag("E");
                rs.setMessage("没有符合条件的数据记录！");
                return rs;
            }
        }
        return rs;
    }
}

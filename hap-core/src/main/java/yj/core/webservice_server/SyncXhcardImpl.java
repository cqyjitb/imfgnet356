package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.webservice_server.dto.Rec_xhcard;
import yj.core.webservice_server.dto.ReturnMessage;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.mapper.XhcardMapper;

import java.util.Date;

/**
 * Created by 917110140 on 2018/10/27.
 */
public class SyncXhcardImpl implements IsyncXhcard {

    @Override
    public ReturnMessage syncXhcard(Rec_xhcard rec_xhcard) {
        XhcardMapper xhcardMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(XhcardMapper.class);
        ReturnMessage rt = new ReturnMessage();
        Xhcard xhcard = new Xhcard();
        int sum = 0;
        if (rec_xhcard != null){
            xhcard.setWerks(rec_xhcard.getWerks());
            xhcard.setMatnr(rec_xhcard.getMatnr());
            xhcard.setCharg(rec_xhcard.getCharg());
            xhcard.setZxhnum(rec_xhcard.getZxhnum());
            xhcard.setZxhzt(rec_xhcard.getZxhzt());
            xhcard.setZxhzt2(rec_xhcard.getZxhzt2());
            xhcard.setLgort(rec_xhcard.getLgort());
            xhcard.setMenge(rec_xhcard.getMenge());
            xhcard.setMeins(rec_xhcard.getMeins());
            xhcard.setZxhwz(rec_xhcard.getZxhwz());
            xhcard.setAufnr(rec_xhcard.getAufnr());
            xhcard.setZxhbar(rec_xhcard.getZxhbar());
            xhcard.setZjyy(rec_xhcard.getZjyy());
            xhcard.setZscbc(rec_xhcard.getZscbc());
            xhcard.setZscx(rec_xhcard.getZscx());
            xhcard.setZmnum(rec_xhcard.getZmnum());
            xhcard.setZsctptm(rec_xhcard.getZsctptm());
            xhcard.setZtxt(rec_xhcard.getZtxt());
            xhcard.setZbqbd(rec_xhcard.getZbqbd());
            xhcard.setChargkc(rec_xhcard.getChargkc());
            Xhcard xhcardtmp = new Xhcard();
            String zxhbar = rec_xhcard.getZxhbar();
            xhcardtmp = xhcardMapper.selectByBacode(zxhbar);
            if (xhcardtmp != null){
                 xhcard.setCreationDate(xhcardtmp.getCreationDate());
                 xhcard.setCreatedBy(xhcardtmp.getCreatedBy());
                 xhcard.setLastUpdatedDate(new Date());
                 xhcard.setLastUpdatedBy(10001L);
                 sum = xhcardMapper.updateXhcard(xhcard);

            }else{

                 xhcard.setCreatedBy(10001L);
                 xhcard.setCreationDate(new Date());
                 sum = xhcardMapper.insertXhcard(xhcard);
            }
            if (sum == 0){
                rt.setFlag("E");
                rt.setMessage(xhcard.getZxhbar());
                return rt;
            }else if(sum == 1){
                rt.setFlag("S");
                rt.setMessage(xhcard.getZxhbar());
                return  rt;
            }
        }else{
            rt.setFlag("E");
            rt.setMessage("");
            return rt;
        }
        return  rt;
    }
}

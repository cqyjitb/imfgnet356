package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.mapper.OutsrgrfeMapper;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webservice_server.dto.Rec_queryoutsrgrfe;
import yj.core.webservice_server.dto.Req_queryoutsrgrfe;

import java.util.ArrayList;
import java.util.List;

public class QueryOutsrgrfeImpl implements IqueryOutsrgrfe {
    @Autowired
    private IOutsrgrfeService outsrgrfeService;
    @Override
    public List<Rec_queryoutsrgrfe> QueryOutsrgrfe(Req_queryoutsrgrfe req) {
        String werks = req.getWerks();
        String matnr = req.getMatnr();
        String lifnr = req.getLifnr();
        String ktsch = req.getKtsch();
        String matnr_l = req.getMatnr_l();
        String matnr_h = req.getMatnr_h();
        String lifnr_l = req.getLifnr_l();
        String lifnr_h = req.getLifnr_h();
        String ktsch_l = req.getKtsch_l();
        String ktsch_h = req.getKtsch_h();

        if (matnr.equals("")){
            matnr = null;
        }

        if (matnr_h.equals("")){
            matnr_h = null;
        }

        if (matnr_l.equals("")){
            matnr_l = null;
        }

        if (lifnr.equals("")){
            lifnr = null;
        }

        if (lifnr_l.equals("")){
            lifnr_l = null;
        }

        if (lifnr_h.equals("")){
            lifnr_h = null;
        }

        if  (ktsch.equals("")){
            ktsch = null;
        }

        if (ktsch_l.equals("")){
            ktsch_l = null;
        }

        if (ktsch_h.equals("")){
            ktsch_h = null;
        }

        List<Outsrgrfe> list = new ArrayList<>();
        List<Rec_queryoutsrgrfe> list2= new ArrayList<>();
        OutsrgrfeMapper outsrgrfeMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(OutsrgrfeMapper.class);
        list = outsrgrfeMapper.sapquery(werks,matnr,matnr_l,matnr_h,lifnr,lifnr_l,lifnr_h,ktsch,ktsch_l,ktsch_h);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Rec_queryoutsrgrfe rec = new Rec_queryoutsrgrfe();
                rec.setKtsch(list.get(i).getKtsch());
                rec.setLifnr(list.get(i).getLifnr());
                rec.setMatnr(list.get(i).getMatnr());
                rec.setWerks(list.get(i).getWerks());
                list2.add(rec);
            }
        }
        return list2;
    }
}

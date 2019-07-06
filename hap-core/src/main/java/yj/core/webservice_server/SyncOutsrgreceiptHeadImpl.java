package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;
import yj.core.outsrgreceipthead.mapper.OutsrgreceiptheadMapper;
import yj.core.outsrgreceipthead.service.IOutsrgreceiptheadService;
import yj.core.webservice_server.dto.Rec_Outsrgreceipthead;
import yj.core.webservice_server.dto.ReturnMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SyncOutsrgreceiptHeadImpl implements IsyncOutsrgreceiptHead {
    @Autowired
    private IOutsrgreceiptheadService outsrgreceiptheadService;
    @Override
    public ReturnMessage sync(Rec_Outsrgreceipthead rec_outsrgreceipthead) {
        ReturnMessage rs = new ReturnMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
        if (rec_outsrgreceipthead.getReceiptnm() == null){
            rs.setFlag("E");
            rs.setMessage("同步数据有误！");
        }

        Outsrgreceipthead outsrgreceipthead = new Outsrgreceipthead();
        outsrgreceipthead.setReceiptnm(rec_outsrgreceipthead.getReceiptnm());
        outsrgreceipthead.setZiptim(rec_outsrgreceipthead.getZiptim());
        outsrgreceipthead.setZipdat(rec_outsrgreceipthead.getZipdat());
        outsrgreceipthead.setZipuser(rec_outsrgreceipthead.getZipuser());
        outsrgreceipthead.setLifnr(rec_outsrgreceipthead.getLifnr());
        outsrgreceipthead.setMatnr(rec_outsrgreceipthead.getMatnr());
        outsrgreceipthead.setPrtflag(rec_outsrgreceipthead.getPrtflag());
        outsrgreceipthead.setStatus(rec_outsrgreceipthead.getStatus());
        outsrgreceipthead.setWerks(rec_outsrgreceipthead.getWerks());
        outsrgreceipthead.setLastUpdateDate(new Date());
        outsrgreceipthead.setLastUpdatedBy(10001L);
        OutsrgreceiptheadMapper outsrgreceiptheadMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(OutsrgreceiptheadMapper.class);
        int num = outsrgreceiptheadMapper.updateOutsrgreceipthead(outsrgreceipthead);
        if (num == 1){
            rs.setMessage("同步成功！");
            rs.setFlag("S");
            rs.setSyncdate(sdf.format(new Date()));
        }else{
            rs.setMessage("同步失败！");
            rs.setFlag("E");
            rs.setSyncdate(sdf.format(new Date()));
        }
        return rs;
    }
}

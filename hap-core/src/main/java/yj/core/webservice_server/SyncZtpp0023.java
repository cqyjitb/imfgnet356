package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.webservice_server.dto.ReZtpp0023;
import yj.core.webservice_server.dto.Rec_ztpp0023;
import yj.core.ztpp0023.dto.Ztpp0023;
import yj.core.ztpp0023.service.IZtpp0023Service;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 917110140 on 2018/7/19.
 */
@WebService(endpointInterface="yj.core.webservice_server.IsyncZtpp0023", serviceName="SyncZtpp0023")
public class SyncZtpp0023 implements IsyncZtpp0023{
    @Autowired
    private IZtpp0023Service ztpp0023Service;
    @Override
    public ReZtpp0023 syncZtpp0023(List<Rec_ztpp0023> parmList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ReZtpp0023 rs = new ReZtpp0023();
        int sum = 0;
        if (parmList.size() > 0){
            for (int i = 0;i<parmList.size();i++){
                Ztpp0023 item =   new Ztpp0023();
                item.setMenge(parmList.get(i).getMenge());
                item.setQaufnr(parmList.get(i).getQaufnr());
                item.setQdauat(parmList.get(i).getQdauat());
                item.setQdwerk(parmList.get(i).getQdwerk());
                item.setQfevor(parmList.get(i).getQfevor());
                item.setQgamng(parmList.get(i).getQgamng());
                item.setQmatnr(parmList.get(i).getQmatnr());
                item.setYaufnr(parmList.get(i).getYaufnr());
                item.setYdauat(parmList.get(i).getYdauat());
                item.setYfevor(parmList.get(i).getYfevor());
                item.setYgamng(parmList.get(i).getYgamng());
                item.setYmatnr(parmList.get(i).getYmatnr());
                String gltrp = df.format(parmList.get(i).getYgltrp()).substring(0,8);
                String gstrp = df.format(parmList.get(i).getYgstrp()).substring(0,8);
                item.setYgltrp(gltrp);
                item.setYgstrp(gstrp);
                item.setYverid(parmList.get(i).getYverid());
                sum = sum + ztpp0023Service.insertZtpp0023(item);

            }
            if (sum == parmList.size()){
                rs.setFlag("S");
                rs.setSyncdate(df.format(new Date()));
            }
        }
        return  rs;
    }
}

package yj.core.webservice_server;

import yj.core.webservice_server.dto.ReZtpp0023;
import yj.core.webservice_server.dto.Rec_ztpp0023;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by 917110140 on 2018/7/19.
 */
@WebService
public interface IsyncZtpp0023 {
    ReZtpp0023 syncZtpp0023(List<Rec_ztpp0023> parmList);
}

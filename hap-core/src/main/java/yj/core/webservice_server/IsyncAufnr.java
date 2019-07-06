package yj.core.webservice_server;

import yj.core.webservice_server.dto.*;

import javax.jws.WebService;
import java.util.List;

@WebService
public  interface IsyncAufnr
{
     ReturnMessage sync(List<Rec_afko> paramList, List<Rec_afvc> paramList1, List<Rec_marc> rec_marc, List<Rec_t435t> rec_t435ts, List<Rec_resb> rec_resbs);
}

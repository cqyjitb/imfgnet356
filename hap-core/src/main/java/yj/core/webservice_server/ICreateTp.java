package yj.core.webservice_server;

import yj.core.webservice_createtp.dto.DTPARAM;
import yj.core.webservice_createtp.dto.DTRETURN;

import javax.jws.WebService;

/**
 * Created by 917110140 on 2018/9/20.
 */
@WebService
public interface ICreateTp {
    DTRETURN createTp(DTPARAM dtparam);
}

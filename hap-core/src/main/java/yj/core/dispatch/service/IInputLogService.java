package yj.core.dispatch.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.appidconf.dto.Appidconf;
import yj.core.cardh.dto.Cardh;
import yj.core.cardt.dto.Cardt;
import yj.core.dispatch.dto.InputLog;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import java.util.List;

public interface IInputLogService extends IBaseService<InputLog>, ProxySelf<IInputLogService> {

    List<InputLog> queryAllWriteOff(IRequest iRequest, InputLog inputLog, int page, int pageSize);//报工冲销页面数据查询

    List<InputLog> queryAllLog(IRequest iRequest, InputLog inputLog, int page, int pageSize);//报工日志界面数据查询

    List<InputLog> queryAllResult(IRequest iRequest, InputLog inputLog, int page, int pageSize);//报工结果

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

    List<InputLog> queryFirstResult(IRequest iRequest, String dispatch, String operation);

    DTPP001ReturnResult inputDispatch(InputLog input);

    void nextId(InputLog input);

    DTPP001ReturnResult writeOffDispatch(InputLog input);

    DTBAOGONGReturnResult writeOffDispatchNew(InputLog input);

    InputLog selectConfirmationSuccess(InputLog inputLog);

    DTBAOGONGReturnResult inputDispatchNew(InputLog input, Cardh cardh, Cardt cardt, Appidconf appidconf, String isfirst);

    DTBAOGONGReturnResult inputDispatchNewWX(InputLog input, Cardh cardh, Cardt cardt, String isfirst);

    List<InputLog> queryBeforeResult(IRequest iRequest, String dispatch, String operation);

    Long selectNextId();

    String queryDispatchMaxOperation(String dispatch);

    InputLog queryByDispatchAndOperation(InputLog inputLog);

    InputLog querySumInputlogForShotnum(String werks, String matnr, String arbpl, String attr6, String attr4);

}
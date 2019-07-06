package yj.core.log.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.log.dto.Logheader;
import yj.core.log.service.ILogheaderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class LogheaderController extends BaseController {

    @Autowired
    private ILogheaderService service;


    @RequestMapping(value = "/wip/logheader/query")
    @ResponseBody
    public ResponseData query(Logheader dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/logheader/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Logheader> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/logheader/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Logheader> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/logheader/createHeader"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData createHeader(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String uuid = request.getParameter("uuid");
        String userid = request.getParameter("userid");
        String appid = request.getParameter("appid");
        Logheader logheader = new Logheader();
        logheader.setLogid(uuid);
        logheader.setAttr1(appid);
        logheader.setCreatedBy(Long.parseLong(userid));
        logheader.setCreationDate(new Date());
        int num = 0;
        num = service.insertNewHeader(logheader);
        if (num == 1){
            rs.setMessage("创建日志表头成功！");
            rs.setSuccess(true);
        }else{
            rs.setMessage("创建日志表头失败！");
            rs.setSuccess(false);
        }
        return rs;
    }
}
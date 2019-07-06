package yj.core.appidconf.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.appidconf.dto.Appidconf;
import yj.core.appidconf.service.IAppidconfService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppidconfController extends BaseController {

@Autowired
private IAppidconfService service;


@RequestMapping(value = "/sap/appidconf/query")
@ResponseBody
public ResponseData query(Appidconf dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/appidconf/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Appidconf> dto){
    IRequest requestCtx = createRequestContext(request);
    ResponseData rs = new ResponseData();
    String msg = service.updateOrInsert(dto);
    rs.setMessage(msg);
    return rs;
}

@RequestMapping(value = "/sap/appidconf/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Appidconf> dto){
    service.batchDelete(dto);
    return new ResponseData();
}


@RequestMapping(value = {"/sap/appidconf/queryByAppid"}, method = {RequestMethod.GET})
@ResponseBody
public ResponseData queryByAppid(HttpServletRequest request){
    String appid = request.getParameter("appid");
    List<Appidconf> list = new ArrayList<>();
    Appidconf appidconf = new Appidconf();
    appidconf = service.selectByAppid(appid);
    list.add(appidconf);
    ResponseData rs = new ResponseData(list);
    return rs;
}


}

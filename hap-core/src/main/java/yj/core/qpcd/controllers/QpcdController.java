package yj.core.qpcd.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.qpcd.dto.Qpcd;
import yj.core.qpcd.service.IQpcdService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QpcdController extends BaseController {

@Autowired
private IQpcdService service;


@RequestMapping(value = "/sap/qpcd/query")
@ResponseBody
public ResponseData query(Qpcd dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/qpcd/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Qpcd> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/qpcd/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Qpcd> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    @RequestMapping(value = {"/sap/qpcd/selectAll"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectAll(HttpServletRequest request){
        List<Qpcd> list = service.selectAllForBlcl();
        return  new ResponseData(list);
    }

    @RequestMapping(value = {"/sap/qpcd/selectAllForJjqj"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectAllForJjqj(HttpServletRequest request){
        List<Qpcd> list = service.selectAllForJjqj();
        return  new ResponseData(list);
    }
}
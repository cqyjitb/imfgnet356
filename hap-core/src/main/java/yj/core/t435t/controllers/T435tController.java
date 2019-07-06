package yj.core.t435t.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.t435t.dto.T435t;
import yj.core.t435t.service.IT435tService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class T435tController extends BaseController {

@Autowired
private IT435tService service;


@RequestMapping(value = "/sap/t435t/query")
@ResponseBody
public ResponseData query(T435t dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/t435t/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<T435t> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/t435t/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<T435t> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
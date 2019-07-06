package yj.core.stpo.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.stpo.dto.Stpo;
import yj.core.stpo.service.IStpoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StpoController extends BaseController {

@Autowired
private IStpoService service;


@RequestMapping(value = "/sap/stpo/query")
@ResponseBody
public ResponseData query(Stpo dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/stpo/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Stpo> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/stpo/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Stpo> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
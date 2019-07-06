package yj.core.plpo.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.plpo.dto.Plpo;
import yj.core.plpo.service.IPlpoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PlpoController extends BaseController {

@Autowired
private IPlpoService service;


@RequestMapping(value = "/sap/plpo/query")
@ResponseBody
public ResponseData query(Plpo dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/plpo/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Plpo> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/plpo/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Plpo> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
package yj.core.cust.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.cust.dto.Cust;
import yj.core.cust.service.ICustService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustController extends BaseController {

@Autowired
private ICustService service;


@RequestMapping(value = "/sap/cust/query")
@ResponseBody
public ResponseData query(Cust dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/cust/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Cust> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/cust/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Cust> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
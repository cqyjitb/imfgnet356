package yj.core.cardhlock.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.cardhlock.dto.Cardhlock;
import yj.core.cardhlock.service.ICardhlockService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CardhlockController extends BaseController {

@Autowired
private ICardhlockService service;


@RequestMapping(value = "/sap/cardhlock/query")
@ResponseBody
public ResponseData query(Cardhlock dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/cardhlock/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Cardhlock> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/cardhlock/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Cardhlock> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
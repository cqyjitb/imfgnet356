package yj.core.wiptrasfer.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wiptrasfer.dto.Trasfer;
import yj.core.wiptrasfer.service.ITrasferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrasferController extends BaseController {

@Autowired
private ITrasferService service;


@RequestMapping(value = "/wip/trasfer/query")
@ResponseBody
public ResponseData query(Trasfer dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/trasfer/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Trasfer> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/trasfer/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Trasfer> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
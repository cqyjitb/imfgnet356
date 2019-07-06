package yj.core.batchpds.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.batchpds.dto.Batchpdlogs;
import yj.core.batchpds.service.IBatchpdlogsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BatchpdlogsController extends BaseController {

@Autowired
private IBatchpdlogsService service;


@RequestMapping(value = "/sap/batchpdlogs/queryAll")
@ResponseBody
public ResponseData query(Batchpdlogs dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.queryAll(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/batchpdlogs/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Batchpdlogs> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/batchpdlogs/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Batchpdlogs> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
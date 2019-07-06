package yj.core.qcauditlist.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;
import yj.core.qcauditlist.service.IQcauditprocessdtlService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QcauditprocessdtlController extends BaseController {

@Autowired
private IQcauditprocessdtlService service;


@RequestMapping(value = "/wip/qcauditprocessdtl/query")
@ResponseBody
public ResponseData query(Qcauditprocessdtl dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/qcauditprocessdtl/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Qcauditprocessdtl> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/qcauditprocessdtl/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Qcauditprocessdtl> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
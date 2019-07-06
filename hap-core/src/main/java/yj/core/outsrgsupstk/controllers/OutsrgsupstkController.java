package yj.core.outsrgsupstk.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.outsrgsupstk.dto.Outsrgsupstk;
import yj.core.outsrgsupstk.service.IOutsrgsupstkService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OutsrgsupstkController extends BaseController {

@Autowired
private IOutsrgsupstkService service;


@RequestMapping(value = "/wip/outsrgsupstk/query")
@ResponseBody
public ResponseData query(Outsrgsupstk dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/outsrgsupstk/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Outsrgsupstk> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/outsrgsupstk/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Outsrgsupstk> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
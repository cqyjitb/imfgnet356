package yj.core.ztbc0002.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.service.IZtbc0002Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Ztbc0002Controller extends BaseController {

@Autowired
private IZtbc0002Service service;


@RequestMapping(value = "/sap/ztbc0002/query")
@ResponseBody
public ResponseData query(Ztbc0002 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/ztbc0002/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Ztbc0002> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/ztbc0002/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Ztbc0002> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
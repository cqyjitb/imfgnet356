package yj.core.ztbc0018.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.service.IZtbc0018Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Ztbc0018Controller extends BaseController {

@Autowired
private IZtbc0018Service service;


@RequestMapping(value = "/sap/ztbc0018/query")
@ResponseBody
public ResponseData query(Ztbc0018 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/ztbc0018/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Ztbc0018> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/ztbc0018/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Ztbc0018> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
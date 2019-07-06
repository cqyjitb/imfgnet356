package yj.core.seversetting.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ServerSettingController extends BaseController {

@Autowired
private IServerSettingService service;


@RequestMapping(value = "/wip/server/setting/query")
@ResponseBody
public ResponseData query(ServerSetting dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/server/setting/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<ServerSetting> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/server/setting/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<ServerSetting> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
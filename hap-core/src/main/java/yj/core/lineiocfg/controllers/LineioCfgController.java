package yj.core.lineiocfg.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.lineiocfg.dto.LineioCfg;
import yj.core.lineiocfg.service.ILineioCfgService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LineioCfgController extends BaseController {

@Autowired
private ILineioCfgService service;


@RequestMapping(value = "/wip/lineio/cfg/query")
@ResponseBody
public ResponseData query(LineioCfg dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/lineio/cfg/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<LineioCfg> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/lineio/cfg/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<LineioCfg> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
@RequestMapping(value = {"/lineiocfg/selectinoutcfg"}, method = {RequestMethod.GET} )
@ResponseBody
public ResponseData selectinoutcfg(HttpServletRequest request, String line_id, String werks){
    ResponseData rs = new ResponseData();
    List<LineioCfg> list = new ArrayList<>();
    list = service.selectinoutcfg(line_id,werks);
    if (list.size() > 0){
        rs.setSuccess(true);
        rs.setRows(list);
        return rs;
    }else{
        rs.setSuccess(false);
        rs.setMessage("未获取到生产线"+line_id+"取件工序的配置信息，请联系系统管理员！");
        return rs;
    }
}
}
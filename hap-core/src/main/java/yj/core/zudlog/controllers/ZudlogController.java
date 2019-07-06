package yj.core.zudlog.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.service.IZudlogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ZudlogController extends BaseController {

@Autowired
private IZudlogService service;


@RequestMapping(value = "/wip/zudlog/query")
@ResponseBody
public ResponseData query(Zudlog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/zudlog/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Zudlog> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/zudlog/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Zudlog> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    /**
     * 不合格品审理单1处理日志查询页面请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/zudlog/selectZudlog")
    @ResponseBody
    public ResponseData queryZudlog(Zudlog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }
}
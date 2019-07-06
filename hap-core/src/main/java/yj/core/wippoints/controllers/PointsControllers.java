package yj.core.wippoints.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wippoints.dto.Points;
import yj.core.wippoints.service.IPointsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PointsControllers extends BaseController {

    @Autowired
    private IPointsService service;

    /**
     * 产线工序配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/points/queryPoints")
    @ResponseBody
    public ResponseData queryPoints(Points dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData(service.selectFromPage(dto,requestContext,page,pageSize));
        return rs;
    }

    /**
     * 产线工序配置维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/points/submitPoints")
    @ResponseBody
    public ResponseData updatePoints(HttpServletRequest request, @RequestBody List<Points> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userName");
        String str = service.setMessageLines(dto);
        if(str != null){
            rs.setSuccess(false);
            rs.setMessage(str);
            return rs;
        }else{
            String result = service.updateOrInsert(requestCtx,dto,userId);
            rs.setMessage(result);
            return rs;
        }
    }

    /**
     * 产线工序配置维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/points/removePoints")
    @ResponseBody
    public ResponseData deletePoints(HttpServletRequest request, @RequestBody List<Points> dto) {
        ResponseData rs =  new ResponseData();
        String result = service.deleteDftdtl(dto);
        if(result != null){
            rs.setSuccess(false);
            rs.setMessage(result);
            return rs;
        }else {
            return new ResponseData();
        }
    }
}

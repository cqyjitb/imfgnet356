package yj.core.bomroutings.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.bomroutings.dto.Routings;
import yj.core.bomroutings.service.IRoutingsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoutingCotroller extends BaseController {
    @Autowired
    private IRoutingsService service;

    /**
     * 机加产线产品工艺路线配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/bom/routings/queryRoutings")
    @ResponseBody
    public ResponseData queryRoutings(Routings dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        if(dto.getStartDateBefore() != null){
            dto.setStartDateBefore(dto.getStartDateBefore().substring(0,10));
        }
        if(dto.getStartDateAfter() != null){
            dto.setStartDateAfter(dto.getStartDateAfter().substring(0,10));
        }
        if(dto.getEndDateBefore() != null){
            dto.setEndDateBefore(dto.getEndDateBefore().substring(0,10));
        }
        if(dto.getEndDateAfter() != null){
            dto.setEndDateAfter(dto.getEndDateAfter().substring(0,10));
        }
        List<Routings> routings = service.selectFromPage(dto,requestContext,page,pageSize);
        return new ResponseData(routings);
    }

    /**
     *机加产线产品工艺路线配置维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bom/routings/submitRoutings")
    @ResponseBody
    public ResponseData updateRoutings(HttpServletRequest request, @RequestBody List<Routings> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userName");
        String result = result = service.updateOrInsert(requestCtx,dto,userId);
        rs.setMessage(result);
        return rs;

    }

    /**
     *机加产线产品工艺路线配置维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bom/routings/removeRoutings")
    @ResponseBody
    public ResponseData deleteRoutings(HttpServletRequest request, @RequestBody List<Routings> dto) {
        service.deleteRoutings(dto);
        return new ResponseData();
    }

}

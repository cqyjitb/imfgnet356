package yj.core.wipdot.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipdot.dto.Dot;
import yj.core.wipdot.service.IDotService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DotCotroller extends BaseController {
    @Autowired
    private IDotService service;

    /**
     * 机加采集点配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/dot/queryDot")
    @ResponseBody
    public ResponseData queryDot(Dot dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
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
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 机加采集点配置维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/dot/submitDot")
    @ResponseBody
    public ResponseData updateDot(HttpServletRequest request, @RequestBody List<Dot> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userName ="" + request.getSession().getAttribute("userName");
        String result = service.updateOrInsert(requestCtx,dto,userName);
        rs.setMessage(result);
        return rs;
    }

    @RequestMapping(value = "/wip/dot/removeDot")
    @ResponseBody
    public ResponseData deleteDot(HttpServletRequest request, @RequestBody List<Dot> dto) {
        ResponseData rs =  new ResponseData();
        String result = service.deleteDot(dto);
        if(result != null){
            rs.setSuccess(false);
            rs.setMessage(result);
            return rs;
        }else {
            return new ResponseData();
        }
    }
}

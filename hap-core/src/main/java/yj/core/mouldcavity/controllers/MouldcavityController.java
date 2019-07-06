package yj.core.mouldcavity.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.mouldcavity.dto.Mouldcavity;
import yj.core.mouldcavity.service.IMouldcavityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MouldcavityController extends BaseController {

@Autowired
private IMouldcavityService service;

    /**
     * 产品模具型腔表维护查询页面请求  918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/sap/mouldcavity/queryMouldcavity")
    @ResponseBody
    public ResponseData queryMouldcavity(Mouldcavity dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectMouldcavity(requestContext,dto,page,pageSize));
    }

    /**
     * 产品模具型腔表维护添加和修改页面请求  918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/sap/mouldcavity/submitMouldcavity")
    @ResponseBody
    public ResponseData updateMouldcavity(HttpServletRequest request, @RequestBody List<Mouldcavity> dto){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String userId ="" + request.getSession().getAttribute("userId");
        String result = service.updateMouldcavity(requestCtx, dto,userId);
        rs.setMessage(result);
        return rs;
    }

    /**
     * 产品模具型腔表维护删除页面请求  918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/sap/mouldcavity/removeMouldcavity")
    @ResponseBody
    public ResponseData deleteMouldcavity(HttpServletRequest request, @RequestBody List<Mouldcavity> dto){
        service.deleteMouldcavity(dto);
        return new ResponseData();
    }

}
package yj.kanb.vblinegroupheader.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VblinegroupheaderController extends BaseController {
    @Autowired
    private IVblinegroupheaderService service;

    /**
     * 看板车间产线组头维护查询请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/Vblinegroupheader/queryVblinegroupheader"})
    @ResponseBody
    public ResponseData queryLineGroupH(HttpServletRequest request, Vblinegroupheader dto){
        IRequest requestCtx = createRequestContext(request);
        List<Vblinegroupheader> list =  service.queryLineGroupH(requestCtx,dto);
        return new ResponseData(list);
    }
    /**
     * 看板车间产线组头维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/Vblinegroupheader/removeVblinegroupheader"})
    @ResponseBody
    public ResponseData removeLineGroupH(HttpServletRequest request, @RequestBody List<Vblinegroupheader> dto){
        IRequest requestCtx = createRequestContext(request);
        service.deleteLineGroupH(requestCtx,dto);
        return new ResponseData();
    }
    /**
     * 看板车间产线组头维护页面添加和修改请求 918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/Vblinegroupheader/submitVblinegroupheader")
    @ResponseBody
    public ResponseData insertLineGroupH(@RequestBody List<Vblinegroupheader> dto, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String result = service.setMessage(dto);
        if(result != null){
            rs.setSuccess(false);
            rs.setMessage(result);
        }else{
            String userId = "" + request.getSession().getAttribute("userId");
            service.insertOrUpdate(requestCtx,dto,userId);
        }
        return rs;
    }

    /**
     * 看板车间产线组头维护查询请求 918100064
     * @param request
     * @return
     */
    @RequestMapping(value = {"/Vblinegroupheader/selectVblinegroupheader"})
    @ResponseBody
    public ResponseData selectLineGroupH(HttpServletRequest request){
        String vbgroupId = request.getParameter("vbgroupId");
        IRequest requestCtx = createRequestContext(request);
        List<Vblinegroupheader> list =  service.selectLineGroupH(requestCtx,vbgroupId);
        return new ResponseData(list);
    }
}

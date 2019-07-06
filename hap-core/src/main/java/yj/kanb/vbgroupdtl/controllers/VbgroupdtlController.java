package yj.kanb.vbgroupdtl.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;
import yj.kanb.vbgroupdtl.service.IVbgroupdtlService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VbgroupdtlController extends BaseController {
    @Autowired
    private IVbgroupdtlService service;

    /**
     * 看板设备产线维护新增页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/groupDtl/insertGroupDtl"})
    @ResponseBody
    public ResponseData insertGroupDtl(HttpServletRequest request, @RequestBody List<Vbgroupdtl> dto){
        IRequest requestCtx = createRequestContext(request);
        String userId = request.getSession().getAttribute("userId") + "";
        service.insertGroupDtl(dto,userId);
        return new ResponseData();
    }
    /**
     * 看板设备产线维护新增页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/groupDtl/deleteGroupDtl"})
    @ResponseBody
    public ResponseData deleteGroupDtl(HttpServletRequest request, @RequestBody List<Vbgroupdtl> dto){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String result = service.deleteGroupDtl(dto);
        if(result == null){
            rs.setMessage("设备产线删除成功！");
        }
        return rs;
    }
}

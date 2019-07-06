package yj.kanb.vbgroupheader.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;
import yj.kanb.vbgroupheader.service.IVbgroupheaderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class VbgroupheaderController extends BaseController {
    @Autowired
    private IVbgroupheaderService service;

    /**
     * 看板显示组查询页面 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/groupH/queryGroupH"})
    @ResponseBody
    public ResponseData queryGroupH(HttpServletRequest request, Vbgroupheader dto){
        IRequest requestCtx = createRequestContext(request);
        List<Vbgroupheader> list =  service.queryGroupH(dto);
        return new ResponseData(list);
    }
}

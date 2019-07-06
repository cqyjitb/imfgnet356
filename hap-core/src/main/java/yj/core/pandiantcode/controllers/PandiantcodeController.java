package yj.core.pandiantcode.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.pandiantcode.dto.Pandiantcode;
import yj.core.pandiantcode.service.IPandiantcodeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PandiantcodeController extends BaseController {

@Autowired
private IPandiantcodeService service;


@RequestMapping(value = "/wip/pandiantcode/query")
@ResponseBody
public ResponseData query(Pandiantcode dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/pandiantcode/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Pandiantcode> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/pandiantcode/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Pandiantcode> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    @RequestMapping(value = {"/wip/pandiantcode/checkCode"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData checkCode(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String username = request.getParameter("username");
        String tj = request.getParameter("tj");
        String gz = request.getParameter("gz");
        Pandiantcode pt = new Pandiantcode();

        if (tj.equals("X")){
            pt = service.checkTjcode(username);
        }

        if (gz.equals("X")){
            pt = service.checkGzcode(username);
        }

        if (pt != null){
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }
}
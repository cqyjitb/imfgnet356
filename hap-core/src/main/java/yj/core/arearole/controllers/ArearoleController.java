package yj.core.arearole.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.arearole.dto.Arearole;
import yj.core.arearole.service.IArearoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ArearoleController extends BaseController {

@Autowired
private IArearoleService service;


@RequestMapping(value = "/wip/arearole/query")
@ResponseBody
public ResponseData query(Arearole dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/arearole/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Arearole> dto){
    IRequest requestCtx = createRequestContext(request);
    String userId = "" + request.getSession().getAttribute("userId");
    if (dto.size() > 0){
        for (int i = 0;i<dto.size();i++){
            dto.get(i).setCreatedBy(Long.valueOf(userId));
            dto.get(i).setCreationDate(new Date());
        }
    }
    int sum = service.insertArearole(dto);
    return new ResponseData(true);
}

@RequestMapping(value = "/wip/arearole/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Arearole> dto){
    service.deleteArearole(dto);
    return new ResponseData();
}
}
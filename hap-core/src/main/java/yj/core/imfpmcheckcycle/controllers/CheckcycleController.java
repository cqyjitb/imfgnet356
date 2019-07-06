package yj.core.imfpmcheckcycle.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.imfpmcheckcycle.dto.Checkcycle;
import yj.core.imfpmcheckcycle.service.ICheckcycleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CheckcycleController extends BaseController {

    @Autowired
    private ICheckcycleService service;

    /**
     * 点检周期配置的查询页面请求 918100064
     * @param id
     * @param checkcycle
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkcycle/query")
    @ResponseBody
    public ResponseData query(Integer id, String checkcycle, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectCheckcycle(requestContext, id,checkcycle, page, pageSize));
    }

    /**
     * 查询点检周期id的最大值 918100064
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkcycle/maxCheckcycle")
    @ResponseBody
    public int selectMaxCheckcycle(){
        return service.selectMaxCheckcycle();
    }

    /**
     * 点检周期的添加和修改页面请求 918100064
     * @param dto
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkcycle/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Checkcycle> dto, HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String userName = "" + request.getSession().getAttribute("userName");
        String massage = service.updateCheckcycle(dto,userName);
        if(massage != null){
            rs.setSuccess(false);
            rs.setMessage(massage);
        }
        return rs;
    }

    /**
     * 点检周期的删除页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkcycle/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Checkcycle> dto) {
        ResponseData rs = new ResponseData();
        String userName = "" + request.getSession().getAttribute("userName");
        String message = service.deleteCheckcycle(dto,userName);
        if(message != null){
            rs.setSuccess(false);
            rs.setMessage(message);
        }
        return rs;
    }

}
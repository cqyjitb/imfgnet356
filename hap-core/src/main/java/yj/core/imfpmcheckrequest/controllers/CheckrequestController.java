package yj.core.imfpmcheckrequest.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.imfpmcheckrequest.dto.Checkrequest;
import yj.core.imfpmcheckrequest.service.ICheckrequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CheckrequestController extends BaseController {

    @Autowired
    private ICheckrequestService service;
    /**
     * 点检要求配置的查询页面请求 918100064
     * @param id
     * @param checkrequest
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkrequest/query")
    @ResponseBody
    public ResponseData query(Integer id, String checkrequest, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectCheckrequest(requestContext, id,checkrequest, page, pageSize));
    }

    /**
     * 查询点检要求id的最大值 918100064
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkrequest/maxCheckrequest")
    @ResponseBody
    public int selectMaxCheckcycle(){
        return service.selectMaxCheckrequest();
    }

    /**
     * 点检要求的添加和修改页面请求 918100064
     * @param dto
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkrequest/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Checkrequest> dto, HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String userName = "" + request.getSession().getAttribute("userName");
        String massage = service.updateCheckrequest(dto,userName);
        if(massage != null){
            rs.setSuccess(false);
            rs.setMessage(massage);
        }
        return rs;
    }

    /**
     * 点检要求的删除页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/imf/pm/checkrequest/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Checkrequest> dto) {
        ResponseData rs = new ResponseData();
        String userName = "" + request.getSession().getAttribute("userName");
        String message = service.deleteCheckrequest(dto,userName);
        if(message != null){
            rs.setSuccess(false);
            rs.setMessage(message);
        }
        return rs;
    }
}
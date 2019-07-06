package yj.core.wipusers.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipusers.dto.Users;
import yj.core.wipusers.service.IUsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersCotroller extends BaseController {
    @Autowired
    private IUsersService service;

    /**
     * 机加采集点配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/users/queryUsers")
    @ResponseBody
    public ResponseData queryUsers(Users dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 机加采集点配置维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/users/submitUsers")
    @ResponseBody
    public ResponseData updateUsers(HttpServletRequest request, @RequestBody List<Users> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userName ="" + request.getSession().getAttribute("userName");
        String result = service.updateOrInsert(requestCtx,dto,userName);
        rs.setMessage(result);
        return rs;
    }

    @RequestMapping(value = "/wip/users/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Users> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}

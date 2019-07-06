package yj.core.wiplockusers.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wiplockusers.dto.LockUsers;
import yj.core.wiplockusers.service.ILockUsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LockUsersController extends BaseController {

    @Autowired
    private ILockUsersService service;

    /**
     * 装箱报错锁程序解锁用户维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/lock/users/queryLockUsers")
    @ResponseBody
    public ResponseData queryLockUsers(LockUsers dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                       @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 装箱报错锁程序解锁用户维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/users/submitLockUsers")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<LockUsers> dto) {
        IRequest requestCtx = createRequestContext(request);
        String userName = "" + request.getSession().getAttribute("userName");
        String result = service.updateOrInsert(requestCtx, dto,userName);
        ResponseData rs =  new ResponseData();
        rs.setMessage(result);
        return rs;
    }

    /**
     * 装箱报错锁程序解锁用户维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/users/removeLockUsers")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<LockUsers> dto) {
        service.deleteLockUsers(dto);
        return new ResponseData();
    }
}
package yj.core.wiplockconstrelation.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wiplockconstrelation.dto.LockConstRelation;
import yj.core.wiplockconstrelation.service.ILockConstRelationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LockConstRelationController extends BaseController {

    @Autowired
    private ILockConstRelationService service;

    /**
     * 装箱报错锁程序常量关系维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/relation/queryLockConstRelation")
    @ResponseBody
    public ResponseData queryLockConstRelation(LockConstRelation dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                               @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 装箱报错锁程序常量关系维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/relation/submitLockConstRelation")
    @ResponseBody
    public ResponseData updateLockConstRelation(HttpServletRequest request, @RequestBody List<LockConstRelation> dto) {
        ResponseData rs =  new ResponseData();
        String userName = "" + request.getSession().getAttribute("userName");
        IRequest requestCtx = createRequestContext(request);
        String result = service.updateOrInsert(requestCtx, dto,userName);
        rs.setMessage(result);
        return rs;
    }

    /**
     * 装箱报错锁程序常量关系维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/relation/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<LockConstRelation> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

}
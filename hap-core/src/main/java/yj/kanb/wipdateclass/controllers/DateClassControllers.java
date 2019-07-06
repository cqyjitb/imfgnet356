package yj.kanb.wipdateclass.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.wipdateclass.dto.DateClass;
import yj.kanb.wipdateclass.service.IDateClassService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class DateClassControllers extends BaseController {
    @Autowired
    private IDateClassService service;

    /**
     * 日期范围及类名维护页面查询请求 918100064
     * @param request
     * @return
     */
    @RequestMapping(value = {"/date/class/queryDateClass"})
    @ResponseBody
    public ResponseData queryEquipment(HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        String className = request.getParameter("className");
        List<DateClass> list = service.selectFromPage(className);
        return new ResponseData(list);
    }

    /**
     * 日期范围及类名维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/date/class/removeDateClass"})
    @ResponseBody
    public ResponseData removeEquipment(HttpServletRequest request, @RequestBody List<DateClass> dto){
        IRequest requestCtx = createRequestContext(request);
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     * 日期范围及类名维护页面添加及修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/date/class/submitDateClass"})
    @ResponseBody
    public ResponseData submitEquipment(HttpServletRequest request, @RequestBody List<DateClass> dto){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        if (dto.size() > 0){
            String userId = request.getSession().getAttribute("userId") + "";
            for (int i=0;i<dto.size();i++){
                DateClass dateClass = dto.get(i);
                if(dateClass.getStartDate() == null){
                    rs.setSuccess(false);
                    rs.setMessage("开始日期不能为空");
                    return rs;
                }else if (dateClass.getEndDate() == null){
                    rs.setSuccess(false);
                    rs.setMessage("结束日期不能为空");
                    return rs;
                }else if (dateClass.getClassName() == null || "".equals(dateClass.getClassName())){
                    rs.setSuccess(false);
                    rs.setMessage("类名不能为空");
                    return rs;
                }else {
                    List<DateClass> list = service.selectFromPage(dateClass.getClassName());
                    if(list.size() > 0){
                        dateClass.setLastUpdatedBy(Long.valueOf(userId));
                        dateClass.setLastUpdateDate(new Date());
                        service.updateDateClass(requestCtx,dateClass);
                    }else {
                        dateClass.setCreatedBy(Long.valueOf(userId));
                        dateClass.setCreationDate(new Date());
                        service.insertDateClass(requestCtx,dateClass);
                    }
                }
            }
        }
        return rs;
    }
}

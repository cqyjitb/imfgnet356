package yj.core.zrwklist.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.service.IZrwklistService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ZrwklistController extends BaseController {

@Autowired
private IZrwklistService service;
@Autowired
private IEmployeeService employeeService;


@RequestMapping(value = "/wip/zrwklist/query")
@ResponseBody
public ResponseData query(Zrwklist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/zrwklist/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Zrwklist> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/zrwklist/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Zrwklist> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
    /**
     *处理机加返工返修单查询页面请求 918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/zrwklist/selectZrwklist")
    @ResponseBody
    public ResponseData selectZrwklist(Zrwklist dto, String createdBy1, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        if(createdBy1 != null && createdBy1 != ""){
            Employee employee = employeeService.queryByCode(createdBy1);
            dto.setCreatedBy(employee.getEmployeeId());
        }
        Zrwklist zrwklist  = resultFormat(dto);
        return new ResponseData(service.selectZrwklist(requestContext,zrwklist));
    }
    /**
     *修改创建日期的方法 918100064
     * @param dto
     * @return
     */
    public Zrwklist resultFormat(Zrwklist dto){
        if ( dto.getCreationDateBefore() !=null){
            String cdBefore = dto.getCreationDateBefore().replace("00:00:00","23:59:59");
            dto.setCreationDateBefore(cdBefore);
        }
        return dto;
    }
}
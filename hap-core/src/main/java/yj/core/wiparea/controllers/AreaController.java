package yj.core.wiparea.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wiparea.dto.Area;
import yj.core.wiparea.service.IAreaService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class AreaController extends BaseController {

@Autowired
private IAreaService service;


@RequestMapping(value = "/wip/area/query")
@ResponseBody
public ResponseData query(Area dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/area/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Area> dto){
    String createdBy = "" + request.getSession().getAttribute("userId");
    IRequest requestCtx = createRequestContext(request);
    int sum = 0;
    if (dto.size() > 0){
        for (int i = 0;i<dto.size();i++){
            if(dto.get(i).get__status().equals("add")){
                dto.get(i).setCreatedBy(Long.valueOf(createdBy));
                dto.get(i).setCreationDate(new Date());
                sum = sum + service.insertArea(dto.get(i));
            }else if (dto.get(i).get__status().equals("update")){
                dto.get(i).setLastUpdatedBy(Long.valueOf(createdBy));
                dto.get(i).setLastUpdateDate(new Date());
                sum = sum + service.updateArea(dto.get(i));
            }
        }
    }
    ResponseData rs = new ResponseData();
    if (sum == dto.size()){
        rs.setSuccess(true);
    }else {
        rs.setSuccess(false);
        rs.setMessage("更新数据失败！");
    }
    return  rs;
}

@RequestMapping(value = "/wip/area/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Area> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}
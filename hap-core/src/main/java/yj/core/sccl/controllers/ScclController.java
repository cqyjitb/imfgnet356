package yj.core.sccl.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.sccl.dto.Sccl;
import yj.core.sccl.service.IScclService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ScclController extends BaseController {

@Autowired
private IScclService service;


@RequestMapping(value = "/sap/sccl/query")
@ResponseBody
public ResponseData query(Sccl dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/sccl/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Sccl> dto){
    IRequest requestCtx = createRequestContext(request);
    //return new ResponseData(service.batchUpdate(requestCtx, dto));
    int sum = service.batchModify(dto);
    ResponseData rs = new ResponseData();
    if (dto.size() == sum){
        rs.setSuccess(true);
        rs.setMessage("数据保存成功！");
    }else{
        rs.setSuccess(false);
        rs.setMessage("数据保存失败！");
    }
    return rs;
}

@RequestMapping(value = "/sap/sccl/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Sccl> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

}
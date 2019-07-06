package yj.core.qjcode.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.service.IQjcodeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QjcodeController extends BaseController {

@Autowired
private IQjcodeService service;


@RequestMapping(value = "/wip/qjcode/query")
@ResponseBody
public ResponseData query(Qjcode dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/qjcode/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Qjcode> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/qjcode/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Qjcode> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

@RequestMapping(value = {"/qjcode/selectcode"}, method = {RequestMethod.GET} )
@ResponseBody
public ResponseData selectcode(HttpServletRequest request){
    ResponseData rs = new ResponseData();
    List<Qjcode> list = new ArrayList<>();
    list = service.selectcode();
    if (list.size() > 0){
        rs.setSuccess(true);
        rs.setRows(list);
        return rs;
    }else{
        rs.setSuccess(false);
        rs.setMessage("没有获取到任何取件原因代码数据！请联系系统管理！");
        return rs;
    }
}

}
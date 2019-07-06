package yj.core.bomoperations.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.bomoperations.dto.Operations;
import yj.core.bomoperations.service.IOperationsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OperationsCotroller extends BaseController {
    @Autowired
    private IOperationsService service;

    /**
     *机加产线产品工艺路线配置维护对话框页面查询请求 918100064
     * @param routingId
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/bom/operations/queryOperations")
    @ResponseBody
    public ResponseData queryOperations(Integer routingId, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData(service.selectFromPage(routingId,requestContext,page,pageSize));
        return rs;
    }

    /**
     *机加产线产品工艺路线配置维护对话框页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bom/operations/submitOperations")
    @ResponseBody
    public ResponseData updateOperations(HttpServletRequest request, @RequestBody List<Operations> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userName");
        String str = service.setMessageOperations(dto);
        if(str != null){
            rs.setSuccess(false);
            rs.setMessage(str);
            return rs;
        }else{
            String result = service.updateOrInsert(requestCtx,dto,userId);
            rs.setMessage(result);
            return rs;
        }
    }

    /**
     * 机加产线产品工艺路线配置维护对话框页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bom/operations/removeOperations")
    @ResponseBody
    public ResponseData deleteOperations(HttpServletRequest request, @RequestBody List<Operations> dto) {
        service.deleteOperations(dto);
        return new ResponseData();
    }
}

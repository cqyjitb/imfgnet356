package yj.core.wipproductscfg.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductsCfgController extends BaseController {

@Autowired
private IProductsCfgService service;


@RequestMapping(value = "/wip/products/cfg/query")
@ResponseBody
public ResponseData query(ProductsCfg dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/products/cfg/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<ProductsCfg> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/products/cfg/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<ProductsCfg> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    /**
     * 机加产线产品配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/products/cfg/queryProductsCfg")
    @ResponseBody
    public ResponseData queryProductsCfg(ProductsCfg dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 机加产线产品配置维护页面增添、修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/products/cfg/submitProductsCfg")
    @ResponseBody
    public ResponseData updateProductsCfg(HttpServletRequest request, @RequestBody List<ProductsCfg> dto){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userId");
        String result = service.updateOrInsert(requestCtx, dto,userId);
        rs.setMessage(result);
        return rs;
    }

    /**
     * 机加产线产品配置维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/products/cfg/removeProductsCfg")
    @ResponseBody
    public ResponseData deleteProductsCfg(HttpServletRequest request, @RequestBody List<ProductsCfg> dto){
        ResponseData rs =  new ResponseData();
        String result = service.deleteProductsCfg(dto);
        if(result != null){
            rs.setSuccess(false);
            rs.setMessage(result);
            return rs;
        }else {
            return new ResponseData();
        }
    }

    /**
     * 根据产线查询物料 918100064
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping(value = "/wip/products/cfg/queryByLineId", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectProductsCfgBylineId(HttpServletRequest request, String lineId){
        ResponseData rs =  new ResponseData();
        String lineIdtmp = "";
        if (!lineId.equals("")){
            lineIdtmp = lineId.replaceAll("L","");
            lineIdtmp.trim();
            lineId = lineIdtmp;
        }

        List<ProductsCfg> list = service.selectByMatnr(Long.parseLong(lineId));
        if(list != null){
            rs.setSuccess(true);
            rs.setRows(list);
        }else {
            rs.setSuccess(false);
        }
        return rs;
    }

}
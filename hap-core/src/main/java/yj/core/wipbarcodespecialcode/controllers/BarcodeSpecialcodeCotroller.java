package yj.core.wipbarcodespecialcode.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipbarcodespecialcode.dto.BarcodeSpecialcode;
import yj.core.wipbarcodespecialcode.service.IBarcodeSpecialcodeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BarcodeSpecialcodeCotroller extends BaseController {
    @Autowired
    private IBarcodeSpecialcodeService service;

    /**
     * 机加产品识别码配置维护对话框页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/barcode/specialcode/queryBarcodeSpecialcode")
    @ResponseBody
    public ResponseData queryBarcodeSpecialcode(BarcodeSpecialcode dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     * 机加产品识别码配置维护对话框页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/barcode/specialcode/submitBarcodeSpecialcode")
    @ResponseBody
    public ResponseData updateBarcodeSpecialcode(HttpServletRequest request, @RequestBody List<BarcodeSpecialcode> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userName ="" + request.getSession().getAttribute("userName");
        String str = service.setMessageBarcodeSpecialcode(dto);
        if(str != null){
            rs.setSuccess(false);
            rs.setMessage(str);
            return rs;
        }else{
            String result = service.updateOrInsert(requestCtx,dto,userName);
            rs.setMessage(result);
            return rs;
        }
    }

    @RequestMapping(value = "/wip/barcode/specialcode/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<BarcodeSpecialcode> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}

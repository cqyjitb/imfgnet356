package yj.core.wipbarcodesetting.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipbarcodesetting.dto.BarcodeSetting;
import yj.core.wipbarcodesetting.service.IBarcodeSettingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BarcodeSettingCotroller extends BaseController {
    @Autowired
    private IBarcodeSettingService service;

    /**
     * 机加产品识别码配置维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/barcode/setting/queryBarcodeSetting")
    @ResponseBody
    public ResponseData queryBarcodeSetting(BarcodeSetting dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFromPage(requestContext,dto,page,pageSize));
    }

    /**
     *机加产品识别码配置维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/barcode/setting/submitBarcodeSetting")
    @ResponseBody
    public ResponseData updateBarcodeSetting(HttpServletRequest request, @RequestBody List<BarcodeSetting> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userName ="" + request.getSession().getAttribute("userName");
        String result = service.updateOrInsert(requestCtx,dto,userName);
        rs.setMessage(result);
        return rs;
    }

    /**
     * 机加产品识别码配置维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/barcode/setting/removeBarcodeSetting")
    @ResponseBody
    public ResponseData deleteBarcodeSetting(HttpServletRequest request, @RequestBody List<BarcodeSetting> dto) {
        service.deleteBarcodeSetting(dto);
        return new ResponseData();
    }
}

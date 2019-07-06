package yj.core.wipcurlzk.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CurlzkController extends BaseController {

@Autowired
private ICurlzkService service;
@Autowired
private ICardhService cardhService;


@RequestMapping(value = "/wip/curlzk/query")
@ResponseBody
public ResponseData query(Curlzk dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/curlzk/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Curlzk> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/curlzk/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Curlzk> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    @RequestMapping(value = "/wip/curlzk/selectById")
    @ResponseBody
    public ResponseData selectCurlzk(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String lineId = request.getParameter("lineId");
        String shift = request.getParameter("shift");
        String matnr = request.getParameter("matnr");
        List list = new ArrayList();
        Curlzk curlzk = new Curlzk();
        curlzk = service.selectById(Long.parseLong(lineId),shift);
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(curlzk.getZpgdbar());
        if (cardh != null){
            if (cardh.getMatnr().equals(matnr)){
                rs.setSuccess(true);
                rs.setCode(cardh.getAufnr());
            }else{
                rs.setSuccess(true);
                rs.setCode("");
            }

        }else{
            rs.setSuccess(true);
            rs.setCode("");
        }
        return rs;
    }
}
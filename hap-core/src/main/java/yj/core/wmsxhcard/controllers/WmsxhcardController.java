package yj.core.wmsxhcard.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.marc.service.IMarcService;
import yj.core.resb.dto.Resb;
import yj.core.resb.service.IResbService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wmsxhcard.dto.Wmsxhcard;
import yj.core.wmsxhcard.service.IWmsxhcardService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WmsxhcardController extends BaseController {

@Autowired
private IWmsxhcardService service;
@Autowired
private ICurlzkService curlzkService;
@Autowired
private ICardhService cardhService;
@Autowired
private IResbService resbService;
@Autowired
private IAfkoService afkoService;
@Autowired
private IMarcService marcService;


@RequestMapping(value = "/wip/wmsxhcard/query")
@ResponseBody
public ResponseData query(Wmsxhcard dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/wmsxhcard/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Wmsxhcard> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/wmsxhcard/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Wmsxhcard> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

@RequestMapping(value = {"/wip/wmsxhcard/selectByBarcode"}, method = {RequestMethod.GET})
@ResponseBody
public ResponseData selectByBarcode(HttpServletRequest request, String  zxhbar, String werks, String lineId, String vornr){
    ResponseData rs = new ResponseData();
    Wmsxhcard wmsxhcard = new Wmsxhcard();
    List list = new ArrayList();
    wmsxhcard = service.selectByBarcode(zxhbar,werks);
    Curlzk curlzk = new Curlzk();
    if (wmsxhcard != null){
//            if (wmsxhcard.getZsxwc() != null){
//                if (wmsxhcard.getZsxwc().equals("X")){
//                    rs.setMessage("该箱已上线，请换箱！");
//                    rs.setSuccess(false);
//                    return rs;
//                }
//            }

        curlzk = curlzkService.selectById2(Long.parseLong(lineId));
        if (curlzk == null){
            rs.setMessage("未能获取到当前产线当前机加流转卡信息！");
            rs.setSuccess(false);
        }else{
            Cardh cardjj = new Cardh();
            cardjj = cardhService.selectByBarcode(curlzk.getZpgdbar());
            if (cardjj == null){
                rs.setMessage("当前机加流转卡信息错误！请确认当前机加流转卡！");
                rs.setSuccess(false);
            }else{
                Afko afko = new Afko();
                afko = afkoService.selectByAufnr(cardjj.getAufnr());
                if (afko == null){
                    rs.setMessage("当前机加流转卡已经的机加生产订单信息错误！");
                    rs.setSuccess(false);
                }else{
                    List<Resb> resbList = new ArrayList<>();
                    resbList = resbService.selectByRsnumForzpjsx(afko.getRsnum());
                    if (resbList.size() == 0){
                        rs.setMessage("未能获取到产品BOM信息，请联系管理员！");
                        rs.setSuccess(false);
                    }else{
                        String l_error = "";
                        String l_error2 = "";
                        for (int i =0;i<resbList.size();i++){
                            if (resbList.get(i).getMatnr().equals(wmsxhcard.getMatnr()) && resbList.get(i).getVornr().equals(vornr)){
                                if  (!resbList.get(i).getLgort().equals(wmsxhcard.getLgort())){
                                    l_error2 = "X";
                                }
                                l_error = "X";
                                break;
                            }
                        }
                        if (l_error.equals("")){
                            rs.setMessage("该箱号对应的物料不属于本工序！");
                            rs.setSuccess(false);
                        }else{
                            if (l_error2.equals("X")){
                                rs.setMessage("箱号条码库存地点错误！");
                                rs.setSuccess(false);
                            }else{

                                list.add(wmsxhcard);
                                rs.setRows(list);
                                rs.setSuccess(true);
                            }
                        }
                    }
                }
            }

        }

    }else{
        rs.setSuccess(false);
        rs.setMessage("该箱号条码错误，请检查箱号二维码！");
    }

    return rs;
}
}
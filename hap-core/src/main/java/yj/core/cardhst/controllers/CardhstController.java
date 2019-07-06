package yj.core.cardhst.controllers;

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
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.service.ICardhstService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CardhstController extends BaseController {

    @Autowired
    private ICardhstService service;
    @Autowired
    private ICardhService cardhService;


    @RequestMapping(value = "/sap/cardhst/query")
    @ResponseBody
    public ResponseData query(Cardhst dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/sap/cardhst/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Cardhst> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/cardhst/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Cardhst> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sap/cardhst/updateStatus")
    @ResponseBody
    public ResponseData updatePrntStatus(HttpServletRequest request, @RequestBody List<Cardhst> dto) {
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                Cardhst tst = new Cardhst();
                Cardh cardh = new Cardh();
                dto.get(i).setId(2L);
                tst = service.selectByBarcodeAndStatus(dto.get(i));
                if (tst == null){
                    dto.get(i).setId(2L);

                    service.insertSingerStatus(dto.get(i));
                }else{

                    service.updateStatus(dto.get(i));
                }
                int maxno = service.getMaxNo(dto.get(i).getZpgdbar());
                if ( maxno <= 2) {
                    if (dto.get(i).getId() == 2L) {//补打不更改当前状态
                        cardh = cardhService.selectByBarcode(dto.get(i).getZpgdbar());
                        cardh.setStatus2(cardh.getStatus());
                        cardh.setStatus("PRNT");
                        cardh.setZdybs("Y");
                        List<Cardh> list = new ArrayList<>();
                        list.add(cardh);
                        cardhService.updateCardhStatus(list);
                    }
                }else{
                    cardh = cardhService.selectByBarcode(dto.get(i).getZpgdbar());
                    cardh.setStatus2(cardh.getStatus2());
                    cardh.setStatus(cardh.getStatus());
                    cardh.setZdybs("Y");
                    List<Cardh> list = new ArrayList<>();
                    list.add(cardh);
                    cardhService.updateCardhStatus(list);
                }

            }
        }
        return new ResponseData(true);
    }
}
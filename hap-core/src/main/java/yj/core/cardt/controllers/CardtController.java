package yj.core.cardt.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.service.IFevorService;
import yj.core.outsrgrfe.service.IOutsrgrfeService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class CardtController
        extends BaseController {
    @Autowired
    private ICardtService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IOutsrgrfeService iOutsrgrfeService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IFevorService fevorService;

    @RequestMapping({"/sap/cardt/query"})
    @ResponseBody
    public ResponseData query(Cardt dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardt/queryAfterSort"})
    @ResponseBody
    public ResponseData queryAfterSort(Cardt dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryAfterSort(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardt/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Cardt> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/cardt/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Cardt> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/sap/cardt/selectByZpgdbar"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByZpgdbar(HttpServletRequest request) {
        String zpgdbar = request.getParameter("zpgdbar");
        return new ResponseData(service.selectByZpgdbar(zpgdbar));
    }

    //根据标准值码和流转卡号查询工序记录
    @RequestMapping(value = {"/sap/cardt/selectByZpgdbarAndKtsch"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByBarcodeAndKtsch(HttpServletRequest request) throws ParseException {
        ResponseData rs = new ResponseData();
        String zpgdbar = request.getParameter("zpgdbar");
        String ktsch = request.getParameter("ktsch");

        Cardt param = new Cardt();
        param.setZpgdbar(zpgdbar);
        param.setKtsch(ktsch);
        List<Cardt> list = new ArrayList<>();
        Cardt cardt = service.selectByBarcodeAndKtsch(param);
        if (cardt == null){
            rs.setSuccess(true);
            rs.setMessage("该派工单不存在标准值码:"+ktsch+"所对应的工序！");
            return rs;
        }

        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(zpgdbar);
        Afko afko = new Afko();
        afko = afkoService.selectByAufnr(cardh.getAufnr());
        Fevor fevor = new Fevor();
        fevor = fevorService.selectByfevorSinger(afko.getFevor());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curdate);
        Integer bancnum = 2;//班次数量 默认2个班次 符合转班条件 为3个班次
        //Integer[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
        Integer w = cal.get(Calendar.DAY_OF_WEEK) - 1;//判断当前日期是星期几
        Integer w1 = 0;
        if ( w == 1){
            w1 = 7;
        }else{
            w1 = w - 1;
        }
        //eg. 2019-05-24 08:00:00 ----------2019-05-25 07:59:59
        if (w.toString().equals(fevor.getShiftDay())){
            String strbg = sdf.format(new Date()).substring(0,10) + " 08:30:00";
            Date bg = sdf.parse(strbg);
            Calendar calbg = Calendar.getInstance();
            calbg.setTime(bg);
            if (cal.after(calbg)){
                Integer week = cal.get(Calendar.WEEK_OF_YEAR) % 2;

                if (fevor.getShiftSeq() == null || fevor.getShiftDay() == null){

                }else{


                        if (fevor.getShiftSeq().equals("0")){
                            bancnum = 3;
                        }

                        if (fevor.getShiftSeq().equals("2") && week == 0){
                            bancnum = 3;
                        }

                        if (fevor.getShiftSeq().equals("1") && week == 1){
                            bancnum = 3;
                        }

                }
            }
        }

        if (w1.toString().equals(fevor.getShiftDay())){
           String stred = sdf.format(new Date()).substring(0,10) + " 08:29:59";
           Date ed = sdf.parse(stred);
           Calendar caled = Calendar.getInstance();
           caled.setTime(ed);
           if (cal.before(caled)){
               Integer week = cal.get(Calendar.WEEK_OF_YEAR) % 2;
               if (fevor.getShiftSeq() == null || fevor.getShiftDay() == null){

               }else{


                       if (fevor.getShiftSeq().equals("0")){
                           bancnum = 3;
                       }

                       if (fevor.getShiftSeq().equals("2") && week == 0){
                           bancnum = 3;
                       }

                       if (fevor.getShiftSeq().equals("1") && week == 1){
                           bancnum = 3;
                       }

               }
           }
        }



        //当前时间
            //判断工序是不是首工序
            List<Cardt> listasc = service.selectByZpgdbarAsc(zpgdbar);
            if (listasc.get(0).getVornr().equals(cardt.getVornr())){
                rs.setCode("FIRST");   
            }else{
                rs.setCode("");
            }

            List all = new ArrayList();
            all.add(cardt);
            all.add(bancnum);
            rs.setRows(all);
            rs.setSuccess(true);
            return rs;

    }

}

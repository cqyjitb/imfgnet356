package yj.core.outsrgrfe.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webserver_weidu.components.WeiduWebserviceUtil;
import yj.core.webserver_weidu.dto.DTWEIDUParam;
import yj.core.webserver_weidu.dto.DTWEIDUReturn;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OutsrgrfeController extends BaseController {

@Autowired
private IOutsrgrfeService service;
@Autowired
private ICardhService cardhService;
@Autowired
private ICardtService cardtService;
@Autowired
private IOutsrgissueService outsrgissueService;
@Autowired
private IMarcService marcService;
@Autowired
private IXhcardService xhcardService;

@RequestMapping(value = "/wip/outsrgrfe/query")
@ResponseBody
public ResponseData query(Outsrgrfe dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/outsrgrfe/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Outsrgrfe> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/outsrgrfe/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Outsrgrfe> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    /**
     * 处理外协发料，查询供应商信息 917110140   2018-11-12
     * @param request
     * @param  lifnr
     * @return
     */


@RequestMapping(value = {"/wip/outsrgrfe/selectForSortl"}, method = {RequestMethod.GET})
@ResponseBody
public ResponseData selectForSortl(HttpServletRequest request, String lifnr){

    ResponseData rs = new ResponseData();
    List<Outsrgrfe> list = service.selectForSortl(lifnr);
    if (list.size() > 0){
        rs.setSuccess(true);
        rs.setRows(list);
    }else{
        rs.setSuccess(false);
        rs.setMessage("外协供应商在外协接口索引表中不存在！");
    }

    return rs;
}

    /**
     *  查询供应商及简称
     * @param request
     * @return
     */
@RequestMapping(value = {"/wip/outsrgrfe/selectAllLinfr"}, method = {RequestMethod.GET})
@ResponseBody
public ResponseData selectAllLifnr(HttpServletRequest request){
    ResponseData rs = new ResponseData();
    List<Outsrgrfe> list = service.selectAllLifnr();
    if (list.size() > 0){
        rs.setRows(list);
    }
    rs.setSuccess(true);
    return rs;
    }

    /**
     *  根据工序流转卡号 供应商编码 查询 1：工序流转卡信息 SAP_cardh
     *                               2：工序外协接口表信息 wip_outsrgrfe
     *                               3：工序信息  sap_cardt
     * @param request
     * @param barcode
     * @param lifnr
     * @return
     */
@RequestMapping(value = {"/wip/outsrgrfe/selectForEbeln"},method = {RequestMethod.GET})
@ResponseBody
public ResponseData selectForEbeln(HttpServletRequest request, String barcode, String lifnr){
    ResponseData rs = new ResponseData();
    //1:查询流转卡信息
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);
        if (cardh == null){
            rs.setMessage("工序流转卡不存在，请确认工序流转号是否正确");
            rs.setSuccess(false);
            return rs;
        }

        if (cardh.getStatus().equals("HOLD")){
            rs.setMessage("当前工序流转卡状态为HOLD，不允许进行工序发料操作！");
            rs.setSuccess(false);
            return rs;
        }

     //查询物流信息
        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());


    //查询工序信息
        String l_error = "X";
        int l_index = 0;
        int l_index2 = 0;
        List<Cardt> listcardt = new ArrayList<>();
        listcardt = cardtService.selectByZpgdbarDesc(barcode);
        for (int i = 0;i<listcardt.size();i++){
            if (listcardt.get(i).getSteus().equals("ZP02")){
                l_error = "";
                l_index = i;
                l_index2 = i + 1;
            }
        }

        if (l_error.equals("X")){
            rs.setMessage("未能获取到当前流转卡的外协工序号！");
            rs.setSuccess(false);
            return  rs;
        }

        if (l_index != 0){
            if (!listcardt.get(l_index2).getConfirmed().equals("X")){
                rs.setSuccess(false);
                rs.setMessage("前工序尚未报工，不允许进行外协发料！");
                return  rs;
            }
        }
     //查询改流转卡对应的箱号是否已经外协发料
    String status = "0";
    Outsrgissue outsrgissue = outsrgissueService.selectByBarcode(cardh.getZpgdbar(),status);
        if (outsrgissue != null){
            if (outsrgissue.getStatus().equals("0") || outsrgissue.getStatus().equals("2")){
                rs.setMessage("该流转卡对应毛坯框已进行外协发料操作，请勿重复扫描！");
                rs.setSuccess(false);
                return rs;
            }

        }

     //查询箱号信息
    Xhcard xhcard = new Xhcard();
        xhcard = xhcardService.selectForZxhbar(cardh.getWerks(),cardh.getAufnr(),cardh.getZxhnum());

    //查询该流转卡对应班标 模号 是否属于围堵批次
        DTWEIDUParam param = new DTWEIDUParam();
        param.setMATNR(cardh.getMatnr());
        param.setWERKS(cardh.getWerks());
        param.setZBANB(cardh.getSfflg());
        param.setZMODEL(cardh.getDiecd());
        param.setZXHBAR(xhcard.getZxhbar());
    //机加上线毛坯围堵查询
    WeiduWebserviceUtil weiduWebserviceUtil = new WeiduWebserviceUtil();
    DTWEIDUReturn dtweiduReturn = weiduWebserviceUtil.receiveConfirmation(param);
    if (dtweiduReturn.getMTYPE().equals("S")) {
        if (dtweiduReturn.getWEIDUFLG() != null) {
            if (dtweiduReturn.getWEIDUFLG().equals("1")) {

                rs.setSuccess(false);
                rs.setMessage("该毛坯框，班标：" + cardh.getSfflg() + ",属于围堵批次！不允许外协发料！");
                return rs;
            }
        }
    }

    //2:查询接口表信息

        Outsrgrfe outsrgrfe = new Outsrgrfe();
        outsrgrfe = service.selectByCondition(cardh.getWerks(),cardh.getAufnr(),listcardt.get(l_index).getVornr(),cardh.getMatnr(),lifnr,null,null);
        if (outsrgrfe == null){
            rs.setSuccess(false);
            rs.setMessage("未能获取到流转卡对应生产订单的外协采购订单信息！");
            return rs;
        }

    //3：查询采购订单的发货记录，汇总已发货数量
        List<Outsrgissue> outsrgissueList = new ArrayList<>();
        outsrgissueList = outsrgissueService.selectByContidion(outsrgrfe.getEbeln(),outsrgrfe.getEbelp(),outsrgrfe.getWerks(),outsrgrfe.getLifnr(),outsrgrfe.getMatnr());

        List<Cardh> listcardh = new ArrayList<>();
        listcardh.add(cardh);
        List<Outsrgrfe> listoutsrgrfe = new ArrayList<>();
        listoutsrgrfe.add(outsrgrfe);
        List<Marc> listmarc = new ArrayList<>();
        listmarc.add(marc);
        List<Cardt> listcardt2 = new ArrayList<>();
        listcardt2.add(listcardt.get(l_index));
        List listall = new ArrayList();
        listall.add(listcardh);
        listall.add(listoutsrgrfe);
        listall.add(outsrgissueList);
        listall.add(listmarc);
        listall.add(listcardt2);
        rs.setRows(listall);
        rs.setSuccess(true);
        return rs;
}
}
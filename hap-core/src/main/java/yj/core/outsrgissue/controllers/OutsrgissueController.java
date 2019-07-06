package yj.core.outsrgissue.controllers;

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
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgissuehead.service.IOutsrgissueheadService;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;
import yj.core.outsrgreceipt.service.IOutsrgreceiptService;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webservice_outsrgissue.components.SyncOutsrgissueWebserviceUtil;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEhead;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEitem;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEreturn;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OutsrgissueController extends BaseController {

@Autowired
private IOutsrgissueService service;
@Autowired
private ICardhService cardhService;
@Autowired
private IOutsrgissueheadService outsrgissueheadService;
@Autowired
private IOutsrgrfeService outsrgrfeService;
@Autowired
private IMarcService marcService;
@Autowired
private ICardtService cardtService;
@Autowired
private IOutsrgreceiptService outsrgreceiptService;


@RequestMapping(value = "/wip/outsrgissue/query")
@ResponseBody
public ResponseData query(Outsrgissue dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/wip/outsrgissue/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Outsrgissue> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/wip/outsrgissue/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Outsrgissue> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

@RequestMapping(value = {"/wip/outsrgissue/wxfl"},method = {RequestMethod.GET})
@ResponseBody
public ResponseData processWxfl(HttpServletRequest request, String barcode, String lifnr, String userId, String ebeln, String menge, String vornr, String userName){
    ResponseData rs = new ResponseData();
    Cardh cardh = new Cardh();
    cardh = cardhService.selectByBarcode(barcode);

    Outsrgrfe outsrgrfe = outsrgrfeService.selectByCondition(cardh.getWerks(),cardh.getAufnr(),vornr,cardh.getMatnr(),lifnr,null,null);
    Marc marc = new Marc();
    marc = marcService.selectByMatnr(cardh.getMatnr());
    Cardt cardt = new Cardt();
    cardt = cardtService.selectByZpgdbarAndVornr(cardh.getZpgdbar(),vornr);
    //判断外协工序是否已经报工
    if  (cardt.getConfirmed() != null){
        if (cardt.getConfirmed().equals("X")){
            rs.setMessage("外协工序已报工，本次外协发货无效！");
            rs.setSuccess(false);
            return rs;
        }
    }

    //查询并生成发料单号
    List<Outsrgissuehead> list = new ArrayList<>();
    list = outsrgissueheadService.selectByMatnrAndLifnrDesc(cardh.getMatnr(),lifnr);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
    String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");
    String curdate1 = df.format(new Date()).substring(0,10);
    String curtim1 = df.format(new Date()).substring(11,19);
    String issuenm = "F" + curdate.substring(2,6) + "000001";
    //准备表头数据

    String l_update = "";
    Outsrgissuehead outsrgissuehead = new Outsrgissuehead();
    if (list.size() == 0 ){
        //产生新的单号 F+ 年 + 月 + 6位流水
        String tmpstr = "F"+curdate.substring(2,6)+"%";
        String maxissuenm = outsrgissueheadService.selectMaxIssuenm(tmpstr);
        if (maxissuenm == null){
            outsrgissuehead.setIssuenm(issuenm);
        }else{
            int num = Integer.valueOf(maxissuenm.substring(5,11)) + 1;
            issuenm = "F" + curdate.substring(2,6) + String.format("%06d",num);
            outsrgissuehead.setIssuenm(issuenm);
        }

        outsrgissuehead.setLifnr(lifnr);
        outsrgissuehead.setMatnr(cardh.getMatnr());
        outsrgissuehead.setPrtflag("");
        outsrgissuehead.setStatus("0");
        outsrgissuehead.setTxz01(cardt.getLtxa1());
        outsrgissuehead.setWerks(cardh.getWerks());
        outsrgissuehead.setCreatedBy(Long.valueOf(userId));
        outsrgissuehead.setCreationDate(new Date());
        outsrgissuehead.setZipuser("");
        outsrgissuehead.setZiptim("");
        outsrgissuehead.setZipdat("");
    }else{
        if (list.get(0).getStatus().equals("0")){
            //使用以前的单号

        }else{
            //产生新的单号 获取当前流水
                outsrgissuehead = list.get(0);
                //产生新的单号 F+ 年 + 月 + 6位流水
                String tmpstr = "F"+curdate.substring(2,6)+"%";
                String maxissuenm = outsrgissueheadService.selectMaxIssuenm(tmpstr);
                if (maxissuenm == null){
                    outsrgissuehead.setIssuenm(issuenm);
                }else{
                    int num = Integer.valueOf(maxissuenm.substring(5,11)) + 1;
                    issuenm = "F" + curdate.substring(2,6) + String.format("%06d",num);
                    outsrgissuehead.setIssuenm(issuenm);
                }
                outsrgissuehead.setLifnr(lifnr);
                outsrgissuehead.setMatnr(cardh.getMatnr());
                outsrgissuehead.setPrtflag("");
                outsrgissuehead.setStatus("0");
                outsrgissuehead.setTxz01(cardt.getLtxa1());
                outsrgissuehead.setWerks(cardh.getWerks());
                outsrgissuehead.setCreatedBy(Long.valueOf(userId));
                outsrgissuehead.setCreationDate(new Date());
                outsrgissuehead.setZipdat("");
                outsrgissuehead.setZiptim("");
                outsrgissuehead.setZipuser("");

        }
    }

    //准备行数据

    Outsrgissue outsrgissue = new Outsrgissue();
//        outsrgissue = service.selectByBarcode(barcode,"1");
//        if (outsrgissue != null && outsrgissue.getIssuenm().equals(outsrgissuehead.getIssuenm())){
//            //  使用以前的老航项目
//            l_update = "X";
//            outsrgissue.setStatus("0");
//            outsrgissue.setLastUpdatedBy(Long.valueOf(userId));
//            outsrgissue.setLastUpdateDate(new Date());
//        }else{//新的行项目
        Long item = 0l;
        outsrgissue = new Outsrgissue();
        if (outsrgissuehead.getIssuenm() == null){
            //使用以前单号
            issuenm = list.get(0).getIssuenm();
            //根据单号查询交货明细最大行号
            List<Outsrgissue> listmx = service.selectByIssuenmDesc(issuenm);
            item = listmx.get(0).getItem() + 1;

            outsrgissue.setDiecd(cardh.getDiecd());
            outsrgissue.setEbeln(outsrgrfe.getEbeln());
            outsrgissue.setEbelp(outsrgrfe.getEbelp());
            outsrgissue.setEtenr("");
            outsrgissue.setGmein(cardh.getGmein());
            outsrgissue.setIssuenm(issuenm);
            outsrgissue.setItem(item);
            outsrgissue.setKtsch(cardt.getKtsch());
            outsrgissue.setLifnr(lifnr);
            outsrgissue.setMatkl(marc.getMatkl());
            outsrgissue.setMatnr(cardh.getMatnr());
            outsrgissue.setMenge(outsrgrfe.getMenge());
            outsrgissue.setSfflg(cardh.getSfflg());
            outsrgissue.setSortl(outsrgrfe.getSortl());
            outsrgissue.setTxz01(cardt.getLtxa1());
            outsrgissue.setVornr(vornr);
            outsrgissue.setVsnda(outsrgrfe.getVsnda());
            outsrgissue.setWerks(cardh.getWerks());
            outsrgissue.setZisnum(Double.valueOf(menge));
            outsrgissue.setZpgdbar(barcode);
            outsrgissue.setCreatedBy(Long.valueOf(userId));
            outsrgissue.setCreationDate(new Date());
            outsrgissue.setStatus("0");
            outsrgissue.setCharg(cardh.getCharg2());
            outsrgissue.setZisdat(curdate1);
            outsrgissue.setZistim(curtim1);
            outsrgissue.setZisuser(userName);

        }else{
            item = 1l;
            outsrgissue.setDiecd(cardh.getDiecd());
            outsrgissue.setEbeln(outsrgrfe.getEbeln());
            outsrgissue.setEbelp(outsrgrfe.getEbelp());
            outsrgissue.setEtenr("");
            outsrgissue.setGmein(cardh.getGmein());
            outsrgissue.setIssuenm(outsrgissuehead.getIssuenm());
            outsrgissue.setItem(item);
            outsrgissue.setKtsch(cardt.getKtsch());
            outsrgissue.setLifnr(lifnr);
            outsrgissue.setMatkl(marc.getMatkl());
            outsrgissue.setMatnr(cardh.getMatnr());
            outsrgissue.setMenge(outsrgrfe.getMenge());
            outsrgissue.setSfflg(cardh.getSfflg());
            outsrgissue.setSortl(outsrgrfe.getSortl());
            outsrgissue.setTxz01(cardt.getLtxa1());
            outsrgissue.setVornr(vornr);
            outsrgissue.setVsnda(outsrgrfe.getVsnda());
            outsrgissue.setWerks(cardh.getWerks());
            outsrgissue.setZisnum(Double.valueOf(menge));
            outsrgissue.setZpgdbar(barcode);
            outsrgissue.setCreatedBy(Long.valueOf(userId));
            outsrgissue.setCreationDate(new Date());
            outsrgissue.setStatus("0");
            outsrgissue.setCharg(cardh.getCharg2());
            outsrgissue.setZisdat(curdate1);
            outsrgissue.setZistim(curtim1);
            outsrgissue.setZisuser(userName);
        }
//        }

    int result = 0;
    SyncOutsrgissueWebserviceUtil syncOutsrgissueWebserviceUtil = new SyncOutsrgissueWebserviceUtil();
    DTOUTSRGISSUEhead head = new DTOUTSRGISSUEhead();
    DTOUTSRGISSUEitem items = new DTOUTSRGISSUEitem();
    if (outsrgissuehead.getIssuenm() != null){
        head.setIssuenm(outsrgissuehead.getIssuenm());
        head.setLifnr(outsrgissuehead.getLifnr());
        head.setMatnr(outsrgissuehead.getMatnr());
        head.setPrtflag(outsrgissuehead.getPrtflag());
        head.setStatus(outsrgissuehead.getStatus());
        head.setTxz01(outsrgissuehead.getTxz01());
        head.setWerks(outsrgissuehead.getWerks());
        head.setZiptim(outsrgissuehead.getZiptim().replaceAll(":",""));
        head.setZipuser(outsrgissuehead.getZipuser());
        head.setZipdat(outsrgissuehead.getZipdat().replaceAll("-",""));
    }else{
        head.setIssuenm("");
        head.setLifnr("");
        head.setMatnr("");
        head.setPrtflag("");
        head.setStatus("");
        head.setTxz01("");
        head.setWerks("");
        head.setZipdat("");
        head.setZiptim("");
        head.setZipuser("");
    }

    if (outsrgissue != null){
        items.setDiecd(outsrgissue.getDiecd());
        items.setEbeln(outsrgissue.getEbeln());
        items.setEbelp(outsrgissue.getEbelp());
        items.setEtenr(outsrgissue.getEtenr());
        items.setGmein(outsrgissue.getGmein());
        items.setIssuenm(outsrgissue.getIssuenm());
        items.setItem(outsrgissue.getItem());
        items.setKtsch(outsrgissue.getKtsch());
        items.setLifnr(outsrgissue.getLifnr());
        items.setMatkl(outsrgissue.getMatkl());
        items.setMatnr(outsrgissue.getMatnr());
        items.setMenge(outsrgissue.getMenge());
        items.setSfflg(outsrgissue.getSfflg());
        items.setSortl(outsrgissue.getSortl());
        items.setTxz01(outsrgissue.getTxz01());
        items.setVornr(outsrgissue.getVornr());
        items.setVsnda(outsrgissue.getVsnda());
        items.setWerks(outsrgissue.getWerks());
        items.setZisnum(outsrgissue.getZisnum());
        items.setZpgdbar(outsrgissue.getZpgdbar());
        items.setCharg(outsrgissue.getCharg());
        items.setStatus(outsrgissue.getStatus());
        items.setZisdat(curdate);
        items.setZistim(curtim);
        items.setZisuser(userName);

    }
    DTOUTSRGISSUEreturn DTRE = new DTOUTSRGISSUEreturn();
    DTRE = syncOutsrgissueWebserviceUtil.receiveConfirmation(head,items);
    if (DTRE.getMSGTY().equals("S")){
        if (outsrgissuehead.getIssuenm() != null){
            result = outsrgissueheadService.insertNewRow(outsrgissuehead);
            if ( result != 1){
                rs.setMessage("数据保存失败，请联系管理员！");
                rs.setSuccess(false);
                return rs;
            }else{
                if (l_update.equals("X")){
                    result = service.updateOutsrgissue(outsrgissue);
                }else{
                    result = service.insertNewRow(outsrgissue);
                }

                if (result != 1){
                    rs.setMessage("数据保存失败，请联系管理员！");
                    rs.setSuccess(false);
                    return rs;
                }
            }
        }else{
            if (l_update.equals("X")){
                result = service.updateOutsrgissue(outsrgissue);
            }else{
                result = service.insertNewRow(outsrgissue);
            }

            if (result != 1){
                rs.setMessage("数据保存失败，请联系管理员！");
                rs.setSuccess(false);
                return rs;
            }
        }
    }else{
        rs.setSuccess(false);
        rs.setMessage(DTRE.getMESSAGE());
        return rs;
    }


    rs.setSuccess(true);
    return rs;
}

    /**
     *  处理冲销外协发料 查询流转卡请求 917110140
     * @param request
     * @param barcode
     * @return
     */
    @RequestMapping(value = {"/wip/outsrgissue/selectForCxwxfl"},method = {RequestMethod.GET})
    @ResponseBody
    ResponseData selectForCxwxfl(HttpServletRequest request, String barcode){
    ResponseData rs = new ResponseData();
    String status = "0";
    Outsrgissue outsrgissue = service.selectByBarcode(barcode,"0");
    if (outsrgissue == null){
        rs.setMessage("该流转卡尚未进行外协发料！不能对其进行冲销外协发料操作！");
        rs.setSuccess(false);
        return rs;
    }

    if (outsrgissue.getStatus().equals("1")){
        rs.setMessage("该流转卡对应外协发料已经被冲销，请勿重复操作！");
        rs.setSuccess(false);
        return rs;
    }

    if (outsrgissue.getStatus().equals("2")){
        rs.setMessage("该流转卡对应外协发料已经收货，不能进行冲销操作！");
        rs.setSuccess(false);
        return rs;
    }

    Outsrgreceipt outsrgreceipt = new Outsrgreceipt();
    outsrgreceipt = outsrgreceiptService.selectByZpgdbarAndStatus(barcode,"0");
    if (outsrgreceipt != null){
        rs.setSuccess(false);
        rs.setMessage("外协已收货，本次外协发货冲销无效！");
        return rs;
    }




    Cardh cardh = new Cardh();
    cardh = cardhService.selectByBarcode(barcode);

    Marc marc = new Marc();
    marc = marcService.selectByMatnr(cardh.getMatnr());

    List list = new ArrayList();
    list.add(outsrgissue);
    list.add(cardh);
    list.add(marc);
    rs.setSuccess(true);
    rs.setRows(list);
    return rs;
    }

    @RequestMapping(value = {"/wip/outsrgissue/cxwxfl"},method = {RequestMethod.GET})
    @ResponseBody
    ResponseData processCxwxfl(HttpServletRequest request, String barcode, String userId){
        ResponseData rs = new ResponseData();
        int sum = 0;
        int sum2 = 0;
        String status = "0";
        Outsrgissue outsrgissue = service.selectByBarcode(barcode,status);

        outsrgissue.setStatus("1");
        outsrgissue.setLastUpdateDate(new Date());
        outsrgissue.setLastUpdatedBy(Long.valueOf(userId));

        sum = service.updateOutsrgissue(outsrgissue);
        if (sum == 1){
            //检查表头对应的行项目是不是全部被冲销 如果是 更新表头的记录状态
            List<Outsrgissue> list = service.selectByIssuenmAndStatus(outsrgissue.getIssuenm(),"0");
            if (list.size() == 0){
                //更新表头状态
                Outsrgissuehead outsrgissuehead = outsrgissueheadService.selectByIssuenm(outsrgissue.getIssuenm());
                outsrgissuehead.setStatus("1");
                outsrgissuehead.setLastUpdateDate(new Date());
                outsrgissuehead.setLastUpdatedBy(Long.valueOf(userId));
                sum2 = outsrgissueheadService.updateOutsrgissueHead(outsrgissuehead);
                if (sum2 == 1){
                    //同步到sap
                    DTOUTSRGISSUEhead head = new DTOUTSRGISSUEhead();
                    DTOUTSRGISSUEitem item = new DTOUTSRGISSUEitem();
                    head.setWerks(outsrgissuehead.getWerks());
                    head.setTxz01(outsrgissuehead.getTxz01());
                    head.setStatus(outsrgissuehead.getStatus());
                    head.setPrtflag(outsrgissuehead.getPrtflag());
                    head.setMatnr(outsrgissuehead.getMatnr());
                    head.setLifnr(outsrgissuehead.getLifnr());
                    head.setIssuenm(outsrgissuehead.getIssuenm());
                    head.setZipuser(outsrgissuehead.getZipuser());
                    head.setZiptim(outsrgissuehead.getZiptim().replaceAll(":",""));
                    head.setZipdat(outsrgissuehead.getZipdat().replaceAll("-",""));

                    item.setStatus(outsrgissue.getStatus());
                    item.setCharg(outsrgissue.getCharg());
                    item.setZpgdbar(outsrgissue.getZpgdbar());
                    item.setZisnum(outsrgissue.getZisnum());
                    item.setWerks(outsrgissue.getWerks());
                    item.setVsnda(outsrgissue.getVsnda());
                    item.setVornr(outsrgissue.getVornr());
                    item.setTxz01(outsrgissue.getTxz01());
                    item.setSortl(outsrgissue.getSortl());
                    item.setSfflg(outsrgissue.getSfflg());
                    item.setMenge(outsrgissue.getMenge());
                    item.setMatnr(outsrgissue.getMatnr());
                    item.setMatkl(outsrgissue.getMatkl());
                    item.setLifnr(outsrgissue.getLifnr());
                    item.setKtsch(outsrgissue.getKtsch());
                    item.setItem(outsrgissue.getItem());
                    item.setIssuenm(outsrgissue.getIssuenm());
                    item.setGmein(outsrgissue.getGmein());
                    item.setEtenr(outsrgissue.getEtenr());
                    item.setEbelp(outsrgissue.getEbelp());
                    item.setEbeln(outsrgissue.getEbeln());
                    item.setDiecd(outsrgissue.getDiecd());
                    item.setZisuser(outsrgissue.getZisuser());
                    item.setZistim(outsrgissue.getZistim().replaceAll(":",""));
                    item.setZisdat(outsrgissue.getZisdat().replaceAll("-",""));

                    SyncOutsrgissueWebserviceUtil syncOutsrgissueWebserviceUtil = new SyncOutsrgissueWebserviceUtil();
                    DTOUTSRGISSUEreturn re = syncOutsrgissueWebserviceUtil.receiveConfirmation(head,item);
                    if (re.getMSGTY().equals("S")){
                        rs.setSuccess(true);
                        rs.setMessage("冲销成功！");
                    }else{
                        rs.setMessage(re.getMESSAGE());
                        rs.setSuccess(false);
                    }

                }else{
                    rs.setSuccess(false);
                    rs.setMessage("冲销失败！");
                }

            }else{
                //同步到sap
                DTOUTSRGISSUEhead head = new DTOUTSRGISSUEhead();
                DTOUTSRGISSUEitem item = new DTOUTSRGISSUEitem();
                head.setWerks("");
                head.setTxz01("");
                head.setStatus("");
                head.setPrtflag("");
                head.setMatnr("");
                head.setLifnr("");
                head.setIssuenm("");
                head.setZipdat("");
                head.setZiptim("");
                head.setZipuser("");

                item.setStatus(outsrgissue.getStatus());
                item.setCharg(outsrgissue.getCharg());
                item.setZpgdbar(outsrgissue.getZpgdbar());
                item.setZisnum(outsrgissue.getZisnum());
                item.setWerks(outsrgissue.getWerks());
                item.setVsnda(outsrgissue.getVsnda());
                item.setVornr(outsrgissue.getVornr());
                item.setTxz01(outsrgissue.getTxz01());
                item.setSortl(outsrgissue.getSortl());
                item.setSfflg(outsrgissue.getSfflg());
                item.setMenge(outsrgissue.getMenge());
                item.setMatnr(outsrgissue.getMatnr());
                item.setMatkl(outsrgissue.getMatkl());
                item.setLifnr(outsrgissue.getLifnr());
                item.setKtsch(outsrgissue.getKtsch());
                item.setItem(outsrgissue.getItem());
                item.setIssuenm(outsrgissue.getIssuenm());
                item.setGmein(outsrgissue.getGmein());
                item.setEtenr(outsrgissue.getEtenr());
                item.setEbelp(outsrgissue.getEbelp());
                item.setEbeln(outsrgissue.getEbeln());
                item.setDiecd(outsrgissue.getDiecd());
                item.setZisdat(outsrgissue.getZisdat().replaceAll("-",""));
                item.setZistim(outsrgissue.getZistim().replaceAll(":",""));
                item.setZisuser(outsrgissue.getZisuser());

                SyncOutsrgissueWebserviceUtil syncOutsrgissueWebserviceUtil = new SyncOutsrgissueWebserviceUtil();
                DTOUTSRGISSUEreturn re = syncOutsrgissueWebserviceUtil.receiveConfirmation(head,item);
                if (re.getMSGTY().equals("S")){
                    rs.setSuccess(true);
                    rs.setMessage("冲销成功！");
                }else{
                    rs.setMessage(re.getMESSAGE());
                    rs.setSuccess(false);
                }
            }

        }else{
            rs.setMessage("冲销失败！");
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     *  处理外协收货 扫描工序流转卡 页面请求  917110140
     * @param request
     * @param barcode
     * @return
     */

    @RequestMapping(value = {"/wip/outsrgissue/selectForwxsh"},method = {RequestMethod.GET})
    @ResponseBody
    ResponseData selectForwxsh(HttpServletRequest request, String barcode){
        ResponseData rs = new ResponseData();
        //1:获取流转卡数据  检查流转卡状态
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);
        if (cardh == null){
            rs.setSuccess(false);
            rs.setMessage("工序流转卡不存在，请检查流转卡号是否正确！");
            return rs;
        }

        if (cardh.getStatus().equals("HOLD")){
            rs.setSuccess(false);
            rs.setMessage("流转卡状态为HOLD，不允许外协收货！");
            return rs;
        }
        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());
        Outsrgreceipt outsrgreceipt = new Outsrgreceipt();
        List<Outsrgreceipt> list3 = new ArrayList<>();
        list3 = outsrgreceiptService.selectByZpgdbarAndStatusM(barcode,null);
        if (list3.size() > 0){
            String l_status_0 = "";
            String l_status_1 = "";
            for (int i = 0;i<list3.size();i++){
                if (list3.get(i).getStatus().equals("0")){
                    l_status_0 = "X";
                }

                if (list3.get(i).getStatus().equals("1")){
                    l_status_1 = "X";
                }
            }

            if (l_status_0.equals("X")){
                rs.setSuccess(false);
                rs.setMessage("该流转卡已进行外协收货，请勿重复处理！");
                return rs;
            }
        }


        //2:获取外协发料单明细数据
        Outsrgissue outsrgissue = new Outsrgissue();
        List<Outsrgissue> list2 = new ArrayList<>();
        list2 = service.selectBybarcodes(barcode,null);
        if (list2.size() == 0){
            rs.setMessage("该流转卡尚未生成外协发料单!");
            rs.setSuccess(false);
            return rs;
        }else{
            String l_status_0 = "";
            String l_status_1 = "";
            String l_status_2 = "";
            for (int i=0;i<list2.size();i++){
                if (list2.get(i).getStatus().equals("0")){
                    l_status_0 = "X";
                    outsrgissue = list2.get(i);
                }

                if (list2.get(i).getStatus().equals("1")){
                    l_status_1 = "X";
                }

                if (list2.get(i).getStatus().equals("2")){
                    l_status_2 = "X";
                }
            }

            if (l_status_2.equals("X")){
                rs.setMessage("流转卡对应的外协发料单已收货！");
                rs.setSuccess(false);
                return rs;
            }else{

                 if (l_status_0.equals("")){

                     if (l_status_1.equals("X")){
                         rs.setMessage("流转卡对应的外协发料单已冲销！");
                         rs.setSuccess(false);
                         return rs;
                     }else{
                         rs.setMessage("该流转卡尚未生成外协发料单！");
                         rs.setSuccess(false);
                         return  rs;
                     }
                 }

            }
        }

        //获取外协发料单表头数据
        Outsrgissuehead outsrgissuehead = new Outsrgissuehead();
        outsrgissuehead = outsrgissueheadService.selectByIssuenm(outsrgissue.getIssuenm());
        if (!outsrgissuehead.getStatus().equals("1")){
            rs.setSuccess(false);
            rs.setMessage("外协发料单尚未完成，不允许进行外协收货！");
            return rs;
        }

        //获取已经收获的记录数据
        List<Outsrgreceipt> listsh = new ArrayList<>();
        listsh = outsrgreceiptService.selectByEbeln(outsrgissue.getEbeln());

        List list = new ArrayList();
        list.add(cardh);
        list.add(marc);
        list.add(outsrgissue);
        list.add(outsrgissuehead);
        if (listsh.size() > 0){
            list.add(listsh);
        }
        rs.setRows(list);
        rs.setSuccess(true);
        return rs;
    }
}
package yj.core.assembonline.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.assembonline.dto.AssembliesOnline;
import yj.core.assembonline.service.IAssembliesOnlineService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wmsxhcard.dto.Wmsxhcard;
import yj.core.wmsxhcard.service.IWmsxhcardService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AssembliesOnlineController extends BaseController {

    @Autowired
    private IAssembliesOnlineService service;
    @Autowired
    private IWmsxhcardService wmsxhcardService;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private ICardhService cardhService;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;


    @RequestMapping(value = "/wip/assemblies/online/query")
    @ResponseBody
    public ResponseData query(AssembliesOnline dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/assemblies/online/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<AssembliesOnline> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/assemblies/online/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<AssembliesOnline> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/assemblies/zpjsx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData zpjsx(HttpServletRequest request, String zxhbar, String lineId, String vornr, String pointNum, String werks, String createdBy) {
        ResponseData rs = new ResponseData();
        Wmsxhcard wmsxhcard = new Wmsxhcard();
        wmsxhcard = wmsxhcardService.selectByBarcode(zxhbar, werks);
        if (wmsxhcard == null) {
            rs.setSuccess(false);
            rs.setMessage("箱号条码错误！请确认箱号条码！");
            return rs;
        }

        if (wmsxhcard.getZsxwc() != null) {
            if (wmsxhcard.getZsxwc().equals("X")) {
                rs.setSuccess(false);
                rs.setMessage("该箱号已上线！不允许重复上线！");
                return rs;
            }
        }


        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById2(Long.parseLong(lineId));
        if (curlzk == null) {
            rs.setMessage("未能获取当前生产线流转卡配置数据，不允许上线！");
            rs.setSuccess(false);
            return rs;
        }

        Afko afko = new Afko();
        if (curlzk.getZpgdbar() == null) {
            rs.setMessage("该生产线不存在当前机加流转卡，不允许上线！");
            rs.setSuccess(false);
            return rs;
        }

        if (curlzk.getZpgdbar().equals("")) {
            rs.setMessage("该生产线不存在当前机加流转卡，不允许上线！");
            rs.setSuccess(false);
            return rs;
        }

        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(curlzk.getZpgdbar());
        if (cardh == null) {
            rs.setMessage("该生产线当前机加流转卡错误，不允许上线！");
            rs.setSuccess(false);
            return rs;
        }


        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        AssembliesOnline assembliesOnline = new AssembliesOnline();
        assembliesOnline.setAssyId(uuidstr);
        assembliesOnline.setOperationCode(pointNum);
        assembliesOnline.setVornr(vornr);
        assembliesOnline.setMatnr(wmsxhcard.getMatnr());
        assembliesOnline.setLineId(lineId);
        assembliesOnline.setMatnr2(cardh.getMatnr());
        assembliesOnline.setLgort(wmsxhcard.getLgort());
        assembliesOnline.setCharg(wmsxhcard.getChargkc());
        assembliesOnline.setLifnr(wmsxhcard.getLifnr());
        assembliesOnline.setSortl(wmsxhcard.getSortl());
        assembliesOnline.setLifnrDate(wmsxhcard.getLifnrDate());
        assembliesOnline.setLifnrLotn(wmsxhcard.getLifnrLotn());
        assembliesOnline.setCartonTyp("3");
        assembliesOnline.setZxhbar(zxhbar);
        assembliesOnline.setZsxnum(wmsxhcard.getMenge());
        assembliesOnline.setGmein(wmsxhcard.getMeins());
        assembliesOnline.setCreatedBy(1001L);
        assembliesOnline.setCreationDate(new Date());
        assembliesOnline.setZzxqty(0D);
        assembliesOnline.setZqjqty(0D);
        assembliesOnline.setZdftqty(0D);
        assembliesOnline.setCreatedBy(Long.parseLong(createdBy));

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

        try {
            int num = service.insertNewData(assembliesOnline);
            wmsxhcard.setZsxwc("X");
            int num2 = wmsxhcardService.updateZsxwc(wmsxhcard);
            if (num == 1 && num2 == 1) {
                transactionManager.commit(status);
                rs.setMessage("上线完成！");
                rs.setSuccess(true);

            } else {
                transactionManager.rollback(status);
                rs.setSuccess(false);
                rs.setMessage("上线失败！请联系管理员！");
                return rs;
            }

        } catch (Exception e) {
            transactionManager.rollback(status);
            rs.setSuccess(false);
            rs.setMessage("上线失败！请联系管理员！");
            return rs;
        }

        return rs;
    }

    /**
     * 取消装配件上线
     *
     * @param request
     * @param zxhbar
     * @param lineId
     * @param vornr
     * @param pointNum
     * @param werks
     * @param createdBy
     * @return
     */
    @RequestMapping(value = {"/wip/assemblies/cxzpjsx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData cxzpjsx(HttpServletRequest request, String zxhbar, String lineId, String vornr, String pointNum, String werks, String createdBy) {
        ResponseData rs = new ResponseData();

        Wmsxhcard wmsxhcard = new Wmsxhcard();
        wmsxhcard = wmsxhcardService.selectByBarcode(zxhbar, werks);
        if (wmsxhcard == null) {
            rs.setMessage("箱号错误，请检查箱号！");
            rs.setSuccess(false);
            return rs;
        }

        AssembliesOnline assembliesOnline = new AssembliesOnline();
        assembliesOnline = service.selectByZxhbar(zxhbar);
        if (assembliesOnline == null) {
            rs.setSuccess(false);
            rs.setMessage("该箱号不存在上线记录，无法进行取消操作！");
            return rs;
        }

        if (assembliesOnline.getZzxqty() > 0 || assembliesOnline.getZqjqty() > 0 || assembliesOnline.getZdftqty() > 0) {
            rs.setSuccess(false);
            rs.setMessage("该箱已经使用，不允许取消上线");
            return rs;
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
        try {
            wmsxhcard.setZsxwc("");
            int num1 = wmsxhcardService.updateZsxwc(wmsxhcard);

            int num2 = service.deleteData(assembliesOnline.getAssyId());

            if (num1 == 1 && num2 == 1) {
                transactionManager.commit(status);
                rs.setMessage("取消上线成功");
                rs.setSuccess(true);
                return rs;
            } else {
                transactionManager.rollback(status);
                rs.setMessage("取消上线失败");
                rs.setSuccess(false);
                return rs;
            }
        } catch (Exception e) {
            transactionManager.rollback(status);
            rs.setMessage("取消上线失败");
            rs.setSuccess(false);
            return rs;
        }

    }
}
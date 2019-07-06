package yj.core.zwipq.controllers;

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
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.lineiocfg.dto.LineioCfg;
import yj.core.lineiocfg.service.ILineioCfgService;
import yj.core.logdtl.dto.Logdtl;
import yj.core.logdtl.service.ILogdtlService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.resb.dto.Resb;
import yj.core.resb.service.IResbService;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.service.IZtbc0018Service;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.dto.Zwipqqj;
import yj.core.zwipq.service.IZwipqService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ZwipqController extends BaseController {

    @Autowired
    private IZwipqService service;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IResbService resbService;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IInOutRecordService iInOutRecordService;
    @Autowired
    private ILineioCfgService iLineioCfgService;
    @Autowired
    private IDftrghlistService dftrghlistService;
    @Autowired
    private IZtbc0018Service ztbc0018Service;
    @Autowired
    private ILogdtlService logdtlService;


    @RequestMapping(value = "/zwipq/query")
    @ResponseBody
    public ResponseData query(Zwipq dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/zwipq/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Zwipq> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/zwipq/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Zwipq> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     *  根据生产线ID 箱号条码 查询在职队列数量 作为最大可取消数量   917110140
     * @param request
     * @param line_id
     * @param zxhbar
     * @param cursum
     * @return
     */
    @RequestMapping(value = {"/zwipq/selectByLineIdAndZxhbar"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByLineIdAndZxhbar(HttpServletRequest request, String line_id, String zxhbar , String cursum ) {
        ResponseData rs = new ResponseData();
        List<Zwipq> list = service.selectByLineIdAndZxhbar(line_id, zxhbar);
        List<Zwipq> listdown = service.selectByLineIdAndZxhbarAndZOFFL(line_id,zxhbar,"1");

        if (list.size() - listdown.size() < Integer.valueOf(cursum)){
            rs.setSuccess(false);
            rs.setMessage("取消上线数量不能大于当前生产线在制队列数量！");
            return rs;
        }

        rs.setSuccess(true);
        return rs;
    }


        @RequestMapping(value = {"/zwipq/selectByZxhbar"}, method = {RequestMethod.GET})
        @ResponseBody
        public ResponseData selectByZxhbar(HttpServletRequest request, String zxhbar, String line_id){
            ResponseData rs = new ResponseData();
            List listall = new ArrayList();
            List<Zwipq> list = service.selectByZxhbar(zxhbar);
            List<Zwipq> listpline = new ArrayList<>();
            Lines lines = new Lines();
            lines = linesService.selectById(Long.valueOf(line_id));
            listall.add(list);
            if (lines.getPlineId() != null){
                listpline = service.selectByLineIdAndZxhbar(lines.getPlineId().toString(),zxhbar);
                listall.add(listpline);
            }


            rs.setRows(listall);
            return rs;
        }
    @RequestMapping(value = {"/zwipq/insertIntoZwipqTmp"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData insertIntoZwipqTmp(HttpServletRequest request, int cursum, Long line_id, String shift, String zpgdbar, String zxhbar, String attr7, String createBy,
                                           String classgrp, String isAll) {
        ResponseData rs = new ResponseData();
        //1；获取产线信息
        Lines lines = linesService.selectById(line_id);

        //查询箱号信息
        Xhcard xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard.getZsxwc() != null){
            if (xhcard.getZsxwc().equals("X")){
                rs.setSuccess(false);
                rs.setMessage("该箱号已完成上线，不允许进行机加上线操作！");
                return rs;
            }
        }


        //查询机加派工单
        Cardh cardh = cardhService.selectByBarcode(zpgdbar);

        Marc marc = marcService.selectByMatnr(xhcard.getMatnr());

        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(Long.valueOf(line_id), classgrp);
        if (curlzk == null){
            rs.setSuccess(false);
            rs.setMessage("未获取到当前生产线，当前机加流转卡信息！");
            return rs;
        }

        //查询队列获取最大序列号
        String line_id_str = line_id.toString();
        Map m = new HashMap();
        m.put("p1", "SEQ_ON_LINE");
        m.put("p2", cursum + 100);
        service.selectMaxQsenq(m);
        Integer qsenq = Integer.valueOf(m.get("p3").toString());
        if (qsenq == 1) {
            curlzk.setZxhbar(zxhbar);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //准备插入的数据集
        List<Zwipq> list = new ArrayList<>();
        for (int i = 0; i < cursum; i++) {
            Zwipq zwipq = new Zwipq();
            UUID uuid = UUID.randomUUID();
            String uuidstr = uuid.toString().replaceAll("-", "");
            zwipq.setZsxjlh(uuidstr);

            zwipq.setArbpr(lines.getArbpl());
            zwipq.setSegOprName(lines.getSegOprName());
            if (lines.getPlineId() == null) {
                zwipq.setPkgLineId("");
            } else {
                zwipq.setPkgLineId(lines.getPlineId().toString());
            }
            zwipq.setShift(shift);
            zwipq.setZpgdbar(zpgdbar);
            zwipq.setZpgdbar2("");
            zwipq.setVornr("0010");
            zwipq.setZxhbar(zxhbar);
            zwipq.setMatnr(xhcard.getMatnr());
            zwipq.setMatnr2(cardh.getMatnr());
            zwipq.setZsxnum(1.0);
            zwipq.setGmein(marc.getMeins());
            zwipq.setLgort(xhcard.getLgort());


            if (lines.getPlineId() == null) {
                zwipq.setLineId(line_id_str);
                zwipq.setPkgLineId(line_id_str);
            } else {
                String pkgline_id_str = lines.getPlineId().toString();
                zwipq.setPkgLineId(pkgline_id_str);
                zwipq.setLineId(line_id_str);
            }
            zwipq.setCharg(xhcard.getChargkc());
            zwipq.setSfflg(attr7);//班标
            zwipq.setDiecd(xhcard.getZmnum());//模号
            zwipq.setQsenq(qsenq.longValue());
            zwipq.setCreatedBy(Long.valueOf(createBy));
            zwipq.setZzxkl(0L);
            zwipq.setZqjkl(0L);
            zwipq.setZoffl(0L);
            zwipq.setStatus(0L);
            zwipq.setZremade(0);
            zwipq.setCreationDate(new Date());
            list.add(zwipq);
            qsenq = qsenq + 1;
        }
        if (list.size() == cursum) {
            int num = service.InsertIntoZwipq(list);
        }
        //如果是毛坯框的第一件物料上线 要更新当前产线的当前毛坯框码
        if (curlzk.getZxhbar() == null ) {
            curlzk.setZxhbar(zxhbar);
            curlzk.setLastUpdatedBy(Long.valueOf(createBy));
            curlzk.setLastUpdateDate(new Date());
            curlzkService.updateZxhbar(curlzk);
        }

        if (!curlzk.getZxhbar().equals(zxhbar)){
            curlzk.setZxhbar(zxhbar);
            curlzk.setLastUpdatedBy(Long.valueOf(createBy));
            curlzk.setLastUpdateDate(new Date());
            curlzkService.updateZxhbar(curlzk);
        }
        //如果勾选了上线完成标识 更新箱号的上线完成标识
        if (isAll.equals("true")) {
            xhcard.setZsxwc("X");
            xhcardService.updateZsxwc(xhcard);
        }

        rs.setSuccess(true);
        return rs;

    }
    /*
    ** 机加上线 插入在制队列
     */
    @RequestMapping(value = {"/zwipq/insertIntoZwipq"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData insertIntoZwipq(HttpServletRequest request, int cursum, Long line_id, String shift, String zpgdbar, String zxhbar, String attr7, String createBy,
                                        String classgrp, String isAll, String uuidH, String bwart, int cynum) {
        ResponseData rs = new ResponseData();
        //1；获取产线信息
        Lines lines = linesService.selectById(line_id);

        //查询箱号信息
        Xhcard xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard.getZsxwc() != null){
            if (xhcard.getZsxwc().equals("X")){
                rs.setSuccess(false);
                rs.setMessage("该箱号已完成上线，本次上线无效！");

                Logdtl logdtl = new Logdtl();
                logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                logdtl.setLogid(uuidH);
                logdtl.setKeyword1(zxhbar);
                logdtl.setKeyword2(zpgdbar);
                logdtl.setKeyword3(line_id.toString());
                logdtl.setKeyword4("");
                logdtl.setOperation("selectByBacode");
                logdtl.setMessage("该箱号已完成上线，本次上线无效！");
                logdtl.setMsgtype("E");
                logdtl.setCreatedBy(Long.parseLong(createBy));
                logdtl.setCreationDate(new Date());
                logdtlService.insertNewDtl(logdtl);
                return rs;
            }
        }

        //查询压铸流转卡
        Cardh cardhyz = cardhService.selectByZxhbar(xhcard.getAufnr(),xhcard.getZxhnum());

        Marc marc = marcService.selectByMatnr(xhcard.getMatnr());

        //查询机加派工单
        Cardh cardh = cardhService.selectByBarcode(zpgdbar);
        //查询机加生产订单
        Afko afko = afkoService.selectByAufnr(cardh.getAufnr());
        //查询机加预留
        List<Resb> resblist = resbService.selectByRsnum(afko.getRsnum());

        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(Long.valueOf(line_id), classgrp);
        if (curlzk == null){
            rs.setSuccess(false);
            rs.setMessage("未获取到当前生产线，当前机加流转卡信息！");
            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(zpgdbar);
            logdtl.setKeyword3(line_id.toString());
            logdtl.setKeyword4("");
            logdtl.setOperation("selectByBacode");
            logdtl.setMessage("未获取到当前生产线，当前机加流转卡信息！");
            logdtl.setMsgtype("E");
            logdtl.setCreatedBy(Long.parseLong(createBy));
            logdtl.setCreationDate(new Date());
            logdtlService.insertNewDtl(logdtl);
            return rs;
        }

        //移动类型不为空，需要进行调账
        if (!bwart.equals("") && cynum != 0){
            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(line_id.toString());
            logdtl.setKeyword3(bwart);
            logdtl.setKeyword4(String.valueOf(cynum));
            logdtl.setOperation("callmigo");
            logdtl.setCreationDate(new Date());
            logdtl.setCreatedBy(Long.valueOf(createBy));


            xhcard = xhcardService.selectByBacode(zxhbar);
            if (xhcard.getZsxwc() != null){
                if (xhcard.getZsxwc().equals("X")){
                    rs.setSuccess(false);
                    rs.setMessage("该箱号已完成上线！上线无效！");
                    logdtl.setMsgtype("E");
                    logdtl.setMessage("该箱号已完成上线！上线无效！");
                    logdtlService.insertNewDtl(logdtl);
                    return rs;
                }
            }


            List<Ztbc0018> list = new ArrayList<>();
            list = ztbc0018Service.selectByZxhbar(zxhbar);
            if (list.size() > 0){
                rs.setSuccess(false);
                rs.setMessage("该箱号已经调账完成，不允许重复调账！");
                logdtl.setMsgtype("E");
                logdtl.setMessage("该箱号已经调账完成，不允许重复调账！");
                logdtlService.insertNewDtl(logdtl);
                return rs;
            }
            DTMIGOReturn dtmigoReturn = service.callMigo(zxhbar, cynum, line_id.toString(), bwart,Integer.parseInt(createBy), zpgdbar);

            if (dtmigoReturn.getMTYPE().equals("S")) {
                //盘点成功
                rs.setMessage("数据调整成功！");
                rs.setSuccess(true);
                rs.setCode("S");
                logdtl.setMsgtype("S");
                logdtl.setMessage("数据调整成功！");
                logdtlService.insertNewDtl(logdtl);
            } else {
                //盘点失败
                rs.setSuccess(true);
                rs.setMessage(dtmigoReturn.getMTMSG());
                logdtl.setMsgtype("E");
                logdtl.setMessage(dtmigoReturn.getMTMSG());
                logdtlService.insertNewDtl(logdtl);
                rs.setCode("E");
            }
        }

        //查询队列获取最大序列号
        String line_id_str = line_id.toString();
        Map m = new HashMap();
        m.put("p1", "SEQ_ON_LINE");
        m.put("p2", cursum + 100);
        service.selectMaxQsenq(m);
        Integer qsenq = Integer.valueOf(m.get("p3").toString());
        if (qsenq == 1) {
            curlzk.setZxhbar(zxhbar);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String newPostingdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        //准备插入的数据集
        List<Zwipq> list = new ArrayList<>();
        for (int i = 0; i < cursum; i++) {
            Zwipq zwipq = new Zwipq();
            UUID uuid = UUID.randomUUID();
            String uuidstr = uuid.toString().replaceAll("-", "");
            zwipq.setZsxjlh(uuidstr);

            zwipq.setArbpr(lines.getArbpl());
            zwipq.setSegOprName(lines.getSegOprName());
            if (lines.getPlineId() == null) {
                zwipq.setPkgLineId("");
            } else {
                zwipq.setPkgLineId(lines.getPlineId().toString());
            }
            zwipq.setShift(shift);
            zwipq.setZpgdbar(zpgdbar);
            zwipq.setZpgdbar2(cardhyz.getZpgdbar());
            zwipq.setVornr("0010");
            zwipq.setZxhbar(zxhbar);
            zwipq.setMatnr(xhcard.getMatnr());
            zwipq.setMatnr2(cardh.getMatnr());
            zwipq.setZsxnum(1.0);
            zwipq.setGmein(marc.getMeins());
            for (int j = 0; j < resblist.size(); j++) {
                if (resblist.get(j).getMatnr().equals(xhcard.getMatnr())) {
                    zwipq.setLgort(resblist.get(j).getLgort());
                }
            }

            if (lines.getPlineId() == null) {
                zwipq.setLineId(line_id_str);
                zwipq.setPkgLineId(line_id_str);
            } else {
                String pkgline_id_str = lines.getPlineId().toString();
                zwipq.setPkgLineId(pkgline_id_str);
                zwipq.setLineId(line_id_str);
            }
            zwipq.setCharg(xhcard.getChargkc());
            zwipq.setSfflg(attr7);//班标
            zwipq.setDiecd(xhcard.getZmnum());//模号
            zwipq.setQsenq(qsenq.longValue());
            zwipq.setCreatedBy(Long.valueOf(createBy));
            zwipq.setZzxkl(0L);
            zwipq.setZqjkl(0L);
            zwipq.setZoffl(0L);
            zwipq.setStatus(0L);
            zwipq.setZremade(0);
            zwipq.setCreationDate(new Date());
            list.add(zwipq);
            qsenq = qsenq + 1;
        }
        if (list.size() == cursum && list.size() != 0) {
            int num = service.InsertIntoZwipq(list);
            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(zpgdbar);
            logdtl.setKeyword3(line_id.toString());
            logdtl.setKeyword4(String.valueOf(cursum));
            logdtl.setOperation("insertIntoZwipq");
            logdtl.setMessage("插入在制队列"+num+"条");
            logdtl.setMsgtype("S");
            logdtl.setCreatedBy(Long.parseLong(createBy));
            logdtl.setCreationDate(new Date());
            logdtlService.insertNewDtl(logdtl);


        }
        //如果是毛坯框的第一件物料上线 要更新当前产线的当前毛坯框码
        if (curlzk.getZxhbar() == null ) {
            curlzk.setZxhbar(zxhbar);
            curlzk.setLastUpdatedBy(Long.valueOf(createBy));
            curlzk.setLastUpdateDate(new Date());
            curlzkService.updateZxhbar(curlzk);

            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(line_id.toString());
            logdtl.setKeyword3("");
            logdtl.setKeyword4("");
            logdtl.setOperation("updateZxhbar");
            logdtl.setMessage("更新当前产线毛坯框码！");
            logdtl.setMsgtype("S");
            logdtl.setCreatedBy(Long.parseLong(createBy));
            logdtl.setCreationDate(new Date());
            logdtlService.insertNewDtl(logdtl);
        }

        if (!curlzk.getZxhbar().equals(zxhbar)){
            curlzk.setZxhbar(zxhbar);
            curlzk.setLastUpdatedBy(Long.valueOf(createBy));
            curlzk.setLastUpdateDate(new Date());
            curlzkService.updateZxhbar(curlzk);

            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(line_id.toString());
            logdtl.setKeyword3("");
            logdtl.setKeyword4("");
            logdtl.setOperation("updateZxhbar");
            logdtl.setMessage("更新当前产线毛坯框码！");
            logdtl.setMsgtype("S");
            logdtl.setCreatedBy(Long.parseLong(createBy));
            logdtl.setCreationDate(new Date());
            logdtlService.insertNewDtl(logdtl);
        }
        //如果勾选了上线完成标识 更新箱号的上线完成标识
        if (isAll.equals("true")) {
            xhcard.setZsxwc("X");
            xhcardService.updateZsxwc(xhcard);

            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2(line_id.toString());
            logdtl.setKeyword3("");
            logdtl.setKeyword4("");
            logdtl.setOperation("updateZsxwc");
            logdtl.setMessage("设置箱号上线完成标识！");
            logdtl.setMsgtype("S");
            logdtl.setCreatedBy(Long.parseLong(createBy));
            logdtl.setCreationDate(new Date());
            logdtlService.insertNewDtl(logdtl);
        }

        rs.setSuccess(true);
        return rs;
    }



    /*
    **机加取消上线 删除在制队列
     */
    @RequestMapping(value = {"/zwipq/writeOffZwipq"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData writeOffZwipq(HttpServletRequest request, String line_id, String zxhbar, String zpgdbar, int cursum, String uuidH, String createdBy) {
        ResponseData rs = new ResponseData();
        Xhcard xhcard = new Xhcard();
        xhcard = xhcardService.selectByBacode(zxhbar);

        Logdtl logdtl = new Logdtl();
        logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        logdtl.setLogid(uuidH);
        logdtl.setKeyword1(zxhbar);
        logdtl.setKeyword2(zpgdbar);
        logdtl.setKeyword3(line_id.toString());
        logdtl.setKeyword4(String.valueOf(cursum));
        logdtl.setOperation("writeOffZwipq");
        logdtl.setCreationDate(new Date());
        logdtl.setCreatedBy(Long.valueOf(createdBy));

        if (xhcard.getZsxwc() != null){
            if (xhcard.getZsxwc().equals("X")){
                rs.setSuccess(false);
                rs.setMessage("该箱号已完成上线，本次取消操作无效！");
                logdtl.setMsgtype("E");
                logdtl.setMessage("该箱号已完成上线，本次取消操作无效！");
                logdtlService.insertNewDtl(logdtl);
                return rs;
            }
        }
        //1：查询当前机加流转卡 当前箱号 所有的队列信息 按照 上线序列号倒序排列
        List<Zwipq> list = service.selectBylineidAndZxhbarAndZpgdbar(line_id, zxhbar, zpgdbar);
        if (list.size() == 0 || list.size() - cursum < 0) {
            rs.setSuccess(false);
            rs.setMessage("队列中没有足够的数量进行取消上线操作！");
            logdtl.setMsgtype("E");
            logdtl.setMessage("该箱号已完成上线，本次取消操作无效！");
            logdtlService.insertNewDtl(logdtl);
        } else {
            List<Zwipq> dellist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                dellist.add(list.get(i));
                if (i == (cursum - 1)) {
                    break;
                }
            }
            //2:删除队列中的这些数据 dellist中的记录
            int sum = service.batchDelete(dellist);
            if (sum == cursum) {
                rs.setSuccess(true);
                rs.setMessage("取消上线记录共：" + sum + " 条！");
                logdtl.setMsgtype("s");
                logdtl.setMessage("取消上线记录共：" + sum + " 条！");
                logdtlService.insertNewDtl(logdtl);
            }
        }

        return rs;
    }

    /*
    **机加上线库存盘点
     */
    @RequestMapping(value = {"/zwipq/callmigo"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData callmigo(HttpServletRequest request, String line_id, String zxhbar, int cynum, String bwart, int createBy, String zpgdbar, String uuidH) {
        ResponseData rd = new ResponseData();
        Logdtl logdtl = new Logdtl();
        logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        logdtl.setLogid(uuidH);
        logdtl.setKeyword1(zxhbar);
        logdtl.setKeyword2(line_id);
        logdtl.setKeyword3(bwart);
        logdtl.setKeyword4(String.valueOf(cynum));
        logdtl.setOperation("callmigo");
        logdtl.setCreationDate(new Date());
        logdtl.setCreatedBy(Long.valueOf(createBy));

        Xhcard xhcard = new Xhcard();
        xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard.getZsxwc() != null){
            if (xhcard.getZsxwc().equals("X")){
                rd.setSuccess(false);
                rd.setMessage("该箱号已完成上线！上线无效！");
                logdtl.setMsgtype("E");
                logdtl.setMessage("该箱号已完成上线！上线无效！");
                logdtlService.insertNewDtl(logdtl);
                return rd;
            }
        }


        List<Ztbc0018> list = new ArrayList<>();
        list = ztbc0018Service.selectByZxhbar(zxhbar);
        if (list.size() > 0){
            rd.setSuccess(false);
            rd.setMessage("该箱号已经调账完成，不允许重复调账！");
            logdtl.setMsgtype("E");
            logdtl.setMessage("该箱号已经调账完成，不允许重复调账！");
            logdtlService.insertNewDtl(logdtl);
            return rd;
        }
        DTMIGOReturn rs = service.callMigo(zxhbar, cynum, line_id, bwart, createBy, zpgdbar);

        if (rs.getMTYPE().equals("S")) {
            //盘点成功
            rd.setMessage("数据调整成功！");
            rd.setSuccess(true);
            rd.setCode("S");
            logdtl.setMsgtype("S");
            logdtl.setMessage("数据调整成功！");
            logdtlService.insertNewDtl(logdtl);
        } else {
            //盘点失败
            rd.setSuccess(true);
            rd.setMessage(rs.getMTMSG());
            logdtl.setMsgtype("E");
            logdtl.setMessage(rs.getMTMSG());
            logdtlService.insertNewDtl(logdtl);
            rd.setCode("E");
        }
        return rd;
    }


    /*
    **查询机加上线数据
     */
    @RequestMapping(value = {"/zwipq/selectforjjsx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectforjjsx(HttpServletRequest request, String line_id) {
        ResponseData rs = new ResponseData();
        List<Zwipq> list = service.selectBylineforjjqj(line_id);
        if (list.size() > 0) {
            List<Zwipqqj> listqj = new ArrayList<>();
            List listtmp = new ArrayList();//存放班标
            for (int i = 0; i < list.size(); i++) {//将队列中所有出现的班标存放到临时list中
                if (listtmp == null) {
                    listtmp.add(list.get(i).getSfflg());
                } else {
                    if (listtmp.indexOf(list.get(i).getSfflg()) == -1) {
                        listtmp.add(list.get(i).getSfflg());
                    } else {
                        continue;
                    }
                }
            }
            for (int i = 0; i < listtmp.size(); i++) {
                Zwipqqj zwipqqj = new Zwipqqj();
                zwipqqj.setId(i);
                zwipqqj.setSfflg(listtmp.get(i).toString());
                int sum = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getSfflg().equals(listtmp.get(i))) {
                        sum = sum + 1;
                        zwipqqj.setMenge(sum);
                    }
                }
                listqj.add(zwipqqj);
            }
            rs.setSuccess(true);
            rs.setRows(listqj);


        } else {
            rs.setSuccess(false);
            rs.setMessage("当前生产线在制队列中无符合条件的数据！");
        }
        return rs;

    }

    /*
    ** line_id:生产线ID ，classgrp：班次，attr6:生产日期，gjcodeval:取件原因代码 lineiocfgval：取件工序
    *  机加取件
     */
    @RequestMapping(value = {"/zwipq/jjqj"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData jjqj(HttpServletRequest request, String sfflg, String cursum, String line_id, String classgrp, String attr6, String qjcodeval, String lineiocfgval, String userId, String attr4,
                             String qxdm, String zissuetxt) {
        ResponseData rs = new ResponseData();
        //1:获取当前产线机加流转卡号
        Curlzk curlzk = curlzkService.selectById(Long.valueOf(line_id), classgrp);
        Cardh cardhjj = cardhService.selectByBarcode(curlzk.getZpgdbar());
        Afko afko = afkoService.selectByAufnr(cardhjj.getAufnr());
        //查询队列中是否包含足够的数量支持取件
        List<Zwipq> listtmp = new ArrayList<>();
        listtmp = service.selectForqj(line_id,sfflg);
        if (listtmp.size() == 0) {
            rs.setSuccess(false);
            rs.setMessage("在制队列中没有班标为：" + sfflg + "的毛坯，不允许进行该班标的取件操作！");
            return rs;
        }

        if (listtmp.size() < Integer.valueOf(cursum)) {
            rs.setSuccess(false);
            rs.setMessage("在制队列中没有足够数量的班标为：" + sfflg + "的毛坯，不允许进行该班标的取件操作！");
            return rs;
        }
        List<Zwipq> listall = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(cursum); i++) {
            listall.add(listtmp.get(i));
        }

        List<InOutRecord> inOutRecordList = new ArrayList<>();
        if (listall.size() > 0) {
            //批量修改zwipq队列里面的取件标识
            for (int i = 0; i < listall.size(); i++) {
                listall.get(i).setZqjkl(1L);
                listall.get(i).setLastUpdatedDate(new Date());
                listall.get(i).setLastUpdatedBy(Long.valueOf(userId));
            }
            service.updateForQj(listall);
            //将个体记录插入到wip_in_out_record
            for (int i = 0; i < listall.size(); i++) {
                InOutRecord inOutRecord = new InOutRecord();
                UUID uuid = UUID.randomUUID();
                String uuidstr = uuid.toString().replaceAll("-", "");
                inOutRecord.setZqjjlh(uuidstr);
                inOutRecord.setZwipqid(listall.get(i).getZsxjlh());
                inOutRecord.setLineId(line_id);
                inOutRecord.setArbpr(listall.get(i).getArbpr());
                inOutRecord.setZbanz(classgrp);
                inOutRecord.setZbanc(attr4);
                inOutRecord.setVornr(lineiocfgval);
                inOutRecord.setZxhbar(listall.get(i).getZxhbar());
                Xhcard xhcard = xhcardService.selectByBacode(listall.get(i).getZxhbar());
                Cardh cardhyz = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
                inOutRecord.setZpgdbar(cardhjj.getZpgdbar());//机加当前流转卡号
                if (xhcard.getZtxt() == null || xhcard.getZtxt().equals("")){
                    inOutRecord.setZpgdbar2(cardhyz.getZpgdbar());//压铸工序流转卡号
                }else{
                    inOutRecord.setZpgdbar2("");//压铸工序流转卡号
                }

                inOutRecord.setMatnr(xhcard.getMatnr());
                inOutRecord.setMatnr2(cardhjj.getMatnr());
                inOutRecord.setZoutnum(1.0);
                inOutRecord.setReflag(0L);
                inOutRecord.setZremade(0);
                inOutRecord.setGmein(xhcard.getMeins());
                inOutRecord.setCharg(xhcard.getChargkc());
                inOutRecord.setDiecd(xhcard.getZmnum());
                inOutRecord.setSfflg(listall.get(i).getSfflg());
                inOutRecord.setZotype(qjcodeval);
                inOutRecord.setZqxdm(qxdm);
                inOutRecord.setZissuetxt(zissuetxt);
                inOutRecord.setZbpjc("");
                inOutRecord.setZjtgx("");
                inOutRecord.setZoplo("");
                inOutRecord.setOutdat(new Date());
                inOutRecord.setOutnam(Long.valueOf(userId));
                inOutRecord.setCreatedBy(Long.valueOf(userId));
                inOutRecord.setCreationDate(new Date());
                inOutRecordList.add(inOutRecord);

            }

            iInOutRecordService.insertQjrecode(inOutRecordList);
            rs.setSuccess(true);
            rs.setMessage("取件成功！数据已保存！");
            return rs;

        } else {
            rs.setSuccess(true);
            rs.setMessage("取件失败！在制队列中没有找到符合条件的取件数据！");
            return rs;
        }

    }

    /*
    **机加还件
     */
    @RequestMapping(value = {"/zwipq/jjhj"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData jjhj(HttpServletRequest request, String sfflg, String vornr, String zotype, String line_id, String classgrp, String userId, String matnr, String werks, int hjtype){
        ResponseData rs = new ResponseData();
        //1:查询取还件记录表中的记录
        //查询取件工序ID

        List<InOutRecord> list = iInOutRecordService.selectNoReflg(line_id,zotype,vornr,sfflg,matnr,hjtype);
        InOutRecord inOutRecord = list.get(0);
        inOutRecord.setReflag(1L);
        //选择在制队列里面的历史数据
          //1：根据ID取在制队里里面的历史记录
            Zwipq zwipqhis = service.selectById(inOutRecord.getZwipqid());
            zwipqhis.setZqjkl(0L);
//        //2：根据line_id 选取在制队列里面所有未打二维码的,未下线的数据
            List<Zwipq> listzwipq = service.selectByLineid(line_id);

          //获取取还件口配置数据
        LineioCfg lineioCfg = iLineioCfgService.selectBYLineVornr(line_id,werks,vornr);
        int num = Integer.valueOf(lineioCfg.getIntvlqty());//还件口距离打码口的工件数量

        //开始调整在制队列顺序号

        if (listzwipq.size() > num){
            List<Zwipq> listupdate = new ArrayList<>();
            zwipqhis.setQsenq(listzwipq.get(num).getQsenq());//获取到准还件的序号.
            zwipqhis.setLastUpdatedDate(new Date());
            zwipqhis.setLastUpdatedBy(Long.valueOf(userId));
            for (int i= 0;i<listzwipq.size();i++){
                if (listzwipq.get(i).getQsenq() >= zwipqhis.getQsenq()){
                    listzwipq.get(i).setQsenq(listzwipq.get(i).getQsenq() + 1);
                    listzwipq.get(i).setLastUpdatedDate(new Date());
                    listzwipq.get(i).setLastUpdatedBy(Long.valueOf(userId));
                    Map m = new HashMap();
                    m.put("p1", "SEQ_ON_LINE");
                    m.put("p2", 1);
                    service.selectMaxQsenq(m);
                    listupdate.add(listzwipq.get(i));//添加到需要修改的队列中
                }
            }

            service.updateZqjklAndQenq(zwipqhis);
            int end = service.updateQsenqBatch(listupdate);
            inOutRecord.setRedat(new Date());
            inOutRecord.setRenam(Long.valueOf(userId));
            inOutRecord.setLastUpdatedBy(Long.valueOf(userId));
            inOutRecord.setLastUpdateDate(new Date());
            iInOutRecordService.updateReflag(inOutRecord);
            if (end == listupdate.size()){
                rs.setSuccess(true);
                rs.setMessage("还件完成");
            }
        }else{
            //获取一个在制队列最大序号
            //更新ZWIPQ 将之前取出的件记录的取件标识还原，修改顺序号
            Map m = new HashMap();
            m.put("p1", "SEQ_ON_LINE");
            m.put("p2", 1);
            service.selectMaxQsenq(m);
            Integer qsenq = Integer.valueOf(m.get("p3").toString());
            zwipqhis.setQsenq(Long.valueOf(qsenq));
            zwipqhis.setLastUpdatedBy(Long.valueOf(userId));
            zwipqhis.setLastUpdatedDate(new Date());
            service.updateZqjklAndQenq(zwipqhis);
            inOutRecord.setLastUpdatedBy(Long.valueOf(userId));
            inOutRecord.setLastUpdateDate(new Date());
            iInOutRecordService.updateReflag(inOutRecord);

            rs.setSuccess(true);
            rs.setMessage("还件完成");
        }
        return rs;
    }

    /*
    ** 查询机加下线数据
     */
    @RequestMapping(value = {"/zwipq/selectForJjxx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectForJjxx(HttpServletRequest request, String line_id, String classgrp){
        ResponseData rs = new ResponseData();
        List<Zwipq> list = new ArrayList<>();
        int sum = 0;
        list = service.selectForJjxx(line_id,classgrp);
        if (list == null){
            rs.setSuccess(false);
            rs.setMessage("在制队列数量为0，无可下线产品！");
        }else{
            for(int i = 0;i<list.size();i++){
                sum = sum + 1;
            }
            rs.setTotal(Long.valueOf(sum));
            rs.setSuccess(true);
        }
        return rs;
    }

    /*
    ** 处理机加下线请求
     */
    @RequestMapping(value = {"/zwipq/sumbitJjxx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData sumbitJjxx(HttpServletRequest request, String line_id, String classgrp, String cursum1, String userId){
        ResponseData rs = new ResponseData();
        List<Zwipq> list = new ArrayList<>();
        list = service.selectForJjxx(line_id,"");//查询队列里面的可下线数据 按照队列序号升序排列
        List<Zwipq> listupdate = new ArrayList<>();
        Integer cursum = Integer.valueOf(cursum1);
        Map m = new HashMap();
        m.put("p1", "SEQ_ON_LINE");
        m.put("p2", cursum + 100);
        service.selectMaxQsenq(m);
        Integer qsenq = Integer.valueOf(m.get("p3").toString());
        List<Zwipq> listtmp = new ArrayList<>();
        for(int i = 0;i<cursum;i++){
            list.get(i).setZoffl(1L);
            list.get(i).setSourceLineId(line_id);//下线时记录源生产线ID
            String uuidtest = list.get(i).getZsxjlh();
            if (!list.get(i).getLineId().equals(list.get(i).getPkgLineId())){//子产线下线 新增队列记录 绑定主产线
                Zwipq zwipqtmp = new Zwipq();
                Lines parline = linesService.selectById(Long.valueOf(list.get(i).getPkgLineId()));
                UUID uuid = UUID.randomUUID();
                String uuidstr = uuid.toString().replaceAll("-", "");
                zwipqtmp.setArbpr(list.get(i).getArbpr());
                if (parline.getSegOprName() == null){
                    zwipqtmp.setSegOprName("");
                }else{
                    zwipqtmp.setSegOprName(parline.getSegOprName());
                }
                zwipqtmp.setPkgLineId(list.get(i).getPkgLineId());
                zwipqtmp.setShift(list.get(i).getShift());
                zwipqtmp.setZpgdbar(list.get(i).getZpgdbar());
                zwipqtmp.setZpgdbar2(list.get(i).getZpgdbar2());
                zwipqtmp.setVornr("0010");
                zwipqtmp.setZxhbar(list.get(i).getZxhbar());
                zwipqtmp.setMatnr(list.get(i).getMatnr());
                zwipqtmp.setMatnr2(list.get(i).getMatnr2());
                zwipqtmp.setZsxnum(1.0);
                zwipqtmp.setGmein(list.get(i).getGmein());
                zwipqtmp.setLgort(list.get(i).getLgort());
                zwipqtmp.setCharg(list.get(i).getCharg());
                zwipqtmp.setSfflg(list.get(i).getSfflg());//班标
                zwipqtmp.setDiecd(list.get(i).getDiecd());//模号
                zwipqtmp.setZzxkl(list.get(i).getZzxkl());
                zwipqtmp.setZqjkl(list.get(i).getZqjkl());
                zwipqtmp.setStatus(list.get(i).getStatus());
                zwipqtmp.setZremade(list.get(i).getZremade());
                zwipqtmp.setZsxjlh(uuidstr);
                zwipqtmp.setLineId(list.get(i).getPkgLineId());
                zwipqtmp.setZoffl(0L);
                zwipqtmp.setCreatedBy(Long.valueOf(userId));
                zwipqtmp.setCreationDate(new Date());
                zwipqtmp.setQsenq(Long.valueOf(qsenq));
                zwipqtmp.setSourceLineId(list.get(i).getLineId());
                listtmp.add(zwipqtmp);
            }
            qsenq = qsenq + 1;
            list.get(i).setLastUpdatedBy(Long.valueOf(userId));
            list.get(i).setLastUpdatedDate(new Date());
            uuidtest = list.get(i).getZsxjlh();
            listupdate.add(list.get(i));

        }

        int num = service.updateZoffl(listupdate);
        if (listtmp.size() > 0){
            num = service.InsertIntoZwipq(listtmp);
            if (num == listtmp.size()){
                rs.setSuccess(true);
                rs.setMessage("下线成功！");
            }else if(num < listtmp.size()){
                rs.setSuccess(false);
                rs.setMessage("部分下线成功！，成功数量："+num);
            }else if(num == 0){
                rs.setSuccess(false);
                rs.setMessage("下线失败！");
            }
        }else{
            if (num == cursum){
                rs.setSuccess(true);
                rs.setMessage("下线成功！");
            }else if(num < cursum){
                rs.setSuccess(false);
                rs.setMessage("部分下线成功！，成功数量："+num);
            }else if(num == 0){
                rs.setSuccess(false);
                rs.setMessage("下线失败！");
            }
        }

        return rs;
    }

    @RequestMapping(value = {"/zwipq/setZsxwc"}, method = {RequestMethod.GET})
    @ResponseBody
    ResponseData updatesxwc(HttpServletRequest request, String zxhbar){
        String uuidH = request.getParameter("uuidH");
        String createBy = request.getParameter("createdBy");
        ResponseData rs = new ResponseData();
        Xhcard xhcard = xhcardService.selectByBacode(zxhbar);
        if (xhcard.getZsxwc() != null){
            if (xhcard.getZsxwc().equals("X")){
                Logdtl logdtl = new Logdtl();
                logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                logdtl.setLogid(uuidH);
                logdtl.setKeyword1(zxhbar);
                logdtl.setKeyword2("");
                logdtl.setKeyword3("");
                logdtl.setKeyword4("");
                logdtl.setOperation("updateZsxwc");
                logdtl.setCreationDate(new Date());
                logdtl.setCreatedBy(Long.valueOf(createBy));
                logdtl.setMessage("该箱号已经上线完成，本次操作无效！");
                logdtl.setMsgtype("E");
                logdtlService.insertNewDtl(logdtl);
                rs.setSuccess(false);
                rs.setMessage("该箱号已经上线完成，本次操作无效！");
                return rs;
            }
        }
        xhcard.setZsxwc("X");
        int i = xhcardService.updateZsxwc(xhcard);
        if (i == 1){
            rs.setMessage("上线完成标识设置成功！");
            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2("");
            logdtl.setKeyword3("");
            logdtl.setKeyword4("");
            logdtl.setOperation("updateZsxwc");
            logdtl.setCreationDate(new Date());
            logdtl.setCreatedBy(Long.valueOf(createBy));
            logdtl.setMessage("上线完成标识设置成功！");
            logdtl.setMsgtype("S");
            logdtlService.insertNewDtl(logdtl);

            rs.setSuccess(true);
        }else{
            rs.setMessage("上线完成标识设置失败！");
            Logdtl logdtl = new Logdtl();
            logdtl.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logdtl.setLogid(uuidH);
            logdtl.setKeyword1(zxhbar);
            logdtl.setKeyword2("");
            logdtl.setKeyword3("");
            logdtl.setKeyword4("");
            logdtl.setOperation("updateZsxwc");
            logdtl.setCreationDate(new Date());
            logdtl.setCreatedBy(Long.valueOf(createBy));
            logdtl.setMessage("上线完成标识设置失败！");
            logdtl.setMsgtype("E");
            logdtlService.insertNewDtl(logdtl);
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     * 处理产线在制队列明细查询页面请求 918100064
     * @param request
     * @param deptId
     * @param lineId
     * @param zremade
     * @param attr1After
     * @param attr1Before
     * @param shift
     * @param sfflg
     * @param diecd
     * @param zxhbar
     * @param zgjbar
     * @param online
     * @param zzxkl
     * @param zqjkl
     * @param zoffl
     * @param status
     * @return
     */
    @RequestMapping(value = "/zwipq/selectZwipq")
    @ResponseBody
    public ResponseData selectZwipq(HttpServletRequest request, String deptId, String lineId, Integer zremade,
                                    String attr1After, String attr1Before, String shift, String sfflg, String diecd,
                                    String zxhbar, String zgjbar, String online, String zzxkl, String zqjkl, String zoffl, String status){
        IRequest requestContext = createRequestContext(request);
        Integer online1 = null,zzxkl1 = null, zqjkl1 = null, zoffl1 = null, status1 = null;
        if (attr1Before != null){
            attr1Before = attr1Before.replace("00:00:00","23:59:59");
        }
        if (attr1After != null){
            attr1After = attr1After.substring(0,10);
        }
        if("Y".equals(zzxkl)){
            zzxkl1 = 1;
        }
        if("Y".equals(zqjkl)){
            zqjkl1 = 1;
        }
        if("Y".equals(zoffl)){
            zoffl1 = 1;
        }
        if("Y".equals(status)){
            status1 = 1;
        }
        if("Y".equals(online)){
            online1 = 1;
        }
        if("N".equals(online)){
            online1 = 0;
        }
        ResponseData rs = new ResponseData();
        List<Zwipq> list = service.selectZwipq(requestContext, deptId,lineId,lineId, zremade, attr1After, attr1Before, shift, sfflg, diecd, zxhbar, zgjbar,online1, zzxkl1, zqjkl1, zoffl1, status1);
        return new ResponseData(list);
    }

    /**
     * 处理期间产品合格率查询页面请求 918100064
     * @param request
     * @param deptId
     * @param lineId
     * @param pmatnr
     * @param attr1After
     * @param attr1Before
     * @param shift
     * @return
     */
    @RequestMapping(value = "/zwipq/selectIORZwipq")
    @ResponseBody
    public ResponseData selectIORZwipq(HttpServletRequest request, String deptId, String lineId, String pmatnr, String attr1After, String attr1Before, String shift){
        IRequest requestContext = createRequestContext(request);
        if (attr1Before != null){
            attr1Before = attr1Before.substring(0,10);
        }
        if (attr1After != null){
            attr1After = attr1After.substring(0,10);
        }
        List<Zwipq> list = service.selectIORZwipq(requestContext,deptId,lineId,pmatnr,attr1After,attr1Before,shift);
        return new ResponseData(list);
    }

    /**
     *  处理机加上线时间检查页面请求
     *  根据扫描的箱号， 当前机加产线ID 获取当前箱号最后一次上线记录的时间
     * @param request
     * @return
     */
    @RequestMapping(value = {"/zwipq/getlastsumbit"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData getlastsumbit(HttpServletRequest request){

        ResponseData rs = new ResponseData();
        String line_id = request.getParameter("line_id");
        String zxhbar = request.getParameter("zxhbar");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Zwipq> list = new ArrayList<>();
        list = service.getlastsumbit(line_id,zxhbar);
        if (list.size() == 0){
            rs.setSuccess(true);
            rs.setCode("S");
        }else if(list.size() > 0) {
            String lasttime = sdf.format(list.get(0).getCreationDate());
            String nowtime = sdf.format(new Date());
            float ca = 0;
            try {
                ca = timeandtime(lasttime,nowtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (ca > 1){
                rs.setSuccess(true);
                rs.setCode("S");
            }else{
                rs.setSuccess(true);
                rs.setCode("E");
                rs.setMessage("机加上线2次提交时间间隔小于1分钟，请稍后再试！");
            }

        }
        return rs;
    }

    public static float timeandtime(String startTime, String endTime) throws ParseException {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        long startLong = start.getTime();
        System.out.println(startLong);
        long endLong = end.getTime();
        System.out.println(endLong);
        // 计算时间差,单位毫秒
        float ms = endLong - startLong;
        System.out.println(ms);
        float day = ms / 24 / 60 / 60 / 1000;
        float hour = ms / 1000 / 60 / 60;
        float minute = ms / 1000 / 60;
        float second = ms / 1000;
        float milliSecond = ms;
        return minute;
    }
}
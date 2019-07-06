package yj.core.qcaudithead.controllers;

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
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.service.IQcauditheadService;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.service.IQcauditlistService;
import yj.core.wipdftrghlist.dto.Dftrghlist;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class QcauditheadController extends BaseController {

    @Autowired
    private IQcauditheadService service;
    @Autowired
    private IQcauditlistService qcauditlistService;
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private ICardhService cardhService;

    @RequestMapping(value = "/wip/qcaudithead/query")
    @ResponseBody
    public ResponseData query(Qcaudithead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/qcaudithead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Qcaudithead> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String createdBy1 = "" + request.getSession().getAttribute("userId");
        for (int i =0;i<dto.size();i++){
            dto.get(i).setLastUpdatedBy(Long.parseLong(createdBy1));
            dto.get(i).setLastUpdateDate(new Date());
        }
        int m = 0;
        m = service.updateRow(dto);
        if (m != dto.size()){
            rs.setSuccess(false);
            rs.setMessage("更新数据失败！");
        }else{
            rs.setSuccess(true);
            rs.setMessage("更新数据成功！");
        }
        return rs;
    }

    @RequestMapping(value = "/wip/qcaudithead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Qcaudithead> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/qcaudithead/selectById"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData selectById(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String werks = request.getParameter("werks");
        String recordid = request.getParameter("recordid");
        List<Qcaudithead> list = new ArrayList<>();
        list = service.selectById(werks,recordid);
        if (list.size() > 0){
            rs.setSuccess(true);
            rs.setRows(list);
        }else{
            rs.setSuccess(false);
            rs.setMessage("没有符合条件的数据记录！");
        }
        return rs;
    }
    @RequestMapping(value = {"/wip/qcaudithead/deleteById"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData deleteById(HttpServletRequest request, @RequestBody List<Qcaudithead> dto){
        ResponseData rs = new ResponseData();
        String werks = dto.get(0).getWerks();
        String recordid = dto.get(0).getRecordid();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
        try{
            int num1 = service.deleteById(werks,recordid);

            List<Qcauditlist> list = qcauditlistService.selectCounts(werks,recordid);

            int num2 = list.size();

            List<InOutRecord> list2 = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord = inOutRecordService.selectById(list.get(i).getZqjjlh());
                inOutRecord.setReflag(list.get(i).getReflag());
                list2.add(inOutRecord);
            }

            int num4 = inOutRecordService.batchUpdateReflag(list2);

            int num3 = qcauditlistService.deleteById(werks,recordid);

            if (num1 == 1 && num2 != 0 && num3 == num2 && num3 == num4){
                transactionManager.commit(status);
                rs.setSuccess(true);
                rs.setMessage("删除不合格品审理单2成功！");
            } else {
                transactionManager.rollback(status);
                rs.setMessage("删除不合格品审理单2失败！");
                rs.setSuccess(false);
            }

        }catch (Exception e){
            transactionManager.rollback(status);
            rs.setMessage("删除不合格品审理单2失败！");
            rs.setSuccess(false);
        }

        return rs;
    }

    @RequestMapping(value = {"/wip/qcaudithead/createQcaudit1"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData createQcaudit1(HttpServletRequest request, @RequestBody List<InOutRecord> dto) throws ParseException {
        ResponseData rs = new ResponseData();
        Qcaudithead qcaudithead = new Qcaudithead();
        List<Qcauditlist> list = new ArrayList<>();
        List<InOutRecord> listinout = new ArrayList<>();
        String userId = "" + request.getSession().getAttribute("userId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                //根据数据 生成不合格品审理单表头
                if (i == 0) {
                    //生成单号
                    String recordID = "";
                    String outdatstr = dto.get(i).getGstrp().toString().substring(0, 10).replace("-", "");
                    //获取当前生产日期 最大流水号
                    String MaxRecordId = service.selectMaxRecordId(dto.get(i).getWerks(), dto.get(i).getAttr1());
                    if (MaxRecordId == null) {
                        recordID = "J" + outdatstr.substring(2, 8) + "001";
                    } else {
                        int number = Integer.valueOf(MaxRecordId.substring(8)) + 1;
                        String numberstr = String.format("%03d", number);
                        recordID = "J" + outdatstr.substring(2, 8) + numberstr;
                    }

                    qcaudithead.setWerks(dto.get(i).getWerks());
                    qcaudithead.setRecordid(recordID);
                    qcaudithead.setSourcetype("Q1");
                    qcaudithead.setDeptResponsible(dto.get(i).getDepartbm());
                    qcaudithead.setLineid(dto.get(i).getLineId());
                    qcaudithead.setLineDesc(dto.get(i).getDeslinetxt());
                    qcaudithead.setShift(dto.get(i).getZbanz());
                    qcaudithead.setKunnr(dto.get(i).getKunnr());
                    qcaudithead.setKunnrDesc(dto.get(i).getSortl());
                    qcaudithead.setZpgdbar(dto.get(i).getZpgdbar());
                    qcaudithead.setMatnr(dto.get(i).getMatnr2());
                    qcaudithead.setMaktx(dto.get(i).getMaktx2());
                    qcaudithead.setMatnr2(dto.get(i).getMatnr());
                    qcaudithead.setMaktx2(dto.get(i).getMaktx());
                    qcaudithead.setZqxdm(dto.get(i).getZqxdm());
                    qcaudithead.setTlevelcode(dto.get(i).getZissuetxt());
                    qcaudithead.setCharg("");//
                    qcaudithead.setSfflg(dto.get(i).getSfflg());
                    qcaudithead.setDiecd(dto.get(i).getDiecd());
                    qcaudithead.setGstrp(sdf.parse(dto.get(i).getGstrp().toString().substring(0, 10)));
                    qcaudithead.setVornr(dto.get(i).getVornr1());
                    qcaudithead.setResponsible(dto.get(i).getUserName());
                    qcaudithead.setDfectQty(Double.valueOf(dto.size()));
                    qcaudithead.setGmein(dto.get(i).getGmein());
                    qcaudithead.setOaPresscls("1");
                    qcaudithead.setOaStatus("0");
                    qcaudithead.setDefctType("");//?
                    qcaudithead.setHandleResults1("0");
                    qcaudithead.setHandleResults3("0");
                    qcaudithead.setScrapQty(0D);
                    qcaudithead.setRworkQty(0D);
                    qcaudithead.setConssQty(0D);
                    qcaudithead.setDfectTxt(dto.get(i).getDfectTxt());
                    qcaudithead.setReportDept("");
                    qcaudithead.setReportor("");
                    qcaudithead.setReportDate(new Date());
                    qcaudithead.setQcEnger(dto.get(i).getUserNameQC());
                    qcaudithead.setCreatedBy(Long.parseLong(userId));
                    qcaudithead.setCreationDate(new Date());
                }

                //准备行数据
                Qcauditlist qcauditlist = new Qcauditlist();
                qcauditlist.setWerks(dto.get(i).getWerks());
                qcauditlist.setRecordid(qcaudithead.getRecordid());
                qcauditlist.setItem(String.format("%04d", 1 + i));
                qcauditlist.setZqjjlh(dto.get(i).getZqjjlh());
                qcauditlist.setZgjbar(dto.get(i).getZgjbar());
                qcauditlist.setZpgdbar(dto.get(i).getZpgdbar());
                qcauditlist.setZxhbar(dto.get(i).getZxhbar());
                qcauditlist.setMatnr(dto.get(i).getMatnr());
                qcauditlist.setCharg("");
                qcauditlist.setZbanc("");
                qcauditlist.setShift("");
                Cardh cardh = new Cardh();
                cardh = cardhService.selectByBarcode(dto.get(i).getZpgdbar2());
                if (cardh.getAttr1() != null){
                    qcauditlist.setYgstrp(sdf.parse(cardh.getAttr1()));
                }
                qcauditlist.setMatnr2(dto.get(i).getMatnr2());
                qcauditlist.setYcharg(dto.get(i).getCharg());
                qcauditlist.setSfflg(dto.get(i).getSfflg());
                qcauditlist.setYzbanc(dto.get(i).getZbanc());
                qcauditlist.setYshift(dto.get(i).getZbanz());
                qcauditlist.setDiecd(dto.get(i).getDiecd());
                qcauditlist.setCode(dto.get(i).getZqxdm());
                qcauditlist.setTlevelcode(dto.get(i).getZissuetxt());
                qcauditlist.setMenge(0D);
                qcauditlist.setDfectQty(dto.get(i).getZoutnum());
                qcauditlist.setGmein(dto.get(i).getGmein());
                qcauditlist.setCreatedBy(Long.parseLong(userId));
                qcauditlist.setCreationDate(new Date());
                qcauditlist.setReflag(dto.get(i).getReflag());
                list.add(qcauditlist);

                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord.setZqjjlh(dto.get(i).getZqjjlh());
                inOutRecord.setLastUpdateDate(new Date());
                inOutRecord.setReflag(4L);
                inOutRecord.setLastUpdatedBy(Long.parseLong(userId));
                listinout.add(inOutRecord);

            }

            if (list.size() > 0) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                //将信息更新到表中
                try {
                    int num1 = service.insertNewRow(qcaudithead);

                    int num2 = qcauditlistService.insertNewRow(list);

                    int num3 = inOutRecordService.batchUpdateReflag(listinout);

                    if (num2 == list.size() && num1 == 1 && num3 == listinout.size()) {
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                        rs.setMessage("不合格品审理单2创建成功！");
                    } else {
                        transactionManager.rollback(status);
                        rs.setMessage("不合格品审理单2创建失败！");
                        rs.setSuccess(false);
                    }
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    rs.setMessage("不合格品审理单2创建失败！");
                    rs.setSuccess(false);
                }

            }
        } else {
            rs.setMessage("不合格品审理单2创建失败！");
            rs.setSuccess(false);
        }
        return rs;
    }

    @RequestMapping(value = {"/wip/qcaudithead/createQcaudit2"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData createQcaudit2(HttpServletRequest request, @RequestBody List<Dftrghlist> dto) {
        ResponseData rs = new ResponseData();
        Qcaudithead qcaudithead = new Qcaudithead();
        List<Qcauditlist> list = new ArrayList<>();
        List<Dftrghlist> listinout = new ArrayList<>();
        String userId = "" + request.getSession().getAttribute("userId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                if (i == 0) {
                    String recordID = "";
                    String gstrpstr = sdf.format(dto.get(i).getGstrp());
                    String outdatstr = gstrpstr.replace("-", "");
                    String MaxRecordId = service.selectMaxRecordId(dto.get(i).getWerks(), gstrpstr);
                    if (MaxRecordId == null) {
                        recordID = "J" + outdatstr.substring(2, 8) + "001";
                    } else {
                        int number = Integer.valueOf(MaxRecordId.substring(8)) + 1;
                        String numberstr = String.format("%03d", number);
                        recordID = "J" + outdatstr.substring(2, 8) + numberstr;
                    }
                    qcaudithead.setWerks(dto.get(i).getWerks());
                    qcaudithead.setRecordid(recordID);
                    qcaudithead.setSourcetype("Q2");
                    qcaudithead.setDeptResponsible(dto.get(i).getDepartbm());
                    qcaudithead.setLineid(dto.get(i).getLineId());
                    qcaudithead.setLineDesc(dto.get(i).getDeslinetxt());
                    qcaudithead.setShift(dto.get(i).getZbanz());
                    qcaudithead.setKunnr(dto.get(i).getKunnr());
                    qcaudithead.setKunnrDesc(dto.get(i).getSortl());
                    qcaudithead.setZpgdbar(dto.get(i).getZpgdbar());
                    qcaudithead.setMatnr(dto.get(i).getMatnr());
                    qcaudithead.setMaktx(dto.get(i).getMaktx());
                    qcaudithead.setMatnr2(dto.get(i).getMatnr2());
                    qcaudithead.setZqxdm(dto.get(i).getZqxdm());
                    qcaudithead.setTlevelcode(dto.get(i).getZissuetxt());
                    qcaudithead.setCharg("");//
                    qcaudithead.setSfflg(dto.get(i).getSfflg());
                    qcaudithead.setDiecd(dto.get(i).getDiecd());
                    qcaudithead.setGstrp(dto.get(i).getGstrp());
                    qcaudithead.setVornr(dto.get(i).getVornr1());
                    qcaudithead.setResponsible(dto.get(i).getUserName());
                    qcaudithead.setDfectQty(Double.valueOf(dto.size()));
                    qcaudithead.setGmein(dto.get(i).getGmein());
                    qcaudithead.setOaPresscls("1");
                    qcaudithead.setOaStatus("0");
                    qcaudithead.setDefctType("");//?
                    qcaudithead.setHandleResults1("0");
                    qcaudithead.setHandleResults3("0");
                    qcaudithead.setScrapQty(0D);
                    qcaudithead.setRworkQty(0D);
                    qcaudithead.setConssQty(0D);
                    qcaudithead.setDfectTxt("");
                    qcaudithead.setReportDept("");
                    qcaudithead.setReportor("");
                    qcaudithead.setReportDate(new Date());
                    qcaudithead.setQcEnger(dto.get(i).getUserNameQC());
                    qcaudithead.setCreatedBy(Long.parseLong(userId));
                    qcaudithead.setCreationDate(new Date());
                }

                //准备行数据
                Qcauditlist qcauditlist = new Qcauditlist();
                qcauditlist.setWerks(dto.get(i).getWerks());
                qcauditlist.setRecordid(qcaudithead.getRecordid());
                qcauditlist.setItem(String.format("%04d", 1 + i));
                //qcauditlist.setZqjjlh(dto.get(i).getZqjjlh());
                //qcauditlist.setZgjbar(dto.get(i).getZgjbar());
                qcauditlist.setZpgdbar(dto.get(i).getZpgdbar());
                qcauditlist.setZxhbar(dto.get(i).getZxhbar());
                qcauditlist.setMatnr(dto.get(i).getMatnr());
                qcauditlist.setCharg("");
                qcauditlist.setZbanc(dto.get(i).getZbanc());
                qcauditlist.setShift(dto.get(i).getZbanz());
                qcauditlist.setGstrp(dto.get(i).getGstrp());
                qcauditlist.setMatnr2(dto.get(i).getMatnr2());
                qcauditlist.setYcharg(dto.get(i).getCharg());
                qcauditlist.setSfflg(dto.get(i).getSfflg());
                qcauditlist.setDiecd(dto.get(i).getDiecd());
                qcauditlist.setCode(dto.get(i).getZqxdm());
                qcauditlist.setTlevelcode(dto.get(i).getZissuetxt());
                qcauditlist.setMenge(0D);
                // qcauditlist.setDfectQty(dto.get(i).getZoutnum());
                qcauditlist.setGmein(dto.get(i).getGmein());
                qcauditlist.setCreatedBy(Long.parseLong(userId));
                qcauditlist.setCreationDate(new Date());
                list.add(qcauditlist);

//                    InOutRecord inOutRecord = new InOutRecord();
//                    inOutRecord.setZqjjlh(dto.get(i).getZqjjlh());
//                    inOutRecord.setLastUpdateDate(new Date());
//                    inOutRecord.setReflag(4L);
//                    inOutRecord.setLastUpdatedBy(Long.parseLong(userId));
//                    listinout.add(inOutRecord);
            }

            if (list.size() > 0) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                //将信息更新到表中
                try {
                    int num1 = service.insertNewRow(qcaudithead);

                    int num2 = qcauditlistService.insertNewRow(list);

                    //int num3 = inOutRecordService.batchUpdateReflag(listinout);

                    if (num2 == list.size() && num1 == 1) {
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                        rs.setMessage("不合格品审理单2创建成功！");
                    } else {
                        transactionManager.rollback(status);
                        rs.setMessage("不合格品审理单2创建失败！");
                        rs.setSuccess(false);
                    }
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    rs.setMessage("不合格品审理单2创建失败！");
                    rs.setSuccess(false);
                }

            }
        }else{
            rs.setSuccess(false);
            rs.setMessage("创建不合格品审理单2失败！");
        }

        return rs;
    }

    @RequestMapping(value = {"/wip/qcaudithead/createQcaudit3"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData createQcaudit3(HttpServletRequest request, @RequestBody List<InOutRecord> dto) throws ParseException {

        ResponseData rs = new ResponseData();
        Qcaudithead qcaudithead = new Qcaudithead();
        List<Qcauditlist> list = new ArrayList<>();
        List<InOutRecord> listinout = new ArrayList<>();
        String userId = "" + request.getSession().getAttribute("userId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                //根据数据 生成不合格品审理单表头
                if (i == 0) {
                    //生成单号
                    String recordID = "";
                    String outdatstr = dto.get(i).getGstrp().toString().substring(0, 10).replace("-", "");
                    //获取当前生产日期 最大流水号
                    String MaxRecordId = service.selectMaxRecordId(dto.get(i).getWerks(), dto.get(i).getAttr1());
                    if (MaxRecordId == null) {
                        recordID = "J" + outdatstr.substring(2, 8) + "001";
                    } else {
                        int number = Integer.valueOf(MaxRecordId.substring(8)) + 1;
                        String numberstr = String.format("%03d", number);
                        recordID = "J" + outdatstr.substring(2, 8) + numberstr;
                    }

                    qcaudithead.setWerks(dto.get(i).getWerks());
                    qcaudithead.setRecordid(recordID);
                    qcaudithead.setSourcetype("Q3");
                    qcaudithead.setDeptResponsible(dto.get(i).getDepartbm());
                    qcaudithead.setLineid(dto.get(i).getLineId());
                    qcaudithead.setLineDesc(dto.get(i).getDeslinetxt());
                    qcaudithead.setShift(dto.get(i).getZbanz());
                    qcaudithead.setKunnr(dto.get(i).getKunnr());
                    qcaudithead.setKunnrDesc(dto.get(i).getSortl());
                    qcaudithead.setZpgdbar(dto.get(i).getZpgdbar());
                    qcaudithead.setMatnr(dto.get(i).getMatnr2());
                    qcaudithead.setMaktx(dto.get(i).getMaktx2());
                    qcaudithead.setMatnr2(dto.get(i).getMatnr());
                    qcaudithead.setMaktx2(dto.get(i).getMaktx());
                    qcaudithead.setZqxdm(dto.get(i).getZqxdm());
                    qcaudithead.setTlevelcode(dto.get(i).getZissuetxt());
                    qcaudithead.setCharg("");//
                    qcaudithead.setSfflg(dto.get(i).getSfflg());
                    qcaudithead.setDiecd(dto.get(i).getDiecd());
                    qcaudithead.setGstrp(sdf.parse(dto.get(i).getGstrp().toString().substring(0, 10)));
                    qcaudithead.setVornr(dto.get(i).getVornr1());
                    qcaudithead.setResponsible(dto.get(i).getUserName());
                    qcaudithead.setDfectQty(Double.valueOf(dto.size()));
                    qcaudithead.setGmein(dto.get(i).getGmein());
                    qcaudithead.setOaPresscls("1");
                    qcaudithead.setOaStatus("0");
                    qcaudithead.setDefctType("");//?
                    qcaudithead.setHandleResults1("0");
                    qcaudithead.setHandleResults3("0");
                    qcaudithead.setScrapQty(0D);
                    qcaudithead.setRworkQty(0D);
                    qcaudithead.setConssQty(0D);
                    qcaudithead.setDfectTxt(dto.get(i).getDfectTxt());
                    qcaudithead.setReportDept("");
                    qcaudithead.setReportor("");
                    qcaudithead.setReportDate(new Date());
                    qcaudithead.setQcEnger(dto.get(i).getUserNameQC());
                    qcaudithead.setCreatedBy(Long.parseLong(userId));
                    qcaudithead.setCreationDate(new Date());
                }

                //准备行数据
                Qcauditlist qcauditlist = new Qcauditlist();
                qcauditlist.setWerks(dto.get(i).getWerks());
                qcauditlist.setRecordid(qcaudithead.getRecordid());
                qcauditlist.setItem(String.format("%04d", 1 + i));
                qcauditlist.setZqjjlh(dto.get(i).getZqjjlh());
                qcauditlist.setZgjbar(dto.get(i).getZgjbar());
                qcauditlist.setZpgdbar(dto.get(i).getZpgdbar());
                qcauditlist.setZxhbar(dto.get(i).getZxhbar());
                qcauditlist.setMatnr(dto.get(i).getMatnr());
                qcauditlist.setCharg("");
                qcauditlist.setZbanc("");
                qcauditlist.setShift("");
                Cardh cardh = new Cardh();
                cardh = cardhService.selectByBarcode(dto.get(i).getZpgdbar2());
                if (cardh.getAttr1() != null){
                    qcauditlist.setYgstrp(sdf.parse(cardh.getAttr1()));
                }
                qcauditlist.setMatnr2(dto.get(i).getMatnr2());
                qcauditlist.setYcharg(dto.get(i).getCharg());
                qcauditlist.setSfflg(dto.get(i).getSfflg());
                qcauditlist.setYzbanc(dto.get(i).getZbanc());
                qcauditlist.setYshift(dto.get(i).getZbanz());
                qcauditlist.setDiecd(dto.get(i).getDiecd());
                qcauditlist.setCode(dto.get(i).getZqxdm());
                qcauditlist.setTlevelcode(dto.get(i).getZissuetxt());
                qcauditlist.setMenge(0D);
                qcauditlist.setDfectQty(dto.get(i).getZoutnum());
                qcauditlist.setGmein(dto.get(i).getGmein());
                qcauditlist.setCreatedBy(Long.parseLong(userId));
                qcauditlist.setCreationDate(new Date());
                qcauditlist.setReflag(dto.get(i).getReflag());
                list.add(qcauditlist);

                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord.setZqjjlh(dto.get(i).getZqjjlh());
                inOutRecord.setLastUpdateDate(new Date());
                inOutRecord.setReflag(4L);
                inOutRecord.setLastUpdatedBy(Long.parseLong(userId));
                listinout.add(inOutRecord);

            }

            if (list.size() > 0) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                //将信息更新到表中
                try {
                    int num1 = service.insertNewRow(qcaudithead);

                    int num2 = qcauditlistService.insertNewRow(list);

                    int num3 = inOutRecordService.batchUpdateReflag(listinout);

                    if (num2 == list.size() && num1 == 1 && num3 == listinout.size()) {
                        transactionManager.commit(status);
                        rs.setSuccess(true);
                        rs.setMessage("不合格品审理单2创建成功！");
                    } else {
                        transactionManager.rollback(status);
                        rs.setMessage("不合格品审理单2创建失败！");
                        rs.setSuccess(false);
                    }
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    rs.setMessage("不合格品审理单2创建失败！");
                    rs.setSuccess(false);
                }

            }
        } else {
            rs.setMessage("不合格品审理单2创建失败！");
            rs.setSuccess(false);
        }
        return rs;

    }
    /**
     * 不合格品审理单2查询页面请求 918100064
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/qcaudithead/selectForQcaudithead")
    @ResponseBody
    public ResponseData selectForQcaudithead(HttpServletRequest request) {
        IRequest requestCtx = createRequestContext(request);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String werks = request.getParameter("werks");
        String deptResponsible = request.getParameter("deptResponsible");
        String lineid = request.getParameter("lineid");
        String shift = request.getParameter("shift");
        String sourcetype = request.getParameter("sourcetype");
        String gstrp = request.getParameter("gstrp");
        Qcaudithead dto = new Qcaudithead();
        dto.setWerks(werks);
        dto.setDeptResponsible(deptResponsible);
        dto.setLineid(lineid);
        dto.setShift(shift);
        dto.setSourcetype(sourcetype);
        if (gstrp != null) {
            try {
                dto.setGstrp(df.parse(gstrp.substring(0, 10)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new ResponseData(service.selectForQcaudithead(requestCtx, dto));
    }
}
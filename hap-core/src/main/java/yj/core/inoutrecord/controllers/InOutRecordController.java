package yj.core.inoutrecord.controllers;

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
import yj.core.inoutrecord.dto.InOutHj;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.service.IQjcodeService;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zudlist.dto.Zudlist;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InOutRecordController extends BaseController {

    @Autowired
    private IInOutRecordService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IQjcodeService qjcodeService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IDftrghlistService dftrghlistService;

    @RequestMapping(value = "/wip/in/out/record/query")
    @ResponseBody
    public ResponseData query(InOutRecord dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/in/out/record/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<InOutRecord> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/in/out/record/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<InOutRecord> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/inoutrecord/selectforjjhj")
    @ResponseBody
    public ResponseData selectforjjhj(HttpServletRequest request, String line_id, String qjcodeval, String lineiocfgval, String matnr, int hjtype) {
        ResponseData rs = new ResponseData();
        IRequest requestContext = createRequestContext(request);
        List<InOutRecord> list = service.selectforjjhj(line_id,qjcodeval,lineiocfgval,matnr,hjtype);
        if (list.size() > 0) {
            List<InOutHj> listhj = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                InOutHj inOutHj = new InOutHj();
                inOutHj.setId(i);
                inOutHj.setSfflg(list.get(i).getSfflg());
                inOutHj.setVORNR(list.get(i).getVornr());
                inOutHj.setZOTYPE(list.get(i).getZotype());
                listhj.add(inOutHj);
            }

            rs.setSuccess(true);
            rs.setRows(listhj);
        } else {
            rs.setSuccess(false);
            rs.setMessage("取件记录中没有符合条件的取件记录!");

        }
        return rs;
    }

    @RequestMapping(value = "/inoutrecord/selectforZud")
    @ResponseBody

        public ResponseData selectforZud(HttpServletRequest request){
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        String matnr2 = request.getParameter("matnr2");
        String creationDateBefore = request.getParameter("creationDateBefore");
        String creationDateAfter = request.getParameter("creationDateAfter");
        String isInclude = request.getParameter("isInclude");
        if (isInclude == null){
            isInclude = "";
        }
        /*if(creationDateAfter != null){
            creationDateAfter = creationDateAfter.replace("00:00:00","23:59:59");
        }*/
        List<InOutRecord> list = new ArrayList<>();
        List<Zudlist> listzuds = new ArrayList<>();
        IRequest requestContext = createRequestContext(request);
        // 判断 line_id 是不是主产线 如果是主产线 需要把 所属的子产线记录都查询出来
//        List<Lines> lines = new ArrayList<>();
//        lines = linesService.selectByPlineId(line_id);
//        if (lines.size() > 0){
//            listzuds = service.selectforZud(line_id,null,classgrp);
//        }else{
//            listzuds = service.selectforZud(null,line_id,classgrp);
//        }
         listzuds = service.selectforZud(line_id,line_id,classgrp,matnr2,creationDateBefore,creationDateAfter,isInclude);

        return new ResponseData(listzuds);
    }

    @RequestMapping(value = "/inoutrecord/selectforZrwk")
    @ResponseBody
    public ResponseData selectforZrwk(HttpServletRequest request){
        String line_id = request.getParameter("line_id");
        String classgrp = request.getParameter("classgrp");
        String zotype = request.getParameter("zotype");
        List<InOutRecord> list = new ArrayList<>();
        List<Zrwklist> listzrwk = new ArrayList<>();
        IRequest requestContext = createRequestContext(request);
        // 判断 line_id 是不是主产线 如果是主产线 需要把 所属的子产线记录都查询出来
//        List<Lines> lines = new ArrayList<>();
//        lines = linesService.selectByPlineId(line_id);
//        if (lines.size() > 0){
//            list = service.selectforZrwk(line_id,null,classgrp,zotype,requestContext);
//        }else{
//            list = service.selectforZrwk(null,line_id,classgrp,zotype,requestContext);
//        }
        list = service.selectforZrwk(line_id,line_id,classgrp,zotype,requestContext);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Zrwklist zrwklist = new Zrwklist();
                zrwklist.setLineId(list.get(i).getLineId());
                zrwklist.setZbanz(list.get(i).getZbanz());
                zrwklist.setZbanc(list.get(i).getZbanc());
                zrwklist.setCharg(list.get(i).getCharg());
                zrwklist.setDiecd(list.get(i).getDiecd());
                zrwklist.setGmein(list.get(i).getGmein());
                zrwklist.setMatnr(list.get(i).getMatnr());
                zrwklist.setMatnr2(list.get(i).getMatnr2());
                zrwklist.setSfflg(list.get(i).getSfflg());
                zrwklist.setZpgdbar(list.get(i).getZpgdbar());
                zrwklist.setZxhbar(list.get(i).getZxhbar());
                zrwklist.setZgjbar(list.get(i).getZgjbar());
                zrwklist.setReviewc("G");
                zrwklist.setArbpr(list.get(i).getArbpr());
                zrwklist.setZotype(list.get(i).getZotype());
                zrwklist.setZqjjlh(list.get(i).getZqjjlh());
                zrwklist.setVornr(list.get(i).getVornr());
                zrwklist.setZpgdbar2(list.get(i).getZpgdbar2());
                //获取取件原因
                Qjcode qjcode = new Qjcode();
                qjcode = qjcodeService.selectById(Integer.valueOf(list.get(i).getZotype()));
                zrwklist.setRcode(qjcode.getRcode());
                //获取机加流转卡数据
                Cardh cardhjj = new Cardh();
                cardhjj = cardhService.selectByBarcode(list.get(i).getZpgdbar());
                Marc marc = marcService.selectByMatnr(cardhjj.getMatnr());
                zrwklist.setMaktx(marc.getMaktx());
                zrwklist.setGstrp(cardhjj.getGstrp());//机加的生产日期
                String gstrp = cardhjj.getGstrp().replace("-","");
                String charg2 = gstrp.substring(2,8) + "000" + list.get(i).getZbanc();
                zrwklist.setCharg2(charg2);//机加批次 根据机加生产订单来拼
                zrwklist.setVornr_old(list.get(i).getVornr());
                zrwklist.setZrnum(1L);
                listzrwk.add(zrwklist);
            }

        }
        return new ResponseData(listzrwk);
    }

    /**
     * 处理产线在制队列汇总表查询的页面请求 918100064
     * @param request
     * @param lineId
     * @param deptId
     * @param zremade
     * @return
     */
    @RequestMapping(value = "/inoutrecord/selectforlines")
    @ResponseBody
    public ResponseData selectforlines(HttpServletRequest request, String lineId, String deptId, Integer zremade) {
        IRequest requestContext = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        List<InOutRecord> list = service.selectforlines(requestContext,lineId,lineId,deptId);
        List<InOutRecord> result = new ArrayList<InOutRecord>();
        if(list.size() > 0 ){
            for(InOutRecord inOutRecord : list){
                String lineId2 = inOutRecord.getLineId();
                String sfflg = inOutRecord.getSfflg();
                String diecd = inOutRecord.getDiecd();
                String matnr = inOutRecord.getMatnr();
                Integer num = service.selectZoutnum(lineId2, zremade,matnr,sfflg,diecd);
                Integer zsxnum = service.selectZsxnum(lineId2, zremade,matnr,sfflg,diecd);
                if(zsxnum != 0 || num != 0){
                    inOutRecord.setZsxnum(zsxnum);
                    inOutRecord.setNum(num);
                    result.add(inOutRecord);
                }
            }
        }
        return new ResponseData(result);
    }

    /**
     *  不合格品审理单2 根据条件取数
     * @param request
     * @return
     */
    @RequestMapping(value = "/inoutrecord/selectForQcaudit")
    @ResponseBody
    public ResponseData selectforQcaudit(HttpServletRequest request, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize){
        ResponseData rs = new ResponseData();
        //String werks = request.getParameter("werks");
        String werks = request.getParameter("werks");
        String matnr = request.getParameter("matnr");
        String matnr2 = request.getParameter("matnr2");
        String gytype = request.getParameter("gytype");
        String deptId = request.getParameter("deptId");
        String line_id = request.getParameter("line_id");
        String zqxdm = request.getParameter("zqxdm");
        String zissuetxt = request.getParameter("zissuetxt");
        String zbanz = request.getParameter("zbanz");
        String gstrp = request.getParameter("gstrp");

        if (gstrp != null){
            gstrp = gstrp.substring(0,10) + "%";
        }

        if (gytype.equals("Q1")){
            if (matnr == null && matnr2 == null){
                rs.setSuccess(false);
                rs.setMessage("条件产品物料号，毛坯物料号必须录入其中一项！");
                return rs;
            }
                  //根据条件查询
                List<InOutRecord> list = service.selectforQcaudit1(werks,line_id,matnr,matnr2,deptId,gstrp,zqxdm,zissuetxt,zbanz);
                if (list.size() > 0) {
                    rs.setRows(list);
                    rs.setSuccess(true);
                }



        }else if (gytype.equals("Q2")){
            if (matnr == null && matnr2 == null){
                rs.setSuccess(false);
                rs.setMessage("条件产品物料号，毛坯物料号必须录入其中一项！");
                return rs;
            }

            List<Dftrghlist> list =  dftrghlistService.selectforQcaudit2(werks,line_id,matnr,matnr2,deptId,gstrp,zqxdm,zissuetxt,zbanz);
            if (list.size() > 0){
                rs.setRows(list);
                rs.setSuccess(true);
            }

        }else if (gytype.equals("Q3")){

            if (matnr == null && matnr2 == null){
                rs.setSuccess(false);
                rs.setMessage("条件产品物料号，毛坯物料号必须录入其中一项！");
                return rs;
            }
            //根据条件查询
            List<InOutRecord> list = service.selectforQcaudit3(werks,line_id,matnr,matnr2,deptId,gstrp,zqxdm,zissuetxt,zbanz);
            if (list.size() > 0) {
                rs.setRows(list);
                rs.setSuccess(true);
            }
        }
        return rs;
    }
}
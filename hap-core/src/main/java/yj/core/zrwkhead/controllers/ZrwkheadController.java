package yj.core.zrwkhead.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.zrwkhead.dto.Zrwkhead;
import yj.core.zrwkhead.dto.recPageDate;
import yj.core.zrwkhead.service.IZrwkheadService;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.service.IZrwklistService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
    public class ZrwkheadController extends BaseController {

    @Autowired
    private IZrwkheadService service;
    @Autowired
    private IInOutRecordService iInOutRecordService;
    @Autowired
    private IZrwklistService zrwklistService;


    @RequestMapping(value = "/wip/zrwkhead/query")
    @ResponseBody
    public ResponseData query(Zrwkhead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/zrwkhead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Zrwkhead> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zrwkhead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Zrwkhead> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    @RequestMapping(value = {"/wip/zrwkhead/createZrwk"},method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData createZrwk(HttpServletRequest request, @RequestBody List<recPageDate> dto){
        ResponseData responseData = new ResponseData();
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<Zrwklist> listitem = new ArrayList<>();
        List<InOutRecord> listinoutRecord = new ArrayList<>();

        if (dto.size() > 0) {
            //创建返工返修审理单表头
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
            Zrwkhead zrwkhead = new Zrwkhead();
            String maxno = service.selectMaxNo(curdate);
            String zudnum = "";
            if (maxno == null) {
                zudnum = "J" + curdate.substring(2, 8) + "001";
            } else {
                String no = maxno.substring(7, 10);
                Integer num = Integer.valueOf(no);
                num = num + 1;
                no = num.toString();
                int times = 3 - no.length();
                for (int i = 0; i < times; i++) {
                    no = "0" + no;
                }
                zudnum = "J" + curdate.substring(2, 8) + no;
            }
            zrwkhead.setZrwknum(zudnum);
            zrwkhead.setArbpr(dto.get(0).getArbpr());
            zrwkhead.setCrdat(curdate);
            zrwkhead.setLineId(dto.get(0).getPlineId());
            zrwkhead.setZotype(dto.get(0).getZotype());
            zrwkhead.setCreatedBy(Long.valueOf(createdBy));
            zrwkhead.setCreationDate(new Date());
            //准备审理单行数据
            for (int i = 0; i < dto.size(); i++) {
                Zrwklist zrwklist = new Zrwklist();
                zrwklist.setZrwknum(zudnum);
                zrwklist.setItem(Integer.valueOf(i + 1).toString());
                zrwklist.setLineId(dto.get(i).getLineId());
                zrwklist.setArbpr(dto.get(i).getArbpr());
                zrwklist.setZqjjlh(dto.get(i).getZqjjlh());
                zrwklist.setZpgdbar(dto.get(i).getZpgdbar());
                if (dto.get(i).getVornr() == null){
                    zrwklist.setVornr("");
                }else{
                    zrwklist.setVornr(dto.get(i).getVornr());
                }

                zrwklist.setZxhbar(dto.get(i).getZxhbar());
                if (zrwklist.getZpgdbar2() == null){
                    zrwklist.setZpgdbar2("");
                }else{
                    zrwklist.setZpgdbar2(dto.get(i).getZpgdbar2());
                }
                zrwklist.setGstrp(dto.get(i).getGstrp());
                zrwklist.setMatnr(dto.get(i).getMatnr());
                zrwklist.setMatnr2(dto.get(i).getMatnr2());
                if (dto.get(i).getZgjbar() == null){
                    zrwklist.setZgjbar("");
                }else{
                    zrwklist.setZgjbar(dto.get(i).getZgjbar());
                }
                zrwklist.setZgjbar(dto.get(i).getZgjbar());
                if (dto.get(i).getZrwktimes() == null){
                    zrwklist.setZrwktimes(0L);
                }else{
                    zrwklist.setZrwktimes(Long.valueOf(dto.get(i).getZrwktimes()));
                }
                zrwklist.setZrnum(Long.valueOf(dto.get(i).getZrnum()));
                zrwklist.setGmein(dto.get(i).getGmein());
                zrwklist.setCharg2(dto.get(i).getCharg2());
                zrwklist.setCharg(dto.get(i).getCharg());
                zrwklist.setDiecd(dto.get(i).getDiecd());
                zrwklist.setSfflg(dto.get(i).getSfflg());
                if (dto.get(i).getZqxdm() == null){
                    zrwklist.setZqxdm("");
                }else{
                    zrwklist.setZqxdm(dto.get(i).getZqxdm());
                }

                zrwklist.setZbanc(dto.get(i).getZbanc());
                zrwklist.setZbanz(dto.get(i).getZbanz());
                zrwklist.setReviewc("G");

                zrwklist.setMark(dto.get(i).getMark());
                zrwklist.setCreatedBy(Long.valueOf(createdBy));
                zrwklist.setReviewc(dto.get(i).getReviewc());
                zrwklist.setCreationDate(new Date());
                listitem.add(zrwklist);

                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord = iInOutRecordService.selectById(dto.get(i).getZqjjlh());
                inOutRecord.setReflag(3L);
                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                inOutRecord.setLastUpdateDate(new Date());
                listinoutRecord.add(inOutRecord);
            }
            int sum = 0;
            if (listitem.size() > 0){
                sum = sum + zrwklistService.insertItem(listitem);
                if (sum == listitem.size()){
                    service.insertHead(zrwkhead);
                    for (int i = 0;i<listinoutRecord.size();i++){
                        iInOutRecordService.updateReflag(listinoutRecord.get(i));
                    }
                }
            }
            responseData.setCode("S");
            responseData.setMessage("返工返修处理单创建成功！");
            responseData.setSuccess(true);
        }
        return responseData;
    }

    }
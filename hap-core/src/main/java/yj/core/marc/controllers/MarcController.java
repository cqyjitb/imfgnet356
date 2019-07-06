package yj.core.marc.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.kanb.marcres.dto.MarcRes;
import yj.kanb.marcres.service.IMarcResService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MarcController extends BaseController {

@Autowired
private IMarcService service;
@Autowired
private IMarcResService iMarcResService;


@RequestMapping(value = "/sap/marc/query")
@ResponseBody
public ResponseData query(Marc dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/marc/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Marc> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/marc/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Marc> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    @RequestMapping(value = "/sap/marc/save")
    @ResponseBody
    public ResponseData saveChange(HttpServletRequest request, @RequestBody List<Marc> dto){
        String createdBy = "" + request.getSession().getAttribute("userId");
        int sum = 0;
        if (dto.size() > 0){
             for (int i = 0;i<dto.size();i++){
                 dto.get(i).setLastUpdatedBy(Long.valueOf(createdBy));
                 dto.get(i).setLastUpdatedDate(new Date());
             }
             sum = service.saveChange(dto);
        }

        ResponseData rs = new ResponseData();
        rs.setSuccess(true);
        rs.setMessage("已经修改数据记录" + sum + "条！");
        return rs;
    }

    /**
     * 根据物料查询 918100064
     * @param request
     * @param matnr
     * @return
     */
    @RequestMapping(value = {"/sap/marc/selectByMatnr"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByMatnr(HttpServletRequest request, String matnr){
        ResponseData rs = new ResponseData();
        Marc marc = service.selectByMatnr(matnr);
        if (marc != null){
            List<Marc> list = new ArrayList<Marc>();
            list.add(marc);
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     * 根据物料查询 918100064
     * @param request
     * @param matnr
     * @return
     */
    @RequestMapping(value = {"/sap/marc/queryByMarc"})
    @ResponseBody
    public ResponseData queryByMarc(HttpServletRequest request, String werks, String matnr){
        IRequest requestCtx = createRequestContext(request);
        List<Marc> list = service.queryByMarc(werks,matnr);
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                Marc marc = list.get(i);
                marc.setBukrs("1000");
                MarcRes marcRes = iMarcResService.selectByMatnr(marc.getBukrs(),marc.getWerks(),marc.getMatnr());
                if(marcRes != null){
                    String fileidAName = service.queryByFileId(marcRes.getFileidA());
                    String fileidBName = service.queryByFileId(marcRes.getFileidB());
                    marc.setFileidA(marcRes.getFileidA());
                    marc.setFileidB(marcRes.getFileidB());
                    marc.setFileidAName(fileidAName);
                    marc.setFileidBName(fileidBName);
                }
            }
        }
        return new ResponseData(list);
    }
}
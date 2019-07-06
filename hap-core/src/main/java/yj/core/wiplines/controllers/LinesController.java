package yj.core.wiplines.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.lineiocfg.dto.LineioCfg;
import yj.core.lineiocfg.service.ILineioCfgService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LinesController extends BaseController {

    @Autowired
    private ILinesService service;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private ILineioCfgService lineioCfgService;

    @RequestMapping(value = "/wip/lines/query")
    @ResponseBody
    public ResponseData query(Lines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/lines/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Lines> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Lines> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     *  处理机加生产线维护页面查询请求 918100064
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/lines/queryLines")
    @ResponseBody
    public ResponseData queryLines(Lines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<Lines> list = service.selectFromPage(dto,requestContext,page,pageSize);
        for(int i=0;i<list.size();i++){
            Lines li = service.selectUnitCode(list.get(i).getDeptId());
            list.get(i).setUnitCode(li.getUnitCode());
            list.get(i).setUname(li.getName());
        }
        return new ResponseData(list);
    }

    /**
     * 处理机加生产线维护页面添加和修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lines/submitLines")
    @ResponseBody
    public ResponseData updateLines(HttpServletRequest request, @RequestBody List<Lines> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userId");
        String str = service.setMessageLines(dto);
        if(str != null){
            rs.setSuccess(false);
            rs.setMessage(str);
            return rs;
        }else{
            String result = service.updateOrInsert(requestCtx,dto,userId);
            rs.setMessage(result);
            return rs;
        }
    }

    /**
     *  机加上线 下线 扫描产线ID 检查    917110140
     * @param request
     * @param line_id
     * @param classgrp
     * @param type
     * @return
     */
    @RequestMapping(value = {"/wip/lines/selectById"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectById(HttpServletRequest request, String line_id, String classgrp, String type) {
        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String erdat = df.format(new Date()).substring(0, 10);
        ResponseData rs = new ResponseData(list);
        //获取生产线信息
        Lines lines = service.selectById(Long.valueOf(line_id));
        if (lines == null) {
            rs.setSuccess(false);
            rs.setMessage("生产线ID错误，请从新输入正确的生产线ID!");
            return rs;
        }

        if (lines.getOnlinetype().equals("1")){
            rs.setSuccess(false);
            rs.setMessage("该生产线上线类型为二维码上线，请在终检台扫码取件/还件！");
            return rs;
        }

        if (type.equals("xx")){

            if (lines.getExecoffFlag() == null){
                rs.setSuccess(false);
                rs.setMessage("该生产线不允许下线确认！");
                return rs;
            }

            if (lines.getExecoffFlag().equals("0")){
                rs.setSuccess(false);
                rs.setMessage("该生产线不允许下线确认！");
                return rs;
            }
        }

        if (type.equals("sx")){
            List<Lines> listtmp = new ArrayList<>();
             listtmp = service.selectByPlineId(line_id.toString());
            if ( listtmp.size() > 0 ){
                rs.setSuccess(false);
                rs.setMessage("该产线为主产线，不允许直接上线！");
                return rs;
            }
        }

        //获取产线当前运行配置
        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(lines.getLineId(), classgrp);
        if (curlzk == null) {
            rs.setSuccess(false);
            rs.setMessage("该生产线无当前处理机加流转卡，请先绑定当前处理流转卡！");
            return rs;
        }


        List<Curlzk> curlzkList = new ArrayList<>();
        curlzkList.add(curlzk);
        List<Cardh> cardhList = new ArrayList<>();
        Cardh cardhjj = cardhService.selectByBarcode(curlzk.getZpgdbar());
        if (cardhjj == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取当前机加流转卡:"+ curlzk.getZpgdbar() +"的数据记录！请联系管理员！");
            return rs;
        }

        Marc marc = marcService.selectByMatnr(cardhjj.getMatnr());

        List<ProductsCfg> listpcfg = new ArrayList<>();
        ProductsCfg pc = new ProductsCfg();
        pc.setLineId(Long.valueOf(line_id));
        pc.setPmatnr(marc.getMatnr());
        pc = productsCfgService.selectByLineidAndPMatnr(Long.valueOf(line_id).toString(),marc.getMatnr());
        if (pc != null){
            listpcfg.add(pc);
        }

        List<Marc> marclist = new ArrayList<>();
        marclist.add(marc);
        cardhList.add(cardhjj);
        List<ProductsCfg> prolist = new ArrayList<>();
        prolist = productsCfgService.selectById(Long.valueOf(line_id));
        if (prolist.size() <= 0) {
            rs.setSuccess(false);
            rs.setMessage("生产线：" + line_id + "尚未配置产品基础信息，请先维护产品配置。");
            return rs;
        }
        rs.setSuccess(true);
        list.add(curlzkList);
        list.add(prolist);
        list.add(lines);
        list.add(cardhList);
        list.add(marclist);
        list.add(listpcfg);
        rs.setRows(list);
        return rs;
    }

    /**
     *
     * 线边库不良毛坯处理，获取生产线信息
     * 根据生产线ID验证生产线ID是否错误，获取生产线信息
     */
    @RequestMapping(value = {"/wip/lines/selectByIdForBlmpcl"}, method = {RequestMethod.GET})
    @ResponseBody
    ResponseData selectByIdForBlmpcl(HttpServletRequest request, Long line_id){

        ResponseData rs = new ResponseData();
        List<Lines> list = new ArrayList<>();
        Lines line = service.selectByIdForBlmpcl(line_id);
        if (line != null){
            list.add(line);
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
            rs.setMessage("未能获取生产线信息,请检查生产线ID是否输入正确！");
        }
        return rs;
    }

    /**
     *  根据生产线ID 获取当前流转卡信息 产线产品配置信息 917110140  机加毛坯查询页面处理。
     * @param request
     * @param line_id
     * @return
     */
    @RequestMapping(value = {"/wip/lines/selectByIdForJjmpck"}, method = {RequestMethod.GET})
    @ResponseBody
    ResponseData selectByIdForJjmpck(HttpServletRequest request, Long line_id){
        ResponseData rs = new ResponseData();
        //获取生产线信息
        Lines lines = service.selectById(Long.valueOf(line_id));
        if (lines == null) {
            rs.setSuccess(false);
            rs.setMessage("生产线ID错误，请从新输入正确的生产线ID!");
            return rs;
        }

        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(lines.getLineId(), null);
        if (curlzk == null) {
            rs.setSuccess(false);
            rs.setMessage("该生产线无当前处理机加流转卡，请先绑定当前处理流转卡！");
            return rs;
        }

        Cardh cardhjj = cardhService.selectByBarcode(curlzk.getZpgdbar());
        if (cardhjj == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取当前机加流转卡:"+ curlzk.getZpgdbar() +"的数据记录！请联系管理员！");
            return rs;
        }
        Marc marcjj = marcService.selectByMatnr(cardhjj.getMatnr());
        if (marcjj == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取物料："+cardhjj.getMatnr()+"主数据记录，请联系管理员！");
            return rs;
        }

        ProductsCfg pc = new ProductsCfg();
        pc.setLineId(Long.valueOf(line_id));
        pc.setPmatnr(marcjj.getMatnr());
        pc = productsCfgService.selectByLineidAndPMatnr(Long.valueOf(line_id).toString(),marcjj.getMatnr());
        if (pc == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取产线："+cardhjj.getMatnr()+"物料："+marcjj.getMatnr()+"的产品配置记录，请联系管理员");
            return rs;

        }

        Marc marcyz = marcService.selectByMatnr(pc.getMatnr());
        if (marcyz == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取物料:" + cardhjj.getMatnr()+ "主数据记录，请联系管理员！");
            return rs;
        }
        List<Curlzk> listcur = new ArrayList<>();
        listcur.add(curlzk);
        List<ProductsCfg> listpc = new ArrayList<>();
        listpc.add(pc);
        List<Marc> listmarc = new ArrayList<>();
        listmarc.add(marcyz);
        List list = new ArrayList();
        list.add(listcur);
        list.add(listpc);
        list.add(listmarc);
        rs.setRows(list);
        return rs;
    }

    /**
     * 扫描产线ID 检查 918100064
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping(value = {"/wip/lines/selectByLineId"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByLineId(HttpServletRequest request, String lineId){
        ResponseData rs = new ResponseData();
        String lineIdtmp = "";
        if (!lineId.equals("")){
            lineIdtmp = lineId.replaceAll("L","");
            lineIdtmp.trim();
            lineId = lineIdtmp;
        }

        Lines lines = service.selectById(Long.valueOf(lineId));
        if (lines != null){
            List<Lines> list = new ArrayList<Lines>();
            list.add(lines);
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     *  装配件上线产线扫描ID
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping(value = {"/wip/lines/selectByLineIdzpjsx"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByLineIdzpjsx(HttpServletRequest request, String lineId){
        ResponseData rs = new ResponseData();
        List list = new ArrayList();
        String lineIdtmp = "";
        if (!lineId.equals("")){
            lineIdtmp = lineId.replaceAll("L","");
            lineIdtmp.trim();
            lineId = lineIdtmp;
        }
        Lines lines = service.selectById(Long.valueOf(lineId));
        if (lines != null){
            list.add(lines);
            List<LineioCfg> listlineiocfg = new ArrayList<>();
            listlineiocfg = lineioCfgService.selectinoutcfgforzbjsx(lineIdtmp,lines.getWerks());
            if (listlineiocfg.size() == 0){
                rs.setSuccess(false);
                rs.setMessage("未能获取到产线配置数据，请联系管理员！");
                return rs;
            }else{
                list.add(listlineiocfg);
                rs.setRows(list);
            }
        }else{
            rs.setSuccess(false);
            rs.setMessage("产线不存在，请确认产线二维码！");
        }


        return rs;
    }
}
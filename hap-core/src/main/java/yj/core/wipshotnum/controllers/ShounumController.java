package yj.core.wipshotnum.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.webservice_queryoldzpgdbar.components.QueryOldZpgdbarUtil;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryParm;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryReturn;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.service.IShotnumService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShounumController extends BaseController {

    @Autowired
    private IShotnumService service;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private QueryOldZpgdbarUtil queryOldZpgdbarUtil;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IInputLogService inputLogService;

    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/queryShotnum")
    @ResponseBody
    public ResponseData queryShotnum(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<Shotnum> shotnum = service.selectShotnum(dto,requestContext);
        return new ResponseData(shotnum);
    }

    /**
     *  保存压射记录 917110140
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/shotnum/commitRow"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData commitRow(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String arbpl = request.getParameter("arbpl");
        String erp_date = request.getParameter("prd_date");
        String shot_start = request.getParameter("shot_start");
        String shot_end = request.getParameter("shot_end");
        String banz = request.getParameter("banz");
        String banc = request.getParameter("banc");
        String zpgdbar = request.getParameter("zpgdbar");
        String ktext = "";
        String userName = request.getParameter("userName");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String crdate = sdf.format(new Date());
        String createdBy = request.getParameter("createdBy");
        String type = request.getParameter("type");
        String werks = "";
        String mode = request.getParameter("mode");
        String smdnum = request.getParameter("mdnum");
        Integer mdnum = 1;
        if (smdnum != null && smdnum != ""){
             mdnum = Integer.valueOf(smdnum);
        }

        String matnr = request.getParameter("matnr");
        String maktx = "";
        Marc marc = new Marc();
        marc = marcService.selectByMatnr(matnr);
        if (marc != null){
            maktx = marc.getMaktx();
        }
//        String maktx = null;
//        try {
//            maktx = new String(request.getParameter("maktx").getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        if (type.equals("old")){
            DtqueryParm parm = new DtqueryParm();
            parm.setZpgdbar(zpgdbar);
            DtqueryReturn re = queryOldZpgdbarUtil.receiveConfirmation(parm);
            ktext = re.getArbpldesc();
            werks = re.getWerks();
        }else{
            List<Afvc> list = new ArrayList<>();
            list = afvcService.selectByArbpl(arbpl);
            if (list.size() > 0){
                ktext = list.get(0).getKtext();
            }

            Cardh cardh = new Cardh();
            cardh = cardhService.selectByBarcode(zpgdbar);
            werks = cardh.getWerks();
        }



        Shotnum shot = new Shotnum();
        shot.setArbpl(arbpl);
        shot.setZpgdbar(zpgdbar);
        shot.setWerks(werks);
        shot.setPrdDate(erp_date);
        shot.setShifts(banc);
        shot.setsClass(banz);
        shot.setShotStart(Long.parseLong(shot_start));
        shot.setShotEnd(Long.parseLong(shot_end));
        shot.setCrnam(userName);
        shot.setKtext(ktext);
        shot.setCreationDate(new Date());
        shot.setCreatedBy(Long.parseLong(createdBy));
        shot.setMatnr(matnr);
        shot.setMaktx(maktx);
        shot.setMdno(mode);
        //shot.setMdnum(mdnum);
        shot.setCrdat(sdf.format(new Date()));
        int i = service.insertRow(shot);

        Double sum = 0D;
        Long shotnum = ( shot.getShotEnd() - shot.getShotStart() ) * mdnum;
        InputLog inputLog = inputLogService.querySumInputlogForShotnum(werks,matnr,arbpl,erp_date,banc);
        if (inputLog != null){
            sum = inputLog.getYeild() + inputLog.getWorkScrap() + inputLog.getRowScrap();
        }
        if (i  == 1){
            rs.setSuccess(true);
            if (sum > shotnum){
                Double tmp = sum - shotnum;

                rs.setMessage("提交保存成功！(报工数量超出压模次数："+ tmp.intValue() +")");
            }else if(sum < shotnum){
                Double tmp = shotnum - sum;
                rs.setMessage("提交保存成功！(压模次数超出报工数量："+ tmp.intValue() +")");
            }else{
                rs.setMessage("提交保存成功！");
            }
        }else{
            rs.setMessage("提交保存失败");
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     *  处理页面请求 根据工厂 工作中心 生产日期 班次 查询记录
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/shotnum/isExit"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData isExit(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String werks = "";
        String arbpl = request.getParameter("arbpl");
        String prd_date = request.getParameter("erp_date");
        String shifts = request.getParameter("banc");
        String zpgdbar = request.getParameter("zpgdbar");
        String type = request.getParameter("type");
        String shot_start = request.getParameter("shot_start");
        String shot_end = request.getParameter("shot_end");

        if (type.equals("old")){
            DtqueryParm parm = new DtqueryParm();
            parm.setZpgdbar(zpgdbar);
            DtqueryReturn re = queryOldZpgdbarUtil.receiveConfirmation(parm);
            if (re.getMsgty().equals("S")){
                werks = re.getWerks();
            }
        }else{
            Cardh cardh = new Cardh();
            cardh = cardhService.selectByBarcode(zpgdbar);
            if (cardh == null){
                rs.setSuccess(false);
                rs.setMessage("压铸流转卡不存在，请检查流转卡！");
                return rs;
            }

            werks = cardh.getWerks();
        }
        List<Shotnum> list = new ArrayList<>();
        list = service.isExit(werks,arbpl,prd_date,shifts);
        String l_error = "";
        if (list.size()>0){
            for (int i =0;i<list.size();i++){
                if (list.get(i).getShotStart() == Long.parseLong(shot_start) && list.get(i).getShotEnd() == Long.parseLong(shot_end)){
                    rs.setMessage("已存在相同的压射号填报记录，不需再填报！");
                    l_error = "X";
                    break;
                }
            }
            if (l_error.equals("X")){
                rs.setRows(list);
                rs.setSuccess(true);
                rs.setCode("E");
            }else{
                rs.setRows(list);
                rs.setMessage("已于创建时间"+list.get(0).getCrdat()+"填报当前班次数据，请确认是否多次填报？");
                rs.setSuccess(true);
                rs.setCode("X");
            }

        }else{
            rs.setSuccess(true);
            rs.setCode("N");
        }
        return rs;
    }

    /**
     *压射号维护页面查询请求 918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/selectShotnum")
    @ResponseBody
    public ResponseData selectShotnum(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<Shotnum> shotnum = service.queryShotnum(dto,requestContext);
        return new ResponseData(shotnum);
    }

    /**
     *压射号维护页面修改请求 918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/updateShotnum")
    @ResponseBody
    public ResponseData updateShotnum(@RequestBody List<Shotnum> dto, HttpServletRequest request) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs =  new ResponseData();
        String userId ="" + request.getSession().getAttribute("userId");
        String result = service.updateShotnum(requestCtx,dto,userId);
        rs.setMessage(result);
        return rs;
    }

    /**
     *压射号维护页面删除请求 918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/deleteShotnum")
    @ResponseBody
    public ResponseData deleteShotnum(@RequestBody List<Shotnum> dto, HttpServletRequest request) {
        service.deleteShotnum(dto);
        return new ResponseData();
    }

    /**
     * 压射号及报工统计表  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/queryShotnum2")
    @ResponseBody
    public ResponseData queryShotnum2(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<Shotnum> shotnum = service.selectShotnum2(dto,requestContext);
        return new ResponseData(shotnum);
    }

    /**
     * 压射号异常明细表  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/queryShotnum3")
    @ResponseBody
    public ResponseData queryShotnum3(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<Shotnum> shotnum = service.selectShotnum3(dto,requestContext);
        return new ResponseData(shotnum);
    }
}
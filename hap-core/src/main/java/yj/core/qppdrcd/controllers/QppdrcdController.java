package yj.core.qppdrcd.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.service.IFevorService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.qppdrcd.dto.Qppdrcd;
import yj.core.qppdrcd.service.IQppdrcdService;
import yj.core.wiplines.service.ILinesService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class QppdrcdController extends BaseController {

    @Autowired
    private IQppdrcdService service;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IFevorService fevorService;


    @RequestMapping(value = "/wip/qppdrcd/query")
    @ResponseBody
    public ResponseData query(Qppdrcd dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/qppdrcd/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Qppdrcd> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qppdrcd/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Qppdrcd> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }


    @RequestMapping(value = "/wip/qppdrcd/queryAll")
    @ResponseBody
    public ResponseData queryAll(HttpServletRequest request, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {

        IRequest requestContext = createRequestContext(request);

        String werks = request.getParameter("werks");
        String pddatbefore = request.getParameter("pddatbefore");
        String pddatafter = request.getParameter("pddatafter");
            String fevor = request.getParameter("fevor");
        return new ResponseData(service.queryAll(werks,pddatbefore,pddatafter,fevor,page,pageSize,requestContext));
    }

    @RequestMapping(value = {"/wip/qppdrcd/insertPdRow"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData insertPdRow(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        Qppdrcd qppdrcd = new Qppdrcd();
        String createdBy = "" + request.getParameter("createdBy");
        String lineId = request.getParameter("lineId") == null ? "" : request.getParameter("lineId");
        String lineName = request.getParameter("lineName") == null ? "" : request.getParameter("lineName");
        String matnr = request.getParameter("matnr") == null ? "" : request.getParameter("matnr");
        String pdnum = request.getParameter("pdnum") == null ? "0" : request.getParameter("pdnum");
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        String num = request.getParameter("num") == null ? "0" : request.getParameter("num");
        String fevorstr = request.getParameter("fevor") == null ? "" : request.getParameter("fevor");
        String zbeiz = request.getParameter("zbeiz") == null ? "" : request.getParameter("zbeiz");
        String operator = request.getParameter("operator") == null ? "" : request.getParameter("operator");
        String werks = "1001";
        String zxhbar = request.getParameter("zxhbar") == null ? "" : request.getParameter("zxhbar");


        qppdrcd.setZxhbar(zxhbar);
        qppdrcd.setCreatedBy(Long.parseLong(createdBy));
        qppdrcd.setOperator(operator);
        qppdrcd.setZbeiz(zbeiz);
        qppdrcd.setLineId(lineId);
        qppdrcd.setLineName(lineName);

        qppdrcd.setMatnr(matnr);
        if (!matnr.equals("")) {
            Marc marc = new Marc();
            marc = marcService.selectByMatnr(matnr);
            qppdrcd.setMaktx(marc.getMaktx());
        } else {
            qppdrcd.setMaktx("");
        }
        if (num != "") {
            qppdrcd.setNum(Double.parseDouble(num));
        } else {
            qppdrcd.setNum(0.0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String rcddat = sdf.format(new Date());

        qppdrcd.setPdnum(Double.parseDouble(pdnum));
        qppdrcd.setCreationDate(new Date());
        qppdrcd.setRcddat(rcddat);
        qppdrcd.setRcdtype(type);
        qppdrcd.setFevor(fevorstr);
        if (!fevorstr.equals("")) {
            Fevor fevor = new Fevor();
            fevor = fevorService.selectByfevorSinger(qppdrcd.getFevor());
            qppdrcd.setFevortxt(fevor.getTxt());
        } else {
            qppdrcd.setFevortxt("");
        }

        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        qppdrcd.setRcdid(uuidstr);
        qppdrcd.setWerks(werks);

        int result = service.insertPdRow(qppdrcd);
        if (result == 1) {
            rs.setMessage("盘点数据保存成功");
            rs.setSuccess(true);
        } else {
            rs.setMessage("盘点数据保存失败");
            rs.setSuccess(false);
        }
        return rs;
    }
}
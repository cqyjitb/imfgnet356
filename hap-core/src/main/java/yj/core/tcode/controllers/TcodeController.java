package yj.core.tcode.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.tcode.dto.Tcode;
import yj.core.tcode.service.ITcodeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TcodeController extends BaseController {

    @Autowired
    private ITcodeService service;


    @RequestMapping(value = "/sap/tcode/query")
    @ResponseBody
    public ResponseData query(Tcode dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/sap/tcode/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Tcode> dto) {
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String msg = service.updateOrInsert(requestCtx, dto);
        rs.setMessage(msg);
        return rs;
    }

    @RequestMapping(value = "/sap/tcode/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Tcode> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sap/tcode/isExit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData isExit(HttpServletRequest request, Tcode dto) {
        ResponseData rs = new ResponseData();

        int i = service.isExit(dto);
        if (i == 1) {
            rs.setCode("S");
        } else {
            rs.setCode("E");
            rs.setMessage("该账号不具备盘点权限！");
        }
        return rs;
    }


    @RequestMapping(value = {"/sap/tcode/getprofile"},method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getProfileByUserName(HttpServletRequest request, String userName) {

        Tcode tcode = new Tcode();
        List<Tcode> list = new ArrayList<>();
        tcode = service.getProfile(userName);
        list.add(tcode);
        return new ResponseData(list);
    }
}
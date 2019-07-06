package yj.core.tmp.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.tmp.dto.Bartobar;
import yj.core.tmp.service.IBartobarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BartobarController
        extends BaseController
{
    @Autowired
    private IBartobarService service;

    @RequestMapping({"/bartobar/query"})
    @ResponseBody
    public ResponseData query(Bartobar dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(this.service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/bartobar/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Bartobar> dto)
    {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(this.service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/bartobar/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Bartobar> dto)
    {
        this.service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value={"/bartobar/getnewbar"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData change(HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        String oldZPGDBAR = request.getParameter("wipZpgdbar");
        List<Bartobar> list = this.service.getNewBar(requestContext, oldZPGDBAR);
        String a = list.toString();
        return new ResponseData(list);
    }
}

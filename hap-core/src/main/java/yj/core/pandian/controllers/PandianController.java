package yj.core.pandian.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.pandian.dto.Pandian;
import yj.core.pandian.service.IPandianService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PandianController extends BaseController {

@Autowired
private IPandianService service;


@RequestMapping(value = "/sap/pandian/queryAlllog")
@ResponseBody
public ResponseData query(Pandian dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.queryAlllog(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/pandian/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<Pandian> dto){
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/sap/pandian/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<Pandian> dto){
    service.batchDelete(dto);
    return new ResponseData();
}

    public Pandian logFormat(Pandian dto){
    /*if(dto.getCreatDateAfter()==null){
        dto.setCreatDateAfter("0000-00-00 00:00:00");
    }
    if (dto.getCreatDateBefore()==null){
        dto.setCreatDateBefore("9999-01-01 23:59:59");
    }*/
    /*if(dto.getPostingDateAfter()==null){
        dto.setPostingDateAfter("0000-00-00");

    }
    if(dto.getPostingDateBefore() ==null){
        dto.setPostingDateBefore("9999-01-01");
    }*/

        if ( dto.getPddatbefore() !=null){
            String cdBefore;
            cdBefore = dto.getPddatbefore();
            cdBefore = dto.getPddatbefore().replace("00:00:00","23:59:59");
            dto.setPddatbefore(cdBefore);

        }
        if(dto.getPddatafter() != null){
            String pdafter;
            pdafter = dto.getPddatafter();
            pdafter = dto.getPddatafter().replace("00:00:00","23:59:59");
            dto.setPddatafter(pdafter);
        }
        return dto;

    }
}
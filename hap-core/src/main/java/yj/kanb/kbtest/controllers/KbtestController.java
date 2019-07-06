package yj.kanb.kbtest.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.kbtest.service.IKbtestService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class KbtestController extends BaseController {
    @Autowired
    private IKbtestService kbtestService;
    @RequestMapping(value = {"/Kbtest/insertKbtest"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData insertKbtest(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        //DataSourceHolder.setDataSources(DataSourceEnum.mySqlDataSource.getKey());
        int i = kbtestService.insertNewData(id);
        if (i == 0){
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

}

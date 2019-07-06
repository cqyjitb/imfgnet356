package yj.kanb.equipment.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.service.IEquipmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EquimentContorller  extends BaseController {

    @Autowired
    private IEquipmentService service;
    @Autowired
    private IAfkoService afkoService;

    @RequestMapping(value = {"/kanb/equipment/selectAfko"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectAfko(HttpServletRequest request){
        //DataSourceHolder.setDataSources(DataSourceEnum.mySqlDataSource.getKey());
        List<Equipment> list = service.selectAllData();
        Afko afko = afkoService.selectByAufnr("1000000411");
        ResponseData rs = new ResponseData();
        rs.setSuccess(true);
        return rs;

    }
    /**
     * 看板设备维护页面查询请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/equipment/queryEquipment"})
    @ResponseBody
    public ResponseData queryEquipment(HttpServletRequest request, Equipment dto){
        IRequest requestContext = createRequestContext(request);
        List<Equipment> list =  service.queryEquipment(dto);
        return new ResponseData(list);
    }
    /**
     * 看板设备维护页面删除请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/equipment/removeEquipment"})
    @ResponseBody
    public ResponseData removeEquipment(HttpServletRequest request, @RequestBody List<Equipment> dto){
        service.deleteEquipment(dto);
        return new ResponseData();
    }
    /**
     * 看板设备维护页面添加请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/equipment/insertEquipment"})
    @ResponseBody
    public ResponseData insertEquipment(HttpServletRequest request, @RequestBody List<Equipment> dto){
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String userId = "" + request.getSession().getAttribute("userId");
        String result = service.insertEquipment(dto,userId);
        if(result != null){
            rs.setSuccess(false);
            rs.setMessage(result);
            return rs;
        }
        return new ResponseData();
    }
    /**
     * 看板设备维护页面修改请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = {"/equipment/updateEquipment"})
    @ResponseBody
    public ResponseData updateEquipment(HttpServletRequest request, @RequestBody List<Equipment> dto){
        IRequest requestContext = createRequestContext(request);
        String userId = "" + request.getSession().getAttribute("userId");
        service.updateEquipment(dto,userId);
        return new ResponseData();
    }
}

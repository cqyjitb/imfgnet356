package yj.core.afko.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.marc.service.IMarcService;
import yj.core.sccl.dto.Sccl;
import yj.core.sccl.service.IScclService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AfkoController
        extends BaseController
{
    @Autowired
    private IAfkoService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IScclService scclService;

    @RequestMapping({"/sap/afko/queryyz"})
    @ResponseBody
    public ResponseData queryyz(Afko dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        if (dto.getGstrp() != null){
            dto.setGstrp(dto.getGstrp().substring(0,10));
        }

        if (dto.getGltrp() != null){
            dto.setGltrp(dto.getGltrp().substring(0,10));
        }
        List<Afko> list = service.selectYaZu(requestContext, dto, page, pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++)
            {
                double gamng_bd = 0.0D;
                double cansum = 0.0D;

                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        if (cardhList.get(j).getEcqty() == null ){
                            gamng_bd += cardhList.get(j).getMenge().doubleValue();
                        }else{
                            if (cardhList.get(j).getEcqty() == 0){
                                gamng_bd += cardhList.get(j).getMenge().doubleValue();
                            }else{
                                gamng_bd += cardhList.get(j).getEcqty().doubleValue();
                            }
                        }

                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                if ( list.get(i).getUmrez() == null){
                    cansum = 0;
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }else{
                    cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / ((list.get(i)).getUmrez().doubleValue()) );
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }

            }
        }
        return new ResponseData(list);
    }
    @RequestMapping({"/sap/afko/queryjj"})
    @ResponseBody
    public ResponseData queryjj(Afko dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        if (dto.getGstrp() != null){
            dto.setGstrp(dto.getGstrp().substring(0,10));
        }

        if (dto.getGltrp() != null){
            dto.setGltrp(dto.getGltrp().substring(0,10));
        }
        List<Afko> list = service.selectJiJa(requestContext, dto, page, pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++)
            {
                double gamng_bd = 0.0D;
                double cansum = 0.0D;

                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        gamng_bd += cardhList.get(j).getMenge().doubleValue();

                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                Sccl sccl = scclService.selectByMatnr(list.get(i).getPlnbez(),list.get(i).getWerks());
                if ( sccl == null){
                    cansum = 0;
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }else{
                    list.get(i).setUmrez(sccl.getZsccl());
                    cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / (sccl.getZsccl().doubleValue()) );
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }

            }
        }
        return new ResponseData(list);
    }

    @RequestMapping({"/sap/afko/queryZuhe"})
    @ResponseBody
    public ResponseData queryZuhe(Afko dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        List<Afko> list = service.selectZuhe(requestContext, dto, page, pageSize);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                //获取机加物料描述
                String qmaktx = marcService.selectByMatnr(list.get(i).getQmatnr()).getMaktx();
                list.get(i).setQmaktx(qmaktx);

                double gamng_bd= 0.0D;
                double cansum = 0.0D;
                //获取压铸订单已经生产流转卡的数量
                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        gamng_bd += cardhList.get(j).getMenge().doubleValue();
                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / ((list.get(i)).getUmrez().doubleValue()) );
                (list.get(i)).setCansum(Double.valueOf(cansum));
            }
        }
        return new ResponseData(list);
    }

    @RequestMapping({"/sap/afko/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Afko> dto)
    {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/afko/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Afko> dto)
    {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     *  根据生产订单号 查询SAP_AFKO  917110140 2019-04-16
     * @return
     */
    @RequestMapping({"/sap/afko/selectByAufnr"})
    @ResponseBody
    public ResponseData selectByAufnr(HttpServletRequest request){
        ResponseData rs = new ResponseData();
            String aufnr = request.getParameter("aufnr");
        String matnr = request.getParameter("matnr");

        Afko afko = new Afko();
        afko = service.selectByAufnr(aufnr);
        if (afko == null){
            rs.setSuccess(false);
            rs.setMessage("生产订单不存在！请重新输入生产订单号！");
        }else{
            String status = afko.getStatus();
            if (status.contains("REL"))
            {
                rs.setSuccess(false);
                rs.setMessage("该生产订单已关闭！请重新输入生产订单号！");
            }else{
                if (!afko.getPlnbez().equals(matnr)){
                    rs.setSuccess(false);
                    rs.setMessage("生产订单物料与不合格品审理单2物料不一致！请重新输入生产订单号！");
                }else{
                    List<Cardh> cardhList = new ArrayList<>();
                    cardhList = cardhService.selectByAufnr(aufnr);
                    if (cardhList.size() > 0){
                        rs.setSuccess(true);
                    }else{
                        rs.setSuccess(false);
                        rs.setMessage("该生产订单尚未生成工序流转卡，请更换生产订单处理！");
                    }

                }
            }
        }
        return rs;
    }
}

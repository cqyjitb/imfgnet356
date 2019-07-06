package yj.core.batchpds.controllers;

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
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.appidconf.dto.Appidconf;
import yj.core.appidconf.service.IAppidconfService;
import yj.core.batchpds.dto.Batchpdlogs;
import yj.core.batchpds.dto.Batchpdsource;
import yj.core.batchpds.service.IBatchpdlogsService;
import yj.core.batchpds.service.IBatchpdsourceService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.service.ICardhstService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class BatchpdsourceController extends BaseController {

    @Autowired
    private IBatchpdsourceService service;

    @Autowired
    private IInputLogService inputLogService;

    @Autowired
    private IBatchpdlogsService batchpdlogsService;

    @Autowired
    private ICardhService cardhService;

    @Autowired
    private ICardhstService cardhstService;

    @Autowired
    private IAfkoService afkoService;

    @Autowired
    private IAfvcService afvcService;

    @Autowired
    private ICardtService cardtService;

    @Autowired
    private IAppidconfService appidconfService;

    @Autowired
    private IXhcardService xhcardService;

    @Autowired
    private IOutsrgissueService outsrgissueService;

    @RequestMapping(value = "/sap/batchpdsource/querybyflag")
    @ResponseBody
    public ResponseData query(Batchpdsource dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.querybyflag(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/sap/batchpdsource/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Batchpdsource> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/batchpdsource/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Batchpdsource> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/dispatch/dopandian")
    @ResponseBody
    public ResponseData dopandian(HttpServletRequest request, @RequestBody List<Batchpdsource> dto) {
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<InputLog> list = new ArrayList();
        int num = 0;
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                IRequest requestContext = createRequestContext(request);
                Batchpdsource bs = new Batchpdsource();
                Batchpdlogs logs = new Batchpdlogs();
                num = num + 1;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String newPostingdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
//                    String newPostingdate = "20180731";
                String dispatch = dto.get(i).getZpgdbar();
                InputLog inputLogtmp = new InputLog();
                inputLogtmp.setDispatch(dispatch);
                String operation = inputLogService.queryDispatchMaxOperation(dispatch);
                inputLogtmp.setOperation(operation);
                logs.setZpgdbar(dispatch);
                logs.setVornr(operation);
                if (operation == null) {
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("派工单错误，或工序错误！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }
                InputLog wirteoffinput = new InputLog();
                wirteoffinput = inputLogService.queryByDispatchAndOperation(inputLogtmp);
                //先记录原始报工记录的合格品数量，报废品数量，为后续重新报工做准备。
                if (wirteoffinput == null) {
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("派工单错误，或工序错误！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }
                Double yeild = wirteoffinput.getYeild();
                Double work_scrap = wirteoffinput.getWorkScrap();
                Double row_scrap = wirteoffinput.getRowScrap();

                //查询到需要冲销的记录之后第一步 进行冲销
                wirteoffinput.setPostingDate(newPostingdate);
                DTPP001ReturnResult dtpp001ReturnResultcx = inputLogService.writeOffDispatch(wirteoffinput);

                if (dtpp001ReturnResultcx.getMSGTY().equals("S")) {
                    //冲销成功
                    //1：记录日志

                    try {
//                                Thread.sleep(2000);
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (Exception e) {

                    }

                    logs.setAufnr(wirteoffinput.getOrderno());
                    logs.setMatnr(wirteoffinput.getMaterial());
                    logs.setWirteoffdate(newPostingdate);
                    logs.setWirteoffflag("S");
                    logs.setWirteoffmsg("冲销成功");
                    //2：重新报工
                    wirteoffinput.setYeild(0.0D);
                    wirteoffinput.setWorkScrap(yeild + work_scrap);
                    wirteoffinput.setRowScrap(row_scrap);
                    wirteoffinput.setPostingDate(newPostingdate);
                    wirteoffinput.setDispatchLogicID(wirteoffinput.getBarcode().substring(14, 18));
                    wirteoffinput.setCreated_by(createdBy);
                    DTPP001ReturnResult dtpp001ReturnResultbg = inputLogService.inputDispatch(wirteoffinput);

                    if (dtpp001ReturnResultbg.getMSGTY().equals("S")) {
                        //重新报工成功
                        logs.setPostdate(newPostingdate);
                        logs.setPostflag("S");
                        logs.setPostmsg("报工成功");

                        bs.setZpgdbar(dispatch);
                        bs.setZflag("S");
                        service.updateflag(bs);
                        try {
//                                Thread.sleep(2000);
                            TimeUnit.MILLISECONDS.sleep(2000);
                        } catch (Exception e) {

                        }

                    } else {
                        logs.setPostdate(newPostingdate);
                        logs.setPostflag("E");
                        logs.setPostmsg("报工失败:" + dtpp001ReturnResultbg.getMESSAGE());
                        bs.setZpgdbar(dispatch);
                        bs.setZflag("E");
                        service.updateflag(bs);

                    }

                } else {
                    //冲销失败，记录日志 进行下一条
                    logs.setWirteoffdate(newPostingdate);
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("冲销失败:" + dtpp001ReturnResultcx.getMESSAGE());
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    batchpdlogsService.insert(requestContext, logs);
                    continue;
                }
                //日志写数据库

                batchpdlogsService.insert(requestContext, logs);
            }
        }
        ResponseData rs = new ResponseData();
        rs.setMessage("共处理" + num + "笔盘点记录！,具体查看批处理日志记录！");
        rs.setSuccess(true);
        return rs;
    }

    @RequestMapping(value = "/dispatch/dopandian2")
    @ResponseBody
    public ResponseData dopandian2(HttpServletRequest request, @RequestBody List<Batchpdsource> dto) {
        ResponseData rs = new ResponseData();
        String createdBy = "" + request.getSession().getAttribute("userId");
        List<InputLog> list = new ArrayList();
        int num = 0;
        if (dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                IRequest requestContext = createRequestContext(request);
                Batchpdsource bs = new Batchpdsource();
                Batchpdlogs logs = new Batchpdlogs();
                Cardh cardh  = new Cardh();
                num = num + 1;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String newPostingdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
//                    String newPostingdate = "20180731";
                String dispatch = dto.get(i).getZpgdbar();
                cardh = cardhService.selectByBarcode(dispatch);
                InputLog inputLogtmp = new InputLog();
                inputLogtmp.setDispatch(dispatch);
                String operation = inputLogService.queryDispatchMaxOperation(dispatch);
                inputLogtmp.setOperation(operation);
                logs.setZpgdbar(dispatch);
                logs.setVornr(operation);
                if (operation == null) {
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("派工单错误，或工序错误！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }
                InputLog wirteoffinput = new InputLog();
                wirteoffinput = inputLogService.queryByDispatchAndOperation(inputLogtmp);
                //先记录原始报工记录的合格品数量，报废品数量，为后续重新报工做准备。
                if (wirteoffinput == null) {
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("派工单错误，或工序错误！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

//                if (wirteoffinput.getAttr15().equals("5")){
//                    logs.setZpgdbar(dispatch);
//                    logs.setVornr(operation);
//                    logs.setPostflag("E");
//                    logs.setWirteoffflag("E");
//                    logs.setWirteoffmsg("外协工序收货/报工只能通过手机APP进行冲销！");
//                    batchpdlogsService.insert(requestContext, logs);
//                    bs.setZpgdbar(dispatch);
//                    bs.setZflag("E");
//                    service.updateflag(bs);
//                    continue;
//                }

                Outsrgissue outsrgissue = new Outsrgissue();
                outsrgissue = outsrgissueService.selectByBarcode(dispatch,"0");
                if (outsrgissue != null){
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("外协工序收货/报工只能通过手机APP进行冲销！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                if (cardh.getStatus().equals("HOLD")){
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("流转卡状态为HOLD不允许冲销！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                if (cardh.getLgort() != null && cardh.getLgort() != ""){
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("流转卡已被转移至暂存区，不允许进行冲销操作！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                List<Cardhst> listcardhst = new ArrayList<>();
                listcardhst = cardhstService.selectAllActive(dispatch);
                String l_error = "";
                String message = "";
                for (int n = 0;n < listcardhst.size();n++){
                    if (listcardhst.get(n).getStatus().equals("HOLD") && listcardhst.get(n).getIsactive().equals("X")){
                        l_error = "E";
                        message = "流转卡状态为HOLD，不允许进行冲销操作！";
                        break;
                    }
                }

                if (l_error.equals("E")){
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg(message);
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                Afko afko = new Afko();
                afko = afkoService.selectByAufnr(cardh.getAufnr());
                if (afko.getAuart().substring(0,1).equals("Q")){//机加订单
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("机加报工冲销，请到装箱系统冲销！");
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                Xhcard xhcard = new Xhcard();
                xhcard = xhcardService.selectForZxhbar(cardh.getWerks(),cardh.getAufnr(),cardh.getZxhnum());
                if (!xhcard.getZxhzt().equals("1")){
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                    logs.setPostflag("E");
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("箱号状态错误，只能处理箱号状态为1的记录，当前箱号状态为："+xhcard.getZxhzt());
                    batchpdlogsService.insert(requestContext, logs);
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    continue;
                }

                List<Afvc> afvclist = afvcService.selectByAufnr(cardh.getAufnr());
                wirteoffinput.setFstvor("");
                wirteoffinput.setLstvor("");
                String fstvor = "";
                String lstvor = "";
                if (afvclist.get(afvclist.size() - 1).getVornr().equals(operation)){
                    fstvor = "X";
                    wirteoffinput.setFstvor(fstvor);

                }

                if (afvclist.get(0).getVornr().equals(operation)){
                    lstvor = "X";
                    wirteoffinput.setLstvor(lstvor);

                }

                Double yeild = wirteoffinput.getYeild();
                Double work_scrap = wirteoffinput.getWorkScrap();
                Double row_scrap = wirteoffinput.getRowScrap();

                //查询到需要冲销的记录之后第一步 进行冲销
                wirteoffinput.setPostingDate(newPostingdate);
                wirteoffinput.setAuart(cardh.getAuart());
                DTBAOGONGReturnResult dtbaogongReturnResult = inputLogService.writeOffDispatchNew(wirteoffinput);

                if (dtbaogongReturnResult.getMSGTY().equals("S")) {
                    //冲销成功
                    //1：记录日志

                    try {
//                                Thread.sleep(2000);
                        TimeUnit.MILLISECONDS.sleep(4000);
                    } catch (Exception e) {

                    }

                    logs.setAufnr(wirteoffinput.getOrderno());
                    logs.setMatnr(wirteoffinput.getMaterial());
                    logs.setWirteoffdate(newPostingdate);
                    logs.setWirteoffflag("S");
                    logs.setWirteoffmsg("冲销成功");

                    //冲销成功后改下流转卡状态
                    Cardhst cardhst = listcardhst.get(0);
                    if (cardhst.getOperation().equals(operation)){
                        cardhst.setIsactive("");
                        cardh.setStatus2(cardhst.getStatus());
                        cardhstService.updateStatus(cardhst);
                        cardhst = listcardhst.get(1);
                        cardh.setStatus(cardhst.getStatus());
                    }

                    if (fstvor.equals("X")){
                        cardh.setFprddat("");
                        cardh.setShift("");
                        cardh.setSfflg("");
                        cardh.setDiecd("");
                        cardh.setEcqty(0D);
                        cardh.setQtysp(0D);
                        cardh.setQtysm(0D);
                        cardh.setLastUpdatedBy(Long.valueOf(createdBy));
                        cardh.setLastUpdatedDate(new Date());
                        cardhService.updateDatforFsvor(cardh.getZpgdbar());
                    }

                    if (lstvor.equals("X")){
                        cardh.setEprddat("");
                        cardh.setAlqty(0D);
                        cardh.setLastUpdatedBy(Long.valueOf(createdBy));
                        cardh.setLastUpdatedDate(new Date());
                        cardhService.updateDatforLsvor(cardh.getZpgdbar());
                    }

                    List<Cardt> listcardt = new ArrayList<>();
                    Cardt parmCardt = new Cardt();
                    parmCardt.setZpgdbar(dispatch);
                    parmCardt.setZgxbh(operation);

                    listcardt = cardtService.queryAfterSort(requestContext,parmCardt,1,1);
                    if (listcardt.size() > 0){
                        for (int m = 0;m<listcardt.size();m++){
                            Cardt cardt = listcardt.get(m);
                            cardt.setConfirmed("");
                            cardtService.updateCardtConfirmed(cardt);
                        }
                    }

                    //2：重新报工
                    wirteoffinput.setYeild(0.0D);
                    wirteoffinput.setWorkScrap(yeild + work_scrap);
                    wirteoffinput.setRowScrap(row_scrap);
                    wirteoffinput.setPostingDate(newPostingdate);
                    wirteoffinput.setDispatchLogicID(wirteoffinput.getBarcode().substring(14, 18));
                    wirteoffinput.setCreated_by(createdBy);
                    wirteoffinput.setZtpbar("");
                    wirteoffinput.setZprtp("1");

                    Cardt cardt = cardtService.selectByZpgdbarAndVornr(dispatch,operation);
                    wirteoffinput.setArbpl(cardt.getArbpl());
                    wirteoffinput.setCharg("");
                    String appconfid = "";
                    Appidconf appidconf = new Appidconf();

                    if (operation.equals("0010")){
                        appconfid = "app0001";
                        appidconf = appidconfService.selectByAppid(appconfid);
                    } else if (operation.equals("0020")) {
                        appconfid = "app0002";
                        appidconf = appidconfService.selectByAppid(appconfid);
                    } else if (operation.equals("0030")) {
                        appconfid = "app0003";
                        appidconf = appidconfService.selectByAppid(appconfid);
                    }

                    DTBAOGONGReturnResult dtbaogongReturnResult1 = inputLogService.inputDispatchNew(wirteoffinput,cardh,cardt,appidconf,fstvor);

                    if (dtbaogongReturnResult1.getMSGTY().equals("S")) {
                        //重新报工成功
                        if (appconfid.equals("app0001")){

                            if (wirteoffinput.getFstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("FCNF");
                            }

                            if (wirteoffinput.getLstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("ECNF");
                            }


                        }else if (appconfid.equals("app0002")){

                            if (wirteoffinput.getFstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("FCNF");
                            }

                            if (wirteoffinput.getLstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("ECNF");
                            }


                            if (wirteoffinput.getFstvor().equals("") && wirteoffinput.getLstvor().equals("")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("CNF");
                            }


                        }else if (appconfid.equals("app0003")){

                            if (wirteoffinput.getFstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("FCNF");
                            }

                            if (wirteoffinput.getLstvor().equals("X")){
                                cardh.setStatus2(cardh.getStatus());
                                cardh.setStatus("ECNF");
                            }
                        }

                        if (wirteoffinput.getFstvor().equals("X")){
                            cardh.setFprddat(newPostingdate);
                            cardh.setShift(wirteoffinput.getClassgrp());
                            cardh.setSfflg(wirteoffinput.getAttr7());
                            cardh.setDiecd(wirteoffinput.getModelNo());
                            cardh.setCharg2(dtbaogongReturnResult1.getCHARG());
                            cardh.setActgstrp(newPostingdate);
                            cardh.setEcqty(wirteoffinput.getYeild());
                            cardh.setActst(df.format(new Date()).substring(11,19).replaceAll(":",""));
                        }

                        if (wirteoffinput.getLstvor().equals("X")){
                            cardh.setEprddat(newPostingdate);
                            cardh.setActgltrp(newPostingdate);
                            cardh.setActdt(df.format(new Date()).substring(11,19).replaceAll(":",""));
                            cardh.setAlqty(wirteoffinput.getYeild());
                        }

                        cardh.setQtysm(wirteoffinput.getRowScrap());
                        cardh.setQtysp(wirteoffinput.getWorkScrap());

                        cardt.setConfirmed("X");
                        cardtService.updateCardtConfirmed(cardt);
                        List<Cardh> cardhList = new ArrayList<>();
                        cardhList.add(cardh);
                        cardhService.updateCardhStatus(cardhList);
                        Cardhst tst = new Cardhst();
                        tst.setZpgdbar(cardh.getZpgdbar());
                        tst.setStatus(cardh.getStatus());
                        tst = cardhstService.selectByBarcodeAndStatus(tst);
                        tst.setIsactive("X");
                        cardhstService.updateStatus(tst);


                        logs.setPostdate(newPostingdate);
                        logs.setPostflag("S");
                        logs.setPostmsg("报工成功");

                        bs.setZpgdbar(dispatch);
                        bs.setZflag("S");
                        service.updateflag(bs);

                        try {
//                                Thread.sleep(2000);
                            TimeUnit.MILLISECONDS.sleep(4000);
                        } catch (Exception e) {

                        }

                    } else {
                        logs.setPostdate(newPostingdate);
                        logs.setPostflag("E");
                        logs.setPostmsg("报工失败:" + dtbaogongReturnResult1.getMESSAGE());
                        bs.setZpgdbar(dispatch);
                        bs.setZflag("E");
                        service.updateflag(bs);

                    }

                } else {
                    //冲销失败，记录日志 进行下一条
                    logs.setWirteoffdate(newPostingdate);
                    logs.setWirteoffflag("E");
                    logs.setWirteoffmsg("冲销失败:" + dtbaogongReturnResult.getMESSAGE());
                    bs.setZpgdbar(dispatch);
                    bs.setZflag("E");
                    service.updateflag(bs);
                    batchpdlogsService.insert(requestContext, logs);
                    continue;
                }
                //日志写数据库

                batchpdlogsService.insert(requestContext, logs);
            }
        }

        rs.setMessage("共处理" + num + "笔盘点记录！,具体查看批处理日志记录！");
        rs.setSuccess(true);
        return rs;

    }
}
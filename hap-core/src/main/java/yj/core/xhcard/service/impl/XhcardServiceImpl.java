package yj.core.xhcard.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.webservice_queryXhcard.components.QueryXhcardWebserviceUtil;
import yj.core.webservice_queryXhcard.dto.QueryXhcardParam;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_xhcard.components.XhcardSyncWebserviceUtil;
import yj.core.webservice_xhcard.dto.XhcardParameters;
import yj.core.webservice_xhcard.dto.XhcardReturnResult;
import yj.core.wipdftrghlist.mapper.DftrghlistMapper;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.mapper.XhcardMapper;
import yj.core.xhcard.service.IXhcardService;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.mapper.Ztbc0018Mapper;
import yj.core.zwipq.mapper.ZwipqMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class XhcardServiceImpl
        extends BaseServiceImpl<Xhcard>
        implements IXhcardService
{
    @Autowired
    XhcardMapper xhcardMapper;
    @Autowired
    XhcardSyncWebserviceUtil xhcardSyncWebserviceUtil;
    @Autowired
    QueryXhcardWebserviceUtil queryXhcardWebserviceUtil;
    @Autowired
    ZwipqMapper zwipqMapper;
    @Autowired
    Ztbc0018Mapper ztbc0018Mapper;
    @Autowired
    DftrghlistMapper dftrghlistMapper;

    public int insertXhcard(List<Xhcard> list)
    {
        int result = 0;
        if (list.size() > 0)
        {
            for (int i=0;i<list.size();i++){
                result = result + xhcardMapper.insertXhcard(list.get(i));
            }
        }
        return result;
    }

    public List<Xhcard> queryAfterSort(IRequest request, Xhcard xhcard, int page, int pagesize)
    {
        PageHelper.startPage(page, pagesize);

        return xhcardMapper.queryAfterSort(xhcard);
    }

    public int deleteXhcard(List<Xhcard> list)
    {
        int result = 0;

        if (list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                result = result + xhcardMapper.deleteXhcard(list.get(i));
            }
        }
        return result;
    }

    public XhcardReturnResult returnSyncXhcard(Xhcard xhcard)
    {
        XhcardParameters params = new XhcardParameters();
        params.setWerks(xhcard.getWerks());
        params.setMatnr(xhcard.getMatnr());
        params.setCharg(xhcard.getCharg());
        params.setZxhnum(xhcard.getZxhnum());
        params.setZxhzt(xhcard.getZxhzt());
        params.setZxhzt2(xhcard.getZxhzt2());
        params.setLgort(xhcard.getLgort());
        params.setMenge(xhcard.getMenge());
        params.setMeins(xhcard.getMeins());
        params.setZxhwz(xhcard.getZxhwz());
        params.setAufnr(xhcard.getAufnr());
        params.setZxhbar(xhcard.getZxhbar());
        params.setZjyy(xhcard.getZjyy());
        params.setZscbc(xhcard.getZscbc());
        params.setZscx(xhcard.getZscx());
        params.setZmnum(xhcard.getZmnum());
        params.setZsctptm(xhcard.getZsctptm());
        params.setZbqbd(xhcard.getZbqbd());
        params.setZtxt(xhcard.getZtxt());
        params.setChargkc(xhcard.getChargkc());

        XhcardReturnResult result = new XhcardReturnResult();
        try {
            result = xhcardSyncWebserviceUtil.receiveConfirmation(params);
            return result;
        }catch (Exception e){
            result.setMSGTY("E");
            result.setMESSAGE("箱号同步接口错误！");
            e.printStackTrace();
            return result;
        }

    }

    @Override
    public Xhcard selectByXhAndAufnr(String zxhbar) {
        return xhcardMapper.selectByXhAndAufnr(zxhbar);
    }

    @Override
    public QueryXhcardReturnResult selectByBacodeFromSap(String zxhbar, String matnr, String lgort, String qtype) {
        QueryXhcardParam param = new QueryXhcardParam();
        param.setZxhbar(zxhbar);
        param.setLgort(lgort);
        param.setMatnr(matnr);
        param.setQtype(qtype);
        QueryXhcardReturnResult rs = new QueryXhcardReturnResult();
        rs =  queryXhcardWebserviceUtil.receiveConfirmation(param);
        return rs;
    }

    @Override
    public Xhcard selectByBacode(String zxhbar) {
        return xhcardMapper.selectByBacode(zxhbar);
    }

    @Override
    public int updateXhcard(List<Xhcard> list) {
        int result = 0;
        if (list.size() > 0)
        {
            XhcardReturnResult rs = null;
            for (int i = 0; i < list.size(); i++)
            {
                rs = returnSyncXhcard(list.get(i));
                if (rs.getMSGTY().equals("E")) {
                    break;
                }
            }
            if (rs.getMSGTY().equals("S")) {
                for (int i = 0; i < list.size(); i++) {
                    if (xhcardMapper.updateXhcard(list.get(i)) == 1) {
                        result += 1;
                    }
                }
            } else {
                result = 0;
            }
        }
        return result;
    }

    @Override
    public int updateXhcardFromSap(List<Xhcard> list) {
        if (xhcardMapper == null){
            xhcardMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(XhcardMapper.class);
        }
        int result = 0;
        if (list.size() > 0){
            for(int i = 0;i<list.size();i++){
//                if (xhcardMapper.updateXhcard(list.get(i)) == 1) {
//                    result += 1;
//                }
                Xhcard xhcard = new Xhcard();
                xhcard = xhcardMapper.selectByBacode(list.get(i).getZxhbar());
                if (xhcard != null){
                    result = result + xhcardMapper.updateXhcard(list.get(i));
                }else{
                    result = result + xhcardMapper.insertXhcard(list.get(i));
                }
            }
        }else{
            result = 0;
        }
        return result;
    }

    @Override
    public List<Xhcard> selectByMatnrAndLgortSort(String matnr, String lgort) {
        return xhcardMapper.selectByMatnrAndLgortSort(matnr,lgort);
    }

    @Override
    public String selectMaxCharg(String matnr, String charg) {
        return xhcardMapper.selectMaxCharg(matnr,charg);
    }

    @Override
    public int updateZsxwc(Xhcard xhcard) {
        return xhcardMapper.updateZsxwc(xhcard);
    }

    @Override
    public List<Xhcard> selectXbkc(Xhcard dto) {
        //PageHelper.startPage(page, pageSize);

        List<Xhcard> list =  xhcardMapper.selectXbkc(dto);
        List<Xhcard> listresult = new ArrayList<>();
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                if (list.get(i).getMenge() == null || list.get(i).getMenge().equals("")){
                    list.get(i).setMenge("0");
                }else{
                    int index = list.get(i).getMenge().indexOf(".");
                    list.get(i).setMenge(list.get(i).getMenge().substring(0,index));
                }

                if (list.get(i).getBmenge() == null ){
                    list.get(i).setBmenge(0L);
                }

                if (list.get(i).getSmenge() == null){
                    list.get(i).setSmenge(0L);
                }

                if (list.get(i).getPkgLineId() != null){
                    list.get(i).setChangeNum(list.get(i).getSmenge() - list.get(i).getBmenge());
                    list.get(i).setSjjcNum( Long.valueOf(list.get(i).getMenge()) - list.get(i).getZsxnum() + list.get(i).getChangeNum());
                }else{
                    list.get(i).setSjjcNum(Long.valueOf(list.get(i).getMenge()));
                }
            }
        }

        if (dto.getDates() != null && dto.getDatee() != null){
            Date dates = null;
            Date datee = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                 dates = sdf.parse(dto.getDates());
                 datee = sdf.parse(dto.getDatee());
            }catch (Exception e){
                e.printStackTrace();
            }


            for (int i =0;i<list.size();i++){
                if (list.get(i).getAttr1() == null){
                    continue;
                }else{
                    if (list.get(i).getAttr1().getTime() < dates.getTime() || list.get(i).getAttr1().getTime() > datee.getTime()){

                        continue;
                    }else{
                        listresult.add(list.get(i));
                    }
                }
            }
        }

        if (dto.getDates() != null && dto.getDatee() == null ){
            Date dates = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                dates = sdf.parse(dto.getDates());
            }catch (Exception e){
                e.printStackTrace();
            }
            for (int i=0;i<list.size();i++){
                if (list.get(i).getAttr1() == null){
                    continue;
                }else{
                    if (list.get(i).getAttr1().getTime() < dates.getTime()){
                        continue;
                    }else{
                        listresult.add(list.get(i));
                    }
                }
            }
        }

        if (dto.getDatee() != null && dto.getDates() == null){
            Date datee = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                datee = sdf.parse(dto.getDatee());
            }catch (Exception e){
                e.printStackTrace();
            }
            for (int i = 0;i<list.size();i++){
                if (list.get(i).getAttr1() == null){

                    continue;
                }else{
                    if (list.get(i).getAttr1().getTime() > datee.getTime()){

                        continue;
                    }else{
                        listresult.add(list.get(i));
                    }
                }
            }
        }

        if (dto.getDatee() == null && dto.getDates() == null){
            return list;
        }else{
            return listresult;
        }
    }

    @Override
    public Xhcard selectForZxhbar(String werks, String aufnr, String zxhnum) {
        return xhcardMapper.selectForZxhbar(werks,aufnr,zxhnum);
    }

    @Override
    public List<Xhcard> selectByMatnrAndLgortSortS7(String matnr, String lgort) {
        return xhcardMapper.selectByMatnrAndLgortSortS7(matnr,lgort);
    }

    @Override
    public int updateXhcardFromSapSinger(Xhcard xhcard) {
        return xhcardMapper.updateXhcard(xhcard);
    }

    @Override
    public List<Xhcard> queryXhcard(IRequest iRequest, Xhcard dto) {
        //PageHelper.startPage(page,pageSize);
        List<Xhcard> list = xhcardMapper.queryXhcard(dto);
        List<Ztbc0018> ztbc0018 = new ArrayList<Ztbc0018>();
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                Integer zsxnum = 0,zsxnumDate = 0,dfectQty=0;
                Long changeNum=0L,sjjcNum=0L;
                Xhcard xhcard = list.get(i);
                zsxnum = zwipqMapper.selectSumZxhbar(xhcard.getZxhbar(),null,null);
                if(dto.getCreationDateAfter()!=null || dto.getCreationDateBefore()!=null){
                    if(zsxnum == 0){
                        list.remove(i);
                        i--;
                        continue;
                    }
                    zsxnumDate = zwipqMapper.selectSumZxhbar(xhcard.getZxhbar(),dto.getCreationDateAfter(),dto.getCreationDateBefore());
                    if(!(zsxnum.equals(zsxnumDate))){
                        list.remove(i);
                        i--;
                        continue;
                    }
                }
                ztbc0018 = ztbc0018Mapper.selectByZxhbar(xhcard.getZxhbar());
                if(ztbc0018.size() > 0){
                    changeNum = ztbc0018.get(0).getSmenge()-ztbc0018.get(0).getBmenge();
                }
                dfectQty = dftrghlistMapper.selectByZxhbar2(xhcard.getZxhbar());
                sjjcNum = Double.valueOf(xhcard.getMenge()).longValue()-zsxnum-dfectQty+changeNum;
                String chargkc = xhcard.getChargkc().substring(0,6);
                String zscbc = xhcard.getZscbc();
                String attr7 = chargkc + zscbc;
                xhcard.setAttr7(attr7);
                xhcard.setZsxnum(zsxnum.longValue());
                xhcard.setChangeNum(changeNum);
                xhcard.setDfectQty(dfectQty);
                xhcard.setSjjcNum(sjjcNum);
            }
        }
        return list;
    }
}

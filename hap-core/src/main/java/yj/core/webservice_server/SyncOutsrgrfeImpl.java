package yj.core.webservice_server;

import org.springframework.web.context.ContextLoaderListener;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.mapper.OutsrgissueMapper;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.mapper.OutsrgrfeMapper;
import yj.core.webservice_server.dto.Rec_Outsrgrfe;
import yj.core.webservice_server.dto.ReturnMessage;

import java.util.Date;
import java.util.List;

/**
 * 处理外协采购订单同步 sap to  imfgnet
 * 917110140  20181108
 */
public class SyncOutsrgrfeImpl implements IsyncOutsrgrfe {

    @Override

    public ReturnMessage sync(Rec_Outsrgrfe outsrgrfe) {
        //根据工厂 生产订单号 供应商编号 外协工序号在 wip_outsrgrfe 中查询是否存在记录
        //如果存在返货错误信息 ：此生产订单已经创建供应商XXX的采购订单，不允许创建
        //如果不存在记录 则在表中新增记录
        int sum = 0;
        ReturnMessage rs = new ReturnMessage();
        Outsrgrfe tmp = new Outsrgrfe();
        String werks = outsrgrfe.getWerks();
        String aufnr = outsrgrfe.getAufnr();
        String vornr = outsrgrfe.getVornr();
        String matnr = outsrgrfe.getMatnr();
        String lifnr = outsrgrfe.getLifnr();
        String tcode = outsrgrfe.getTcode();
        String sortl = outsrgrfe.getSortl();
        String ebeln = outsrgrfe.getEbeln();
        String ebelp = outsrgrfe.getEbelp();
        Outsrgrfe newdate = new Outsrgrfe();
        newdate.setAufnr(outsrgrfe.getAufnr());
        newdate.setEbeln(outsrgrfe.getEbeln());
        newdate.setEbelp(outsrgrfe.getEbelp());
        newdate.setKtsch(outsrgrfe.getKtsch());
        newdate.setLifnr(outsrgrfe.getLifnr());
        newdate.setLoekz(outsrgrfe.getLoekz());
        newdate.setMatnr(outsrgrfe.getMatnr());
        newdate.setMenge(outsrgrfe.getMenge());
        newdate.setVornr(outsrgrfe.getVornr());
        newdate.setVsnda(outsrgrfe.getVsnda());
        newdate.setWerks(outsrgrfe.getWerks());
        newdate.setSortl(outsrgrfe.getSortl());
        if (outsrgrfe.getLoekz() == null){
            newdate.setLoekz("");
        }

        OutsrgrfeMapper outsrgrfeMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(OutsrgrfeMapper.class);
        OutsrgissueMapper outsrgissueMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(OutsrgissueMapper.class);
        if (tcode.equals("ME21N")){//新增业务
            tmp = outsrgrfeMapper.selectByCondition(werks,aufnr,vornr,matnr,lifnr,null,null);
            if (tmp == null){
                newdate.setCreatedBy(10001L);
                newdate.setCreationDate(new Date());
                sum = outsrgrfeMapper.insertOutsrgrfe(newdate);
                if (sum == 1){
                    rs.setFlag("S");
                }else{
                    rs.setFlag("E1");//新增记录失败
                }
            }else{
                rs.setFlag("E");//该生产订单已经存在外协采购订单
            }
        }

        if (tcode.equals("ME22N")){
            tmp = outsrgrfeMapper.selectByCondition(werks,aufnr,vornr,matnr,lifnr,ebeln,ebelp);
            if (tmp == null){
                newdate.setCreatedBy(10001L);
                newdate.setCreationDate(new Date());
                sum = outsrgrfeMapper.insertOutsrgrfe(newdate);
                if (sum == 1){
                    rs.setFlag("S");
                }else{
                    rs.setFlag("E1");//新增记录失败
                }
            }else{
                //查询采购订单是否已经发生发货业务
                //如果已经发生发货业务 只能修改数量 > 已发货数量
                List<Outsrgissue> list = outsrgissueMapper.selectByContidion(ebeln,ebelp,werks,lifnr,matnr);
                if (list.size() == 0){//未发货 可以修改
                    //检查是否修改了供应商
                    if (!tmp.getLifnr().equals(lifnr)){
                        rs.setFlag("E2");//已经同步的外协采购订单不允许修改供应商
                        return rs;
                    }
                    newdate.setLastUpdateDate(new Date());
                    newdate.setLastUpdatedBy(10001L);
                    sum = outsrgrfeMapper.updateByCondition(newdate);
                    if (sum == 1){
                        rs.setFlag("S");
                    }
                }else{
                    double sumfhsl = 0;
                    //汇总已发货数量
                    for(int i =0;i<list.size();i++){
                        sumfhsl = sumfhsl + list.get(i).getZisnum();
                    }
                    if (sumfhsl > outsrgrfe.getMenge()){
                        rs.setFlag("E3");//已经发生外协发货的外协采购订单，修改数量不能小于已发货数量
                        return rs;
                    }

                    if (newdate.getLoekz().equals("L")){
                        rs.setFlag("E4");
                        return rs;
                    }

                    if (newdate.getLoekz().equals("S")){
                        rs.setFlag("E5");
                        return rs;
                    }

                    newdate.setLastUpdateDate(new Date());
                    newdate.setLastUpdatedBy(10001L);
                    sum = outsrgrfeMapper.updateByCondition(newdate);
                    if (sum == 1){
                        rs.setFlag("S");
                    }
                }
            }
        }
        return rs;
    }
}

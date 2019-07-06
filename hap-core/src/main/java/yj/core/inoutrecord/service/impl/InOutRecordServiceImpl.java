package yj.core.inoutrecord.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.mapper.QjcodeMapper;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.mapper.DftrghlistMapper;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.mapper.LinesMapper;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.mapper.QcparamLinesMapper;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zwipq.mapper.ZwipqMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InOutRecordServiceImpl extends BaseServiceImpl<InOutRecord> implements IInOutRecordService {

    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private CardhMapper cardhMapper;
    @Autowired
    private QjcodeMapper qjcodeMapper;
    @Autowired
    private ZwipqMapper zwipqMapper;
    @Autowired
    private QcparamLinesMapper qcparamLinesMapper;
    @Autowired
    private DftrghlistMapper dftrghlistMapper;
    @Autowired
    private LinesMapper linesMapper;

    @Override
    public int insertQjrecode(List<InOutRecord> list) {
        int sum = 0;
        for (int i =0;i<list.size();i++){
        sum = sum +   inOutRecordMapper.insertQjrecode(list.get(i));
        }
        return sum;
    }

    @Override
    public List<InOutRecord> selectforjjhj(String line_id, String qjcodeval, String lineiocfgval, String matnr, int hjtype) {
        return inOutRecordMapper.selectforjjhj(line_id,qjcodeval,lineiocfgval,matnr,hjtype);
    }

    @Override
    public List<InOutRecord> selectNoReflg(String line_id, String zotype, String vornr, String sfflg, String matnr, int hjtype) {
        return inOutRecordMapper.selectNoReflg(line_id,zotype,vornr,matnr,sfflg,hjtype);
    }

    @Override
    public int updateReflag(InOutRecord inOutRecord) {
        return inOutRecordMapper.updateReflag(inOutRecord);
    }

    @Override
//    public List<Zudlist> selectforZud(String line_id, String classgrp, int page, int pagesize, IRequest iRequest) {
    public List<Zudlist> selectforZud(String Pline_id,String line_id, String classgrp,String matnr2,String creationDateBefore,String creationDateAfter,String isInclude) {
//        PageHelper.startPage(page, pagesize);
        List<InOutRecord> list = inOutRecordMapper.selectforZud(Pline_id,line_id,classgrp,matnr2,creationDateBefore,creationDateAfter);
        List<Zudlist> listzuds = new ArrayList<>();

        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Zudlist zudlist = new Zudlist();
                zudlist.setZctype("0");
                zudlist.setKunnr(list.get(i).getKunnr());
                zudlist.setName1(list.get(i).getName1());
                zudlist.setLineId(list.get(i).getLineId());
                zudlist.setZbanz(list.get(i).getZbanz());
                zudlist.setZbanc(list.get(i).getZbanc());
                zudlist.setArbpr(list.get(i).getArbpr());
                zudlist.setCharg(list.get(i).getCharg());
                zudlist.setCharg2("");
                zudlist.setDiecd(list.get(i).getDiecd());
                zudlist.setGmein(list.get(i).getGmein());
                zudlist.setMatnr(list.get(i).getMatnr());
                zudlist.setMatnr2(list.get(i).getMatnr2());
                zudlist.setZbpjc(list.get(i).getKunnr());
                Marc marc2 = new Marc();
                marc2 = marcMapper.selectByMatnr(list.get(i).getMatnr2());
                zudlist.setMaktx(marc2.getMaktx());
                zudlist.setSfflg(list.get(i).getSfflg());
                zudlist.setUdtype("1");
                zudlist.setZdnum(1L);
                zudlist.setZxhbar(list.get(i).getZxhbar());
                zudlist.setZpgdbar(list.get(i).getZpgdbar());
                zudlist.setZpgdbar2(list.get(i).getZpgdbar2());
                //获取机加流转卡数据
                Cardh cardhjj = new Cardh();
                cardhjj = cardhMapper.selectByBarcode(list.get(i).getZpgdbar());
                zudlist.setGstrp(cardhjj.getGstrp());//机加的生产日期
                String gstrp = cardhjj.getGstrp().replace("-","");
                String charg2 = gstrp.substring(2,8) + "000" + list.get(i).getZbanc();
                zudlist.setCharg2(charg2);//机加批次 根据机加生产订单来拼
                //获取取件原因
                Qjcode qjcode = new Qjcode();
                qjcode = qjcodeMapper.selectById(Integer.valueOf(list.get(i).getZotype()));
                zudlist.setRcode(qjcode.getRcode());
                zudlist.setVornr_old(list.get(i).getVornr());
                zudlist.setZgjbar(list.get(i).getZgjbar());
                zudlist.setZqjjlh(list.get(i).getZqjjlh());
                zudlist.setReviewc("");
                zudlist.setZqxdm(list.get(i).getZqxdm());
                zudlist.setZissuetxt(list.get(i).getZissuetxt());
                if (!zudlist.getZqxdm().equals("")){
                    if (zudlist.getZqxdm().substring(0,1).equals("M")){
                        QcparamLines qcparamLines = qcparamLinesMapper.selectForYz(Long.valueOf(list.get(i).getLineId()),"1001");
                        zudlist.setRspart(qcparamLines.getDefaultCastdept());
                        zudlist.setName(qcparamLines.getName());
                    }else{
                        QcparamLines qcparamLines = qcparamLinesMapper.selectForJj(Long.valueOf(list.get(i).getLineId()),"1001");
                        zudlist.setRspart(qcparamLines.getDefaultLinedept());
                        zudlist.setName(qcparamLines.getName());
                    }
                }
                listzuds.add(zudlist);
            }
        }
        if (isInclude.equals("Y")){
            List<Dftrghlist> list2 = new ArrayList<>();
            list2 = dftrghlistMapper.selectForZudlist(Pline_id,line_id,classgrp,matnr2,creationDateBefore,creationDateAfter);
            if (list2.size() > 0){
                for (int i =0;i<list2.size();i++){
                    Zudlist zudlist = new Zudlist();
                    zudlist.setZctype("1");
                    zudlist.setKunnr(list2.get(i).getKunnr());
                    zudlist.setName1(list2.get(i).getName1());
                    zudlist.setLineId(list2.get(i).getLineId());
                    zudlist.setZbanz(list2.get(i).getShift());
                    zudlist.setZbanc(list2.get(i).getZbanc());
                    Lines lines = new Lines();
                    lines = linesMapper.selectById(Long.parseLong(line_id));
                    zudlist.setArbpr(lines.getArbpl());
                    zudlist.setCharg(list2.get(i).getYcharge());

                    zudlist.setDiecd(list2.get(i).getDiecd());
                    zudlist.setGmein(list2.get(i).getGmein());
                    zudlist.setMatnr(list2.get(i).getMatnr2());
                    zudlist.setMatnr2(list2.get(i).getMatnr());
                    zudlist.setZbpjc(list2.get(i).getKunnr());
                    Marc marc2 = new Marc();
                    marc2 = marcMapper.selectByMatnr(list2.get(i).getMatnr());
                    zudlist.setMaktx(marc2.getMaktx());
                    zudlist.setSfflg(list2.get(i).getSfflg());
                    zudlist.setUdtype("1");
                    zudlist.setUdtype("1");
                    zudlist.setZdnum(list2.get(i).getDfectQty());
                    zudlist.setZxhbar(list2.get(i).getZxhbar());
                    zudlist.setZpgdbar(list2.get(i).getZpgdbarjj());
                    zudlist.setZpgdbar2(list2.get(i).getZpgdbar());
                    Cardh cardhjj = new Cardh();
                    cardhjj = cardhMapper.selectByBarcode(list2.get(i).getZpgdbarjj());
                    zudlist.setGstrp(cardhjj.getGstrp());//机加的生产日期
                    String gstrp = cardhjj.getGstrp().replace("-","");
                    String charg2 = gstrp.substring(2,8) + "0001";
                    zudlist.setCharg2(charg2);//机加批次 根据机加生产订单来拼
                    zudlist.setVornr_old(list2.get(i).getVornrjj());
                    zudlist.setZgjbar("");
                    zudlist.setZqjjlh(list2.get(i).getWerks() + "_" +list2.get(i).getRecordid() + "_" + list2.get(i).getItem());
                    zudlist.setZqxdm(list2.get(i).getCode());
                    zudlist.setZissuetxt(list2.get(i).getTlevelcode());
                    if (!zudlist.getZqxdm().equals("")){
                        if (zudlist.getZqxdm().substring(0,1).equals("M")){
                            QcparamLines qcparamLines = qcparamLinesMapper.selectForYz(Long.valueOf(list2.get(i).getLineId()),"1001");
                            zudlist.setRspart(qcparamLines.getDefaultCastdept());
                            zudlist.setName(qcparamLines.getName());
                        }else{
                            QcparamLines qcparamLines = qcparamLinesMapper.selectForJj(Long.valueOf(list2.get(i).getLineId()),"1001");
                            zudlist.setRspart(qcparamLines.getDefaultLinedept());
                            zudlist.setName(qcparamLines.getName());
                        }
                    }
                    listzuds.add(zudlist);
                }
            }
        }


        return listzuds;
    }

    @Override
    public InOutRecord selectById(String zqjjlh) {
        return inOutRecordMapper.selectById(zqjjlh);
    }

    @Override
    public int batchUpdateReflag(List<InOutRecord> list) {
        int sum = 0;
        for (int i = 0;i<list.size();i++){
            sum = sum + inOutRecordMapper.updateReflag(list.get(i));
        }
        return sum;
    }

    @Override
    public List<InOutRecord> selectforZrwk(String pline_id, String line_id, String classgrp, String zotype, IRequest iRequest) {
        return inOutRecordMapper.selectforZrwk(pline_id,line_id,classgrp,zotype);
    }

    @Override
    public List<InOutRecord> selectforlines(IRequest request, String lineId, String plineId, String deptId) {
        return inOutRecordMapper.selectforlines(lineId,plineId, deptId);
    }

    @Override
    public int selectZoutnum(String lineId, Integer zremade,String matnr, String sfflg, String diecd) {
        return inOutRecordMapper.selectZoutnum(lineId, zremade,matnr, sfflg, diecd);
    }

    @Override
    public int selectZsxnum(String lineId, Integer zremade,String matnr, String sfflg, String diecd) {
        return zwipqMapper.selectZsxnum(lineId, zremade,matnr,sfflg,diecd);
    }

    @Override
    public List<InOutRecord> selectforQcaudit1(String werks, String line_id, String matnr, String matnr2, String deptId, String gstrp, String zqxdm, String zissuetxt, String zbanz) {
        return inOutRecordMapper.selectforQcaudit1(werks,line_id,matnr,matnr2,deptId,gstrp,zqxdm,zissuetxt,zbanz);
    }

    @Override
    public List<InOutRecord> selectforQcaudit3(String werks, String line_id, String matnr, String matnr2, String deptId, String gstrp, String zqxdm, String zissuetxt, String zbanz) {
        return inOutRecordMapper.selectforQcaudit3(werks,line_id,matnr,matnr2,deptId,gstrp,zqxdm,zissuetxt,zbanz);
    }

    @Override
    public List<InOutRecord> selectforKanb(String werks, String line_id, String matnr, String start, String end) {
        return inOutRecordMapper.selectforKanb(werks,line_id,matnr,start,end);
    }

    @Override
    public List<InOutRecord> selectByZpgdbar(String zpgdbar) {
        return inOutRecordMapper.selectByZpgdbar(zpgdbar);
    }

    @Override
    public List<InOutRecord> selectByCreateDate(String startDate, String endDate) {
        return inOutRecordMapper.selectByCreateDate(startDate,endDate);
    }

    @Override
    public List<InOutRecord> XmngaCount(String lineId, String matnr, String startDate, String endDate) {
        return inOutRecordMapper.XmngaCount(lineId, matnr, startDate, endDate);
    }

    @Override
    public List<InOutRecord> RmngaCount(String lineId, String matnr, String startDate, String endDate) {
        return inOutRecordMapper.RmngaCount(lineId, matnr, startDate, endDate);
    }

    @Override
    public List<InOutRecord> selectByNgRecode(String startDate, String endDate) {
        return inOutRecordMapper.selectByNgRecode(startDate, endDate);
    }

    @Override
    public List<InOutRecord> zissuetxtCount(InOutRecord dto) {
        return inOutRecordMapper.zissuetxtCount(dto);
    }
}
package yj.core.zwipq.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.webservice_migo.components.MigoWebserviceUtil;
import yj.core.webservice_migo.dto.DTMIGOParam;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.mapper.XhcardMapper;
import yj.core.ztbc0018.dto.Ztbc0018;
import yj.core.ztbc0018.mapper.Ztbc0018Mapper;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.mapper.ZwipqMapper;
import yj.core.zwipq.service.IZwipqService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ZwipqServiceImpl extends BaseServiceImpl<Zwipq> implements IZwipqService {
    @Autowired
    private ZwipqMapper zwipqMapper;
    @Autowired
    private MigoWebserviceUtil migo;
    @Autowired
    private XhcardMapper xhcardMapper;
    @Autowired
    private Ztbc0018Mapper ztbc0018Mapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private CardhMapper cardhMapper;
    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private IDftrghlistService dftrghlistService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Zwipq> selectByLineIdAndZxhbar(String line_id, String zxhbar) {
        return zwipqMapper.selectByLineIdAndZxhbar(line_id, zxhbar);
    }

    @Override
    public Map selectMaxQsenq(Map m) {

        zwipqMapper.selectMaxQsenq(m);
        return m;
    }

    @Override
    public int InsertIntoZwipq(List<Zwipq> list) {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num = num + 1;
            zwipqMapper.InsertIntoZwipq(list.get(i));
        }
        return num;
    }

    @Override
    public DTMIGOReturn callMigo(String zxhbar, int cynum, String line_id, String bwart, int createBy, String zpgdbar) {
        DTMIGOParam param = new DTMIGOParam();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newPostingdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
        Xhcard xhcard = xhcardMapper.selectByBacode(zxhbar);
        Cardh cardh = cardhMapper.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
        Marc marc = marcMapper.selectByMatnr(xhcard.getMatnr());
        List<Dftrghlist> listdf = new ArrayList<>();
        listdf = dftrghlistService.selectByZxhbar(zxhbar);

        Integer cy = cynum;
        param.setACTION("");
        param.setREFDOC("");
        param.setAUFNR(xhcard.getAufnr());
        param.setBKTXT("");
        param.setWERKS(xhcard.getWerks());
        param.setBLDAT(newPostingdate);
        param.setBUDAT(newPostingdate);
        param.setBWART(bwart);
        param.setCHARG(xhcard.getChargkc());
        param.setERFME(marc.getMeins());//单位
        param.setLGORT(xhcard.getLgort());
        param.setERFMG(cy.toString());//数量
        param.setMATNR(xhcard.getMatnr());
        param.setRSNUM("");
        param.setRSPOS("");
        param.setSJAHR("");
        param.setSMBLN("");
        param.setSMBLP("");
        param.setTAKEIT("");
        param.setUMCHA("");
        param.setUMLGO("");
        DTMIGOReturn rs = migo.receiveConfirmation(param);

        //将调账记录保存到表sap_ztbc0018中
        Ztbc0018 ztbc0018 = new Ztbc0018();
        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        ztbc0018.setId(uuidstr);
        ztbc0018.setMblnr(rs.getMBLNR());
        ztbc0018.setLineId(line_id);
        ztbc0018.setMjahr(rs.getMJAHR());
        Long bmenge = Long.parseLong(xhcard.getMenge().substring(0, xhcard.getMenge().length() - 2));
        ztbc0018.setBmenge(bmenge);
        Long dfnum = 0L;
        if (listdf.size() > 0) {
            for (int i = 0; i < listdf.size(); i++) {
                dfnum = dfnum + listdf.get(i).getDfectQty();
            }
        }
        if (bwart.equals("702")) {
            Long smenge = Long.parseLong(xhcard.getMenge().substring(0, xhcard.getMenge().length() - 2)) - cynum - dfnum;
            ztbc0018.setSmenge(smenge);
        } else {
            Long smenge = Long.parseLong(xhcard.getMenge().substring(0, xhcard.getMenge().length() - 2)) + cynum - dfnum;
            ztbc0018.setSmenge(smenge);
        }
        ztbc0018.setLgort(xhcard.getLgort());
        ztbc0018.setMeins(xhcard.getMeins());
        ztbc0018.setZxhbar(xhcard.getZxhbar());
        ztbc0018.setZpgdbar2(cardh.getZpgdbar());
        ztbc0018.setCreatedBy(Long.valueOf(createBy));
        ztbc0018.setCreationDate(new Date());
        ztbc0018.setZdlty("");
        ztbc0018.setZspbz("");
        ztbc0018.setZspty("");
        ztbc0018Mapper.insertZtbc0018(ztbc0018);
        return rs;
    }

    @Override
    public List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(String line_id, String zxhbar, String zpgdbar) {
        return zwipqMapper.selectBylineidAndZxhbarAndZpgdbar(line_id, zxhbar, zpgdbar);
    }

    @Override
    public List<Zwipq> selectBylineforjjqj(String line_id) {
        return zwipqMapper.selectBylineforjjqj(line_id);
    }

    @Override
    public List<Zwipq> selectForqj(String line_id, String sfflg) {
        return zwipqMapper.selectForqj(line_id, sfflg);
    }

    @Override
    public int updateForQj(List<Zwipq> zwipqs) {
        int sum = 0;
        for (int i = 0; i < zwipqs.size(); i++) {
            sum = sum + zwipqMapper.updateForQj(zwipqs.get(i));
        }
        return sum;
    }

    @Override
    public Zwipq selectById(String zwipqid) {
        return zwipqMapper.selectById(zwipqid);
    }

    @Override
    public List<Zwipq> selectByLineid(String line_id) {
        return zwipqMapper.selectByLineid(line_id);
    }

    @Override
    public int updateZqjklAndQenq(Zwipq zwipq) {
        return zwipqMapper.updateZqjklAndQenq(zwipq);
    }

    @Override
    public int updateQsenqBatch(List<Zwipq> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (zwipqMapper.updateQsenq(list.get(i)) == 1) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    @Override
    public List<Zwipq> selectForJjxx(String line_id, String classgrp) {
        return zwipqMapper.selectForJjxx(line_id, classgrp);
    }

    @Override
    public int updateZoffl(List<Zwipq> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (zwipqMapper.updateZoffl(list.get(i)) == 1) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    @Override
    public List<Zwipq> selectZwipq(IRequest request, String deptId, String lineId, String plineId, Integer zremade, String attr1After, String attr1Before,
                                   String shift, String sfflg, String diecd, String zxhbar, String zgjbar, Integer online, Integer zzxkl, Integer zqjkl, Integer zoffl, Integer status) {
        List<Zwipq> zwipq = zwipqMapper.selectZwipq(deptId, lineId, plineId, zremade, attr1After, attr1Before, shift, sfflg, diecd, zxhbar, zgjbar, online, zzxkl, zqjkl, zoffl, status);
        for (int i = 0; i < zwipq.size(); i++) {
            Zwipq zwipq1 = zwipq.get(i);
            Employee list = new Employee();
            list.setEmployeeId(zwipq1.getCreatedBy());
            Employee employee = employeeMapper.selectOne(list);
            if (employee == null) {
                zwipq1.setCreateBy("");
            } else {
                zwipq1.setCreateBy(employee.getEmployeeCode());
            }
        }
        return zwipq;
    }

    @Override
    public List<Zwipq> selectIORZwipq(IRequest request, String deptId, String lineId, String pmatnr, String attr1After, String attr1Before, String shift) {
        List<Zwipq> list = zwipqMapper.selectIORZwipq(deptId, lineId, pmatnr, attr1After, attr1Before, shift);
        List<Zwipq> list1 = new ArrayList<Zwipq>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < list.size(); i++) {
            Zwipq zwipq = list.get(i);
            String lineId1 = zwipq.getLineId();
            String pmatnr1 = zwipq.getPmatnr();
            String zpgdbar = zwipq.getZpgdbar();
            double zsxnum = zwipqMapper.selectZsxnum1(lineId1, pmatnr1, zpgdbar, null, null, null, null);
            if (zsxnum != 0) {
                long zzxkl = zwipqMapper.selectZsxnum1(lineId1, pmatnr1, zpgdbar, 1, 0, 0, 0);
                zwipq.setZsxnum(zsxnum);
                zwipq.setZzxkl(zzxkl);
                zwipq.setProcessed(zwipqMapper.selectZsxnum1(lineId1, pmatnr1, zpgdbar, 0, 0, 0, 0));
                zwipq.setZoutnum(inOutRecordMapper.selectZoutnum1(lineId1, pmatnr1, zpgdbar, 3));
                zwipq.setScrap(inOutRecordMapper.selectZoutnum1(lineId1, pmatnr1, zpgdbar, 2));
                String rate = df.format(zzxkl / zsxnum * 100) + "%";
                zwipq.setRate(rate);
                list1.add(zwipq);
            }
        }
        return list1;
    }

    @Override
    public List<Zwipq> selectByZxhbar(String zxhbar) {
        return zwipqMapper.selectByZxhbar(zxhbar);
    }

    @Override
    public List<Zwipq> selectByLineIdAndZxhbarAndZOFFL(String line_id, String zxhbar, String zoffl) {
        return zwipqMapper.selectByLineIdAndZxhbarAndZOFFL(line_id, zxhbar, zoffl);
    }

    @Override
    public int selectByJjzpgdbar(String zpgdbar) {
        return zwipqMapper.selectByJjzpgdbar(zpgdbar);
    }

    @Override
    public List<Zwipq> getlastsumbit(String line_id, String zxhbar) {
        return zwipqMapper.getlastsumbit(line_id, zxhbar);
    }

    @Override
    public List<Zwipq> selectSumzsxnum(String zxhbar) {
        return zwipqMapper.selectSumzsxnum(zxhbar);
    }

    @Override
    public List<Zwipq> selectcharg(String pkgLineId, String matnr) {
        return zwipqMapper.selectcharg(pkgLineId, matnr);
    }

    @Override
    public List<Zwipq> selectcharg2(String pkgLineId, String matnr) {
        return zwipqMapper.selectcharg2(pkgLineId, matnr);
    }

    @Override
    public Zwipq selectcharg3(String zsxjlh, String pkgLineId, String matnr) {
        return zwipqMapper.selectcharg3(zsxjlh,pkgLineId,matnr);
    }

    @Override
    public Integer selectByzsxnum(String pkgLineId, String matnr,String charg) {
        return zwipqMapper.selectByzsxnum(pkgLineId, matnr,charg);
    }

    @Override
    public Integer selectByzsxnum1(String pkgLineId, String matnr,String charg) {
        return zwipqMapper.selectByzsxnum1(pkgLineId, matnr,charg);
    }

    @Override
    public Integer selectByzsxnum2(String pkgLineId, String matnr,String charg) {
        return zwipqMapper.selectByzsxnum2(pkgLineId, matnr,charg);
    }

    @Override
    public List<Zwipq> selectByZpgdbar2(String zpgdbar2) {
        return zwipqMapper.selectByZpgdbar2(zpgdbar2);
    }
}
package yj.core.dftdtl.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dftdtl.dto.Dftdtl;
import yj.core.dftdtl.mapper.DftdtlMapper;
import yj.core.dftdtl.service.IDftdtlService;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.zudlist.mapper.ZudlistMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DftdtlServiceImpl extends BaseServiceImpl<Dftdtl> implements IDftdtlService {
    @Autowired
    private DftdtlMapper dftdlMapper;
    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private ZudlistMapper zudlistMapper;
    @Override
    public List<Dftdtl> selectByQpcode(String code, String matnr) {
        return dftdlMapper.selectByQpcode(code,matnr);
    }

    @Override
    public List<Dftdtl> selectFromPage(IRequest requestContext, int page, int pageSize, String werks, String matnr, String code) {
        PageHelper.startPage(page, pageSize);
        return dftdlMapper.selectFromPage(werks, matnr, code);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Dftdtl> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Dftdtl dftdtl = dto.get(i);
                dftdtl.setWerks("1001");
                if("Y".equals(dftdtl.getCastingFlag())){
                    dftdtl.setCastingFlag("1");
                }else{
                    dftdtl.setCastingFlag("0");
                }if("Y".equals(dftdtl.getMachingFlag())){
                    dftdtl.setMachingFlag("1");
                }else{
                    dftdtl.setMachingFlag("0");
                }
                if ("Y".equals(dftdtl.getDftalarm())) {
                    dftdtl.setDftalarm("1");
                }else{
                    dftdtl.setDftalarm("0");
                }
                int num = dftdlMapper.isExit(dftdtl.getWerks(),dftdtl.getTlevelcode(),dftdtl.getMatnr());
                if (num == 1){
                    if(dftdtl.getCreationDate() == null){
                        dftdtl.setCreationDate(new Date());
                        dftdtl.setCreatedBy(Long.valueOf(userId));
                    }
                    dftdtl.setLastUpdatedBy(Long.valueOf(userId));
                    dftdtl.setLastUpdateDate(new Date());
                    dftdlMapper.updateDftdtl(dftdtl);
                }else{
                    dftdtl.setCreationDate(new Date());
                    dftdtl.setCreatedBy(Long.valueOf(userId));
                    dftdlMapper.insertDftdtl(dftdtl);
                }
            }
        }
        return null;
    }

    @Override
    public String deleteDftdtl(List<Dftdtl> dto) {
        if(dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                Dftdtl dftdtl = dto.get(i);
                String matnr = dftdtl.getMatnr();
                String tlevelcode = dftdtl.getTlevelcode();
                Integer num = inOutRecordMapper.selectDftdtl(matnr,tlevelcode);
                Integer num2 = zudlistMapper.selectDftdtl(matnr,tlevelcode);
                if(num != 0 || num2 != 0){
                    return "缺陷代码已使用，不允许删除！";
                }else{
                    dftdlMapper.deleteDftdtl(dftdtl);
                }
            }
        }
        return null;
    }

    @Override
    public List<Dftdtl> selectbyWerksAndMatnr(String werks, String matnr) {
        return dftdlMapper.selectbyWerksAndMatnr(werks,matnr);
    }

    @Override
    public List<Dftdtl> selectByQpcodeForJj(String code, String Matnr) {
        return dftdlMapper.selectByQpcodeForJj(code,Matnr);
    }
}
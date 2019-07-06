package yj.kanb.marcres.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.marcres.dto.MarcRes;
import yj.kanb.marcres.mapper.MarcResMapper;
import yj.kanb.marcres.service.IMarcResService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MarcResServiceImpl extends BaseServiceImpl<MarcRes> implements IMarcResService {
    @Autowired
    private MarcResMapper marcResMapper;

    @Override
    public String submitMarcRes(IRequest requestCtx, List<MarcRes> dto, String userId) {
        if (dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                MarcRes marcRes = dto.get(i);
                MarcRes marcRes1 = marcResMapper.selectByMatnr(marcRes.getBukrs(),marcRes.getWerks(),marcRes.getMatnr());
                if(marcRes1 == null){
                    marcRes.setCreatedBy(Long.valueOf(userId));
                    marcRes.setCreationDate(new Date());
                    marcResMapper.insertMarcRes(marcRes);
                }else{
                    marcRes.setLastUpdatedBy(Long.valueOf(userId));
                    marcRes.setLastUpdateDate(new Date());
                    marcResMapper.updateMarcRes(marcRes);
                }
            }
        }
        return null;
    }

    @Override
    public MarcRes selectByMatnr(String bukrs, String werks, String matnr) {
        return marcResMapper.selectByMatnr(bukrs,werks,matnr);
    }
}
package yj.core.mouldcavity.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.mouldcavity.dto.Mouldcavity;
import yj.core.mouldcavity.mapper.MouldcavityMapper;
import yj.core.mouldcavity.service.IMouldcavityService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MouldcavityServiceImpl extends BaseServiceImpl<Mouldcavity> implements IMouldcavityService {

    @Autowired
    private MouldcavityMapper mouldcavityMapper;

    @Override
    public List<Mouldcavity> selectMouldcavity(IRequest requestCtx, Mouldcavity dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return mouldcavityMapper.selectMouldcavity(dto);
    }

    @Override
    public String updateMouldcavity(IRequest requestCtx, List<Mouldcavity> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Mouldcavity mouldcavity = dto.get(i);
                List<Mouldcavity> list = mouldcavityMapper.selectMouldcavity(mouldcavity);
                if(list.size() == 0){
                    mouldcavity.setCreatedBy(Long.valueOf(userId));
                    mouldcavity.setCreationDate(new Date());
                    mouldcavityMapper.insertMouldcavity(mouldcavity);
                }else{
                    mouldcavity.setLastUpdatedBy(Long.valueOf(userId));
                    mouldcavity.setLastUpdateDate(new Date());
                    mouldcavityMapper.updateMouldcavity(mouldcavity);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteMouldcavity(List<Mouldcavity> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                mouldcavityMapper.deleteMouldcavity(dto.get(i));
            }
        }
    }

    @Override
    public List<Mouldcavity> selectByWerksAndMatnr(String matnr, String werks) {
        return mouldcavityMapper.selectByWerksAndMatnr(matnr,werks);
    }
}
package yj.core.wiplockconstrelation.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiplockconstrelation.dto.LockConstRelation;
import yj.core.wiplockconstrelation.mapper.LockConstRelationMapper;
import yj.core.wiplockconstrelation.service.ILockConstRelationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LockConstRelationServiceImpl extends BaseServiceImpl<LockConstRelation> implements ILockConstRelationService {
    @Autowired
    private LockConstRelationMapper lockConstRelationMapper;

    @Override
    public List<LockConstRelation> selectFromPage(IRequest requestContext, LockConstRelation dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return lockConstRelationMapper.selectLockConstRelation(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<LockConstRelation> dto, String userName) {
        if(dto.size() > 0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(dto.get(0).getId() == null || dto.get(0).getId() == ""){
                String uuid = UUID.randomUUID().toString();
                dto.get(0).setId(uuid);
                dto.get(0).setCreatedBy(userName);
                dto.get(0).setCreationDate(df.format(new Date()));
                lockConstRelationMapper.insertLockConstRelation(dto.get(0));
            }else{
                dto.get(0).setLastUpdatedBy(userName);
                dto.get(0).setLastUpdatedDate(df.format(new Date()));
                lockConstRelationMapper.updateLockConstRelation(dto.get(0));
            }
        }
        return null;
    }
}
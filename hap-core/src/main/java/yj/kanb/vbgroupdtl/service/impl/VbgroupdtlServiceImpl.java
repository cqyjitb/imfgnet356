package yj.kanb.vbgroupdtl.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;
import yj.kanb.vbgroupdtl.mapper.VbgroupdtlMapper;
import yj.kanb.vbgroupdtl.service.IVbgroupdtlService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.mapper.VblinegroupheaderMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VbgroupdtlServiceImpl extends BaseServiceImpl<Vbgroupdtl> implements IVbgroupdtlService {
    @Autowired
    private VbgroupdtlMapper vbgroupdtlMapper;
    @Autowired
    private VblinegroupheaderMapper vblinegroupheaderMapper;

    @Override
    public String insertGroupDtl(List<Vbgroupdtl> dto, String userId) {
        if (dto.size() > 0){
            List<Vblinegroupheader> list = vblinegroupheaderMapper.selectLineGroupH2(dto.get(0).getVbgroupId());
            for(int i=0;i<dto.size();i++){
                if (list.size() > 0) {
                    int j;
                    for (j = 0; j < list.size(); j++) {
                        if (list.get(j).getGroupId().equals(dto.get(i).getGroupId())) {
                            break;
                        }
                    }
                    if(j == list.size()){
                        Vbgroupdtl vbgroupdtl = dto.get(i);
                        vbgroupdtl.setCreatedBy(Long.valueOf(userId));
                        vbgroupdtl.setCreationDate(new Date());
                        vbgroupdtlMapper.insertGroupDtl(vbgroupdtl);
                    }
                }else{
                    Vbgroupdtl vbgroupdtl = dto.get(i);
                    vbgroupdtl.setCreatedBy(Long.valueOf(userId));
                    vbgroupdtl.setCreationDate(new Date());
                    vbgroupdtlMapper.insertGroupDtl(vbgroupdtl);
                }
            }
        }
        return null;
    }

    @Override
    public String deleteGroupDtl(List<Vbgroupdtl> dto) {
        if (dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                vbgroupdtlMapper.deleteGroupDtl(dto.get(i));
            }
        }
        return null;
    }
}

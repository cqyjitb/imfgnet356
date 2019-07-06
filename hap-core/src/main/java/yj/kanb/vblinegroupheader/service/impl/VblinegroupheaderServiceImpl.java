package yj.kanb.vblinegroupheader.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.mapper.VblinegroupheaderMapper;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VblinegroupheaderServiceImpl extends BaseServiceImpl<Vblinegroupheader> implements IVblinegroupheaderService {
    @Autowired
    private VblinegroupheaderMapper vblinegroupheaderMapper;
    @Override
    public List<Vblinegroupheader> selectAllGroup() {
        return vblinegroupheaderMapper.selectAllGroup();
    }

    @Override
    public List<Vblinegroupheader> queryLineGroupH(IRequest requestCtx, Vblinegroupheader dto) {
        return vblinegroupheaderMapper.selectLineGroupH(dto);
    }

    @Override
    public void deleteLineGroupH(IRequest requestCtx, List<Vblinegroupheader> dto) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                vblinegroupheaderMapper.deleteLineGroupH(dto.get(i));
            }
        }
    }

    @Override
    public String insertOrUpdate(IRequest requestCtx, List<Vblinegroupheader> dto, String userId) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                Vblinegroupheader lineGroupH = dto.get(i);
                if("".equals(lineGroupH.getGroupId()) || lineGroupH.getGroupId() == null){
                    String groupId = UUID.randomUUID().toString();
                    lineGroupH.setGroupId(groupId);
                    lineGroupH.setCreatedBy(Long.valueOf(userId));
                    lineGroupH.setCreationDate(new Date());
                    vblinegroupheaderMapper.insertLineGroupH(lineGroupH);
                }else {
                    lineGroupH.setLastUpdatedBy(Long.valueOf(userId));
                    lineGroupH.setLastUpdateDate(new Date());
                    vblinegroupheaderMapper.updateLineGroupH(lineGroupH);
                }
            }
        }
        return null;
    }

    @Override
    public String setMessage(List<Vblinegroupheader> dto) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                if(dto.get(i).getBukrs() == null || "".equals(dto.get(i).getBukrs())){
                    return "公司不能为空！";
                }else if(dto.get(i).getWerks() == null || "".equals(dto.get(i).getWerks())){
                    return "工厂不能为空！";
                }else if(dto.get(i).getWorkshopId() == null || "".equals(dto.get(i).getWorkshopId())){
                    return "车间ID不能为空！";
                }else if(dto.get(i).getProduct() == null || "".equals(dto.get(i).getProduct())){
                    return "产品物料编码不能为空！";
                }else if(!("".equals(dto.get(i).getTempleteUrl()))){
                    try {
                        URL url = new URL(dto.get(i).getTempleteUrl());
                        URLConnection conn = url.openConnection();
                        conn.connect();
                    } catch (IOException e) {
                        return "模报URL格式不正确！";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Vblinegroupheader> selectLineGroupH(IRequest requestCtx, String vbgroupId) {
        return vblinegroupheaderMapper.selectLineGroupH2(vbgroupId);
    }
}

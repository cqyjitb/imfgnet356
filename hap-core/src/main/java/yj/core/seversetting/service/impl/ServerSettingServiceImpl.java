package yj.core.seversetting.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.mapper.ServerSettingMapper;
import yj.core.seversetting.service.IServerSettingService;

@Service
@Transactional
public class ServerSettingServiceImpl extends BaseServiceImpl<ServerSetting> implements IServerSettingService {

    @Autowired
    private ServerSettingMapper serverSettingMapper;
    @Override
    public ServerSetting selectByLineId(String werks, String lineId) {
        return serverSettingMapper.selectByLineId(werks,lineId);
    }
}
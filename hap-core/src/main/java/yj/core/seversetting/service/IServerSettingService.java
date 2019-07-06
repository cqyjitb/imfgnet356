package yj.core.seversetting.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.seversetting.dto.ServerSetting;

public interface IServerSettingService extends IBaseService<ServerSetting>, ProxySelf<IServerSettingService> {

    ServerSetting selectByLineId(String werks, String lineId);
}
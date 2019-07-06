package yj.core.wipbarcodesetting.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipbarcodesetting.dto.BarcodeSetting;

import java.util.List;

public interface IBarcodeSettingService extends IBaseService<BarcodeSetting>, ProxySelf<IBarcodeSettingService> {

    List<BarcodeSetting> selectFromPage(IRequest request, BarcodeSetting dto, int page, int pageSize);
    String updateOrInsert(IRequest requestCtx, List<BarcodeSetting> dto, String userName);
    void deleteBarcodeSetting(List<BarcodeSetting> dto);
}
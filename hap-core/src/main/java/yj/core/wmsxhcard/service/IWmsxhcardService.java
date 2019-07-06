package yj.core.wmsxhcard.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wmsxhcard.dto.Wmsxhcard;

public interface IWmsxhcardService extends IBaseService<Wmsxhcard>, ProxySelf<IWmsxhcardService> {
    Wmsxhcard selectByBarcode(String zxhbar, String werks);
    int updateZsxwc(Wmsxhcard wmsxhcard);
}
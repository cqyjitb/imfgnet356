package yj.core.wipbarcodespecialcode.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipbarcodespecialcode.dto.BarcodeSpecialcode;

import java.util.List;

public interface IBarcodeSpecialcodeService extends IBaseService<BarcodeSpecialcode>, ProxySelf<IBarcodeSpecialcodeService> {

    List<BarcodeSpecialcode> selectFromPage(IRequest request, BarcodeSpecialcode dto, int page, int pageSize);
    String setMessageBarcodeSpecialcode(List<BarcodeSpecialcode> dto);
    String updateOrInsert(IRequest requestCtx, List<BarcodeSpecialcode> dto, String userName);
}
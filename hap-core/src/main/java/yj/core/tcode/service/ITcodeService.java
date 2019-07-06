package yj.core.tcode.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.tcode.dto.Tcode;

import java.util.List;

public interface ITcodeService extends IBaseService<Tcode>, ProxySelf<ITcodeService> {
    String updateOrInsert(IRequest request, List<Tcode> dto);
    int isExit(Tcode dto);
    int selectByUserName(String username);

    Tcode getProfile(String userName);
}
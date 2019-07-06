package yj.core.pandiantcode.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.pandiantcode.dto.Pandiantcode;

public interface IPandiantcodeService extends IBaseService<Pandiantcode>, ProxySelf<IPandiantcodeService> {

    /**
     *  检查天津盘点权限 917110140
     * @param username
     * @return
     */
    Pandiantcode checkTjcode(String username);

    /**
     *  检查工装制造部盘点权限 917110140
     * @param username
     * @return
     */
    Pandiantcode checkGzcode(String username);
}
package yj.core.cardhlock.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.cardhlock.dto.Cardhlock;

public interface ICardhlockService extends IBaseService<Cardhlock>, ProxySelf<ICardhlockService> {

    /**
     *  根据流转卡号 查询锁记录
     * @param zpgdbar
     * @return
     */
    Cardhlock selectByZpgdbar(String zpgdbar, String vornr);

    /**
     *  新增流转卡锁记录
     * @param lock
     * @return
     */
    int insertCardhlock(Cardhlock lock);

    /**
     *  根据流转卡号 删除锁记录
     * @param zpgdbar
     * @return
     */
    int deleteCardhlock(String zpgdbar, String vornr);

}
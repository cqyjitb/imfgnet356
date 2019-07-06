package yj.core.cardhlock.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.cardhlock.dto.Cardhlock;

public interface CardhlockMapper extends Mapper<Cardhlock> {

    /**
     *  根据流转卡号 查询锁记录
     * @param zpgdbar
     * @return
     */
    Cardhlock selectByZpgdbar(@Param("zpgdbar") String zpgdbar, @Param("vornr") String vornr);

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
    int deleteCardhlock(@Param("zpgdbar") String zpgdbar, @Param("vornr") String vornr);

}
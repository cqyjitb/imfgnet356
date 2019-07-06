package yj.core.wipshotnum.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipshotnum.dto.Shotnum;

import java.util.List;

public interface IShotnumService extends IBaseService<Shotnum>, ProxySelf<IShotnumService> {
    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext);

    /**
     *  插入新记录 917110140
     * @param shot
     * @return
     */
    int insertRow(Shotnum shot);

    /**
     *  根据 工厂 工作中心 生产日期 班次 查询记录 917110140
     * @param werks
     * @param arbpl
     * @param prd_date
     * @param shifts
     * @return
     */
    List<Shotnum> isExit(String werks, String arbpl, String prd_date, String shifts);

    /**
     * 压射号维护查询 918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> queryShotnum(Shotnum dto, IRequest requestContext);

    /**
     * 压射号维护修改 918100064
     * @param requestContext
     * @param dto
     * @param userId
     * @return
     */
    String updateShotnum(IRequest requestContext, List<Shotnum> dto, String userId);

    /**
     * 压射号维护删除 918100064
     * @param dto
     * @return
     */
    String deleteShotnum(List<Shotnum> dto);

    /**
     * 压射号及报工统计表  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum2(Shotnum dto, IRequest requestContext);

    /**
     * 压射号异常明细表  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum3(Shotnum dto, IRequest requestContext);
}
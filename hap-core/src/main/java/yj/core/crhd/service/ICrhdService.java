package yj.core.crhd.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.crhd.dto.Crhd;

import java.util.List;

public interface ICrhdService extends IBaseService<Crhd>, ProxySelf<ICrhdService> {

    /**
     *  根据OBJID 查询记录数量 917110140
     * @param objid
     * @return
     */
    int selectnumByObjid(String objid);

    /**
     *  更新条目 917110140
     * @param list
     * @return
     */
    int updateRows(List<Crhd> list);

    /**
     *  插入新条目 917110140
     * @param list
     * @return
     */
    int insertRows(List<Crhd> list);

    /**
     *  根据工作中心编号，工厂查询记录
     * @param werks
     * @param arbpl
     * @return
     */
    List<Crhd> selecByWerksAndArbpl(String werks, String arbpl);
}
package yj.core.zudhead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zudhead.dto.Zudhead;

public interface IZudheadService extends IBaseService<Zudhead>, ProxySelf<IZudheadService> {
    String selectMaxNo(String curdat);
    int insertHead(Zudhead zudhead);

    /**
     *  根据单号查询不合格品审理单1 表头 917110140
     * @param zudnum
     * @return
     */
    Zudhead selectByZudnum(String zudnum);

    /**
     *  更新不合格品审理单1 表头状态 917110140
     * @param head
     * @return
     */
    int updateHead(Zudhead head);
}
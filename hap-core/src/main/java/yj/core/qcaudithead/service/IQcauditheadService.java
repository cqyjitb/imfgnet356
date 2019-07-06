package yj.core.qcaudithead.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcaudithead.dto.Qcaudithead;

import java.util.List;

public interface IQcauditheadService extends IBaseService<Qcaudithead>, ProxySelf<IQcauditheadService> {
    /**
     *  根据工厂,生产日期 获取当前工厂当前生产日期 最大单号 917110140
     * @param werks
     * @param gstrp
     * @return
     */
    String selectMaxRecordId(String werks, String gstrp);

    /**
     *  插入新行 917110140
     * @param qcaudithead
     * @return
     */
    int insertNewRow(Qcaudithead qcaudithead);

    /**
     * 根据条件查询不合格品审理单2  918100064
     * @param requestCtx
     * @param dto
     * @return
     */
    List<Qcaudithead> selectForQcaudithead(IRequest requestCtx, Qcaudithead dto);

    /**
     *  根据工厂 单号查询不合格品审理单2表头信息 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcaudithead> selectById(String werks, String recordid);

    int updateRow(List<Qcaudithead> list);

    int deleteById(String werks, String recordid);
}
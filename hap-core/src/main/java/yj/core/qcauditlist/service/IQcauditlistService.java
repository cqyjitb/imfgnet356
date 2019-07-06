package yj.core.qcauditlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcauditlist.dto.Qcauditlist;

import java.util.List;

public interface IQcauditlistService extends IBaseService<Qcauditlist>, ProxySelf<IQcauditlistService> {
    /**
     *  插入新数据记录 917110140
     * @param list
     * @return
     */
    int insertNewRow(List<Qcauditlist> list);

    /**
     *  根据主记录ID 查询明细行 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditlist> selectById(String werks, String recordid);

    /**
     *  根据工厂 ID 查询行项目数量
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditlist> selectCounts(String werks, String recordid);

    /**
     *  根据工厂 ID 删除行数据
     * @param werks
     * @param recordid
     * @return
     */
    int deleteById(String werks, String recordid);

    /**
     *  根据工厂 id 行号 批量查询不合格品审理单2 行数据 917110140
     * @param werks
     * @param recordid
     * @param item
     * @return
     */
    Qcauditlist selectBatch(String werks, String recordid, String item);

    /**
     *  更新不合格品审理单2 处理状态 917110140
     * @param list
     * @return
     */
    int updateStatus(List<Qcauditlist> list);
}
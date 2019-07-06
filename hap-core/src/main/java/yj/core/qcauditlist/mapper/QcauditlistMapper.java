package yj.core.qcauditlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qcauditlist.dto.Qcauditlist;

import java.util.List;

public interface QcauditlistMapper extends Mapper<Qcauditlist> {
    /**
     *  插入新数据记录 917110140
     * @param qcauditlist
     * @return
     */
    int insertNewRow(Qcauditlist qcauditlist);

    /**
     *  根据主记录ID 查询明细行 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditlist> selectById(@Param("werks") String werks, @Param("recordid") String recordid);

    /**
     *  根据工厂 ID 查询行项目数量
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditlist> selectCounts(@Param("werks") String werks, @Param("recordid") String recordid);

    /**
     *  根据工厂 ID 删除行记录
     * @param werks
     * @param recordid
     * @return
     */
    int deleteById(@Param("werks") String werks, @Param("recordid") String recordid);


    /**
     *  根据工厂 id 行号 批量查询不合格品审理单2 行数据 917110140
     * @param werks
     * @param recordid
     * @param item
     * @return
     */
    Qcauditlist selectBatch(@Param("werks") String werks, @Param("recordid") String recordid, @Param("item") String item);

    /**
     *  更新不合格品审理单2 处理状态 917110140
     * @param qcauditlist
     * @return
     */
    int updateStatus(Qcauditlist qcauditlist);
}
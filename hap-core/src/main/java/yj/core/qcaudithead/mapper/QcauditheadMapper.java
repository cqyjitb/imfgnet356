package yj.core.qcaudithead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qcaudithead.dto.Qcaudithead;

import java.util.List;

public interface QcauditheadMapper extends Mapper<Qcaudithead> {
    /**
     *  根据工厂,生产日期 获取当前工厂当前生产日期 最大单号 917110140
     * @param werks
     * @param gstrp
     * @return
     */
    String selectMaxRecordId(@Param("werks") String werks, @Param("gstrp") String gstrp);

    /**
     *  插入新行 917110140
     * @param qcaudithead
     * @return
     */
    int insertNewRow(Qcaudithead qcaudithead);

    /**
     * 根据条件查询不合格品审理单2   918100064
     * @param dto
     * @return
     */
    List<Qcaudithead> selectForQcaudithead(Qcaudithead dto);

    /**
     *  根据工厂 单号查询不合格品审理单2表头信息 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcaudithead> selectById(@Param("werks") String werks, @Param("recordid") String recordid);

    /**
     *  更新行 917110140
     * @param qcaudithead
     * @return
     */
    int updateRow(Qcaudithead qcaudithead);

    /**
     *  根据工厂 ID 删除行 917110140
     * @param werks
     * @param recordid
     * @return
     */
    int deleteById(@Param("werks") String werks, @Param("recordid") String recordid);
}
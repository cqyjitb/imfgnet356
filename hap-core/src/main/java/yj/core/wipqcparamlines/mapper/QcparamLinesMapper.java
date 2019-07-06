package yj.core.wipqcparamlines.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipqcparamlines.dto.QcparamLines;

import java.util.List;

public interface QcparamLinesMapper extends Mapper<QcparamLines> {

    /**
     *  根据生产线ID 查询产线参数配置信息 + 机加责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForJj(@Param("lineId") Long line_id, @Param("werks") String werks);


    /**
     *  根据生产线ID 查询产线参数配置信息 + 压铸责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */

    QcparamLines selectForYz(@Param("lineId") Long line_id, @Param("werks") String werks);

    /**
     * 根据生产线ID 查询产线参数配置信息 918100064
     * @param line_id
     * @return
     */
    QcparamLines selectByLineId(@Param("lineId") Long line_id);

    /**
     * 机加质量控制参数维护表查询 918100064
     * @param dto
     * @return
     */
    List<QcparamLines> selectQcparamLines(QcparamLines dto);

    /**
     * 根据生产线ID 查询机加质量控制参数维护表数据 918100064
     * @param line_id
     * @return
     */
    List<QcparamLines> selectByLineId2(@Param("lineId") Long line_id);
    /**
     * 机加质量控制参数维护表添加 918100064
     * @param dto
     */
    void insertQcparamLines(QcparamLines dto);

    /**
     * 机加质量控制参数维护表修改 918100064
     * @param dto
     */
    void updateQcparamLines(QcparamLines dto);

    /**
     * 机加质量控制参数维护表删除 918100064
     * @param dto
     */
    void deleteQcparamLines(QcparamLines dto);
}
package yj.core.wiplines.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wiplines.dto.Lines;

import java.util.List;

public interface LinesMapper extends Mapper<Lines> {
    Lines selectById(long line_id);

    List<Lines> selectAll();

    Integer isExit(Long lineId);

    /**
     * 机加生产线维护查询 918100064
     * @param lines
     * @return
     */
    List<Lines> selectFromPage(Lines lines);

    /**
     *机加生产线维护修改 918100064
     * @param lines
     * @return
     */
    Integer updateLines(Lines lines);

    /**
     *机加生产线维护添加 918100064
     * @param lines
     */
    void insertLines(Lines lines);

    /**
     * LOV_LINE_NEW：根据生产车间查询生产线Id 918100064
     * @return
     */
    List<Lines> selectLov(@Param("deptId") String deptId, @Param("lineId") Long lineId);

    /**
     * 根据表hr_org_unit_b中unitCode查询记录 918100064
     * @param unitCode
     * @return
     */
    Lines selectUnit(@Param("unitCode") String unitCode);

    /**
     *根据表hr_org_unit_b的主键unitId查询表wip_lines的部门Id 918100064
     * @param unitId
     * @return
     */
    String selectDeptId(@Param("unitId") Long unitId);

    /**
     *根据wip_lines的父产线ID查询父产线描述 918100064
     * @param plineId
     * @return
     */
    String selectDescription(@Param("plineId") Long plineId);

    /**
     *  检查一条产线是否是 主产线 917110140
     * @param line_id
     * @return
     */
    List<Lines> selectByPlineId(String line_id);

    /**
     * 根据生产车间查询车间名称
     * @param unitCode
     * @return
     */
    String selectByUnitCode(@Param("unitCode") String unitCode);

    /**
     *  根据工厂 车间ID 查询产线
     * @param werks
     * @param deptId
     * @return
     */
    List<Lines> selectByWerksAndDeptid(@Param("werks") String werks, @Param("deptId") String deptId);
}
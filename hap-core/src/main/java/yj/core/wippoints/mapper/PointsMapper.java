package yj.core.wippoints.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wippoints.dto.Points;

import java.util.List;

public interface PointsMapper extends Mapper<Points> {

    /**
     * 产线工序配置维护查询 918100064
     * @param points
     * @return
     */
    List<Points> selectFromPage(Points points);

    /**
     * 根据主键查询是否有数据 918100064
     * @param pointId
     * @return
     */
    Integer isExit(Integer pointId);

    /**
     * 产线工序配置维护修改 918100064
     * @param lines
     * @return
     */
    Integer updatePoints(Points lines);

    /**
     * 产线工序配置维护添加 918100064
     * @param lines
     */
    void insertPoints(Points lines);

    /**
     * 产线工序配置维护删除 918100064
     * @param points
     */
    void deletePoints(Points points);

    /**
     * 查询产线ID及描述（LOV_POINTS_NEW）918100064
     * @return
     */
    List<Points> selectLineId();

    /**
     * 根据产线ID查询工艺编码(LOV_POINTS_NEW2) 918100064
     * @param lineId
     * @return
     */
    List<Points> selectPointId(@Param("lineId") Long lineId);
}
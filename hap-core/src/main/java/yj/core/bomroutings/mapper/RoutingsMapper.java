package yj.core.bomroutings.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.bomroutings.dto.Routings;

import java.util.List;

public interface RoutingsMapper extends Mapper<Routings> {
    /**
     * 工艺路线头表查询 918100064
     * @param routings
     * @return
     */
    List<Routings> selectFromPage(Routings routings);

    /**
     * 根据主键查询是否有数据 918100064
     * @param routingId
     * @return
     */
    Integer isExit(Integer routingId);

    /**
     * 工艺路线头表修改 918100064
     * @param routings
     * @return
     */
    Integer updateRoutings(Routings routings);

    /**
     * 工艺路线头表添加 918100064
     * @param routings
     */
    void insertRoutings(Routings routings);

    /**
     * 工艺路线头表删除 918100064
     * @param routingId
     */
    void deleteRoutings(@Param("routingId") Integer routingId);

    /**
     * 查询主键的最大值 918100064
     * @return
     */
    Integer selectMaxRoutings();
}
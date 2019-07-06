package yj.core.wipdot.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipdot.dto.Dot;

import java.util.List;

public interface DotMapper extends Mapper<Dot> {

    /**
     * 根据工序Id查询数据的条数 918100064
     * @param pointId
     * @return
     */
    int selectPoints(@Param("pointId") Integer pointId);

    /**
     * 机加采集点配置维护查询 918100064
     * @param dot
     * @return
     */
    List<Dot> selectFromPage(Dot dot);

    /**
     * 机加采集点配置维护修改 918100064
     * @param dot
     */
    void updateDot(Dot dot);

    /**
     * 机加采集点配置维护添加 918100064
     * @param dot
     */
    void insertDot(Dot dot);

    /**
     * 根据生产线查询采集点 918100064
     * @param lineId
     * @return
     */
    List<Dot> selectByLineId(@Param("lineId") Long lineId);
}
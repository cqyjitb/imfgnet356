package yj.core.bomoperations.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.bomoperations.dto.Operations;

import java.util.List;

public interface OperationsMapper extends Mapper<Operations> {

    /**
     * 根据工序Id查询数据的条数 918100064
     * @param pointId
     * @return
     */
    int selectPoints(@Param("pointId") Integer pointId);

    /**
     * 工艺路线行表查询 918100064
     * @param routingId
     * @return
     */
    List<Operations> selectFromPage(@Param("routingId") Integer routingId);

    /**
     * 根据主键查询是否有数据 918100064
     * @param operationId
     * @return
     */
    Integer isExit(Integer operationId);

    /**
     * 工艺路线行表修改 918100064
     * @param operations
     * @return
     */
    Integer updateOperations(Operations operations);

    /**
     * 工艺路线行表添加 918100064
     * @param operations
     */
    void insertOperations(Operations operations);

    /**
     * 工艺路线行表删除 918100064
     * @param operationId
     * @param routingId
     */
    void deleteOperations(@Param("operationId") Integer operationId, @Param("routingId") Integer routingId);
}
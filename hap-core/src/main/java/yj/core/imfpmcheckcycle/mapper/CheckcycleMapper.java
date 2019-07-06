package yj.core.imfpmcheckcycle.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.imfpmcheckcycle.dto.Checkcycle;

import java.util.List;

public interface CheckcycleMapper extends Mapper<Checkcycle> {

    /**
     * 点检周期的查询 918100064
     * @param id
     * @param checkcycle
     * @return
     */
    List<Checkcycle> selectCheckcycle(@Param("id") Integer id, @Param("checkcycle") String checkcycle);

    /**
     * 查询点检周期id的最大值 918100064
     * @return
     */
    int selectMaxCheckcycle();

    /**
     * 点检周期的修改 918100064
     * @param dto
     */
    void updateCheckcycle(Checkcycle dto);

    /**
     * 点检周期的添加 918100064
     * @param dto
     */
    void insertCheckcycle(Checkcycle dto);
}
package yj.core.imfpmcheckrequest.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.imfpmcheckrequest.dto.Checkrequest;

import java.util.List;

public interface CheckrequestMapper extends Mapper<Checkrequest> {

    /**
     * 点检要求的查询 918100064
     * @param id
     * @param checkreq
     * @return
     */
    List<Checkrequest> selectCheckrequest(@Param("id") Integer id, @Param("checkreq") String checkreq);

    /**
     * 查询点检要求id的最大值 918100064
     * @return
     */
    int selectMaxCheckrequest();

    /**
     * 点检要求的修改 918100064
     * @param dto
     */
    void updateCheckrequest(Checkrequest dto);

    /**
     * 点检要求的添加 918100064
     * @param dto
     */
    void insertCheckrequest(Checkrequest dto);
}
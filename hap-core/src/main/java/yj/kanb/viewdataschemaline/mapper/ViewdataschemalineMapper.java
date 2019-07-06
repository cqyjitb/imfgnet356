package yj.kanb.viewdataschemaline.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;

public interface ViewdataschemalineMapper extends Mapper<Viewdataschemaline> {
    Viewdataschemaline selectforKanb(@Param("groupId") String groupId, @Param("matnr") String matnr, @Param("deptId") String deptId,
                                     @Param("bukrs") String bukrs, @Param("werks") String werks);
    int insertforKanb(Viewdataschemaline viewdata);
    int updateforKanb(Viewdataschemaline viewdata);
}

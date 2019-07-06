package yj.kanb.wipdateclass.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.wipdateclass.dto.DateClass;

import java.util.List;

public interface DateClassMapper extends Mapper<DateClass> {
    /**
     * 日期范围及类名表查询 918100064
     * @param className
     * @return
     */
    List<DateClass> queryDateClass(@Param("className") String className);

    /**
     * 日期范围及类名表添加 918100064
     * @param dateClass
     */
    void insertDateClass(DateClass dateClass);

    /**
     * 日期范围及类名表修改 918100064
     * @param dateClass
     */
    void updateDateClass(DateClass dateClass);
}

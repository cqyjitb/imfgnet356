package yj.core.tmp.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.tmp.dto.Bartobar;

import java.util.List;

public abstract interface BartobarMapper
        extends Mapper<Bartobar>
{
    public abstract List<Bartobar> getNewBar(@Param("oldZPGDBAR") String paramString);
}

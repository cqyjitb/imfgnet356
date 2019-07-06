package yj.core.ztbc0018.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.ztbc0018.dto.Ztbc0018;

import java.util.List;

public interface Ztbc0018Mapper extends Mapper<Ztbc0018> {
    int insertZtbc0018(Ztbc0018 ztbc0018);

    /**
     *  根据箱号记录查询调账记录
     * @param zxhbar
     * @return
     */
    List<Ztbc0018> selectByZxhbar(String zxhbar);
}
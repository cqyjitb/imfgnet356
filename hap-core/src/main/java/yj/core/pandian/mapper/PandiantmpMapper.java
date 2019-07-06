package yj.core.pandian.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.pandian.dto.Pandiantmp;

public interface PandiantmpMapper extends Mapper<Pandiantmp> {

    /**
     *  插入新的盘点记录 917110140
     * @param pdtmp
     * @return
     */
    int insertNewRow(Pandiantmp pdtmp);
}
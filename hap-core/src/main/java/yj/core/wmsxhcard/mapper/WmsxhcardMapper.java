package yj.core.wmsxhcard.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wmsxhcard.dto.Wmsxhcard;

public interface WmsxhcardMapper extends Mapper<Wmsxhcard> {
    Wmsxhcard selectByBarcode(@Param("zxhbar") String zxhbar, @Param("werks") String werks);
    int updateZsxwc(Wmsxhcard wmsxhcard);
}
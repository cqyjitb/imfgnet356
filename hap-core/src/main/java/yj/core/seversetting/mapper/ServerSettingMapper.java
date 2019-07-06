package yj.core.seversetting.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.seversetting.dto.ServerSetting;

public interface ServerSettingMapper extends Mapper<ServerSetting> {

    /**
     *  根据产线ID 查询数据库用户等配置
     * @param werks
     * @param lineId
     * @return
     */
    ServerSetting selectByLineId(@Param("werks") String werks, @Param("lineId") String lineId);

}
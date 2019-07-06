package yj.core.wipbarcodesetting.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipbarcodesetting.dto.BarcodeSetting;

import java.util.List;

public interface BarcodeSettingMapper extends Mapper<BarcodeSetting> {

    /**
     * 机加产品识别码配置维护查询 918100064
     * @param dto
     * @return
     */
    List<BarcodeSetting> selectFromPage(BarcodeSetting dto);

    /**
     * 机加产品识别码配置维护修改 918100064
     * @param dto
     */
    void updateBarcodeSetting(BarcodeSetting dto);

    /**
     * 机加产品识别码配置维护添加 918100064
     * @param dto
     */
    void insertBarcodeSetting(BarcodeSetting dto);

    /**
     * 机加产品识别码配置维护删除 918100064
     * @param dto
     */
    void deleteBarcodeSetting(BarcodeSetting dto);

    /**
     * 查询表主键的最大值 918100064
     * @return
     */
    int maxBarcodeSettingByHeaderId();
}
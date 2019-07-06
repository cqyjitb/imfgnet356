package yj.core.wipbarcodespecialcode.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipbarcodespecialcode.dto.BarcodeSpecialcode;

import java.util.List;

public interface BarcodeSpecialcodeMapper extends Mapper<BarcodeSpecialcode> {
    /**
     * 机加产品识别码配置维护对话框查询 918100064
     * @param dto
     * @return
     */
    List<BarcodeSpecialcode> selectFromPage(BarcodeSpecialcode dto);

    /**
     * 机加产品识别码配置维护根据表头Id删除
     * @param headerId
     */
    void deleteBarcodeSpecialcode(@Param("headerId") Integer headerId);
}
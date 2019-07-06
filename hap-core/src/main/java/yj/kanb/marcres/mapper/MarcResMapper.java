package yj.kanb.marcres.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.marcres.dto.MarcRes;

public interface MarcResMapper extends Mapper<MarcRes> {
    /**
     * 根据公司、工厂及物料编码查询 918100064
     * @param bukrs
     * @param werks
     * @param matnr
     * @return
     */
    MarcRes selectByMatnr(@Param("bukrs") String bukrs, @Param("werks") String werks, @Param("matnr") String matnr);

    /**
     * 物料图片上传  918100064
     * @param marcRes
     */
    void insertMarcRes(MarcRes marcRes);

    /**
     * 物料图片修改 918100064
     * @param marcRes
     */
    void updateMarcRes(MarcRes marcRes);
}
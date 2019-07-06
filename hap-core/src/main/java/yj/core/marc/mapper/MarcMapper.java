package yj.core.marc.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.marc.dto.Marc;

import java.util.List;

public interface MarcMapper extends Mapper<Marc> {
    int isExit(String matnr);

    int updateByMatnr(Marc dto);

    int insertByMatnr(Marc dto);

    Marc selectByMatnr(String matnr);

    int saveChange(Marc marc);

    /**
     * 根据物料编码matnr查询表sap_marc 918100064
     * @param matnr
     * @return
     */
    List<Marc> selectMatnr(@Param("matnr") String matnr);

    /**
     * 查询产品物料 918100064
     * @param werks
     * @param matnr
     * @return
     */
    List<Marc> queryByMarc(@Param("werks") String werks, @Param("matnr") String matnr);

    String queryByFileId(Long fileId);
}
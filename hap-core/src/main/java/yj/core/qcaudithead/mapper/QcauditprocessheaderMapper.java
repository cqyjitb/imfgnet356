package yj.core.qcaudithead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qcaudithead.dto.Qcauditprocessheader;

public interface QcauditprocessheaderMapper extends Mapper<Qcauditprocessheader> {

    /**
     *  更新表头数据
     * @param qcauditprocessheader
     * @return
     */
    int updateData(Qcauditprocessheader qcauditprocessheader);

    /**
     * 根据ID 查询表头记录 917110140
     * @param recordid
     * @return
     */
    Qcauditprocessheader selectById(@Param("werks") String werks, @Param("recordid") String recordid);

    int insertData(Qcauditprocessheader qcauditprocessheader);
}
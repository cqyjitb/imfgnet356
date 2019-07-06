package yj.core.qcauditlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;

import java.util.List;

public interface QcauditprocessdtlMapper extends Mapper<Qcauditprocessdtl> {

    /**
     *  更新表数据 917110140
     * @param qcauditprocessdtl
     * @return
     */
    int insertData(Qcauditprocessdtl qcauditprocessdtl);

    /**
     *  删除记录 917110140
     * @param recrodid
     * @return
     */
    int deleteById(@Param("werks") String werks, @Param("recordid") String recrodid);

    /**
     *  根据工厂 id 查询已经保存的行记录 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditprocessdtl> selectById(@Param("werks") String werks, @Param("recordid") String recordid, @Param("status") String status);

    /**
     *  根据不合格平审理单中间表ID 和行ID 修改处理状态
     * @param list
     * @return
     */
    int updateStatus(Qcauditprocessdtl list);
}
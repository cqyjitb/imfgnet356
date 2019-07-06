package yj.core.outsrgissuehead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;

import java.util.List;

public interface IOutsrgissueheadService extends IBaseService<Outsrgissuehead>, ProxySelf<IOutsrgissueheadService> {

    /**
     *  根据 物流编码 供应商编码 查询发料单号 状态=0的记录 917110140
     * @param matnr
     * @param lifnr
     * @return
     */
    List<Outsrgissuehead> selectByMatnrAndLifnrDesc(@Param("matnr") String matnr, @Param("lifnr") String lifnr);

    /**
     *  插入新记录 917110140
     * @param outsrgissuehead
     * @return
     */
    int insertNewRow(Outsrgissuehead outsrgissuehead);

    /**
     *  根据单号 修改状态及时间 917110140
     * @param outsrgissuehead
     * @return
     */
    int updateOutsrgissueHead(Outsrgissuehead outsrgissuehead);

    /**
     *  根据单号查询表头信息 917110140
     * @param issuenm
     * @return
     */
    Outsrgissuehead selectByIssuenm(String issuenm);

    /**
     *  根据 条件字符串 查询单号类似的 最大的单号 917110140
     * @param issuenm
     * @return
     */
    String selectMaxIssuenm(String issuenm);
}
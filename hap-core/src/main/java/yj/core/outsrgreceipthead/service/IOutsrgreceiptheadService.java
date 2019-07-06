package yj.core.outsrgreceipthead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;

import java.util.List;

public interface IOutsrgreceiptheadService extends IBaseService<Outsrgreceipthead>, ProxySelf<IOutsrgreceiptheadService> {
    /**
     *  根据物料编码 供应商编码查询收货单表头 降序排列 917110140
     * @param matnr
     * @param lifnr
     * @return
     */
    List<Outsrgreceipthead> selectByMatnrAndLifnrDesc(String matnr, String lifnr, String Status);

    /**
     *  插入新行 917110140
     * @param outsrgreceipthead
     * @return
     */
    int insertNewRow(Outsrgreceipthead outsrgreceipthead);

    /**
     *  更新表头 917110140
     * @param outsrgreceipthead
     * @return
     */
    int updateOutsrgreceipthead(Outsrgreceipthead outsrgreceipthead);

    /**
     *  查询所有收货单数据 根据单号倒序排列 917110140
     * @return
     */
    List<Outsrgreceipthead> selectAllDesc();

    /**
     *  获取最大单号
     * @return
     */
    String getMaxNo();
}
package yj.core.outsrgrfe.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgrfe.dto.Outsrgrfe;

import java.util.List;

public interface IOutsrgrfeService extends IBaseService<Outsrgrfe>, ProxySelf<IOutsrgrfeService> {
    /**
     * 新增记录   917110140
     * @param outsrgrfe
     * @return
     */
    int insertOutsrgrfe(Outsrgrfe outsrgrfe);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 查询  917110140
     * @param werks
     * @param aufnr
     * @param vornr
     * @param matnr
     * @param lifnr
     * @param ebeln
     * @param ebelp
     * @return
     */
    Outsrgrfe selectByCondition(String werks, String aufnr, String vornr, String matnr, String lifnr, String ebeln, String ebelp);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 修改  917110140
     * @param outsrgrfe
     * @return
     */
    int updateByCondition(Outsrgrfe outsrgrfe);

    /**
     * 根据供应商编码查询 外协采购订单接口表记录
     * @param lifnr
     * @return
     */
    List<Outsrgrfe> selectForSortl(String lifnr);

    List<Outsrgrfe> selectAllLifnr();

    /**
     *  根据sap传入的条件取值 917110140
     * @param werks
     * @param matnr
     * @param lifnr
     * @param ktsch
     * @return
     */
    List<Outsrgrfe> sapquery(String werks, String matnr, String matnr_l, String matnr_h, String lifnr, String lifnr_l, String lifnr_h, String ktsch, String ktsch_l, String ktsch_h);
}
package yj.core.outsrgreceipt.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;

import java.util.List;

public interface IOutsrgreceiptService extends IBaseService<Outsrgreceipt>, ProxySelf<IOutsrgreceiptService> {
    /**
     *  根据采购订单获取外协发料数据记录 917110140
     * @param ebeln
     * @return
     */
    List<Outsrgreceipt> selectByEbeln(String ebeln);

    /**
     *  根据流转卡号 状态 查询收货记录 917110140
     * @param zpgdbar
     * @param status
     * @return
     */
    Outsrgreceipt selectByZpgdbarAndStatus(String zpgdbar, String status);

    /**
     *  根据单号查询 行 按 单号 行号 降序排列 917110140
     * @param receiptnm
     * @return
     */
    List<Outsrgreceipt> selectByReceiptDesc(String receiptnm);

    /**
     *  插入新行 917110140
     * @param outsrgreceipt
     * @return
     */
    int insertNewRow(Outsrgreceipt outsrgreceipt);

    /**
     *  更新行 917110140
     * @param outsrgreceipt
     * @return
     */

    int updateOutsrgreceipt(Outsrgreceipt outsrgreceipt);

    /**
     *  根据流转卡号 收货状态 获取外协收货数据 917110140
     * @param barcode
     * @param status
     * @return
     */
    List<Outsrgreceipt> selectByZpgdbarAndStatusM(String barcode, String status);


}
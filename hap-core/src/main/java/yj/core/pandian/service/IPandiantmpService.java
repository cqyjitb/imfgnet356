package yj.core.pandian.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.pandian.dto.Pandiantmp;

public interface IPandiantmpService extends IBaseService<Pandiantmp>, ProxySelf<IPandiantmpService> {
    /**
     *  插入新的盘点记录 917110140
     * @param pdtmp
     * @return
     */
    int insertNewRow(Pandiantmp pdtmp);
}
package yj.core.qcaudithead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcaudithead.dto.Qcauditprocessheader;

public interface IQcauditprocessheaderService extends IBaseService<Qcauditprocessheader>, ProxySelf<IQcauditprocessheaderService> {

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
    Qcauditprocessheader selectById(String werks, String recordid);

    /**
     *  插入新条目 917110140
     * @param qcauditprocessheader
     * @return
     */
    int insertData(Qcauditprocessheader qcauditprocessheader);
}
package yj.core.resb.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.resb.dto.Resb;

import java.util.List;

public interface IResbService extends IBaseService<Resb>, ProxySelf<IResbService> {
    int isExit(String rsnum, String rspos);
    int updateByRsnum(Resb resb);
    int insertByRsnum(Resb resb);
    List<Resb> selectByRsnum(String rsnum);
    List<Resb> selectByRsnumForzpjsx(String rsnum);
    /**
     *  根据 aufpl 删除预留记录 917110140
     * @param aufpl
     * @return
     */
    int deleteByAufpl(String aufpl);
}
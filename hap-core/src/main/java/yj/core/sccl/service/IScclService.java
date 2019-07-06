package yj.core.sccl.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.sccl.dto.Sccl;

import java.util.List;

public interface IScclService extends IBaseService<Sccl>, ProxySelf<IScclService> {
    Sccl selectByMatnr(String matnr, String werks);
    boolean isExit(String matnr, String werks);
    int batchModify(List<Sccl> list);
}
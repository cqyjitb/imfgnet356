package yj.core.pandian.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.pandian.dto.Pandian;

import java.util.List;

public interface IPandianService extends IBaseService<Pandian>, ProxySelf<IPandianService> {
        int insertpdlog(Pandian pd);
        List<Pandian> queryAlllog(IRequest iRequest, Pandian pd, int page, int pageSize);
}
package yj.core.qpcd.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qpcd.dto.Qpcd;

import java.util.List;

public interface IQpcdService extends IBaseService<Qpcd>, ProxySelf<IQpcdService> {
    /**
     * 查询所有一级质量原因代码
     * @return
     */
    public List<Qpcd> selectAllForBlcl();

    public List<Qpcd> selectAllForJjqj();
}
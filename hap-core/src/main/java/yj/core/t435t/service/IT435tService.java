    package yj.core.t435t.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.t435t.dto.T435t;

    public interface IT435tService extends IBaseService<T435t>, ProxySelf<IT435tService> {

        int isExit(String vlsch);

        int insertByVlsch(T435t dto);

        int updateByVlsch(T435t dto);
    }
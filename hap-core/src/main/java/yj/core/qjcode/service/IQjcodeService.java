package yj.core.qjcode.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qjcode.dto.Qjcode;

import java.util.List;

public interface IQjcodeService extends IBaseService<Qjcode>, ProxySelf<IQjcodeService> {
    List<Qjcode> selectcode();
    Qjcode selectById(int id);
}
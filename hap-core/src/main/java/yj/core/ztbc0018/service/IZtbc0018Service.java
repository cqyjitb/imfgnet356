package yj.core.ztbc0018.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.ztbc0018.dto.Ztbc0018;

import java.util.List;

public interface IZtbc0018Service extends IBaseService<Ztbc0018>, ProxySelf<IZtbc0018Service> {

//    int insertZtbc0018(Ztbc0018 ztbc0018);

    /**
     *  根据箱号记录查询调账记录
     * @param zxhbar
     * @return
     */
      List<Ztbc0018> selectByZxhbar(String zxhbar);
}
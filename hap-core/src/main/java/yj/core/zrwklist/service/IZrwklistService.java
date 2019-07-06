package yj.core.zrwklist.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zrwklist.dto.Zrwklist;

import java.util.List;

public interface IZrwklistService extends IBaseService<Zrwklist>, ProxySelf<IZrwklistService> {
    int insertItem(List<Zrwklist> list);
    List<Zrwklist> selectZrwklist(IRequest requestContext, Zrwklist dto);
}
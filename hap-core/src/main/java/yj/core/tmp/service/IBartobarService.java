package yj.core.tmp.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.tmp.dto.Bartobar;

import java.util.List;

public abstract interface IBartobarService
        extends IBaseService<Bartobar>, ProxySelf<IBartobarService>
{
    public abstract List<Bartobar> getNewBar(IRequest paramIRequest, String paramString);
}

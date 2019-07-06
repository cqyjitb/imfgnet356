package yj.core.tmp.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.tmp.dto.Bartobar;
import yj.core.tmp.mapper.BartobarMapper;
import yj.core.tmp.service.IBartobarService;

import java.util.List;

@Service
@Transactional
public class BartobarServiceImpl
        extends BaseServiceImpl<Bartobar>
        implements IBartobarService
{
    @Autowired
    private BartobarMapper bartobarMapper;

    public List<Bartobar> getNewBar(IRequest iRequest, String oldZPGDBAR)
    {
        return this.bartobarMapper.getNewBar(oldZPGDBAR);
    }
}

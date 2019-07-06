package yj.core.cardh.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.cardh.service.ICardhService;

import java.util.List;

@Service
@Transactional
public class CardhServiceImpl
        extends BaseServiceImpl<Cardh>
        implements ICardhService
{
    @Autowired
    private CardhMapper cardhMapper;

    public Cardh selectByBarcode(String zpgdbar){ return cardhMapper.selectByBarcode(zpgdbar);}

    public List<Cardh> selectByAufnr(String aufnr)
    {
        return cardhMapper.selectByAufnr(aufnr);
    }

    public int insertCardh(List<Cardh> list)
    {
        int result = 0;
        if (list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++) {
                if (cardhMapper.insertCardh(list.get(i)) == 1) {
                    result += 1;
                }
            }
            return result;
        }
        return 0;
    }

    public List<Cardh> queryAfterSort(IRequest iRequest, Cardh cardh, int page, int pageSize)
    {
        PageHelper.startPage(page, pageSize);
        return cardhMapper.queryAfterSort(cardh);
    }

    public List<Cardh> queryZuheAfterSort(IRequest iRequest, Cardh cardh, int page, int pageSize)
    {
        PageHelper.startPage(page, pageSize);
        return cardhMapper.queryZuheAfterSort(cardh);
    }

    public int updateCardhStatus(List<Cardh> list)
    {
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += cardhMapper.updateCardhStatus(list.get(i));
        }
        return result;
    }

    public int deleteCardh(List<Cardh> list)
    {
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += cardhMapper.deleteCardh(list.get(i));
        }
        return result;
    }

    @Override
    public String selectByAufnrMxno(String aufnr) {
        return cardhMapper.selectByAufnrMxno(aufnr);
    }

    @Override
    public Cardh selectByZxhbar(String aufnr,String zxhnum) {
        return cardhMapper.selectByZxhbar(aufnr,zxhnum);
    }

    @Override
    public int updateDatforLsvor(String zpgdbar) {
        return cardhMapper.updateDatforLsvor(zpgdbar);
    }

    @Override
    public int updateDatforFsvor(String zpgdbar) {
        return cardhMapper.updateDatforFsvor(zpgdbar);
    }

    @Override
    public int updateForLgort(Cardh cardh) {
        return cardhMapper.updateForLgort(cardh);
    }

    @Override
    public List<Cardh> queryAfterSortForClientPrint(Cardh dto) {
        return cardhMapper.queryAfterSortForClientPrint(dto);
    }

}

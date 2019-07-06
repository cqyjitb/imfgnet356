package yj.core.cardt.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;
import yj.core.cardt.mapper.CardtMapper;
import yj.core.cardt.service.ICardtService;

import java.util.List;

@Service
@Transactional
public class CardtServiceImpl
        extends BaseServiceImpl<Cardt>
        implements ICardtService
{
    @Autowired
    CardtMapper cardtMapper;

    public int insertCardt(List<Cardt> list)
    {
        int result = 0;
        if (list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++) {

                if (cardtMapper.insertCardt(list.get(i)) == 1) {
                    result += 1;
                }
            }
            return result;
        }
        return 0;
    }

    public List<Cardt> selectByZpgdbar(String zpgdbar)
    {
        return cardtMapper.selectByZpgdbar(zpgdbar);
    }

    public List<Cardt> queryAfterSort(IRequest request, Cardt cardt, int page, int pagesize)
    {
        PageHelper.startPage(page, pagesize);
        return cardtMapper.queryAfterSort(cardt);
    }

    public int deleteCardt(List<Cardt> list)
    {
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result = result + cardtMapper.deleteCardt(list.get(i));
        }
        return result;
    }

    @Override
    public List<Cardt> selectBybarcode(String zpgdbar) {
        return cardtMapper.selectBybarcode(zpgdbar);
    }

    @Override
    public VCardt selectViewVCardt(VCardt vcardt) {
        return cardtMapper.selectViewVCardt(vcardt);
    }

    @Override
    public Cardt selectByBarcodeAndKtsch(Cardt dto) {
        return cardtMapper.selectByBarcodeAndKtsch(dto);
    }

    @Override
    public int updateCardtConfirmed(Cardt dto) {
        return cardtMapper.updateCardtConfirmed(dto);
    }

    @Override
    public List<Cardt> selectByZpgdbarAsc(String zpgdbar) {
        return cardtMapper.selectByZpgdbarAsc(zpgdbar);
    }

    @Override
    public List<Cardt> selectByZpgdbarDesc(String zpgdbar) {
        return cardtMapper.selectByZpgdbarDesc(zpgdbar);
    }

    @Override
    public Cardt selectByZpgdbarAndVornr(String zpgdbar, String vornr) {
        return cardtMapper.selectByZpgdbarAndVornr(zpgdbar,vornr);
    }

    @Override
    public Cardt selectByAufnrAndKtsch(String aufnr, String ktsch) {
        return cardtMapper.selectByAufnrAndKtsch(aufnr,ktsch);
    }
}

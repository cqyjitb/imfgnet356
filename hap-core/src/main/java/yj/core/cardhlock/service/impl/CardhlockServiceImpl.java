package yj.core.cardhlock.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardhlock.dto.Cardhlock;
import yj.core.cardhlock.mapper.CardhlockMapper;
import yj.core.cardhlock.service.ICardhlockService;

@Service
@Transactional
public class CardhlockServiceImpl extends BaseServiceImpl<Cardhlock> implements ICardhlockService {
    @Autowired
    private CardhlockMapper cardhlockMapper;

    @Override
    public Cardhlock selectByZpgdbar(String zpgdbar, String vornr) {
        return cardhlockMapper.selectByZpgdbar(zpgdbar,vornr);
    }

    @Override
    public int insertCardhlock(Cardhlock lock) {
        return cardhlockMapper.insertCardhlock(lock);
    }

    @Override
    public int deleteCardhlock(String zpgdbar,String vornr) {
        return cardhlockMapper.deleteCardhlock(zpgdbar,vornr);
    }
}
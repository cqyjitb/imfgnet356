package yj.core.cardhst.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.mapper.CardhstMapper;
import yj.core.cardhst.service.ICardhstService;

import java.util.List;

@Service
@Transactional
public class CardhstServiceImpl extends BaseServiceImpl<Cardhst> implements ICardhstService{
    @Autowired
    private CardhstMapper cardhstMapper;
    @Override
    public int insertStatus(List<Cardhst> list) {
        int sum = 0;
        for(int i = 0;i<list.size();i++){
            sum = sum + cardhstMapper.insertStatus(list.get(i));
        }
        return sum;
    }

    @Override
    public int deleteAll(List<Cardhst> list) {
        int sum = 0;
        for(int i = 0;i<list.size();i++){
            sum = sum + cardhstMapper.deleteAll(list.get(i).getZpgdbar());
        }
        return sum;
    }

    @Override
    public int updateStatus(Cardhst dto) {
        return cardhstMapper.updateStatus(dto);
    }

    @Override
    public List<Cardhst> selectByBarcode(String zpgdbar) {
        return cardhstMapper.selectByBarcode(zpgdbar);
    }

    @Override
    public Cardhst selectByBarcodeAndStatus(Cardhst dto) {
        return cardhstMapper.selectByBarcodeAndStatus(dto);
    }

    @Override
    public int getMaxNo(String zpgdbar) {
        return cardhstMapper.getMaxNo(zpgdbar);
    }

    @Override
    public List<Cardhst> selectAllActive(String zpgdbar) {
        return cardhstMapper.selectAllActive(zpgdbar);
    }

    @Override
    public Cardhst selectAllUnActive(String zpgdbar) {
        return cardhstMapper.selectAllUnActive(zpgdbar);
    }

    @Override
    public Cardhst selectByBarcodeAndId(Cardhst dto) {
        return cardhstMapper.selectByBarcodeAndId(dto);
    }

    @Override
    public int insertSingerStatus(Cardhst dto) {
        return cardhstMapper.insertStatus(dto);
    }

    @Override
    public Cardhst selectForHczy(String zpgdbar, String status) {
        return cardhstMapper.selectForHczy(zpgdbar,status);
    }
}
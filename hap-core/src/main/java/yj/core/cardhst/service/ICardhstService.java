package yj.core.cardhst.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.cardhst.dto.Cardhst;

import java.util.List;

public interface ICardhstService extends IBaseService<Cardhst>, ProxySelf<ICardhstService> {

    int insertStatus(List<Cardhst> list);
    int updateStatus(Cardhst dto);
    List<Cardhst> selectByBarcode(String zpgdbar);
    Cardhst selectByBarcodeAndStatus(Cardhst dto);
    int getMaxNo(String zpgdbar);
    List<Cardhst> selectAllActive(String zpgdbar);
    Cardhst selectAllUnActive(String zpgdbar);
    Cardhst selectByBarcodeAndId(Cardhst dto);
    int deleteAll(List<Cardhst> list);
    int insertSingerStatus(Cardhst dto);
    Cardhst selectForHczy(String zpgdbar, String status);
}
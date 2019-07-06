package yj.core.cardhst.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.cardhst.dto.Cardhst;

import java.util.List;

public interface CardhstMapper extends Mapper<Cardhst> {
    int insertStatus(Cardhst dto);
    int updateStatus(Cardhst dto);
    List<Cardhst> selectByBarcode(String zpgdbar);
    Cardhst selectByBarcodeAndStatus(Cardhst dto);
    int getMaxNo(String zpgdbar);
    List<Cardhst> selectAllActive(String zpgdbar);
    Cardhst selectAllUnActive(String zpgdbar);
    Cardhst selectByBarcodeAndId(Cardhst dto);
    int deleteAll(String Zpgdbar);
    Cardhst selectForHczy(@Param("zpgdbar") String zpgdbar, @Param("status") String status);
}
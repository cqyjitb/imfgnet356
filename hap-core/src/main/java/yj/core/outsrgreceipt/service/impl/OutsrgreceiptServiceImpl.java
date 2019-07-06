package yj.core.outsrgreceipt.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;
import yj.core.outsrgreceipt.mapper.OutsrgreceiptMapper;
import yj.core.outsrgreceipt.service.IOutsrgreceiptService;

import java.util.List;

@Service
@Transactional
public class OutsrgreceiptServiceImpl extends BaseServiceImpl<Outsrgreceipt> implements IOutsrgreceiptService{
    @Autowired
    private OutsrgreceiptMapper outsrgreceiptMapper;
    @Override
    public List<Outsrgreceipt> selectByEbeln(String ebeln) {
        return outsrgreceiptMapper.selectByEbeln(ebeln);
    }

    @Override
    public Outsrgreceipt selectByZpgdbarAndStatus(String zpgdbar, String status) {
        return outsrgreceiptMapper.selectByZpgdbarAndStatus(zpgdbar,status);
    }

    @Override
    public List<Outsrgreceipt> selectByReceiptDesc(String receiptnm) {
        return outsrgreceiptMapper.selectByReceiptDesc(receiptnm);
    }

    @Override
    public int insertNewRow(Outsrgreceipt outsrgreceipt) {
        return outsrgreceiptMapper.insertNewRow(outsrgreceipt);
    }

    @Override
    public int updateOutsrgreceipt(Outsrgreceipt outsrgreceipt) {
        return outsrgreceiptMapper.updateOutsrgreceipt(outsrgreceipt);
    }

    @Override
    public List<Outsrgreceipt> selectByZpgdbarAndStatusM(String barcode, String status) {
        return outsrgreceiptMapper.selectByZpgdbarAndStatusM(barcode,status);
    }
}
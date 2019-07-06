package yj.core.wmsxhcard.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wmsxhcard.dto.Wmsxhcard;
import yj.core.wmsxhcard.mapper.WmsxhcardMapper;
import yj.core.wmsxhcard.service.IWmsxhcardService;

@Service
@Transactional
public class WmsxhcardServiceImpl extends BaseServiceImpl<Wmsxhcard> implements IWmsxhcardService{
    @Autowired
    private WmsxhcardMapper WmsxhcardMapper;
    @Override
    public Wmsxhcard selectByBarcode(String zxhbar,String werks) {
        return WmsxhcardMapper.selectByBarcode(zxhbar,werks);
    }

    @Override
    public int updateZsxwc(Wmsxhcard wmsxhcard) {
        return WmsxhcardMapper.updateZsxwc(wmsxhcard);
    }
}
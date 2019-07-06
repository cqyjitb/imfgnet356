package yj.core.wipbarcodesetting.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipbarcodesetting.dto.BarcodeSetting;
import yj.core.wipbarcodesetting.mapper.BarcodeSettingMapper;
import yj.core.wipbarcodesetting.service.IBarcodeSettingService;
import yj.core.wipbarcodespecialcode.mapper.BarcodeSpecialcodeMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BarcodeSettingServiceImpl extends BaseServiceImpl<BarcodeSetting> implements IBarcodeSettingService {
    @Autowired
    private BarcodeSettingMapper barcodeSettingMapper;
    @Autowired
    private BarcodeSpecialcodeMapper barcodeSpecialcodeMapper;

    @Override
    public List<BarcodeSetting> selectFromPage(IRequest request, BarcodeSetting dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return barcodeSettingMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<BarcodeSetting> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                BarcodeSetting barcodeSetting = dto.get(i);
                if(barcodeSetting.getHeaderId() != null){
                    barcodeSetting.setLastUpdatedBy(userName);
                    barcodeSetting.setLastUpdatedDate(new Date());
                    barcodeSettingMapper.updateBarcodeSetting(barcodeSetting);
                }else{
                    barcodeSetting.setCreatedBy(userName);
                    barcodeSetting.setCreationDate(new Date());
                    barcodeSettingMapper.insertBarcodeSetting(barcodeSetting);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteBarcodeSetting(List<BarcodeSetting> dto) {
        if(dto.size() > 0) {
            for (int i = 0; i < dto.size(); i++) {
                BarcodeSetting barcodeSetting = dto.get(i);
                barcodeSpecialcodeMapper.deleteBarcodeSpecialcode(barcodeSetting.getHeaderId());
                barcodeSettingMapper.deleteBarcodeSetting(barcodeSetting);
            }
        }
    }
}

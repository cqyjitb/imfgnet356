package yj.core.wipbarcodespecialcode.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipbarcodesetting.mapper.BarcodeSettingMapper;
import yj.core.wipbarcodespecialcode.dto.BarcodeSpecialcode;
import yj.core.wipbarcodespecialcode.mapper.BarcodeSpecialcodeMapper;
import yj.core.wipbarcodespecialcode.service.IBarcodeSpecialcodeService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BarcodeSpecialcodeServiceImpl extends BaseServiceImpl<BarcodeSpecialcode> implements IBarcodeSpecialcodeService{
    @Autowired
    private BarcodeSpecialcodeMapper barcodeSpecialcodeMapper;
    @Autowired
    private BarcodeSettingMapper barcodeSettingMapper;

    @Override
    public List<BarcodeSpecialcode> selectFromPage(IRequest request, BarcodeSpecialcode dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return barcodeSpecialcodeMapper.selectFromPage(dto);
    }

    @Override
    public String setMessageBarcodeSpecialcode(List<BarcodeSpecialcode> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                BarcodeSpecialcode barcodeSpecialcode = dto.get(i);
                if(barcodeSpecialcode.getStartPos() == null){
                    return "起始位置不能为空";
                }else if(barcodeSpecialcode.getEndPos() == null){
                    return "结束位置不能为空";
                }else if(barcodeSpecialcode.getCodeType() == null){
                    return "数据类型不能为空";
                }
            }
        }
        return null;
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<BarcodeSpecialcode> dto, String userName) {
        if(dto.size() > 0){
            int max = barcodeSettingMapper.maxBarcodeSettingByHeaderId();
            for(int i=0;i<dto.size();i++){

                BarcodeSpecialcode barcodeSpecialcode = dto.get(i);
                if(barcodeSpecialcode.getHeaderId() == 0){
                    barcodeSpecialcode.setHeaderId(max);
                }
                if(barcodeSpecialcode.getSpecRowId() != 0){
                    barcodeSpecialcode.setLastUpdatedBy(userName);
                    barcodeSpecialcode.setLastUpdatedDate(new Date());
                    barcodeSpecialcodeMapper.updateByPrimaryKey(barcodeSpecialcode);
                }else {
                    barcodeSpecialcode.setCreatedBy(userName);
                    barcodeSpecialcode.setCreationDate(new Date());
                    barcodeSpecialcodeMapper.insert(barcodeSpecialcode);
                }
            }
        }
        return null;
    }
}

package yj.core.wipproductscfg.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.mapper.ProductsCfgMapper;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.core.zwipq.mapper.ZwipqMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductsCfgServiceImpl extends BaseServiceImpl<ProductsCfg> implements IProductsCfgService {
    @Autowired
    private ProductsCfgMapper productsCfgMapper;
    @Autowired
    private ZwipqMapper zwipqMapper;

    @Override
    public List<ProductsCfg> selectById(long line_id) {
        return productsCfgMapper.selectById(line_id);
    }

    @Override
    public int selectMaxByLineidAndMatnr(ProductsCfg dto) {
        return productsCfgMapper.selectMaxByLineidAndMatnr(dto);
    }

    @Override
    public int selectMaxByLineidAndMatnrLgort(ProductsCfg dto) {
        return productsCfgMapper.selectMaxByLineidAndMatnrLgort(dto);
    }

    @Override
    public ProductsCfg selectByLineidAndPMatnr(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndPMatnr(line_id,matnr);
    }

    @Override
    public ProductsCfg selectByLineidAndMatnr(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndMatnr(line_id,matnr);
    }

    @Override
    public List<ProductsCfg> selectFromPage(IRequest requestContext, ProductsCfg dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return productsCfgMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestContext, List<ProductsCfg> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                ProductsCfg productsCfg = dto.get(i);
                if("Y".equals(productsCfg.getCustbarcodeynCarton())){
                    productsCfg.setCustbarcodeynCarton("1");
                }else{
                    productsCfg.setCustbarcodeynCarton("0");
                }if("Y".equals(productsCfg.getCustbarcodeynBox())){
                    productsCfg.setCustbarcodeynBox("1");
                }else{
                    productsCfg.setCustbarcodeynBox("0");
                }
                int num = productsCfgMapper.isExit(productsCfg.getWerks(),productsCfg.getLineId(),productsCfg.getPmatnr());
                if (num == 1){
                    productsCfg.setLastUpdatedDate(new Date());
                    productsCfg.setLastUpdatedBy(Long.valueOf(userId));
                    productsCfgMapper.updateProductsCfg(productsCfg);
                }else{
                    productsCfg.setCreationDate(new Date());
                    productsCfg.setCreatedBy(Long.valueOf(userId));
                    productsCfgMapper.insertProductsCfg(productsCfg);
                }
            }
        }
        return null;
    }

    @Override
    public String deleteProductsCfg(List<ProductsCfg> dto) {
        if(dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                ProductsCfg productsCfg = dto.get(i);
                int num = zwipqMapper.selectLineIdMatnr2(productsCfg.getLineId()+"",productsCfg.getPmatnr());
                if(num != 0){
                    return "生产线和产品编码已使用，不允许删除！";
                }else{
                    productsCfgMapper.deleteProductsCfg(productsCfg);
                }
            }
        }
        return null;
    }

    @Override
    public List<ProductsCfg> selectByMatnr(Long lineId) {
        return productsCfgMapper.selectByMatnr(lineId);
    }

    @Override
    public List<ProductsCfg> selectByLineidAndMatnr2(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndMatnr2(line_id,matnr);
    }
}
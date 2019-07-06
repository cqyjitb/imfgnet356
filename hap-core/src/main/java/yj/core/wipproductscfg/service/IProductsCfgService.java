package yj.core.wipproductscfg.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipproductscfg.dto.ProductsCfg;

import java.util.List;

public interface IProductsCfgService extends IBaseService<ProductsCfg>, ProxySelf<IProductsCfgService> {
    List<ProductsCfg> selectById(long line_id);

    int selectMaxByLineidAndMatnr(ProductsCfg dto);

    int selectMaxByLineidAndMatnrLgort(ProductsCfg dto);

    /**
     * 按照机加物料编码查询 生产线ID
     * @param line_id
     * @param matnr
     * @return
     */
    ProductsCfg selectByLineidAndPMatnr(String line_id, String matnr);

    /**
     * 按照压铸毛坯物料 生产线ID 查询
     * @param line_id
     * @param matnr
     * @return
     */

    ProductsCfg selectByLineidAndMatnr(String line_id, String matnr);

    List<ProductsCfg> selectFromPage(IRequest requestContext, ProductsCfg dto, int page, int pageSize);
    String updateOrInsert(IRequest requestContext, List<ProductsCfg> dto, String userId);
    String deleteProductsCfg(List<ProductsCfg> dto);

    /**
     * 根据产线ID 物料号查询
     * @param lineId
     * @return
     */
    List<ProductsCfg> selectByMatnr(Long lineId);

    /**
     * 按照压铸毛坯物料 生产线ID 查询
     * @param line_id
     * @param matnr
     * @return
     */
    List<ProductsCfg> selectByLineidAndMatnr2(String line_id, String matnr);
}
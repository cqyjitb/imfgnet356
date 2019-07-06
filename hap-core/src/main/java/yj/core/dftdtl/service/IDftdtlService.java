package yj.core.dftdtl.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dftdtl.dto.Dftdtl;

import java.util.List;

public interface IDftdtlService extends IBaseService<Dftdtl>, ProxySelf<IDftdtlService> {
    /**
     * 根据一级质量原因代码 获取对应的二级原因代码
     * @param code
     * @return
     */
    List<Dftdtl> selectByQpcode(String code, String Matnr);

    List<Dftdtl> selectFromPage(IRequest requestContext, int page, int pageSize, String werks, String matnr, String code);
    String updateOrInsert(IRequest requestCtx, List<Dftdtl> dto, String userId);
    String deleteDftdtl(List<Dftdtl> dto);
    List<Dftdtl> selectbyWerksAndMatnr(String werks, String matnr);
    List<Dftdtl> selectByQpcodeForJj(String code, String Matnr);
}
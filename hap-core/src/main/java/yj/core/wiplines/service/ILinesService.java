package yj.core.wiplines.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplines.dto.Lines;

import java.util.List;

public interface ILinesService extends IBaseService<Lines>, ProxySelf<ILinesService> {
    Lines selectById(Long line_id);

    /**
     * 线边库不良毛坯处理，获取生产线信息
     * @param line_id
     * @return
     */
    Lines selectByIdForBlmpcl(Long line_id);

    List<Lines> selectFromPage(Lines dto, IRequest requestContext, int page, int pageSize);
    String selectDescription(Long plineId);
    Lines selectUnitCode(String deptId);
    String setMessageLines(List<Lines> dto);
    String updateOrInsert(IRequest requestCtx, List<Lines> dto, String userId);

    /**
     *  检查一条产线是否是 主产线 917110140
     * @param line_id
     * @return
     */
    List<Lines> selectByPlineId(String line_id);
}
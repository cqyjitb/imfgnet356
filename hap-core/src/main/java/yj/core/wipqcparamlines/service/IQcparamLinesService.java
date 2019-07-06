package yj.core.wipqcparamlines.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipqcparamlines.dto.QcparamLines;

import java.util.List;

public interface IQcparamLinesService extends IBaseService<QcparamLines>, ProxySelf<IQcparamLinesService> {

    /**
     *  根据生产线ID 查询产线参数配置信息 + 机加责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForJj(Long line_id, String werks);

    /**
     *  根据生产线ID 查询产线参数配置信息 + 压铸责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForYz(Long line_id, String werks);

    /**
     * 根据生产车间和生产线查询在制队列预警 918100064
     * @param deptId
     * @param lineId
     * @return
     */
    List<QcparamLines> selectByScale(String deptId, Long lineId);

    /**
     *机加质量控制参数维护查询 918100064
     * @param requestContext
     * @param dto
     * @param page
     * @param pageSize
     * @return
     */
    List<QcparamLines> selectFromPage(IRequest requestContext, QcparamLines dto, int page, int pageSize);

    /**
     *机加质量控制参数维护验证 918100064
     * @param dto
     * @return
     */
    String setMessage(List<QcparamLines> dto);

    /**
     * 机加质量控制参数维护添加和修改 918100064
     * @param requestContext
     * @param dto
     * @param userId
     * @return
     */
    String updateOrInsert(IRequest requestContext, List<QcparamLines> dto, Long userId);

    /**
     * 机加质量控制参数维护删除 918100064
     * @param dto
     */
    void deleteQcparamLines(List<QcparamLines> dto);
}
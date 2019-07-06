package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.dispatch.dto.InputLog;

import java.util.List;

public interface InputLogMapper extends Mapper<InputLog> {

    List<InputLog> queryAllWriteOff(InputLog inputLog);//报功冲销页面数据查询

    List<InputLog> queryAllLog(InputLog inputLog);//报功日志页面数据查询

    List<InputLog> queryAllResult(InputLog inputLog);//报工结果

    List<InputLog> queryFirstResult(@Param("dispatch") String dispatch, @Param("operation") String operation);

    List<InputLog> queryBeforeResult(@Param("dispatch") String dispatch, @Param("operation") String operation);

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

    /**
     * 获取当前报工工序历史信息
     * @param inputLog 报工信息
     * @return
     */
    List<InputLog> confirmationInfoByOrdernoAndOperation(InputLog inputLog);

    /**
     * 获取前工序最大工序
     * @param inputLog 报工信息
     * @return
     */
    String confirmationBeforeMaxOperation(InputLog inputLog);

    /**
     * 存在最大工序时，按派工单号和工序号查询信息
     * @param dispatch 派工单号
     * @param operation 工序号
     * @return
     */
    List<InputLog> confirmationExistMaxOperaInfo(@Param("dispatch") String dispatch, @Param("operation") String operation);

    Long selectNextId();

    List<InputLog> queryAllGTOperation(InputLog inputLog);

    /*add furong.tang*/

    /**
     * 查询当前条码是否存在未冲销的报工数据
     * @return
     */
    List<InputLog> queryBybarcodeAndisReversed(InputLog inputLog);

    /**
     * 获取字段派工单，指定工序的报工成功结果
     * @param inputLog
     * @return
     */
    InputLog selectConfirmationSuccess(InputLog inputLog);

    String queryDispatchMaxOperation(String dispatch);

    InputLog queryByDispatchAndOperation (InputLog inputLog);

    /**
     * 查询表格confirmation_input_log中合格品数量汇总 918100064
     * @param arbpl 工作中心
     * @param postingDateBefore 过账日期
     * @param postingDateAfter  过账日期
     * @return
     */
    int selectByOrderno(@Param("arbpl") String arbpl, @Param("postingDateAfter") String postingDateAfter,
                        @Param("postingDateBefore") String postingDateBefore);

    /**
     *  根据机加 bguuid 查询报工日志 917110140
     * @param bguuid
     * @return
     */
    InputLog queryInputlogByJjuuidbg(@Param("bguuid") String bguuid);

    InputLog queryInputlogByJjuuidcx(@Param("bguuid") String bguuid);

    int updateCxuuid(InputLog inputLog);

    InputLog queryAllGTOperationJj(InputLog inputLog);

    InputLog querySumInputlogForShotnum(@Param("werks") String werks, @Param("matnr") String matnr, @Param("arbpl") String arbpl, @Param("attr6") String attr6, @Param("attr4") String attr4);


    /**
     * 根据条件查询报工合格数量、工废数量、料废数量汇总 918100064
     * @param  plant 工厂
     * @param arbpl 工作中心
     * @param attr4 班次
     * @param attr6 日期
     * @return
     */
    InputLog selectByOrderno2(@Param("plant") String plant, @Param("arbpl") String arbpl, @Param("attr4") String attr4, @Param("attr6") String attr6);

    InputLog selectByBgUuid(@Param("bguuid") String bguuid);

    InputLog selectByCxUuid(@Param("cxuuid") String cxuuid);
}
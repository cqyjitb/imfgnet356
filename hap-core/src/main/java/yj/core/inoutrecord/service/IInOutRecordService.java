package yj.core.inoutrecord.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface IInOutRecordService extends IBaseService<InOutRecord>, ProxySelf<IInOutRecordService> {
    int insertQjrecode(List<InOutRecord> inOutRecord);
    List<InOutRecord> selectforjjhj(String line_id, String qjcodeval, String lineiocfgval, String matnr, int hjtype);
    List<InOutRecord> selectNoReflg(String line_id, String zotype, String vornr, String sfflg, String matnr, int hjtype);
    int updateReflag(InOutRecord inOutRecord);
//    List<Zudlist> selectforZud(String line_id, String classgrp, int page, int pagesize, IRequest iRequest);

    /**
     *   917110140 修改成可以适应父产线 子产线共用的查询
     * @param pline_id 父产线ID
     * @param line_id  产线ID
     * @param classgrp 班组
     * @return
     */
    List<Zudlist> selectforZud(String pline_id, String line_id, String classgrp, String matnr2, String creationDateBefore, String creationDateAfter, String isInclude);
    InOutRecord selectById(String zqjjlh);
    int batchUpdateReflag(List<InOutRecord> list);

    /** 917110140 修改成可以适应父产线 子产线共用的查询
     * @param pline_id
     * @param line_id
     * @param classgrp
     * @param zotype
     * @param iRequest
     * @return
     */
    List<InOutRecord> selectforZrwk(String pline_id, String line_id, String classgrp, String zotype, IRequest iRequest);
    List<InOutRecord> selectforlines(IRequest request, String lineId, String plineId, String deptId);
    int selectZoutnum(String lineId, Integer zremade, String matnr, String sfflg, String diecd);
    int selectZsxnum(String lineId, Integer zremade, String matnr, String sfflg, String diecd);

    /**
     *  不合格品审理单2 产品所处工艺状态=已加工未入库产品 917110140
     * @param werks
     * @param matnr
     * @param deptId
     * @param line_id
     * @param gstrp
     * @return
     */
    List<InOutRecord> selectforQcaudit1(String werks, String line_id, String matnr, String matnr2, String deptId, String gstrp,
                                        String zqxdm, String zissuetxt, String zbanz);

    /**
     *  不合格品审理单2 产品所处工艺状态=已入库成品 917110140
     * @param werks
     * @param matnr
     * @param deptId
     * @param line_id
     * @return
     */
    List<InOutRecord> selectforQcaudit3(String werks, String line_id, String matnr, String matnr2, String deptId, String gstrp,
                                        String zqxdm, String zissuetxt, String zbanz);

    List<InOutRecord> selectforKanb(String werks, String line_id, String matnr, String start, String end);

    /**
     *  根据取件机加流转卡 查询取件记录
     */

    List<InOutRecord> selectByZpgdbar(String zpgdbar);

    /**
     * 根据创建日期查询产线、物料汇总 918100064
     * @param startDate
     * @param endDate
     * @return
     */
    List<InOutRecord> selectByCreateDate(String startDate, String endDate);

    /**
     * 根据产线、物料、创建日期查询工废数 918100064
     * @param lineId
     * @param matnr
     * @param startDate
     * @param endDate
     * @return
     */
    List<InOutRecord> XmngaCount(String lineId, String matnr, String startDate, String endDate);

    /**
     * 根据产线、物料、创建日期查询料废数 918100064
     * @param lineId
     * @param matnr
     * @param startDate
     * @param endDate
     * @return
     */
    List<InOutRecord> RmngaCount(String lineId, String matnr, String startDate, String endDate);

    /**
     * 根据创建日期查询不合格品记录 918100064
     * @param startDate
     * @param endDate
     * @return
     */
    List<InOutRecord> selectByNgRecode(String startDate, String endDate);

    /**
     * 根据日期等查询，汇总 918100064
     * @param dto
     * @return
     */
    List<InOutRecord> zissuetxtCount(InOutRecord dto);
}
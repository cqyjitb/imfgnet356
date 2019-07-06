package yj.core.wipdftrghlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipdftrghlist.dto.Dftrghlist;

import java.util.List;

public interface IDftrghlistService extends IBaseService<Dftrghlist>, ProxySelf<IDftrghlistService> {
    /**
     * 按照工厂 机加生产线id 成品物料 机加班组 机加生产日期查询记录
     * @param werks
     * @param matnr
     * @param line_id
     * @param shift
     * @param gstrp
     * @return
     */
    List<Dftrghlist> selectByCondition(String werks, String matnr, String line_id, String shift, String gstrp);

    /**
     * 插入新记录
     * @param dftrghlist
     * @return
     */
    int insertDftrghlist(Dftrghlist dftrghlist);

    /**
     * 更新记录
     * @param dftrghlist
     * @return
     */
    int updateDftrghlist(Dftrghlist dftrghlist);

    /**
     * 根据条件 查询符合条件的记录中的最大行号
     * @param werks
     * @param matnr
     * @param line_id
     * @param shift
     * @param gstrp
     * @return
     */
    int selectMaxItemByCondition(String werks, String matnr, String line_id, String shift, String gstrp);

    /**
     * 根据 生产线id 班组 箱号 查询不良品处理记录
     * @param line_id
     * @param classgrp
     * @param zxhbar
     * @return
     */
    List<Dftrghlist> selectByLindIdAndZxhbar(String line_id, String classgrp, String zxhbar);

    /**
     * 根据单号 行号 查询记录
     * @param recordid
     * @param item
     * @return
     */
    Dftrghlist selectByIdAndItem(String recordid, Long item);

    /**
     * 根据单号 行号 修改记录
     * @param dto
     * @return
     */
    int updateByIdAndItem(Dftrghlist dto);

    /**
     *  根据箱号查询已经处理的不良毛坯记录
     * @param zxhbar
     * @return
     */
    List<Dftrghlist> selectByZxhbar(String zxhbar);

    /**
     *  查询指定机加生产日期 不良品处理单号最大值
     * @param werks
     * @param gstrp
     * @return
     */
    String selectMaxRecordid(String werks, String gstrp);

    /**
     *  根据条件查询 for 不合格品审理单2 917110140
     * @param werks
     * @param line_id
     * @param matnr
     * @param matnr2
     * @param deptId
     * @param gstrp
     * @param zqxdm
     * @param zissuetxt
     * @param zbanz
     * @return
     */
    List<Dftrghlist> selectforQcaudit2(String werks, String line_id, String matnr, String matnr2, String deptId, String gstrp, String zqxdm, String zissuetxt, String zbanz);

    /**
     *  汇总 CANCEL_FLAG = 1 的记录
     * @param zxhbar
     * @return
     */
    List<Dftrghlist> selectSum(String zxhbar);

    /**
     *  批量更新线边库 记录 cancle_flag 状态
     * @param list
     * @return
     */
    int batchUpdateCancelflag(List<Dftrghlist> list);
}
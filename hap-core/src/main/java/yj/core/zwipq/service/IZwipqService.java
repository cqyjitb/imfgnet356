package yj.core.zwipq.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.zwipq.dto.Zwipq;

import java.util.List;
import java.util.Map;

public interface IZwipqService extends IBaseService<Zwipq>, ProxySelf<IZwipqService> {
    List<Zwipq> selectByLineIdAndZxhbar(String line_id, String zxhbar);
    Map selectMaxQsenq(Map m);
    int InsertIntoZwipq(List<Zwipq> list);
    DTMIGOReturn callMigo(String zxhbar, int cynum, String line_id, String bwart, int createBy, String zpgdbar);
    List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(String line_id, String zxhbar, String zpgdbar);
    List<Zwipq> selectBylineforjjqj(String line_id);
    List<Zwipq> selectForqj(String line_id, String attr6);
    int updateForQj(List<Zwipq> zwipqs);
    Zwipq selectById(String zwipqid);
    List<Zwipq> selectByLineid(String line_id);
    int updateZqjklAndQenq(Zwipq zwipq);
    int updateQsenqBatch(List<Zwipq> list);
    List<Zwipq> selectForJjxx(String line_id, String classgrp);
    int updateZoffl(List<Zwipq> list);
    List<Zwipq> selectZwipq(IRequest request, String deptId, String lineId, String plineId, Integer zremade,
                            String attr1After, String attr1Before, String shift, String sfflg, String diecd,
                            String zxhbar, String zgjbar, Integer online, Integer zzxkl, Integer zqjkl, Integer zoffl, Integer status);
    List<Zwipq> selectIORZwipq(IRequest request, String deptId, String lineId, String pmatnr, String attr1After, String attr1Before, String shift);

    /**
     *  根据箱号查询在制队列数据 917110140
     * @param zxhbar
     * @return
     */
    List<Zwipq> selectByZxhbar(String zxhbar);

    /**
     *   根据生产线ID 箱号条码 查询在职队列数量 作为最大可取消数量   917110140
     * @param line_id
     * @param zxhbar
     * @param zoffl
     * @return
     */
    List<Zwipq> selectByLineIdAndZxhbarAndZOFFL(String line_id, String zxhbar, String zoffl);

    /**
     *  根据机加流转卡查询 在制队列记录数量 917110140
     * @param zpgdbar
     * @return
     */
    int selectByJjzpgdbar(String zpgdbar);

    /**
     *  根据箱号，产线id 获取最后一次上线的记录
     * @param line_id
     * @param zxhbar
     * @return
     */
    List<Zwipq> getlastsumbit(String line_id, String zxhbar);

    /**
     *  汇总箱号的上线数量 917110140
     * @param zxhbar
     * @return
     */
    List<Zwipq> selectSumzsxnum(String zxhbar);

    /**
     * 根据生产线和物料号查询取件毛坯批次 918100064
     * @param pkgLineId
     * @param matnr
     * @return
     */
    List<Zwipq> selectcharg(String pkgLineId, String matnr);

    /**
     * 根据生产线和物料号查询在线毛坯批次 918100064
     * @param pkgLineId
     * @param matnr
     * @return
     */
    List<Zwipq> selectcharg2(String pkgLineId, String matnr);

    /**
     * 根据生产线和物料号查询装箱未报工毛坯批次 918100064
     * @param zsxjlh
     * @param pkgLineId
     * @param matnr
     * @return
     */
    Zwipq selectcharg3(String zsxjlh, String pkgLineId, String matnr);

    /**
     * 根据生产线和物料号查询取件数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum(String pkgLineId, String matnr, String charg);
    /**
     * 根据生产线和物料号查询取件数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum1(String pkgLineId, String matnr, String charg);
    /**
     * 根据生产线和物料号查询在线数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum2(String pkgLineId, String matnr, String charg);

    /**
     *  根据压铸派工单查询在制队列记录
     * @param zpgdbar2
     * @return
     */
    List<Zwipq> selectByZpgdbar2(String zpgdbar2);
}
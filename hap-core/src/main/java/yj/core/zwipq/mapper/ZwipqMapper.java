package yj.core.zwipq.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.zwipq.dto.Zwipq;

import java.util.List;
import java.util.Map;

public interface ZwipqMapper extends Mapper<Zwipq> {
    List<Zwipq> selectByLineIdAndZxhbar(@Param("line_id") String line_id, @Param("zxhbar") String zxhbar);

    //查询在制队列中队里顺序号
    void selectMaxQsenq(Map m);

    int InsertIntoZwipq(Zwipq zwipq);

    List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(@Param("line_id") String line_id, @Param("zxhbar") String zxhbar, @Param("zpgdbar") String zpgdbar);

    //根据产线ID查询队列中的数据
    List<Zwipq> selectBylineforjjqj(String line_id);

    //查询队列中符合取件的数据
    List<Zwipq> selectForqj(@Param("line_id") String line_id, @Param("sfflg") String sfflg);

    //更新在制队列取件标识
    int updateForQj(Zwipq zwipq);

    Zwipq selectById(String zwipqid);

    List<Zwipq> selectByLineid(String line_id);

    int updateZqjklAndQenq(Zwipq zwipq);

    int updateQsenq(Zwipq zwipq);

    List<Zwipq> selectForJjxx(@Param("line_id") String line_id, @Param("classgrp") String classgrp);

    int updateZoffl(Zwipq zwipq);

    /**
     * 根据生产线、返工件标识、工件状态查询ZWIPQ表汇总上线数量 918100064
     *
     * @param lineId
     * @param zremade
     * @param sfflg
     * @param diecd
     * @return
     */
    int selectZsxnum(@Param("lineId") String lineId, @Param("zremade") Integer zremade, @Param("matnr") String matnr, @Param("sfflg") String sfflg, @Param("diecd") String diecd);

    /**
     * 产线在制队列明细查询 918100064
     *
     * @param deptId
     * @param lineId
     * @param zremade
     * @param attr1After
     * @param attr1Before
     * @param shift
     * @param sfflg
     * @param diecd
     * @param zxhbar
     * @param zgjbar
     * @param online
     * @param zzxkl
     * @param zqjkl
     * @param zoffl
     * @param status
     * @return
     */
    List<Zwipq> selectZwipq(@Param("deptId") String deptId, @Param("lineId") String lineId, @Param("plineId") String plineId, @Param("zremade") Integer zremade, @Param("attr1After") String attr1After,
                            @Param("attr1Before") String attr1Before, @Param("shift") String shift, @Param("sfflg") String sfflg, @Param("diecd") String diecd, @Param("zxhbar") String zxhbar,
                            @Param("zgjbar") String zgjbar, @Param("online") Integer online, @Param("zzxkl") Integer zzxkl, @Param("zqjkl") Integer zqjkl, @Param("zoffl") Integer zoffl, @Param("status") Integer status);

    /**
     * 期间产品合格率查询 918100064
     *
     * @param deptId
     * @param lineId
     * @param pmatnr
     * @param attr1After
     * @param attr1Before
     * @param shift
     * @return
     */
    List<Zwipq> selectIORZwipq(@Param("deptId") String deptId, @Param("lineId") String lineId, @Param("pmatnr") String pmatnr, @Param("attr1After") String attr1After,
                               @Param("attr1Before") String attr1Before, @Param("shift") String shift);

    /**
     * 按产线、成品物料、工序流转卡集查询投入数量、装箱数量、在制数量 918100064
     * @param lineId
     * @param matnr
     * @param zpgdbar
     * @param zzxkl
     * @param zqjkl
     * @param zoffl
     * @param status
     * @return
     */
    int selectZsxnum1(@Param("lineId") String lineId, @Param("matnr") String matnr, @Param("zpgdbar") String zpgdbar, @Param("zzxkl") Integer zzxkl,
                      @Param("zqjkl") Integer zqjkl, @Param("zoffl") Integer zoffl, @Param("status") Integer status);


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
    List<Zwipq> selectByLineIdAndZxhbarAndZOFFL(@Param("line_id") String line_id, @Param("zxhbar") String zxhbar, @Param("zoffl") String zoffl);

    /**
     * 根据生产线和物料描述查询数据多少 918100064
     * @param lineId
     * @param matnr2
     * @return
     */
    int selectLineIdMatnr2(@Param("lineId") String lineId, @Param("matnr2") String matnr2);

    /**
     *  根据机加流转卡查询 在制队列记录数量 917110140
     * @param zpgdbar
     * @return
     */
    int selectByJjzpgdbar(String zpgdbar);

    /**
     * 根据生产线和物料描述查询汇总在线数量 918100064
     * @param lineId
     * @param matnr2
     * @return
     */
    int selectByLineIdMatnr2(@Param("lineId") Long lineId, @Param("matnr2") String matnr2);

    /**
     *  根据箱号，产线id 获取最后一次上线的记录
     * @param line_id
     * @param zxhbar
     * @return
     */
    List<Zwipq> getlastsumbit(@Param("line_id") String line_id, @Param("zxhbar") String zxhbar);

    /**
     *  汇总箱号的上线数量 917110140
     * @param zxhbar
     * @return
     */
    List<Zwipq> selectSumzsxnum(@Param("zxhbar") String zxhbar);

    /**
     * 汇总箱号的投料数量 918100064
     * @param zxhbar
     * @return
     */
    Integer selectSumZxhbar(@Param("zxhbar") String zxhbar, @Param("attr1After") String attr1After,
                            @Param("attr1Before") String attr1Before);

    /**
     * 根据生产线和物料号查询取件毛坯批次 918100064
     * @param pkgLineId
     * @param matnr
     * @return
     */
    List<Zwipq> selectcharg(@Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr);
    /**
     * 根据生产线和物料号查询在线毛坯批次 918100064
     * @param pkgLineId
     * @param matnr
     * @return
     */
    List<Zwipq> selectcharg2(@Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr);
    /**
     * 根据生产线和物料号查询装箱报工毛坯批次 918100064
     * @param zsxjlh
     * @param pkgLineId
     * @param matnr
     * @return
     */
    Zwipq selectcharg3(@Param("zsxjlh") String zsxjlh, @Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr);
    /**
     * 根据生产线和物料号查询取件数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum(@Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr, @Param("charg") String charg);
    /**
     * 根据生产线和物料号查询取件数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum1(@Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr, @Param("charg") String charg);
    /**
     * 根据生产线和物料号查询在线数量 918100064
     * @param pkgLineId
     * @param matnr
     * @param charg
     * @return
     */
    Integer selectByzsxnum2(@Param("pkgLineId") String pkgLineId, @Param("matnr") String matnr, @Param("charg") String charg);

    List<Zwipq> selectByZpgdbar2(@Param("zpgdbar2") String zpgdbar2);
}
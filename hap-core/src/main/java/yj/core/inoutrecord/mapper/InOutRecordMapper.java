package yj.core.inoutrecord.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.inoutrecord.dto.InOutRecord;

import java.util.List;

public interface InOutRecordMapper extends Mapper<InOutRecord> {
        int insertQjrecode(InOutRecord inOutRecord);
        List<InOutRecord> selectforjjhj(@Param("line_id") String line_id, @Param("qjcodeval") String qjcodeval, @Param("lineiocfgval") String lineiocfgval, @Param("matnr") String matnr, @Param("hjtype") int hjtype);
        List<InOutRecord> selectNoReflg(@Param("line_id") String line_id, @Param("zotype") String zotype, @Param("vornr") String vornr, @Param("matnr") String matnr, @Param("sfflg") String sfflg, @Param("hjtype") int hjtype);
        int updateReflag(InOutRecord inOutRecord);
        /**
         *   917110140 修改成可以适应父产线 子产线共用的查询
         * @param Pline_id 父产线ID
         * @param line_id  产线ID
         * @param classgrp 班组
         * @return
         */
        List<InOutRecord> selectforZud(@Param("pline_id") String Pline_id, @Param("line_id") String line_id, @Param("classgrp") String classgrp, @Param("matnr2") String matnr2,
                                       @Param("creationDateBefore") String creationDateBefore, @Param("creationDateAfter") String creationDateAfter);

        InOutRecord selectById(String zqjjlh);

        /** 917110140
         * @param pline_id
         * @param line_id
         * @param classgrp
         * @param zotype
         * @return
         */
        List<InOutRecord> selectforZrwk(@Param("pline_id") String pline_id, @Param("line_id") String line_id, @Param("classgrp") String classgrp, @Param("zotype") String zotype);

        /**
         * 产线在制队列汇总表查询 918100064
         * @param lineId
         * @param deptId
         * @return
         */
        List<InOutRecord> selectforlines(@Param("lineId") String lineId, @Param("plineId") String plineId, @Param("deptId") String deptId);

        /**
         * 根据生产线、返工件标识、处理标识查询WIP_IN_OUT_RECORD表汇总取件数量（未报废） 918100064
         * @param lineId
         * @param zremade
         * @param sfflg
         * @param diecd
         * @return
         */
        int selectZoutnum(@Param("lineId") String lineId, @Param("zremade") Integer zremade, @Param("matnr") String matnr, @Param("sfflg") String sfflg, @Param("diecd") String diecd);

        /**
         * 按产线、成品物料、工序流转卡集查询待处理数量、报废数量 918100064
         * @param lineId
         * @param matnr
         * @param zpgdbar
         * @param reflag
         * @return
         */
        int selectZoutnum1(@Param("lineId") String lineId, @Param("matnr") String matnr, @Param("zpgdbar") String zpgdbar, @Param("reflag") Integer reflag);


        /**
         * 根据物料编码、不良原因查询数据的条数 918100064
         * @param matnr2
         * @param zissuetxt
         * @return
         */
        int selectDftdtl(@Param("matnr2") String matnr2, @Param("zissuetxt") String zissuetxt);

        /**
         *  不合格品审理单2 产品所处工艺状态=已加工未入库产品 917110140
         * @param werks
         * @param matnr
         * @param deptId
         * @param line_id
         * @param gstrp
         * @return
         */
        List<InOutRecord> selectforQcaudit1(@Param("werks") String werks,
                                            @Param("line_id") String line_id,
                                            @Param("matnr") String matnr,
                                            @Param("matnr2") String matnr2,
                                            @Param("depId") String deptId,
                                            @Param("gstrp") String gstrp,
                                            @Param("zqxdm") String zqxdm,
                                            @Param("zissuetxt") String zissuetxt,
                                            @Param("zbanz") String zbanz);

        /**
         *  不合格品审理单2 产品所处工艺状态=已入库成品 917110140
         * @param werks
         * @param matnr
         * @param deptId
         * @param line_id
         * @return
         */
        List<InOutRecord> selectforQcaudit3(@Param("werks") String werks,
                                            @Param("line_id") String line_id,
                                            @Param("matnr") String matnr,
                                            @Param("matnr2") String matnr2,
                                            @Param("depId") String deptId,
                                            @Param("gstrp") String gstrp,
                                            @Param("zqxdm") String zqxdm,
                                            @Param("zissuetxt") String zissuetxt,
                                            @Param("zbanz") String zbanz);

        /**
         * 查询取件记录 for 车间看板
         *
         */
        List<InOutRecord> selectforKanb(@Param("werks") String werks, @Param("line_id") String line_id, @Param("matnr") String matnr, @Param("start") String start, @Param("end") String end);

        List<InOutRecord> selectByZpgdbar(@Param("zpgdbar") String zpgdbar);

        /**
         * 根据创建日期查询产线、物料汇总 918100064
         * @param creationDateBefore
         * @param creationDateAfter
         * @return
         */
        List<InOutRecord> selectByCreateDate(@Param("creationDateBefore") String creationDateBefore, @Param("creationDateAfter") String creationDateAfter);
        /**
         * 根据产线、物料、创建日期查询工废数 918100064
         * @param lineId
         * @param matnr2
         * @param creationDateBefore
         * @param creationDateAfter
         * @return
         */
        List<InOutRecord> XmngaCount(@Param("lineId") String lineId, @Param("matnr2") String matnr2, @Param("creationDateBefore") String creationDateBefore, @Param("creationDateAfter") String creationDateAfter);

        /**
         * 根据产线、物料、创建日期查询料废数 918100064
         * @param lineId
         * @param matnr2
         * @param creationDateBefore
         * @param creationDateAfter
         * @return
         */
        List<InOutRecord> RmngaCount(@Param("lineId") String lineId, @Param("matnr2") String matnr2, @Param("creationDateBefore") String creationDateBefore, @Param("creationDateAfter") String creationDateAfter);

        /**
         * 根据创建日期查询不合格品记录 918100064
         * @param creationDateBefore
         * @param creationDateAfter
         * @return
         */
        List<InOutRecord> selectByNgRecode(@Param("creationDateBefore") String creationDateBefore, @Param("creationDateAfter") String creationDateAfter);

        /**
         * 根据日期等查询，汇总 918100064
         * @param dto
         * @return
         */
        List<InOutRecord> zissuetxtCount(InOutRecord dto);
}
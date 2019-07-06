package yj.core.dftdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.dftdtl.dto.Dftdtl;

import java.util.List;

public interface DftdtlMapper extends Mapper<Dftdtl> {
    Dftdtl selectForLov();
    /**
     * 根据一级质量原因代码 获取对应的二级原因代码
     * @param code
     * @return
     */
    List<Dftdtl> selectByQpcode(@Param("code") String code, @Param("matnr") String matnr);

    /**
     * 根据一级质量原因代码 获取对应的二级原因代码 机加专用
     * @param code
     * @param matnr
     * @return
     */
    List<Dftdtl> selectByQpcodeForJj(@Param("code") String code, @Param("matnr") String matnr);
    /**
     *根据主键查询是否有这条数据 918100064
     * @param werks
     * @param tlevelcode
     * @param matnr
     * @return
     */
    Integer isExit(@Param("werks") String werks, @Param("tlevelcode") String tlevelcode, @Param("matnr") String matnr);

    /**
     *质量缺陷明细代码维护查询 918100064
     * @param werks
     * @param matnr
     * @param code
     * @return
     */
    List<Dftdtl> selectFromPage(@Param("werks") String werks, @Param("matnr") String matnr, @Param("code") String code);

    /**
     * 质量缺陷明细代码维护的修改 918100064
     * @param dftdtl
     * @return
     */
    Integer updateDftdtl(Dftdtl dftdtl);

    /**
     *质量缺陷明细代码维护的添加 918100064
     * @param dftdtl
     */
    void insertDftdtl(Dftdtl dftdtl);

    /**
     *质量缺陷明细代码维护的删除 918100064
     * @param dftdtl
     */
    void deleteDftdtl(Dftdtl dftdtl);

    /**
     *  根据物料编码工厂取产品缺陷分类数据
     * @param werks
     * @param matnr
     * @return
     */
    List<Dftdtl> selectbyWerksAndMatnr(@Param("werks") String werks, @Param("matnr") String matnr);

}
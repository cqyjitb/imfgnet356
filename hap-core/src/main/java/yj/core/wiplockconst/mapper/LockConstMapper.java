package yj.core.wiplockconst.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wiplockconst.dto.LockConst;

import java.util.List;

public interface LockConstMapper extends Mapper<LockConst> {
    /**
     * 装箱报错锁程序常量表查询 918100064
     * @param dto
     * @return
     */
    List<LockConst> selectLockConst(LockConst dto);
    /**
     * 装箱报错锁程序常量表添加 918100064
     * @param lockConst
     */
    void insertLockConst(LockConst lockConst);
    /**
     * 装箱报错锁程序常量表修改 918100064
     * @param lockConst
     */
    void updateLockConst(LockConst lockConst);
    /**
     * 根据产线ID和常量类型查询 LOV_CONST_VALUE 918100064
     * @param lineId 产线ID
     * @param constType 常量类型
          */
    List<LockConst> selectByConstType(@Param("lineId") Long lineId, @Param("constType") String constType);
}
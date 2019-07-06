package yj.core.wiplockconstrelation.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wiplockconstrelation.dto.LockConstRelation;

import java.util.List;

public interface LockConstRelationMapper extends Mapper<LockConstRelation> {
    /**
     * 装箱报错锁程序常量关系表查询 918100064
     * @param lockConstRelation
     * @return
     */
    List<LockConstRelation> selectLockConstRelation(LockConstRelation lockConstRelation);

    /**
     * 装箱报错锁程序常量关系表添加 918100064
     * @param lockConstRelation
     */
    void insertLockConstRelation(LockConstRelation lockConstRelation);

    /**
     * 装箱报错锁程序常量关系表修改 918100064
     * @param lockConstRelation
     */
    void updateLockConstRelation(LockConstRelation lockConstRelation);
}
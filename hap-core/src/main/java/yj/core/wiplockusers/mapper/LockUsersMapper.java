package yj.core.wiplockusers.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wiplockusers.dto.LockUsers;

import java.util.List;

public interface LockUsersMapper extends Mapper<LockUsers> {
    /**
     * 装箱报错锁程序常量关系表查询 918100064
     * @param lockUsers
     * @return
     */
    List<LockUsers> selectLockUsers(LockUsers lockUsers);

    /**
     * 判断装箱报错锁程序常量关系表数据是否存在 918100064
     * @param werks
     * @param lineId
     * @param userid
     * @return
     */
    Integer isExit(@Param("werks") String werks, @Param("lineId") Long lineId, @Param("userid") String userid);

    /**
     * 装箱报错锁程序常量关系表添加 918100064
     * @param lockUsers
     */
    void insertLockUsers(LockUsers lockUsers);

    /**
     * 装箱报错锁程序常量关系表修改 918100064
     * @param lockUsers
     */
//    void updateLockUsers(LockUsers lockUsers);

    /**
     * 装箱报错锁程序常量关系表删除 9181100064
     * @param lockUsers
     */
    void deleteLockUsers(LockUsers lockUsers);
}
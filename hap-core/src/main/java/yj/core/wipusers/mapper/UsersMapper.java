package yj.core.wipusers.mapper;

import com.hand.hap.account.dto.User;
import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipusers.dto.Users;

import java.util.List;

public interface UsersMapper extends Mapper<Users> {

    /**
     * 根据采集点Id查询表中数据多少 918100064
     * @param dotId
     * @return
     */
    int selectDotId(@Param("dotId") Integer dotId);
    /**
     * 机加采集点配置维护查询 918100064
     * @param users
     * @return
     */
    List<Users> selectFromPage(Users users);

    /**
     * 机加采集点配置维护修改 918100064
     * @param users
     */
    void updateUsers(Users users);

    /**
     * 机加采集点配置维护添加 918100064
     * @param users
     */
    void insertUsers(Users users);

    /**
     * 用户查询LOV_USER 918100064
     * @param userId
     * @param userName
     * @return
     */
    List<User> selectByUserId(@Param("userId") String userId, @Param("userName") String userName);

}
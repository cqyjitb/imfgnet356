package yj.core.tcode.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.tcode.dto.Tcode;

public interface TcodeMapper extends Mapper<Tcode> {
        int isExit(Tcode dto);
        int selectByUserName(String userName);
        int update(Tcode dto);
        Tcode getProfile(String userName);//获取用户权限列表

}
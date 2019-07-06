package yj.core.appidconf.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.appidconf.dto.Appidconf;

public interface AppidconfMapper extends Mapper<Appidconf> {
    Integer isExit(String appid);
    Integer update(Appidconf appidconf);
    String updateOrInsert(Appidconf dto);
    Appidconf selectByAppid(String appid);
}
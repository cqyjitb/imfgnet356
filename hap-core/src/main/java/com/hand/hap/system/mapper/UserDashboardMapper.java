package com.hand.hap.system.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.UserDashboard;

public interface UserDashboardMapper extends Mapper<UserDashboard> {
    
    List<UserDashboard>  selectMyDashboardConfig(UserDashboard userDashboard);
}

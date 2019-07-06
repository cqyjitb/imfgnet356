package com.hand.hap.activiti.mapper;

import com.hand.hap.activiti.dto.HiIdentitylink;
import com.hand.hap.mybatis.common.Mapper;

import java.util.Map;

public interface HiIdentitylinkMapper extends Mapper<HiIdentitylink> {

    int updateReadFlag(HiIdentitylink hiIdentitylink);

    int insertCarbonCopy(Map<String, String> params);
}
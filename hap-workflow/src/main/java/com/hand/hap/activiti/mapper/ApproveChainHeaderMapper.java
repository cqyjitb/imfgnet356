package com.hand.hap.activiti.mapper;

import com.hand.hap.activiti.dto.ApproveChainHeader;
import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;

public interface ApproveChainHeaderMapper extends Mapper<ApproveChainHeader> {

    ApproveChainHeader selectByUserTask(@Param("processKey") String processKey,
                                        @Param("usertaskId") String usertaskId);
}
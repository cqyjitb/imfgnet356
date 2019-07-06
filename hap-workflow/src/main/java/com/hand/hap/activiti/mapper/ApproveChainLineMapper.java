package com.hand.hap.activiti.mapper;

import com.hand.hap.activiti.dto.ApproveChainLine;
import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApproveChainLineMapper extends Mapper<ApproveChainLine> {

    List<ApproveChainLine> selectByHeaderId(Long headerId);

    List<ApproveChainLine> selectByNodeId(@Param("processKey") String processKey, @Param("nodeId") String nodeId);
}
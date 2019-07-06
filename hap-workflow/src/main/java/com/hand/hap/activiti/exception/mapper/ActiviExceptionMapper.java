package com.hand.hap.activiti.exception.mapper;

import com.hand.hap.activiti.exception.dto.ActiviException;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

public interface ActiviExceptionMapper extends Mapper<ActiviException> {

    List<ActiviException> selectAllException(ActiviException exception);

}
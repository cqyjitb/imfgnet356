package com.hand.hap.system.service.impl;

import com.hand.hap.system.dto.ParameterConfig;
import com.hand.hap.system.mapper.ParameterConfigMapper;
import com.hand.hap.system.service.IParameterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qiang.zeng
 * @date 2017/11/6
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParameterConfigServiceImpl extends BaseServiceImpl<ParameterConfig> implements IParameterConfigService {
    @Autowired
    private ParameterConfigMapper parameterConfigMapper;

    @Override
    public List<ParameterConfig> selectByReportCode(String reportCode) {
        return parameterConfigMapper.selectByReportCode(reportCode);
    }

    @Override
    public List<ParameterConfig> selectByCodeAndTargetId(String code, Long targetId) {
        return parameterConfigMapper.selectByCodeAndTargetId(code, targetId);
    }

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }
}

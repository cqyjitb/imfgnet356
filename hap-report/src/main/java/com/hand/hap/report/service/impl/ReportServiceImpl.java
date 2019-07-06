package com.hand.hap.report.service.impl;

import com.hand.hap.report.dto.Report;
import com.hand.hap.report.mapper.ReportMapper;
import com.hand.hap.report.service.IReportService;
import com.hand.hap.system.mapper.ParameterConfigMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报表服务接口实现.
 *
 * @author qiang.zeng
 * @date 2017/9/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl extends BaseServiceImpl<Report> implements IReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ParameterConfigMapper parameterConfigMapper;

    @Override
    public int batchDelete(List<Report> reports) {
        int count = 0;
        if (CollectionUtils.isNotEmpty(reports)) {
            for (Report report : reports) {
                int updateCount = reportMapper.deleteByPrimaryKey(report);
                checkOvn(updateCount, report);
                parameterConfigMapper.deleteByCodeAndTargetId("REPORT", report.getReportId());
                count++;
            }
        }
        return count;
    }

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }

    @Override
    public List<Report> selectByReportCode(String reportCode) {
        return reportMapper.selectByReportCode(reportCode);
    }
}
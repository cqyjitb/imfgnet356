package com.hand.hap.report.service.impl;


import com.hand.hap.report.dto.ReportFiles;
import com.hand.hap.report.mapper.ReportFilesMapper;
import com.hand.hap.report.service.IReportFilesService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 报表文件服务接口实现.
 *
 * @author qiang.zeng
 * @date 2017/9/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportFilesServiceImpl extends BaseServiceImpl<ReportFiles> implements IReportFilesService {
    @Autowired
    private ReportFilesMapper reportFilesMapper;

    @Override
    public ReportFiles selectByName(String name) {
        return reportFilesMapper.selectByName(name);
    }

    @Override
    public int deleteByName(String name) {
        return reportFilesMapper.deleteByName(name);
    }

    @Override
    public ReportFiles selectReportFileParams(String reportCode) {
        return reportFilesMapper.selectReportFileParams(reportCode);
    }
}
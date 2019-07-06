package com.hand.hap.report.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.report.dto.Report;

import java.util.List;

/**
 * 报表Mapper.
 *
 * @author qiang.zeng
 * @date 2017/9/21
 */
public interface ReportMapper extends Mapper<Report> {
    /**
     * 根据报表编码查询报表信息.
     *
     * @param reportCode 报表编码
     * @return 报表列表
     */
    List<Report> selectByReportCode(String reportCode);
}
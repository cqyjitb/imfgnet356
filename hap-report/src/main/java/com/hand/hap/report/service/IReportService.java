package com.hand.hap.report.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.report.dto.Report;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

/**
 * 报表服务接口.
 *
 * @author qiang.zeng@hand-china.com
 * @date 2017/9/21
 */
public interface IReportService extends IBaseService<Report>, ProxySelf<IReportService> {
    /**
     * 根据报表编码查询报表信息.
     *
     * @param reportCode 报表编码
     * @return 报表列表
     */
    List<Report> selectByReportCode(String reportCode);
}
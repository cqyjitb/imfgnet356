package com.hand.hap.report.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.report.dto.ReportFiles;
import com.hand.hap.system.service.IBaseService;

/**
 * 报表文件服务接口.
 *
 * @author qiang.zeng@hand-china.com
 * @date 2017/9/21
 */
public interface IReportFilesService extends IBaseService<ReportFiles>, ProxySelf<IReportFilesService> {
    /**
     * 查询报表文件.
     *
     * @param name 报表名称
     * @return 报表文件
     */
    ReportFiles selectByName(String name);

    /**
     * 删除报表文件.
     *
     * @param name 报表名称
     * @return int
     */
    int deleteByName(String name);

    /**
     * 查询报表所有参数.
     *
     * @param reportCode 报表编码
     * @return 报表文件
     */
    ReportFiles selectReportFileParams(String reportCode);
}
package com.hand.hap.excel.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.excel.dto.ExportConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jialong.zuo@hand-china.com on 2016/11/30.
 */
public interface IExportService {

    /**
     * @param sqlId sqlId
     * @param exportConfig 导出信息
     * @param httpServletRequest httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param iRequest iRequest环境
     * @throws IOException IOException异常
     */
    void exportAndDownloadExcel(String sqlId, ExportConfig exportConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,IRequest iRequest) throws IOException;

    /**
     * @param sqlId sqlId
     * @param exportConfig 导出信息
     * @param httpServletRequest httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param iRequest iRequest环境
     * @throws IOException IOException异常
     * @param rowMaxNumber sheet页最大导出数
     */
    void exportAndDownloadExcel(String sqlId, ExportConfig exportConfig, HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, IRequest iRequest, int rowMaxNumber) throws IOException;

}

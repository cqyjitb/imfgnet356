package com.hand.hap.excel.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.excel.ExcelException;
import com.hand.hap.excel.dto.ExportConfig;
import com.hand.hap.system.dto.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jialong.zuo@hand-china.com on 2016/11/30.
 */
public interface IHapExcelExportService {

    /**
     * @param responseData 导出数据
     * @param httpServletRequest httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param columns 选中所需导出的列
     * @throws IOException io异常
     * @throws ExcelException excel异常
     * @throws SQLException sql异常
     */
    void exportAndDownloadExcel(Object responseData, HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, List<String> columns) throws IOException, SQLException, ExcelException;


}

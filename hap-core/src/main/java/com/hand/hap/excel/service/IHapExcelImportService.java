package com.hand.hap.excel.service;

import com.hand.hap.excel.ExcelException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
/**
 * Created by jialong.zuo@hand-china.com on 2017/1/22.
 */
public interface IHapExcelImportService {

    /**
     * 导入excel
     * @param inputStream 输入流
     * @param tableName 表名
     * @throws IOException io异常
     * @throws ExcelException excel异常
     * @throws SQLException sql异常
     */
    void loadExcel(InputStream inputStream, String tableName)
            throws IOException, ExcelException, SQLException;

    /** 获取导入模板
     * @param tableName 表名
     * @param httpServletResponse httpServletResponse
     * @param httpServletRequest httpServletRequest
     * @throws IOException io异常
     * @throws ExcelException excel异常
     * @throws SQLException sql异常
     */
    void exportExcelTemplate(String tableName, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException, SQLException, ExcelException;

}

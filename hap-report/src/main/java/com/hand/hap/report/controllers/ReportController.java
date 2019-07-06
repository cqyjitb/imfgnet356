package com.hand.hap.report.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.report.dto.Report;
import com.hand.hap.report.dto.ReportFiles;
import com.hand.hap.report.service.IReportFilesService;
import com.hand.hap.report.service.IReportService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 对报表的操作.
 *
 * @author qiang.zeng
 * @date 2017/9/21
 */
@RestController
@RequestMapping(value = {"/sys/report", "/api/sys/report"})
public class ReportController extends BaseController {

    @Autowired
    private IReportService reportService;
    @Autowired
    private IReportFilesService reportFilesService;

    @RequestMapping(value = "/query")
    public ResponseData query(HttpServletRequest request, Report report, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(reportService.selectOptions(requestContext, report, null, page, pageSize));
    }

    @RequestMapping(value = "/queryByCode")
    public ResponseData queryByCode(HttpServletRequest request, @RequestParam(required = false) String reportCode) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(reportService.selectByReportCode(reportCode));
    }

    @RequestMapping(value = "/submit")
    public ResponseData update(@RequestBody List<Report> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(reportService.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/remove")
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Report> dto) {
        reportService.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/queryReportFileParams")
    public ResponseData queryReportFileParams(HttpServletRequest request, @RequestParam(required = false) String reportCode) {
        ReportFiles reportFiles = reportFilesService.selectReportFileParams(reportCode);
        String[] params = {};
        if (reportFiles != null && StringUtils.isNotEmpty(reportFiles.getParams())) {
            params = StringUtils.split(reportFiles.getParams(), ";");
        }
        return new ResponseData(Arrays.asList(params));
    }


}
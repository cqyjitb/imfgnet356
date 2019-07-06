/*
 * #{copyright}#
 */

package com.hand.hap.job.controllers;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.job.service.IJobRunningInfoService;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.job.dto.JobRunningInfoDto;
import com.hand.hap.job.service.impl.JobRunningInfoService;

/**
 * @author liyan.shi@hand-china.com
 */
@Controller
@RequestMapping(value = {"/job/jobinfo", "/api/job/jobinfo"})
public class JobInfoController extends BaseController {
    @Autowired
    private IJobRunningInfoService jobRunningInfoService;

    /**
     * 查询Job运行记录.
     * 
     * @param dto
     *            参数
     * @param page
     *            页码
     * @param pagesize
     *            每页数量
     * @param request
     *            HttpServletRequest
     * @return 运行记录结果
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseData queryJobRunningInfo(JobRunningInfoDto dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize, HttpServletRequest request) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(jobRunningInfoService.queryJobRunningInfo(requestCtx, dto, page, pagesize));
    }

}

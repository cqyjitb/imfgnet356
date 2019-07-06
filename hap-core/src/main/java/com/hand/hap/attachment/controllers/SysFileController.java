/*
 * #{copyright}#
 */
package com.hand.hap.attachment.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.attachment.service.ISysFileService;

/**
 * 系统文件Controller.
 * 
 * @author xiaohua
 *
 */
@Controller
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 系统文件列表.
     * 
     * @param request
     *            HttpServletRequest
     * @param sysFile
     *            SysFile参数对象
     * @param categoryId
     *            所属分类
     * @param page
     *            页号
     * @param pagesize
     *            单页条数
     * @return ResponseData 结果对象
     */
    @RequestMapping(value = "/sys/attach/file/query")
    public ResponseData query(HttpServletRequest request, SysFile sysFile, Long categoryId,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(sysFileService.selectFilesByCategoryId(requestCtx, sysFile, categoryId, page, pagesize));
    }



    @RequestMapping(value = "/sys/attachment/query")
    public ResponseData queryFilesBySourceTypeAndSourceKey(HttpServletRequest request, String sourceType, String sourceKey) {
        return new ResponseData(sysFileService.selectFilesBySourceTypeAndSourceKey(createRequestContext(request), sourceType, sourceKey));
    }


    /**
     * 系统文件列表.
     * 
     * @param request
     *            HttpServletRequest
     * @param sysFile
     *            SysFile参数对象
     * @param page
     *            页号
     * @param pagesize
     *            单页条数
     * @return ResponseData 结果对象
     */
    @RequestMapping(value = "/sys/attach/file/queryFiles")
    public ResponseData query(HttpServletRequest request, SysFile sysFile,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(sysFileService.selectFiles(requestCtx, sysFile, page, pagesize));
    }

    /**
     * 删除.
     * 
     * @param request
     *            HttpServletRequest
     * @param sysFiles
     *            SysFile列表
     * @return ResponseData 结果对象
     */
    @RequestMapping(value = "/sys/attach/file/remove")
    public ResponseData remove(HttpServletRequest request, @RequestBody List<SysFile> sysFiles) {
        IRequest requestContext = createRequestContext(request);
        sysFileService.removeFiles(requestContext, sysFiles);
        return new ResponseData();
    }

    /* 删除文除一个文件   */
    @RequestMapping(value = "/sys/attach/file/delete")
    public ResponseData deleteFile(HttpServletRequest request, @RequestBody SysFile file) throws TokenException {
        checkToken(request, file);
        IRequest requestContext = createRequestContext(request);
        sysFileService.deletefiles(requestContext, file);

        return new ResponseData(Arrays.asList(file));

    }
}

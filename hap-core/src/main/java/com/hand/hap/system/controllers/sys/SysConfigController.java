package com.hand.hap.system.controllers.sys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import com.hand.hap.attachment.impl.StandardUploader;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.components.SysConfigManager;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.core.util.FormatUtil;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.dto.SysConfig;
import com.hand.hap.system.service.ISysConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 * 系统配置信息.
 *
 * @author hailin.xu@hand-china.com
 */
@Controller
@RequestMapping(value = {"/sys/config", "/api/sys/config"})
public class SysConfigController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigController.class);

    private static final String SYS_LOGO_FILE_NAME = "logo.png";
    private static final String SYS_FAVICON_FILE_NAME = "favicon.png";
    private static final String SYS_LOGO_PARAMETER_NAME = "logo";
    private static final String SYS_FAVICON_PARAMETER_NAME = "favicon";

    private static final String MSG_ALERT_UPLOAD_FILE_TYPE_MISMATCH = "hap.upload.file.type.mismatch";
    private static final String MSG_ALERT_UPLOAD_FILE_SIZE_LIMIT_EXCEEDED = "hap.upload.file.size.limit.exceeded";
    private static final String MSG_ALERT_UPLOAD_SUCCESS = "hap.upload.success";
    private static final String MSG_ALERT_UPLOAD_ERROR = "hap.upload.error";

    @Autowired
    private ISysConfigService configService;


    /**
     * 配置信息查询.
     *
     * @return ResponseData
     */
    @PostMapping(value = "/query")
    @ResponseBody
    public ResponseData getConfig(HttpServletRequest request) {
        return new ResponseData(configService.selectAll(createRequestContext(request)));
    }


    /**
     * 配置信息保存.
     *
     * @param config  config
     * @param result  BindingResult
     * @param request HttpServletRequest
     * @return ResponseData
     * @throws TokenException 防篡改token校验异常
     */
    @PostMapping(value = "/submit")
    public ResponseData submitConfig(@RequestBody List<SysConfig> config, BindingResult result, HttpServletRequest request) throws TokenException {
        checkToken(request, config);
        getValidator().validate(config, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        IRequest requestCtx = createRequestContext(request);
        for (int i = 0; i < config.size(); i++) {
            if (config.get(i).getConfigCode().equals("PASSWORD_MIN_LENGTH")) {

                if (Integer.parseInt(config.get(i).getConfigValue()) < 6) {
                    config.get(i).setConfigValue("6");
                } else if (Integer.parseInt(config.get(i).getConfigValue()) > 16) {
                    config.get(i).setConfigValue("16");
                }
            }
        }

        return new ResponseData(configService.batchUpdate(requestCtx, config));
    }


    @RequestMapping(value = "/logo/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadSystemLogo(HttpServletRequest request) {
        return uploadSystemImage(request, 500, SYS_LOGO_FILE_NAME, SYS_LOGO_PARAMETER_NAME, SysConfigManager.KEY_SYS_LOGO_VERSION);
    }

    @RequestMapping(value = "/favicon/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadFaviconLogo(HttpServletRequest request) {
        return uploadSystemImage(request, 100, SYS_FAVICON_FILE_NAME, SYS_FAVICON_PARAMETER_NAME, SysConfigManager.KEY_SYS_FAVICON_VERSION);
    }

    private String uploadSystemImage(HttpServletRequest request, int fileSizeWithKB, String fileName, String parameterName, String type) {
        File uploadFolder = new File(request.getServletContext().getRealPath("/") + "/resources/upload");
        Locale locale = RequestContextUtils.getLocale(request);
        long fileSize = fileSizeWithKB * 1024;
        boolean isMultiPartFiled = request instanceof MultipartHttpServletRequest;
        List<FileItem> items;
        try {
            FileUtils.forceMkdir(uploadFolder);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(fileSize);
            if (isMultiPartFiled) {
                items = new StandardUploader().getMultipartFileItem(request, parameterName);
            } else {
                items = upload.parseRequest(request);
            }
            if (CollectionUtils.isNotEmpty(items)) {
                for (FileItem fi : items) {
                    if (!fi.isFormField()) {
                        String contentType = fi.getContentType();
                        if (StringUtils.contains(contentType, "image/")) {
                            try (InputStream is = fi.getInputStream(); FileOutputStream os = new FileOutputStream(new File(uploadFolder, fileName))) {
                                IOUtils.copy(is, os);
                            }
                            String logoVersion = configService.updateSystemImageVersion(type);
                            WebUtils.setSessionAttribute(request, SysConfigManager.SYS_LOGO_VERSION, logoVersion);

                            return "<script>window.parent.uploadSuccess('" + getMessageSource().getMessage(MSG_ALERT_UPLOAD_SUCCESS, null, locale) + "')</script>";
                        } else {
                            return "<script>window.parent.showUploadMessage('" + getMessageSource().getMessage(MSG_ALERT_UPLOAD_FILE_TYPE_MISMATCH, null, locale) + "')</script>";
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("upload error", e);
            }
            if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
                return "<script>window.parent.showUploadMessage('" + getMessageSource().getMessage(MSG_ALERT_UPLOAD_FILE_SIZE_LIMIT_EXCEEDED, new String[]{FormatUtil.formatFileSize(fileSize)}, locale) + "')</script>";
            }
        }
        return "<script>window.parent.showUploadMessage('" + getMessageSource().getMessage(MSG_ALERT_UPLOAD_ERROR, null, locale) + "')</script>";
    }
}

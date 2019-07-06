package yj.kanb.marcres.controllers;

import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.attachment.exception.FileReadIOException;
import com.hand.hap.attachment.service.ISysFileService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.apppak.controllers.ApppakController;
import yj.kanb.marcres.dto.MarcRes;
import yj.kanb.marcres.service.IMarcResService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class MarcResController extends BaseController {

@Autowired
private IMarcResService service;
    private static Logger logger = LoggerFactory.getLogger(ApppakController.class);
    @Autowired
    private ISysFileService fileService;


@RequestMapping(value = "/sap/marc/res/query")
@ResponseBody
public ResponseData query(MarcRes dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/sap/marc/res/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, @RequestBody List<MarcRes> dto){
    IRequest requestCtx = createRequestContext(request);
    ResponseData rs = new ResponseData();
    String userId = request.getSession().getAttribute("userId") + "";
    String result = service.submitMarcRes(requestCtx, dto,userId);
    if (result != null){
        rs.setSuccess(false);
        rs.setMessage(result);
    }
    return rs;
}

@RequestMapping(value = "/sap/marc/res/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request, @RequestBody List<MarcRes> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
    @RequestMapping(value = {"/kanb/picture/download"})
    public void download(HttpServletRequest request, HttpServletResponse response, String fileId) throws FileReadIOException, TokenException {
        IRequest requestContext = this.createRequestContext(request);
        SysFile sysFile = fileService.selectByPrimaryKey(requestContext, Long.valueOf(fileId));
        try {
            if(StringUtils.isNotBlank(sysFile.getFilePath())) {
                File file = new File(sysFile.getFilePath());
                if(file.exists()) {
                    response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(sysFile.getFileName(), "UTF-8") + "\"");
                    response.setContentType(sysFile.getFileType() + ";charset=" + "UTF-8");
                    response.setHeader("Accept-Ranges", "bytes");
                    int fileLength = (int)file.length();
                    response.setContentLength(fileLength);
                    if(fileLength > 0) {
                        this.writeFileToResp(response, file);
                    }
                } else {
                    response.getWriter().write("file_not_exsit");
                }
            } else {
                response.getWriter().write("file_not_exsit");
            }

        } catch (IOException var9) {
            logger.error(var9.getMessage(), var9);
            throw new FileReadIOException();
        }
    }
    private void writeFileToResp(HttpServletResponse response, File file) throws FileNotFoundException, IOException {
        byte[] buf = new byte[1024];
        InputStream inStream = new FileInputStream(file);
        Throwable var5 = null;
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            Throwable var7 = null;
            try {
                int readLength;
                while((readLength = inStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, readLength);
                }
            } catch (Throwable var30) {
                var7 = var30;
                throw var30;
            } finally {
                if(outputStream != null) {
                    if(var7 != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable var29) {
                            var7.addSuppressed(var29);
                        }
                    } else {
                        outputStream.close();
                    }
                }
            }
        } catch (Throwable var32) {
            var5 = var32;
            throw var32;
        } finally {
            if(inStream != null) {
                if(var5 != null) {
                    try {
                        inStream.close();
                    } catch (Throwable var28) {
                        var5.addSuppressed(var28);
                    }
                } else {
                    inStream.close();
                }
            }
        }
    }
}
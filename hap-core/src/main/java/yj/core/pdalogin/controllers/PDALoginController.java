package yj.core.pdalogin.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.attachment.service.IAttachmentService;
import com.hand.hap.attachment.service.ISysFileService;
import com.hand.hap.core.IRequest;
import com.hand.hap.security.TokenUtils;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by libo on 2017/6/10.
 */
@Controller
public class PDALoginController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ISysFileService sysFileService;

    @RequestMapping(value = "/yujiang/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData loginView(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        TokenUtils.generateAndSetToken(TokenUtils.getSecurityKey(request.getSession(false)), user);
        String token = user.get_token();
        System.out.println(token);
        try {
            iUserService.login(user);
            return new ResponseData(true);
        } catch (UserException e) {
            ResponseData rd= new ResponseData(false);
            rd.setMessage(e.getMessage());
            return rd;
        }
    }

    @RequestMapping(value = "/yujiang/checkVersion",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData checkVersion(HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String type = request.getParameter("type");
        String key = request.getParameter("key");
        String curversion = request.getParameter("version");
        if  (curversion == null){
            curversion = "0000";
        }
        Attachment attachment = new Attachment();
        attachment = attachmentService.selectAttachByCodeAndKey(requestContext,type,key);
        if (attachment != null){
            List<SysFile> list = sysFileService.selectFilesByTypeAndKey(requestContext,type,key);
            if (list.size() > 0){
                SysFile file = list.get(list.size() - 1);
                String version = file.getFileName().substring(file.getFileName().length() - 8,file.getFileName().length() - 4);
                Long lv = Long.valueOf(version);
                Long lcv = Long.valueOf(curversion);
                if (lv > lcv){
                    //发现新版本
                    rs.setCode(file.getFileId().toString());
                    rs.setSuccess(true);
                    rs.setMessage("发现新版本！即将更新程序！");
                }else{
                    rs.setCode("X");
                    rs.setSuccess(true);
                    rs.setMessage("当前为最新版本！");
                }
            }

        }
        return rs;
    }

    /**
     *  新增查询app 最新版本文件id 返回文件ID 生产下载链接 917110140
     * @param request
     * @return
     */
    @RequestMapping(value = "/yujiang/getDownloadinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getDownloadinfo(HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String type = request.getParameter("type");
        String key = request.getParameter("key");
        Attachment attachment = new Attachment();
        attachment = attachmentService.selectAttachByCodeAndKey(requestContext,type,key);
        if (attachment != null){
            List<SysFile> list = sysFileService.selectFilesByTypeAndKey(requestContext,type,key);
            if (list.size() > 0){
                rs.setCode(list.get(list.size() - 1).getFileId().toString());
                rs.setSuccess(true);
            }else{
                rs.setCode("E");
                rs.setSuccess(true);
            }
        }

        return rs;
    }


}

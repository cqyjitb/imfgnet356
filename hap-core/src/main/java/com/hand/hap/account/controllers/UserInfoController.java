package com.hand.hap.account.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.service.IUserInfoService;
import com.hand.hap.core.IRequest;
import com.hand.hap.security.PasswordManager;
import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理控制器.
 *
 * @author Zhaoqi
 * @author njq.niu@hand-china.com
 */
@RestController
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private PasswordManager passwordManager;

    @RequestMapping(value = "/sys/um/sys_user_info.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userInfo(final HttpServletRequest request) throws UserException {
        ModelAndView mv = new ModelAndView(getViewPath() + "/sys/um/sys_user_info");
        IRequest requestContext = createRequestContext(request);
        User user = userInfoService.selectUserByPrimaryKey(requestContext, requestContext.getUserId());
        Integer length = passwordManager.getPasswordMinLength();
        String complexity = passwordManager.getPasswordComplexity();
        mv.addObject("user", user);
        mv.addObject("length", length);
        mv.addObject("complexity", complexity);
        return mv;
    }
}

package com.hand.hap.mail.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.mail.dto.MessageEmailAccount;
import com.hand.hap.mail.service.IMessageEmailAccountService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对邮件账号的操作.
 *
 * @author Clerifen Li
 */
@RestController
@RequestMapping(value = {"/sys/messageEmailAccount", "/api/sys/messageEmailAccount"})
public class MessageEmailAccountController extends BaseController {

    @Autowired
    private IMessageEmailAccountService service;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData getMessageEmailAccount(HttpServletRequest request, MessageEmailAccount example,
                                               @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                               @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectMessageEmailAccounts(requestContext, example, page, pagesize));
    }

    @RequestMapping(value = "/queryAccount", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData getMessageEmailAccountPassword(HttpServletRequest request, MessageEmailAccount example) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectMessageEmailAccountWithPassword(requestContext, example, 1, 1));
    }

    @PostMapping(value = "/remove")
    public ResponseData deleteMessageEmailAccount(HttpServletRequest request, @RequestBody List<MessageEmailAccount> objs) throws BaseException {
        IRequest requestContext = createRequestContext(request);
        service.batchDelete(requestContext, objs);
        return new ResponseData();
    }

}

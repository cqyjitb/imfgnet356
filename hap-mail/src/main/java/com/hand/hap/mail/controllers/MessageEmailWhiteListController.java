package com.hand.hap.mail.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.mail.dto.MessageEmailWhiteList;
import com.hand.hap.mail.service.IMessageEmailWhiteListService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对邮件白名单的操作.
 *
 * @author Clerifen Li
 */
@RestController
@RequestMapping(value = {"/sys/messageEmailWhiteList", "/api/sys/messageEmailWhiteList"})
public class MessageEmailWhiteListController extends BaseController {

    @Autowired
    private IMessageEmailWhiteListService service;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData getMessageEmailWhiteList(HttpServletRequest request, MessageEmailWhiteList example,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectMessageEmailWhiteLists(requestContext, example, page, pagesize));
    }

    @PostMapping(value = "/remove")
    public ResponseData deleteMessageEmailWhiteList(HttpServletRequest request, @RequestBody List<MessageEmailWhiteList> objs) throws BaseException {
        IRequest requestContext = createRequestContext(request);
        service.batchDelete(requestContext, objs);
        return new ResponseData();
    }

}

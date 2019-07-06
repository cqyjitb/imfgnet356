package com.hand.hap.mail.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.core.exception.EmailException;
import com.hand.hap.mail.dto.MessageEmailConfig;
import com.hand.hap.mail.service.IMessageEmailConfigService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对邮件配置的操作.
 *
 * @author Clerifen Li
 */
@RestController
@RequestMapping(value = {"/sys/messageEmailConfig", "/api/sys/messageEmailConfig"})
public class MessageEmailConfigController extends BaseController {

    @Autowired
    private IMessageEmailConfigService service;

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData getMessageEmailConfig(HttpServletRequest request, MessageEmailConfig example,
                                              @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectMessageEmailConfigs(requestContext, example, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitLov(@RequestBody MessageEmailConfig obj, BindingResult result, HttpServletRequest request) throws EmailException {

        getValidator().validate(obj, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestContext = createRequestContext(request);
        service.batchUpdate(requestContext, obj);
        return new ResponseData();
    }

    @PostMapping(value = "/remove")
    public ResponseData deleteMessageEmailConfig(HttpServletRequest request, @RequestBody List<MessageEmailConfig> objs) throws BaseException {
        IRequest requestContext = createRequestContext(request);
        service.batchDelete(requestContext, objs);
        return new ResponseData();
    }

}

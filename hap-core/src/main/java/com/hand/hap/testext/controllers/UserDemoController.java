package com.hand.hap.testext.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.testext.dto.UserDemo;
import com.hand.hap.testext.service.IUserDemoService;

;

@Controller
public class UserDemoController extends BaseController {

    @Autowired
    private IUserDemoService service;

    @RequestMapping(value = "/sys/demo/query")
    @ResponseBody
    public ResponseData query(UserDemo dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<UserDemo> list = service.select(requestContext, dto, page, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping(value = "/sys/demo/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<UserDemo> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/demo/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<UserDemo> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}
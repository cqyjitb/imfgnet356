/*
 * #{copyright}#
 */

package com.hand.hap.system.controllers.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.dto.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.ILanguageService;

/**
 * @author shengyang.zhou@hand-china.com
 */
@RestController
@RequestMapping(value = {"/sys/language", "/api/sys/language"})
public class LanguageController extends BaseController {

    @Autowired
    private ILanguageService languageService;

    @PostMapping(value = "/query")
    public ResponseData query(HttpServletRequest request, Language example,
                       @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                       @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest iRequest = createRequestContext(request);
        return new ResponseData(languageService.select(iRequest, example, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submit(HttpServletRequest request, @RequestBody List<Language> languageList,
            BindingResult result) {
        getValidator().validate(languageList, result);
        if (result.hasErrors()) {
            ResponseData rs = new ResponseData(false);
            rs.setMessage(getErrorMessage(result, request));
            return rs;
        }
        IRequest iRequest = createRequestContext(request);
        return new ResponseData(languageService.submit(iRequest, languageList));
    }

    @PostMapping(value = {"/remove", "/delete"})
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Language> languageList) {
        languageService.remove(languageList);
        return new ResponseData();
    }
}

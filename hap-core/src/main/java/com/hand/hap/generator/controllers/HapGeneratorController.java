package com.hand.hap.generator.controllers;

import com.hand.hap.generator.service.IHapGeneratorService;
import com.hand.hap.generator.dto.GeneratorInfo;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jialong.zuo@hand-china.com on 2016/10/24.
 */
@Controller
@RequestMapping(value = "/generator")
public class HapGeneratorController extends BaseController {
    @Autowired
    IHapGeneratorService service;

    @RequestMapping(value = "/alltables", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData showTables() {
        return new ResponseData(service.showTables());
    }

    @RequestMapping(value = "/newtables")
    @ResponseBody
    public int generatorTables(GeneratorInfo generatorInfo) {
        int rs = service.generatorFile(generatorInfo);
        return rs;
    }

}

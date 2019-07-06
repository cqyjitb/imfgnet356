package yj.core.imfpmcheckplan.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.imfpmcheckplan.service.ICheckplanService;

@Controller
public class CheckplanController extends BaseController {

    @Autowired
    private ICheckplanService service;


}
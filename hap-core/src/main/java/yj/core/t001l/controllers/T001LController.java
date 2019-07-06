package yj.core.t001l.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.t001l.service.IT001LService;

@Controller
public class T001LController extends BaseController {

    @Autowired
    private IT001LService service;

}